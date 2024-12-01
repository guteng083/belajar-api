package org.practice.libraryspring.constant;

public enum Role {
    ROLE_ADMIN("Admin"),
    ROLE_LIBRARIAN("Pustakawan");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public static Role getRole(String role) {
        for (Role roles : Role.values()) {
            if (roles.role.equalsIgnoreCase(role)) {
                return roles;
            }
        }
        return null;
    }
}
