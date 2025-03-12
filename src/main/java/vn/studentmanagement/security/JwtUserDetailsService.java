package vn.studentmanagement.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.studentmanagement.api.entity.User;
import vn.studentmanagement.api.service.UserService;

@Service
@AllArgsConstructor
@Data
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userService.findById(Long.parseLong(id));
      return new CustomUserDetails(user);
    }


}
