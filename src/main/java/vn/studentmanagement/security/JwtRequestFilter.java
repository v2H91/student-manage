package vn.studentmanagement.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;
import vn.studentmanagement.api.utils.JwtUtils;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    @Value("${jwt.prefix}")
    private String jwtPrefix;
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String authorization = request.getHeader("Authorization");

        String jwt = null;
        Cookie cookie = WebUtils.getCookie(request, "JWT_TOKEN");
        if (cookie != null) {
            jwt = cookie.getValue();
        }

        if (jwt == null && authorization != null && authorization.startsWith(jwtPrefix)) {
            jwt = authorization.substring(jwtPrefix.length() + 1);
        }

        // Create Authentication object
        UsernamePasswordAuthenticationToken authenticationObject = getAuthentication(jwt);
        if (authenticationObject == null) {
            chain.doFilter(request, response);
            return;
        }

        // Authentication successful, save the Authentication object to the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationObject);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            String userId = jwtUtils.extractUsername(jwtSecret, token);
            if (Boolean.TRUE.equals(jwtUtils.isTokenValid(jwtSecret, token, userId))
                    && Boolean.TRUE.equals(jwtUtils.isActiveToken(token, userId))
            ) {
                UserDetails user = jwtUserDetailsService.loadUserByUsername(userId);

                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            }
            return null;
        } catch (Exception exception) {
            if (StringUtils.isNotBlank(token))
                log.warn("jwt = " + token);
            return null;
        }
    }
}
