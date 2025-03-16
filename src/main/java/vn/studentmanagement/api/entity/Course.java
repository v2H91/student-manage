package vn.studentmanagement.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer credits;

    @Column(name = "attendance_percentage", nullable = false)
    private Double attendancePercentage;

    @Column(name = "test_percentage", nullable = false)
    private Double testPercentage;

    @Column(name = "practice_percentage", nullable = false)
    private Double practicePercentage;

    @Column(name = "project_percentage", nullable = false)
    private Double projectPercentage;
}
