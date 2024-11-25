package com.toDoHibernate.igu.dto;

public class UserLoginDTO {

    private final String email;
    private final String userName;
    private final String password;

    public UserLoginDTO(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
