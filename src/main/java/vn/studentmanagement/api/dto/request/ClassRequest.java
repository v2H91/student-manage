package vn.studentmanagement.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRequest {
    @NotBlank(message = "Môn học chưa được chọn!")
    private Integer courseId;
    @NotBlank(message = "Nhóm lớp chưa được nhập!")
    private String classGroup;
    @NotBlank(message = "Tên lớp chưa được nhập!")
    private String className;
    @NotBlank(message = "Giáo viên giảng dạy của lớp chưa được lựa chọn!")
    private Integer teacherId;

    private Integer semesterId;
}
