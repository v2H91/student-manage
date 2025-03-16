package vn.studentmanagement.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.ClassScoreRequest;
import vn.studentmanagement.api.dto.request.ClassScoreUpdate;
import vn.studentmanagement.api.entity.ClassScore;
import vn.studentmanagement.api.entity.Course;
import vn.studentmanagement.api.repository.ClassRepository;
import vn.studentmanagement.api.repository.ClassScoreRepository;
import vn.studentmanagement.api.repository.StudentRepository;
import vn.studentmanagement.api.service.ClassScoreService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassScoreServiceImpl implements ClassScoreService {
    private final ClassScoreRepository classScoreRepository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    @Override
    public List<ClassScore> getByClassId(Integer classId) {
        return classScoreRepository.findByClassIds(List.of(classId));
    }

    @Override
    public List<ClassScore> getByStudentId(Integer studentId) {
        return classScoreRepository.findByStudentId(List.of(studentId));
    }

    @Override
    public void createScore(ClassScoreRequest classScoreRequest) {
        ClassScore classScore = new ClassScore();
        classScore.setAClass(
                classRepository.findById(classScoreRequest.getClassId()).orElseThrow(
                        () -> new ApplicationException(new AppBusinessError("class id not found",400)
                )
        ));

        classScore.setStudent(
                studentRepository.findById(classScoreRequest.getClassId()).orElseThrow(
                        () -> new ApplicationException(new AppBusinessError("student id not found",400)
                        )
                ));
        classScore.setAttendanceScore(classScore.getAttendanceScore());
        classScore.setProjectScore(classScoreRequest.getProjectScore());
        classScore.setPracticeScore(classScoreRequest.getPracticeScore());
        classScore.setTestScore(classScoreRequest.getTestScore());
    }

    @Override
    public ClassScore updateScore(Integer id, ClassScoreUpdate classScoreRequest) {
        Optional<ClassScore> byId = classScoreRepository.findById(id);
        if(byId.isEmpty()){
            throw new ApplicationException(new AppBusinessError("score id not found",400));
        }
        ClassScore classScore = byId.get();
        classScore.setTestScore(classScoreRequest.getTestScore() != null ? classScoreRequest.getTestScore() : classScore.getTestScore());
        classScore.setProjectScore(classScoreRequest.getProjectScore() != null ? classScoreRequest.getProjectScore() : classScore.getProjectScore());
        classScore.setPracticeScore(classScoreRequest.getPracticeScore() != null ? classScoreRequest.getPracticeScore() : classScore.getPracticeScore());
        classScore.setAttendanceScore(classScoreRequest.getAttendanceScore() != null ? classScoreRequest.getAttendanceScore() : classScore.getAttendanceScore());
        return classScoreRepository.save( classScore);
    }

}
