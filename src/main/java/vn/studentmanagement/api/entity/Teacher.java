package vn.studentmanagement.api.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String department;
    private String phone;
    private String email;
}
