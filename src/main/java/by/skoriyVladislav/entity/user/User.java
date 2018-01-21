package by.skoriyVladislav.entity.user;

public class User {
    private String login;
    private String name;
    private String surname;
    private double balance;
    private String email;
    private UserRole role;


    public User(String login, String name, String surname, double balance, String email, UserRole role) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.email = email;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
