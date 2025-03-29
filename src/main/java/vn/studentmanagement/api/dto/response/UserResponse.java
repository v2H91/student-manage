package vn.studentmanagement.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Date;
@Data
@AllArgsConstructor
public class UserResponse {

    private Long userId;

    private String role;

    private String fullName;

    private String email;

    private Date dateOfBirth;


}
