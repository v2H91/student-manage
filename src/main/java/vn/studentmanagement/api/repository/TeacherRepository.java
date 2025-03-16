package vn.studentmanagement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.studentmanagement.api.entity.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {


    @Query("SELECT t FROM Teacher t WHERE " +
            "(:fullName IS NULL OR t.name LIKE %:fullName%) AND " +
            "(:department IS NULL OR t.department LIKE %:department%) AND " +
            "(:phone IS NULL OR t.phone LIKE %:phone%) AND " +
            "(:email IS NULL OR t.email = :email)")
    List<Teacher> searchTeachers(@Param("fullName") String fullName,
                                 @Param("department") String department,
                                 @Param("phone") String phone,
                                 @Param("email") String email);
}
