package vn.studentmanagement.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.ChangePasswordRequest;
import vn.studentmanagement.api.dto.request.ClassScoreRequest;
import vn.studentmanagement.api.dto.request.ClassScoreUpdate;
import vn.studentmanagement.api.dto.response.UserResponse;
import vn.studentmanagement.api.entity.ClassScore;
import vn.studentmanagement.api.entity.User;
import vn.studentmanagement.api.service.UserService;
import vn.studentmanagement.api.utils.AuthenticationHelper;
import vn.studentmanagement.config.BaseResponse;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final AuthenticationHelper authenticationHelper;

    @PostMapping("/change-password")
    public BaseResponse<String> changePassword(@Valid @RequestBody ChangePasswordRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new ApplicationException(new AppBusinessError("Validation failed: " + result.getAllErrors(), 400));
        }
        String success = userService.changePassword(request);
        return BaseResponse.ofSucceeded(success);
    }

    @GetMapping("/users")
    public BaseResponse<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return BaseResponse.ofSucceeded(users);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return BaseResponse.ofSucceeded();
    }

}
