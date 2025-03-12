package vn.studentmanagement.api.common.enums;

public enum RoleEnum {
    STUDENT, TEACHER, ADMIN;

    public static RoleEnum fromString(String role) {
        if (role != null) {
            for (RoleEnum r : RoleEnum.values()) {
                if (role.equalsIgnoreCase(r.name())) {
                    return r;
                }
            }
        }
        throw new IllegalArgumentException("No enum constant for role: " + role);
    }
}
