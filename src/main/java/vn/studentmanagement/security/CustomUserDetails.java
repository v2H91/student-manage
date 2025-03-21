package vn.studentmanagement.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.studentmanagement.api.common.enums.RoleEnum;
import vn.studentmanagement.api.entity.User;

import java.util.ArrayList;
import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var roles = new ArrayList<GrantedAuthority>();
        SimpleGrantedAuthority roleUser;
        if (user.getRole().equals(RoleEnum.ADMIN)) {
            roleUser = new SimpleGrantedAuthority(
                    "ROLE_ADMIN"
            );
        } else {
            roleUser = new SimpleGrantedAuthority(
                    "ROLE_USER"
            );
        }
        roles.add(
                roleUser
        );
        return roles;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
