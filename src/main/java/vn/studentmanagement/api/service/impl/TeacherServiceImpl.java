package vn.studentmanagement.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.TeacherRequest;
import vn.studentmanagement.api.entity.Teacher;
import vn.studentmanagement.api.repository.TeacherRepository;
import vn.studentmanagement.api.service.TeacherService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher updateTeacher(Integer id, TeacherRequest teacherRequest) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ApplicationException(new AppBusinessError("Teacher not found", 400)));
        teacher.setName(teacherRequest.getName());
        teacher.setDepartment(teacherRequest.getDepartment());
        teacher.setPhone(teacherRequest.getPhone());
        teacher.setEmail(teacherRequest.getEmail());
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher addTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherRequest.getName());
        teacher.setDepartment(teacherRequest.getDepartment());
        teacher.setPhone(teacherRequest.getPhone());
        teacher.setEmail(teacherRequest.getEmail());
        return teacherRepository.save(teacher);
    }

    @Override
    public Optional<Teacher> getTeacherById(Integer id) {
        return teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> getAllStudents() {
        return teacherRepository.findAll();
    }
}
