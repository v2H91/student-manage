package vn.studentmanagement.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.entity.Course;
import vn.studentmanagement.api.repository.CourseRepository;
import vn.studentmanagement.api.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void save(Course monHoc) {
        courseRepository.save(monHoc);
    }

    @Override
    public boolean existsByCourseCode(String courseCode) {
       return courseRepository.existsByCourseCode(courseCode);
    }
}
