package vn.studentmanagement.api.service;

import vn.studentmanagement.api.dto.request.ClassRequest;

public interface ClassService {
    void saveLopMonHoc(ClassRequest classRequest);
}
