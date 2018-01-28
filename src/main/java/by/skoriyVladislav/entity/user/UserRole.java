package by.skoriyVladislav.entity.user;

public enum UserRole {
    PLAYER("player"),
    ADMIN("admin"),
    BOOKMAKER("bookmaker");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
