package vn.studentmanagement.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.ClassRequest;
import vn.studentmanagement.api.entity.Clazz;
import vn.studentmanagement.api.repository.ClassRepository;
import vn.studentmanagement.api.repository.CourseRepository;
import vn.studentmanagement.api.repository.TeacherRepository;
import vn.studentmanagement.api.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void saveLopMonHoc(ClassRequest classRequest) {
        Clazz clazz = new Clazz();
        clazz.setClassCode(classRequest.getClassCode());
        clazz.setClassName(classRequest.getClassName());
        clazz.setClassGroup(classRequest.getClassGroup());
        clazz.setTeacher(teacherRepository.findById(classRequest.getTeacherId()).orElseThrow(() -> new ApplicationException(new AppBusinessError("not found teacher",400))));
        clazz.setCourse(courseRepository.findById(classRequest.getCourseId()).orElseThrow(() -> new ApplicationException(new AppBusinessError("not found course",400))));
        classRepository.save(clazz);
    }
}
