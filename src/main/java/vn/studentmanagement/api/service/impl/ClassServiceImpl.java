package vn.studentmanagement.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.ClassRequest;
import vn.studentmanagement.api.entity.Clazz;
import vn.studentmanagement.api.entity.Semester;
import vn.studentmanagement.api.entity.SemesterClass;
import vn.studentmanagement.api.repository.*;
import vn.studentmanagement.api.service.ClassService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private SemesterClassRepository semesterClassRepository;

    @Override
    public void saveLopMonHoc(ClassRequest classRequest) {
        String uniqueValue = UUID.randomUUID().toString().replace("-", "");
        uniqueValue = uniqueValue.substring(0, 49);
        Clazz clazz = new Clazz();
        clazz.setClassCode(uniqueValue);
        clazz.setClassName(classRequest.getClassName());
        clazz.setClassGroup(classRequest.getClassGroup());
        clazz.setTeacher(teacherRepository.findById(classRequest.getTeacherId()).orElseThrow(() -> new ApplicationException(new AppBusinessError("not found teacher",400))));
        clazz.setCourse(courseRepository.findById(classRequest.getCourseId()).orElseThrow(() -> new ApplicationException(new AppBusinessError("not found course",400))));
        Clazz save = classRepository.save(clazz);

        if (classRequest.getSemesterId() != null) {
            Optional<Semester> semester = semesterRepository.findById(classRequest.getSemesterId());
            if (semester.isEmpty()){
                throw new ApplicationException(new AppBusinessError("Không tìm thấy học kỳ", 400));
            }
            SemesterClass semesterClass = new SemesterClass();
            semesterClass.setAClass(save);
            semesterClass.setSemester(semester.get());
            semesterClassRepository.save(semesterClass);
        }
    }

    @Override
    public void deleteClass(Integer id) {
        classRepository.deleteById(id);
    }

    @Override
    public List<Clazz> getAllClass() {
        return classRepository.findAll();
    }

    @Override
    public Optional<SemesterClass> getClassById(Integer id) {
        Optional<Clazz> byId = classRepository.findById(id);
        if (byId.isEmpty()){
            throw new ApplicationException(new AppBusinessError("Không tìm thấy sinh viên", 400));
        }
       return semesterClassRepository.findByaClass(byId.get());

    }

    @Override
    public Clazz updateClass(Integer id, ClassRequest classRequest) {
        Optional<Clazz> clazzOpt = classRepository.findById(id);
        if (clazzOpt.isEmpty()){
            throw new ApplicationException(new AppBusinessError("Không tìm thấy sinh viên", 400));
        }
        Clazz clazz  = clazzOpt.get();
        clazz.setClassName(classRequest.getClassName());
        clazz.setClassGroup(classRequest.getClassGroup());
        clazz.setTeacher(teacherRepository.findById(classRequest.getTeacherId()).orElseThrow(() -> new ApplicationException(new AppBusinessError("not found teacher",400))));
        clazz.setCourse(courseRepository.findById(classRequest.getCourseId()).orElseThrow(() -> new ApplicationException(new AppBusinessError("not found course",400))));
        return classRepository.save(clazz);
    }

}
