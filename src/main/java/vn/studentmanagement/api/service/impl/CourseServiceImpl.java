package vn.studentmanagement.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.CourseRequest;
import vn.studentmanagement.api.entity.Course;
import vn.studentmanagement.api.repository.CourseRepository;
import vn.studentmanagement.api.service.CourseService;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void save(CourseRequest courseRequest) {
        Course course = new Course();
        course.setCourseCode(courseRequest.getCourseCode());
        course.setName(courseRequest.getName());
        course.setCredits(courseRequest.getCredits());
        course.setAttendancePercentage(courseRequest.getAttendancePercentage());
        course.setPracticePercentage(courseRequest.getPracticePercentage());
        course.setProjectPercentage(courseRequest.getProjectPercentage());
        course.setTestPercentage(courseRequest.getTestPercentage());
         courseRepository.save(course);
    }

    @Override
    public boolean existsByCourseCode(String courseCode) {
       return courseRepository.existsByCourseCode(courseCode);
    }

    @Override
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Integer id) {
        return  courseRepository.findById(id);
    }

    @Override
    public Course updateCourse(Integer id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ApplicationException(new AppBusinessError("Course not found", 400)));
        course.setCourseCode(courseRequest.getCourseCode());
        course.setName(courseRequest.getName());
        course.setCredits(courseRequest.getCredits());
        course.setAttendancePercentage(courseRequest.getAttendancePercentage());
        course.setPracticePercentage(courseRequest.getPracticePercentage());
        course.setProjectPercentage(courseRequest.getProjectPercentage());
        course.setTestPercentage(courseRequest.getTestPercentage());
        return course;
    }
}
