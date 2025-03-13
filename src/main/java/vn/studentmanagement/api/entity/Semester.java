package vn.studentmanagement.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import vn.studentmanagement.api.common.enums.SemesterStatus;

@Entity
@Data
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer term;

    @Column(name = "start_year", nullable = false)
    private Integer startYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SemesterStatus status = SemesterStatus.CHUA_DIEN_RA;

}