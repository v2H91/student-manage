package vn.studentmanagement.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.StudentRequest;
import vn.studentmanagement.api.dto.request.TeacherRequest;
import vn.studentmanagement.api.entity.Teacher;
import vn.studentmanagement.api.service.TeacherService;
import vn.studentmanagement.config.BaseResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAllStudents() {
        return teacherService.getAllStudents();
    }


    @GetMapping("/{id}")
    public BaseResponse<Teacher> getStudentById(@PathVariable Integer id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        if (teacher.isPresent()) {
            return BaseResponse.ofSucceeded(teacher.get());
        }
        throw new ApplicationException(new AppBusinessError("Không tìm thấy sinh viên", 400));
    }

    @PostMapping
    public BaseResponse<Teacher> addStudent(@Valid @RequestBody TeacherRequest teacherRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new ApplicationException(new AppBusinessError("Validation failed: " + result.getAllErrors(), 400));
        }
        return BaseResponse.ofSucceeded(teacherService.addTeacher(teacherRequest));
    }

    @PutMapping("/{id}")
    public BaseResponse<Teacher> updateStudent(@PathVariable Integer id, @RequestBody TeacherRequest teacherRequest) {
        Teacher updated = teacherService.updateTeacher(id, teacherRequest);
        return BaseResponse.ofSucceeded(updated);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteStudent(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return BaseResponse.ofSucceeded();
    }

}
