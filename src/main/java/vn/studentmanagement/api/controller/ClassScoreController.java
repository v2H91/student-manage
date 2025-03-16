package vn.studentmanagement.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.ClassScoreRequest;
import vn.studentmanagement.api.dto.request.ClassScoreUpdate;
import vn.studentmanagement.api.entity.ClassScore;
import vn.studentmanagement.api.service.ClassScoreService;
import vn.studentmanagement.config.BaseResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/class-scores")
@RequiredArgsConstructor
public class ClassScoreController {

    private final ClassScoreService classScoreService;

    @GetMapping("/{classId}/class")
    public BaseResponse<List<ClassScore>> getClassById(@PathVariable Integer classId) {
        List<ClassScore> classScores = classScoreService.getByClassId(classId);
            return BaseResponse.ofSucceeded(classScores);
    }

    @GetMapping("/{studentId}/student")
    public BaseResponse<List<ClassScore>> getStudentById(@PathVariable Integer studentId) {
        List<ClassScore> classScores = classScoreService.getByStudentId(studentId);
        return BaseResponse.ofSucceeded(classScores);
    }

    @PostMapping
    public BaseResponse<String> createClassScore(@Valid @RequestBody ClassScoreRequest classScoreReuqest, BindingResult result) {
        if (result.hasErrors()) {
            throw  new ApplicationException(new AppBusinessError("Validation failed: " + result.getAllErrors(),400));
        }
        classScoreService.createScore(classScoreReuqest);
        return BaseResponse.ofSucceeded("score đã được lưu vào CSDL thành công");
    }

    @PutMapping("/{id}")
    public BaseResponse<ClassScore> updateClasScore(@PathVariable Integer id, @RequestBody ClassScoreUpdate scoreUpdate) {
        ClassScore classScore = classScoreService.updateScore(id, scoreUpdate);
        return BaseResponse.ofSucceeded(classScore);
    }
}
