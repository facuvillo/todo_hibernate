package com.toDoHibernate.igu.dto;

public class UserLoginDTO {

    private String email;
    private String userName;
    private String password;

    public UserLoginDTO(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
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
