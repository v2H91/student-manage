package vn.studentmanagement.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;
    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters.")
    private String password;
    private String fullName;
    @NotNull(message = "Role is required.")
    @Pattern(regexp = "STUDENT|TEACHER|ADMIN", message = "Role must be one of: STUDENT, TEACHER, ADMIN.")
    private String role;  // "student", "teacher", "admin"
}
