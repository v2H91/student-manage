package vn.studentmanagement.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.SemesterRequest;
import vn.studentmanagement.api.entity.Semester;
import vn.studentmanagement.api.repository.SemesterRepository;
import vn.studentmanagement.api.service.SemesterService;
@Service
public class SemesterServiceImpl implements SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

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
        return false;
    }
}
