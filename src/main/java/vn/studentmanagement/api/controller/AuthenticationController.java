package vn.studentmanagement.api.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.studentmanagement.api.common.AppBusinessError;
import vn.studentmanagement.api.dto.request.LoginRequest;
import vn.studentmanagement.api.dto.request.UserRequest;
import vn.studentmanagement.api.dto.response.AuthenticationResponse;
import vn.studentmanagement.api.dto.response.SuccessResponse;
import vn.studentmanagement.api.entity.User;
import vn.studentmanagement.api.service.UserService;
import vn.studentmanagement.api.utils.AuthenticationHelper;
import vn.studentmanagement.config.BaseResponse;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationHelper authenticationHelper;

    @PostMapping("/register")
    public BaseResponse<?> registerUser(@Valid @RequestBody UserRequest user, BindingResult result) {
        if (result.hasErrors()) {
            return BaseResponse.ofFailed(new AppBusinessError("Validation failed: " + result.getAllErrors(), 400));
        }
        userService.registerUser(user);
        return BaseResponse.ofSucceeded(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public BaseResponse<AuthenticationResponse> login(
            @Validated @RequestBody LoginRequest request,
            HttpServletResponse res
    ) {
        AuthenticationResponse authentication = userService.login(request);
        authenticationHelper.setCookie(authentication.getToken(), 4320, res);
        return BaseResponse.ofSucceeded(authentication);
    }

    @PostMapping("/logout")
    public BaseResponse<SuccessResponse<String>> logout(HttpServletResponse response) {
        userService.disableUserToken();
        authenticationHelper.setCookie("", 0, response);
        return BaseResponse.ofSucceeded(new SuccessResponse<>(HttpStatus.OK.value(), null, null));
    }

}
