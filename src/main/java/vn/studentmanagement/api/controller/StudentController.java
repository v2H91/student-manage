package vn.studentmanagement.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.StudentRequest;
import vn.studentmanagement.api.entity.Student;
import vn.studentmanagement.api.entity.Teacher;
import vn.studentmanagement.api.service.StudentService;
import vn.studentmanagement.config.BaseResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @GetMapping("/{id}")
    public BaseResponse<Student> getStudentById(@PathVariable Integer id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            return BaseResponse.ofSucceeded(student.get());
        }
        throw new ApplicationException(new AppBusinessError("Không tìm thấy sinh viên", 400));
    }

    @PostMapping
    public BaseResponse<Student> addStudent(@Valid @RequestBody StudentRequest student, BindingResult result) {
        if (result.hasErrors()) {
            throw new ApplicationException(new AppBusinessError("Validation failed: " + result.getAllErrors(), 400));
        }
        return BaseResponse.ofSucceeded(studentService.addStudent(student));
    }

    @PutMapping("/{id}")
    public BaseResponse<Student> updateStudent(@PathVariable Integer id, @RequestBody StudentRequest studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return BaseResponse.ofSucceeded(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return BaseResponse.ofSucceeded();
    }

    @GetMapping("/search-students")
    public List<Student> searchStudents(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String department,
                                        @RequestParam(required = false) String studentCode,
                                        @RequestParam(required = false) String email) {
        return studentService.searchStudents(name, department, studentCode, email);
    }
}
