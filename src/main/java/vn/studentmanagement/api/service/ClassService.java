package vn.studentmanagement.api.service;

import vn.studentmanagement.api.dto.request.ClassRequest;
import vn.studentmanagement.api.entity.Clazz;
import vn.studentmanagement.api.entity.SemesterClass;

import java.util.List;
public interface ClassService {
    void saveLopMonHoc(ClassRequest classRequest);

    void deleteClass(Integer id);

    List<SemesterClass> getAllClass();

    SemesterClass getClassById(Integer id);

    Clazz updateClass(Integer id, ClassRequest classDetails);

}
