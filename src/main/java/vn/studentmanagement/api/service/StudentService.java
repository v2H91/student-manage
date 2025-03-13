package vn.studentmanagement.api.service;

import vn.studentmanagement.api.dto.request.StudentRequest;
import vn.studentmanagement.api.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();

    Optional<Student> getStudentById(Integer id);

    Student addStudent(StudentRequest student);

    void deleteStudent(Integer id);

    Student updateStudent(Integer id, StudentRequest studentDetails);
}
