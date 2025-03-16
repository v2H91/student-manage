package vn.studentmanagement.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.SemesterRequest;
import vn.studentmanagement.api.entity.ClassScore;
import vn.studentmanagement.api.entity.Semester;
import vn.studentmanagement.api.entity.SemesterClass;
import vn.studentmanagement.api.repository.ClassScoreRepository;
import vn.studentmanagement.api.repository.SemesterClassRepository;
import vn.studentmanagement.api.repository.SemesterRepository;
import vn.studentmanagement.api.service.SemesterService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private ClassScoreRepository classScoreRepository;
    @Autowired
    private SemesterClassRepository semesterClassRepository;

    @Override
    public void saveSemester(SemesterRequest kyHoc) {
        Semester semester = new Semester();
        semester.setTerm(kyHoc.getTerm());
        semester.setName(kyHoc.getName());
        semester.setStartYear(kyHoc.getStartYear());
        semesterRepository.save(semester);
    }

    @Override
    public Semester findById(Long id) {
        return semesterRepository.findById(id.intValue()).orElseThrow(
                () -> new ApplicationException(new AppBusinessError("not found", 400))
        );
    }

    @Override
    public void save(Semester kyHoc) {
        semesterRepository.save(kyHoc);
    }

    @Override
    public boolean kiemTraDiemThanhPhan(Long id) {
        Optional<Semester> byId = semesterRepository.findById(id.intValue());
        if (byId.isEmpty()) {
            throw new ApplicationException(new AppBusinessError("not found", 400));
        }
        Semester semester = byId.get();
        List<SemesterClass> bySemester = semesterClassRepository.findBySemester(semester);
        if (bySemester.isEmpty()) return true;
        boolean flag = true;
        List<Integer> classIds = bySemester.stream().map(e -> e.getAClass().getId()).collect(Collectors.toList());
        List<ClassScore> byClassIds = classScoreRepository.findByClassIds(classIds);
        for (ClassScore e : byClassIds) {
            if (e.getAttendanceScore() == null || e.getPracticeScore() == null
                    || e.getProjectScore() == null || e.getTestScore() == null) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public Semester updateSemester(Integer id, SemesterRequest semesterRequest) {
        Semester semester = semesterRepository.findById(id.intValue()).orElseThrow(
                () -> new ApplicationException(new AppBusinessError("not found", 400))
        );
        semester.setTerm(semesterRequest.getTerm() != null ? semesterRequest.getTerm() : semester.getTerm());
        semester.setName(semesterRequest.getName() != null ? semesterRequest.getName() : semester.getName());
        semester.setStartYear(semesterRequest.getStartYear() != null ? semesterRequest.getStartYear() : semester.getStartYear());
        return semesterRepository.save(semester);

    }

    @Override
    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }
}
