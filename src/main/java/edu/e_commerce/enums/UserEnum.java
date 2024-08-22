package edu.e_commerce.enums;

public enum UserEnum {
    
    ADMIN("admin"),
    USER("user");

    private String role;

    UserEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
