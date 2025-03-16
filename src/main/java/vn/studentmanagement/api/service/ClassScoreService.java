package vn.studentmanagement.api.service;

import vn.studentmanagement.api.dto.request.ClassScoreRequest;
import vn.studentmanagement.api.dto.request.ClassScoreUpdate;
import vn.studentmanagement.api.entity.ClassScore;
import vn.studentmanagement.api.entity.Course;

import java.util.List;

public interface ClassScoreService {
    List<ClassScore> getByClassId(Integer classId);

    List<ClassScore> getByStudentId(Integer studentId);

    void createScore(ClassScoreRequest classScoreReuqest);

    ClassScore updateScore(Integer id, ClassScoreUpdate classScoreRequest);
}
