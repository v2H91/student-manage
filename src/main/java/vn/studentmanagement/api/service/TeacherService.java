package vn.studentmanagement.api.service;


import vn.studentmanagement.api.entity.Teacher;
import vn.studentmanagement.api.dto.request.TeacherRequest;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    void deleteTeacher(Integer id);

    Teacher updateTeacher(Integer id, TeacherRequest teacherRequest);

    Teacher addTeacher(TeacherRequest student);

    Optional<Teacher> getTeacherById(Integer id);

    List<Teacher> getAllStudents();

    List<Teacher> searchTeachers(String fullName, String department, String phone, String email);
}
