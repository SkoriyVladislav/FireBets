package by.skoriyVladislav.entity.user;

public enum UserRole {
    USER("user"),
    ADMIN("admin"),
    GUEST("guest");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
