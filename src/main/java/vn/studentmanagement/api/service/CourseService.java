package vn.studentmanagement.api.service;

import vn.studentmanagement.api.entity.Course;

public interface CourseService {
    void save(Course monHoc);

    boolean existsByCourseCode(String courseCode);
}
