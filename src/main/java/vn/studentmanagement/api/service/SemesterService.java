package vn.studentmanagement.api.service;

import vn.studentmanagement.api.dto.request.SemesterRequest;
import vn.studentmanagement.api.entity.Course;
import vn.studentmanagement.api.entity.Semester;

import java.util.List;

public interface SemesterService {
    void saveSemester(SemesterRequest kyHoc);

    Semester findById(Long id);
    void save(Semester kyHoc);
    boolean kiemTraDiemThanhPhan(Long id);

    Semester updateSemester(Integer id, SemesterRequest semesterRequest);

    List<Semester> getAllSemesters();
}
