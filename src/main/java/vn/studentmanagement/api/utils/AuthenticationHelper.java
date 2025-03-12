package vn.studentmanagement.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.studentmanagement.api.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.Authentication;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import vn.studentmanagement.security.CustomUserDetails;

import java.util.Optional;

@Component
public class AuthenticationHelper {
    @Value("${jwt.duration}")
    private Integer duration;
    public String getUserId() {
        if(this.getUser() == null ){
            return null;
        }
        return getUser().getUserId().toString();
    }

    public User getUser(){
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(u -> {
                    if (u instanceof CustomUserDetails) return (((CustomUserDetails) u).getUser());
                    else return null;
                }).orElse(null);
    }

    public void setCookie(String token,Integer duration, HttpServletResponse res){
        Cookie cookie = new Cookie("JWT_TOKEN", token);
        cookie.setMaxAge(duration);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        res.addCookie(cookie);

        var cookieJwt = res.getHeader("Set-Cookie");
        if (cookieJwt != null && (cookieJwt.contains("JWT_TOKEN"))) {
            cookieJwt += "; SameSite=None";
            res.setHeader("Set-Cookie", cookieJwt);
        }
    }
}