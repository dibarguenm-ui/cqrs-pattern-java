package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.StudentEvent;
import co.vinni.cqrs.persistence.entity.Student;
import co.vinni.cqrs.persistence.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentCommandService {
    StudentRepository studentRepository;

    KafkaTemplate<String,Object> kafkaTemplate;

    public Student create(StudentEvent studentEvent) {
        Student studentDO=  this.studentRepository.save(studentEvent.getStudent());
        StudentEvent event=new StudentEvent("CreateStudent", studentDO);
        kafkaTemplate.send("student-event-topic",event);
        return studentDO;
    }
    public Student update(StudentEvent studentEvent) {
        Student existStudent =  this.studentRepository.findById(studentEvent.getStudent().getCode()).get();
        Student newStudent = studentEvent.getStudent()  ;
        existStudent.setFirstName(newStudent.getFirstName());
        existStudent.setLastName(newStudent.getLastName());
        existStudent.setEmail(newStudent.getEmail());
        Student studentDO = this.studentRepository.save(existStudent);
        return studentDO;
    }
    public Student updateProduct(long id, StudentEvent studentEvent){
        Student existingStudent = studentRepository.findById(id).get();
        Student newStudent=studentEvent.getStudent();
        existingStudent.setFirstName(newStudent.getFirstName());
        existingStudent.setFirstName(newStudent.getFirstName());
        existingStudent.setFirstName(newStudent.getFirstName());
        Student studentDO = studentRepository.save(existingStudent);
        StudentEvent event=new StudentEvent("UpdateStudent", studentDO);
        kafkaTemplate.send("student-event-topic", event);
        return studentDO;
    }
}
