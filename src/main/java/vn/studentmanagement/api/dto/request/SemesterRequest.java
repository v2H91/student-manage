package vn.studentmanagement.api.dto.request;


import lombok.Data;

@Data
public class SemesterRequest {
    private String name;

    private Integer term;

    private Integer startYear;

}
