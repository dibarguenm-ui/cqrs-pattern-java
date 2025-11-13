package co.vinni.cqrs.dto;

import co.vinni.cqrs.persistence.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vinni
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEvent {
    private String eventType;
    private Student student;

}
