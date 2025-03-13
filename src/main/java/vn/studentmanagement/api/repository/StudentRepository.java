package vn.studentmanagement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.studentmanagement.api.entity.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByStudentCode(String studentCode);

    Optional<Student> findByEmail(String email);
}
