package vn.studentmanagement.api.service;

import vn.studentmanagement.api.dto.request.SemesterRequest;
import vn.studentmanagement.api.entity.Semester;

public interface SemesterService {
    void saveSemester(SemesterRequest kyHoc);

    Semester findById(Long id);
    void save(Semester kyHoc);
    boolean kiemTraDiemThanhPhan(Long id);
}
