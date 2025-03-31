package vn.studentmanagement.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.ClassScoreRequest;
import vn.studentmanagement.api.dto.request.ClassScoreUpdate;
import vn.studentmanagement.api.entity.ClassScore;
import vn.studentmanagement.api.entity.SemesterClass;
import vn.studentmanagement.api.repository.ClassRepository;
import vn.studentmanagement.api.repository.ClassScoreRepository;
import vn.studentmanagement.api.repository.SemesterClassRepository;
import vn.studentmanagement.api.repository.StudentRepository;
import vn.studentmanagement.api.service.ClassScoreService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassScoreServiceImpl implements ClassScoreService {
    private final ClassScoreRepository classScoreRepository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final SemesterClassRepository semesterClassRepository;
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
                studentRepository.findById(classScoreRequest.getStudentId()).orElseThrow(
                        () -> new ApplicationException(new AppBusinessError("student id not found",400)
                        )
                ));
        classScore.setAttendanceScore(classScoreRequest.getAttendanceScore());
        classScore.setProjectScore(classScoreRequest.getProjectScore());
        classScore.setPracticeScore(classScoreRequest.getPracticeScore());
        classScore.setTestScore(classScoreRequest.getTestScore());
        classScore.setFinalScore(classScoreRequest.getFinalScore());
        classScoreRepository.save(classScore);
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
        classScore.setFinalScore(classScoreRequest.getFinalScore() != null ? classScoreRequest.getFinalScore() : classScore.getFinalScore());
        return classScoreRepository.save( classScore);
    }

    @Override
    public List<ClassScore> getByStudentIdAndSemester(Integer studentId, Integer semesterId) {
        List<SemesterClass> bySemesterId = semesterClassRepository.findBySemesterId(semesterId);
        List<Integer> classIds = bySemesterId.stream().map(e -> e.getAClass().getId()).collect(Collectors.toList());
        return  classScoreRepository.findByStudentIdAndClassIds(studentId,classIds);
    }

}
