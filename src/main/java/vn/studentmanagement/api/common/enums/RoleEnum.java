package vn.studentmanagement.api.common.enums;

public enum RoleEnum {
    STUDENT("student"), TEACHER("teacher"), ADMIN("admin");


    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static RoleEnum fromString(String role) {
        for (RoleEnum r : RoleEnum.values()) {
            if (r.role.equalsIgnoreCase(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No enum constant for role: " + role);
    }
}
