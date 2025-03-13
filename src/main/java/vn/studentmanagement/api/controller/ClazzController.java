package vn.studentmanagement.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.ClassRequest;
import vn.studentmanagement.api.service.ClassService;
import vn.studentmanagement.config.BaseResponse;

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
        return BaseResponse.ofSucceeded("Lớp môn học đã được lưu vào CSDL thành công");
    }


}
