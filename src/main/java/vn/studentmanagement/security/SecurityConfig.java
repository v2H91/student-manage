package vn.studentmanagement.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.studentmanagement.api.common.CustomAccessDeniedHandler;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/api/v1/auth/**",
                                "swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**",
                                         "api/v1/users/**").permitAll()
                                .requestMatchers("api/v1/admin/**",
                                        "/api/v1/classes/**","/api/v1/courses/**",
                                        "/api/v1/semesters/**",
                                        "/api/v1/students/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedHandler(new CustomAccessDeniedHandler())
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}

