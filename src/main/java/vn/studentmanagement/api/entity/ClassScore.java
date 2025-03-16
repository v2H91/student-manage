package vn.studentmanagement.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Class_Score")
public class ClassScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Clazz aClass;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "attendance_score")
    private Double attendanceScore;

    @Column(name = "test_score")
    private Double testScore;

    @Column(name = "practice_score")
    private Double practiceScore;

    @Column(name = "project_score")
    private Double projectScore;

}
