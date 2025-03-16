package vn.studentmanagement.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.CourseRequest;
import vn.studentmanagement.api.entity.Course;
import vn.studentmanagement.api.service.CourseService;
import vn.studentmanagement.config.BaseResponse;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public BaseResponse<String> taoMonHoc(@RequestBody CourseRequest monHoc) {
        if (courseService.existsByCourseCode(monHoc.getCourseCode())) {
            throw new ApplicationException(new AppBusinessError("Mã môn học đã có trong CSDL",400));
        }
        courseService.save(monHoc);
        return BaseResponse.ofSucceeded(monHoc.getName() + " đã được lưu vào CSDL thành công");
    }


    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }


    @GetMapping("/{id}")
    public BaseResponse<Course> getCourseById(@PathVariable Integer id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isPresent()) {
            return BaseResponse.ofSucceeded(course.get());
        }
        throw new ApplicationException(new AppBusinessError("Không tìm thấy sinh viên", 400));
    }

    @PutMapping("/{id}")
    public BaseResponse<Course> updateCourse(@PathVariable Integer id, @RequestBody CourseRequest CourseDetails) {
        Course updatedCourse = courseService.updateCourse(id, CourseDetails);
        return BaseResponse.ofSucceeded(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return BaseResponse.ofSucceeded();
    }
}
