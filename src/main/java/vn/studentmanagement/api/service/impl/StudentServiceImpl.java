package vn.studentmanagement.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.common.enums.RoleEnum;
import vn.studentmanagement.api.dto.request.StudentRequest;
import vn.studentmanagement.api.entity.Student;
import vn.studentmanagement.api.entity.Teacher;
import vn.studentmanagement.api.entity.User;
import vn.studentmanagement.api.repository.StudentRepository;
import vn.studentmanagement.api.repository.UserRepository;
import vn.studentmanagement.api.service.StudentService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static vn.studentmanagement.api.utils.JwtUtils.bCryptPasswordEncoder;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student addStudent(StudentRequest studentRequest) {
        Optional<Student> byStudentCode = studentRepository.findByStudentCode(studentRequest.getStudentCode());
        if (byStudentCode.isPresent()){
            throw new ApplicationException(new AppBusinessError("Không thể thực hiện thêm mới bởi Mã SV đã có trong CSDL", 400));
        }
        Optional<Student> byEmail= studentRepository.findByEmail(studentRequest.getEmail());
        if (byEmail.isPresent()){
            throw new ApplicationException(new AppBusinessError("email đã được sử dụng", 400));
        }

        User user = new User();
        String encryptedPassword = bCryptPasswordEncoder.encode(studentRequest.getStudentCode());
        user.setPassword(encryptedPassword);
        user.setEmail(studentRequest.getEmail());
        user.setRole(RoleEnum.STUDENT);
        user.setFullName(studentRequest.getFirstName() + " " + studentRequest.getLastName());
        user.setDateOfBirth(new Date());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        userRepository.save(user);

        Student student = new Student();
        student.setStudentCode(studentRequest.getStudentCode());
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setPhone(studentRequest.getPhone());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Integer id, StudentRequest studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ApplicationException(new AppBusinessError("Student not found", 400)));
        student.setStudentCode(studentDetails.getStudentCode());
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setPhone(studentDetails.getPhone());
        return studentRepository.save(student);
    }

    @Override
    public List<Student> searchStudents(String name, String department, String studentCode, String email) {
        return studentRepository.searchStudents(name,department,studentCode,email);
    }

    @Override
    public List<Student> getStudentsByIds(List<Integer> ids) {
        return studentRepository.findAllById(ids);
    }
}
