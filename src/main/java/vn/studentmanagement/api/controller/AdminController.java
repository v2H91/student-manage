package vn.studentmanagement.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.common.ApplicationException;
import vn.studentmanagement.api.dto.request.ChangePasswordRequest;
import vn.studentmanagement.api.service.UserService;
import vn.studentmanagement.api.utils.AuthenticationHelper;
import vn.studentmanagement.config.BaseResponse;

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
}
