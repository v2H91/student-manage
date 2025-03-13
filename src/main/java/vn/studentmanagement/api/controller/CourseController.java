package vn.studentmanagement.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.entity.Course;
import vn.studentmanagement.api.service.CourseService;
import vn.studentmanagement.config.BaseResponse;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public BaseResponse<String> taoMonHoc(@RequestBody Course monHoc) {
        if (courseService.existsByCourseCode(monHoc.getCourseCode())) {
            throw new ApplicationException(new AppBusinessError("Mã môn học đã có trong CSDL",400));
        }
        courseService.save(monHoc);
        return BaseResponse.ofSucceeded(monHoc.getName() + " đã được lưu vào CSDL thành công");
    }
}
