package vn.studentmanagement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.studentmanagement.api.entity.Clazz;
import vn.studentmanagement.api.entity.Semester;
import vn.studentmanagement.api.entity.SemesterClass;

import java.util.List;
import java.util.Optional;


@Repository
public interface SemesterClassRepository extends JpaRepository<SemesterClass, Integer> {
    List<SemesterClass> findBySemester(Semester semester);

    Optional<SemesterClass> findByaClass(Clazz aClass);

    @Query("FROM SemesterClass S WHERE S.semester.id = :id")
    List<SemesterClass> findBySemesterId(Integer id);
}
