package vn.studentmanagement.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.studentmanagement.api.common.enums.RoleEnum;

@Entity
@Table(name = "Permissions")
@Getter
@Setter
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private int permissionId;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum userRole;  // "student", "teacher", "admin"

    @Column(name = "access_function", nullable = false, length = 255)
    private String accessFunction;

    @Column(name = "can_access", nullable = false)
    private boolean canAccess;


}

