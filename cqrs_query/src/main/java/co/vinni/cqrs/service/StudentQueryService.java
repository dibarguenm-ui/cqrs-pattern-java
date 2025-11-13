package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.StudentEvent;
import co.vinni.cqrs.persistence.entity.Student;
import co.vinni.cqrs.persistence.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentQueryService {
    private final StudentRepository studentRepository;

    public StudentQueryService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAll() {
        return this.studentRepository.findAll();
    }

    @KafkaListener(topics = "student-event-topic", groupId = "student-event-group")
    public void processProductEvents(StudentEvent studentEvent) {
        Student student = studentEvent.getStudent();
        if (studentEvent.getEventType().equals("CreateStudent")) {
            studentRepository.save(student);
        }
        if (studentEvent.getEventType().equals("UpdateStudent")) {
            Student existingStudent = studentRepository.findById(student.getCode()).get();
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setEmail(student.getEmail());

            studentRepository.save(existingStudent);
        }
    }
}
