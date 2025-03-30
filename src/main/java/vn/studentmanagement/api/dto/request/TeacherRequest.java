package vn.studentmanagement.api.dto.request;

import lombok.Data;

@Data
public class TeacherRequest {
    private String name;
    private String department;
    private String phone;
    private String email;
}
