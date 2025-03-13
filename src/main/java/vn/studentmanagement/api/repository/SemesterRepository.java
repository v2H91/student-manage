package vn.studentmanagement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.studentmanagement.api.entity.Semester;
@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
}
