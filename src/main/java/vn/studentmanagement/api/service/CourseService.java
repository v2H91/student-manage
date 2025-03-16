package vn.studentmanagement.api.service;

import vn.studentmanagement.api.dto.request.CourseRequest;
import vn.studentmanagement.api.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    void save(CourseRequest monHoc);

    boolean existsByCourseCode(String courseCode);

    void deleteCourse(Integer id);

    List<Course> getAllCourses();

    Optional<Course> getCourseById(Integer id);

    Course updateCourse(Integer id, CourseRequest courseDetails);
}
