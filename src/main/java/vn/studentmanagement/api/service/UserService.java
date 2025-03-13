package vn.studentmanagement.api.service;

import vn.studentmanagement.api.dto.request.ChangePasswordRequest;
import vn.studentmanagement.api.dto.request.LoginRequest;
import vn.studentmanagement.api.dto.request.UserRequest;
import vn.studentmanagement.api.dto.response.AuthenticationResponse;
import vn.studentmanagement.api.entity.User;

public interface UserService {
    AuthenticationResponse login(LoginRequest request);

    User findById(Long id);

    void disableUserToken();

    User registerUser(UserRequest user);

    String changePassword(ChangePasswordRequest request);
}
