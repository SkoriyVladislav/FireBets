package by.skoriyVladislav.entity.user;

import by.skoriyVladislav.constants.Constants;

public enum UserRole {
    PLAYER(Constants.PLAYER),
    ADMIN(Constants.ADMIN),
    BOOKMAKER(Constants.BOOKMAKER),
    BANNED(Constants.BANNED);

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
