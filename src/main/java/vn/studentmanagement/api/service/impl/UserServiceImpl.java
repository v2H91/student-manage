package vn.studentmanagement.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.common.enums.RoleEnum;
import vn.studentmanagement.api.dto.request.LoginRequest;
import vn.studentmanagement.api.dto.request.UserRequest;
import vn.studentmanagement.api.dto.response.AuthenticationResponse;
import vn.studentmanagement.api.entity.User;
import vn.studentmanagement.api.entity.UserToken;
import vn.studentmanagement.api.repository.UserRepository;
import vn.studentmanagement.api.repository.UserTokenRepository;
import vn.studentmanagement.api.service.UserService;
import vn.studentmanagement.api.utils.AuthenticationHelper;
import vn.studentmanagement.api.utils.JwtUtils;
import io.jsonwebtoken.Claims;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationHelper authenticationHelper;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserTokenRepository userTokenRepository;
@Autowired
    public UserServiceImpl(UserRepository userRepository, AuthenticationHelper authenticationHelper, JwtUtils jwtUtils, UserTokenRepository userTokenRepository) {
        this.userRepository = userRepository;
        this.authenticationHelper = authenticationHelper;
        this.jwtUtils = jwtUtils;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.userTokenRepository = userTokenRepository;
    }

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.duration}")
    private Integer duration;
    @Override
    public AuthenticationResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
                var token = jwtUtils.generateToken(jwtSecret, user.getUserId(), (duration * 1_000));
                var expiration = jwtUtils.extractClaim(jwtSecret, token, Claims::getExpiration);

                userTokenRepository.save(new UserToken(token, user.getUserId().toString(), true, new Timestamp(expiration.getTime())));
                return new AuthenticationResponse(token, user.getRole().toString(), expiration.getTime());
            }
        }
        throw new RuntimeException("Email hoặc mật khẩu không đúng.");
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()){
            return userOpt.get();
        }
        throw new UsernameNotFoundException("user not found");
    }

    @Override
    public void disableUserToken() {
        Optional<UserToken> optionalUserToken =
                userTokenRepository.findByUserId(authenticationHelper.getUserId());
        if (optionalUserToken.isPresent()){
            UserToken userToken = optionalUserToken.get();
            userToken.setActive(false);
            userTokenRepository.save(userToken);
        }
    }

    @Override
    public User registerUser(UserRequest userRequest) {
        User user = new User();

        String encryptedPassword = bCryptPasswordEncoder.encode(userRequest.getPassword());
        user.setPassword(encryptedPassword);
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getStudentCode());
        user.setRole(RoleEnum.fromString(userRequest.getRole()));
        user.setFullName(userRequest.getFullName());
        return userRepository.save(user);
    }
}
