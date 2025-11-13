package co.vinni.cqrs.controller;
import co.vinni.cqrs.persistence.entity.Student;
import co.vinni.cqrs.service.StudentQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class StudentQueryController {
    StudentQueryService studentQueryService;

    @GetMapping("/")
    public List<Student> obtenerAll() {
        return this.studentQueryService.getAll();
    }


}
