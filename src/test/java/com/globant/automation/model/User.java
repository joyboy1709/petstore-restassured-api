package com.globant.automation.model;

public class User {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public User() {}

    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = "Default";
        this.lastName = "User";
        this.email = username + "@test.com";
        this.phone = "1234567890";
        this.userStatus = 1;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
