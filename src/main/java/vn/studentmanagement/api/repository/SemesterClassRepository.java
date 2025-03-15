package vn.studentmanagement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.studentmanagement.api.entity.Semester;
import vn.studentmanagement.api.entity.SemesterClass;

import java.util.List;


@Repository
public interface SemesterClassRepository extends JpaRepository<SemesterClass, Integer> {
    List<SemesterClass> findBySemester(Semester semester);
}
