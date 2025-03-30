package vn.studentmanagement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.studentmanagement.api.entity.Student;
import vn.studentmanagement.api.entity.Teacher;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByStudentCode(String studentCode);

    Optional<Student> findByEmail(String email);

    @Query("SELECT t FROM Student t WHERE " +
            "(:fullName IS NULL OR t.firstName LIKE %:fullName% or t.lastName like %:fullName%) AND " +
            "(:department IS NULL OR t.studentCode LIKE %:studentCode%) AND " +
            "(:phone IS NULL OR t.phone LIKE %:phone%) AND " +
            "(:email IS NULL OR t.email = :email)")
    List<Student> searchStudents(@Param("fullName") String fullName,
                                 @Param("department") String studentCode,
                                 @Param("phone") String phone,
                                 @Param("email") String email);
}
