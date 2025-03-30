package vn.studentmanagement.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name ="Class")
@Setter
@Getter
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "class_group", nullable = false)
    private String classGroup;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "class_code", nullable = false, unique = true)
    private String classCode;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @JsonIgnore
    private String students;
}
