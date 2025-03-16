package vn.studentmanagement.api.dto.request;

import lombok.Data;

@Data
public class CourseRequest {

    private String courseCode;


    private String name;


    private Integer credits;


    private Double attendancePercentage;


    private Double testPercentage;


    private Double practicePercentage;

    private Double projectPercentage;
}
