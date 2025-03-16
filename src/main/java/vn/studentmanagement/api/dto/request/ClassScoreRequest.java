package vn.studentmanagement.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClassScoreRequest {
    @NotBlank
    private Integer classId;
    @NotBlank
    private Integer studentId;

    private Double attendanceScore;

    private Double testScore;

    private Double practiceScore;

    private Double projectScore;
}
