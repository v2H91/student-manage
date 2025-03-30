package vn.studentmanagement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.studentmanagement.api.entity.Clazz;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<Clazz, Integer> {

@Query("FROM Clazz c WHERE c.course.id = :courseId")
List<Clazz> findByCourseId(Integer courseId);
}
