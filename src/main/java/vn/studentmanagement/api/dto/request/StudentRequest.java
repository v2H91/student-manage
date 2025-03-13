package vn.studentmanagement.api.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentRequest {

    @NotBlank(message = "Thiếu Mã Sinh viên")
    private String studentCode;

    @NotBlank(message = "Thiếu Họ và tên sinh viên")
    private String firstName;

    @NotBlank(message = "Thiếu Họ và tên sinh viên")
    private String lastName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;
}
