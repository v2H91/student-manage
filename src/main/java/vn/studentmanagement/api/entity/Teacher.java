package vn.studentmanagement.api.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;


}
