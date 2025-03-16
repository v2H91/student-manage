package vn.studentmanagement.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.common.enums.SemesterStatus;
import vn.studentmanagement.api.dto.request.CourseRequest;
import vn.studentmanagement.api.dto.request.SemesterRequest;
import vn.studentmanagement.api.entity.Course;
import vn.studentmanagement.api.entity.Semester;
import vn.studentmanagement.api.service.SemesterService;
import vn.studentmanagement.config.BaseResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/semesters")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;
    @GetMapping
    public List<Semester> getAllSemester() {
        return semesterService.getAllSemesters();
    }
    @PostMapping("/create")
    public BaseResponse<String> create(@RequestBody SemesterRequest kyHoc) {
        semesterService.saveSemester(kyHoc);
        return BaseResponse.ofSucceeded(kyHoc.getName() + " đã được lưu vào CSDL thành công");
    }

    @PostMapping("/change-status/{id}")
    public BaseResponse<String> chuyenTrangThaiKyHoc(@PathVariable Long id) {
        Semester kyHoc = semesterService.findById(id);
        if (kyHoc.getStatus().equals(SemesterStatus.CHUA_DIEN_RA)) {
            kyHoc.setStatus(SemesterStatus.DANG_DIEN_RA);
        } else if (kyHoc.getStatus().equals(SemesterStatus.DANG_DIEN_RA)) {
            if (semesterService.kiemTraDiemThanhPhan(id)) {
                throw new ApplicationException(new AppBusinessError("Còn lớp chưa nhập đủ điểm thành phần",400));
            }
            kyHoc.setStatus(SemesterStatus.DA_KET_THUC);
        }
        semesterService.save(kyHoc);
        return BaseResponse.ofSucceeded("Chuyển đổi trạng thái kỳ học thành công");
    }

    @GetMapping("/{id}")
    public BaseResponse<Semester> getSemesterById(@PathVariable Integer id) {
            return BaseResponse.ofSucceeded(semesterService.findById(Long.valueOf(id)));
    }


    @PutMapping("/{id}")
    public BaseResponse<Semester> updateSemester(@PathVariable Integer id, @RequestBody SemesterRequest semesterRequest) {
        Semester semester = semesterService.updateSemester(id, semesterRequest);
        return BaseResponse.ofSucceeded(semester);
    }
}
