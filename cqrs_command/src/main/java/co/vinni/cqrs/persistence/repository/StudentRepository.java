package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
