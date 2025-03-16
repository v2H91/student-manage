package vn.studentmanagement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.studentmanagement.api.entity.ClassScore;

import java.util.List;

@Repository
public interface ClassScoreRepository extends JpaRepository<ClassScore, Integer> {

    @Query("select cs from ClassScore cs where cs.aClass.id in :classIds")
    List<ClassScore> findByClassIds(List<Integer> classIds);
    @Query("select cs from ClassScore cs where cs.student.id in :studentId")
    List<ClassScore> findByStudentId(List<Integer>  studentId);
}
