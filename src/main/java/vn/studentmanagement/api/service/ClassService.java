package vn.studentmanagement.api.service;

import vn.studentmanagement.api.dto.request.ClassRequest;
import vn.studentmanagement.api.entity.Clazz;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    void saveLopMonHoc(ClassRequest classRequest);

    void deleteClass(Integer id);

    List<Clazz> getAllClass();

    Optional<Clazz> getClassById(Integer id);

    Clazz updateClass(Integer id, ClassRequest classDetails);
}
