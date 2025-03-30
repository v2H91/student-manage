package vn.studentmanagement.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.ClassRequest;
import vn.studentmanagement.api.entity.Clazz;
import vn.studentmanagement.api.entity.Semester;
import vn.studentmanagement.api.entity.SemesterClass;
import vn.studentmanagement.api.entity.Student;
import vn.studentmanagement.api.repository.*;
import vn.studentmanagement.api.service.ClassService;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private StudentRepository studentRepository;


    @Override
    public void saveLopMonHoc(ClassRequest classRequest) {
        String uniqueValue = UUID.randomUUID().toString().replace("-", "");
        uniqueValue = uniqueValue.substring(0, 30);

        Clazz clazz = new Clazz();
        if(!classRequest.getStudentIds().isEmpty()){{
            clazz.setStudents(classRequest.getStudentIds().stream().map(
                    String::valueOf
            ).collect(Collectors.joining(",")));
        }}
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
    public List<SemesterClass> getAllClass() {
        List<SemesterClass> semesterClasses = new ArrayList<>();
        List<Clazz> all = classRepository.findAll();
        for(Clazz cls : all){
            Optional<SemesterClass> byaClass = semesterClassRepository.findByaClass(cls);
            SemesterClass semesterClass = byaClass.get();
            if (!cls.getStudents().isEmpty()){
                List<Integer> studentIds = Arrays.stream(cls.getStudents().split(",")).map(
                        Integer::parseInt
                ).toList();
                List<Student> allById = studentRepository.findAllById(studentIds);
                semesterClass.setStudents(allById);
            }
            semesterClasses.add(semesterClass);
        }
        return semesterClasses;
    }

    @Override
    public SemesterClass getClassById(Integer id) {
        Optional<Clazz> byId = classRepository.findById(id);
        if (byId.isEmpty()){
            throw new ApplicationException(new AppBusinessError("Không tìm thấy class", 400));
        }
        Clazz clazz = byId.get();
        Optional<SemesterClass> byaClass = semesterClassRepository.findByaClass(clazz);
        if (byaClass.isEmpty()){
            throw new ApplicationException(new AppBusinessError("Không tìm thấy class", 400));
        }
        SemesterClass semesterClass = byaClass.get();
        if (!clazz.getStudents().isEmpty()){
            List<Integer> studentIds = Arrays.stream(clazz.getStudents().split(",")).map(
                    Integer::parseInt
            ).toList();
            List<Student> allById = studentRepository.findAllById(studentIds);
            semesterClass.setStudents(allById);
        }
        return semesterClass;
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
        if(!classRequest.getStudentIds().isEmpty()){{
            clazz.setStudents(classRequest.getStudentIds().stream().map(
                    String::valueOf
            ).collect(Collectors.joining(",")));
        }}
        return classRepository.save(clazz);
    }


}
