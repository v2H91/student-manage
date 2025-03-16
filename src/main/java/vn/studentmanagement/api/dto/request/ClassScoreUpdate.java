package vn.studentmanagement.api.dto.request;

import lombok.Data;

@Data
public class ClassScoreUpdate {
    private Double attendanceScore;

    private Double testScore;

    private Double practiceScore;

    private Double projectScore;
}
