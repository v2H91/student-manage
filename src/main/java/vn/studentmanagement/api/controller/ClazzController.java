package vn.studentmanagement.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.ClassRequest;
import vn.studentmanagement.api.entity.Clazz;
import vn.studentmanagement.api.entity.SemesterClass;
import vn.studentmanagement.api.service.ClassService;
import vn.studentmanagement.config.BaseResponse;

import java.util.List;
import java.util.Optional;

import static vn.studentmanagement.config.BaseResponse.ofSucceeded;

@RestController
@RequestMapping("/api/v1/classes")
public class ClazzController {

    @Autowired
    private ClassService classService;

    @PostMapping("/create")
    public BaseResponse<String> taoLopMonHoc(@Valid @RequestBody ClassRequest classRequest, BindingResult result) {
        if (result.hasErrors()) {
           throw  new ApplicationException(new AppBusinessError("Validation failed: " + result.getAllErrors(),400));
        }
        classService.saveLopMonHoc(classRequest);
        return ofSucceeded("Lớp môn học đã được lưu vào CSDL thành công");
    }
    @GetMapping
    public List<Clazz> getAllClass() {
        return classService.getAllClass();
    }

    @GetMapping("/{id}")
    public BaseResponse<SemesterClass> getClassById(@PathVariable Integer id) {
        SemesterClass clazz = classService.getClassById(id);
            return ofSucceeded(clazz);
   }

//    @GetMapping("/{studentId}")
//    public BaseResponse<SemesterClass> getClassByStudentId(@PathVariable Integer studentId) {
//        Optional<SemesterClass> clazz = classService.getClassByStudentId(studentId);
//        if (clazz.isPresent()) {
//            return BaseResponse.ofSucceeded(clazz.get());
//        }
//        throw new ApplicationException(new AppBusinessError("Không tìm thấy Class", 400));
//    }

    @PutMapping("/{id}")
    public BaseResponse<Clazz> updateClass(@PathVariable Integer id, @RequestBody ClassRequest classDetails) {
        Clazz updatedClass = classService.updateClass(id, classDetails);
        return ofSucceeded(updatedClass);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteClass(@PathVariable Integer id) {
        classService.deleteClass(id);
        return ofSucceeded();
    }

}
