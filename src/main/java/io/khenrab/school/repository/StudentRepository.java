package io.khenrab.school.repository;

import io.khenrab.school.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAll(Pageable pageable);
    Page<Student> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
}
