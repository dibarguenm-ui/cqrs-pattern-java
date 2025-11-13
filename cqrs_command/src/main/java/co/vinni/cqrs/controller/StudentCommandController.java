package co.vinni.cqrs.controller;

import co.vinni.cqrs.dto.StudentEvent;
import co.vinni.cqrs.persistence.entity.Student;
import co.vinni.cqrs.service.StudentCommandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class StudentCommandController {
    StudentCommandService studentCommandService;

    @PostMapping("/")
    public Student create(@RequestBody StudentEvent studentEvent) {
        return this.studentCommandService.create(studentEvent);
    }
    @PutMapping("/")
    public Student update(@RequestBody StudentEvent studentEvent) {
        return this.studentCommandService.update(studentEvent);
    }
}
