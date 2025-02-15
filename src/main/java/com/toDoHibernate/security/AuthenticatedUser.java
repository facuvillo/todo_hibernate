package com.toDoHibernate.security;

import com.toDoHibernate.persistence.dao.UserDAOImpl;
import com.toDoHibernate.persistence.entities.User;

public class AuthenticatedUser {

    private static AuthenticatedUser instance;
    private User user;
    private UserDAOImpl userDAO = new UserDAOImpl();

    private AuthenticatedUser() {}

    public static AuthenticatedUser getInstance(){
        if (instance == null) {
            instance = new AuthenticatedUser();
        }
        return instance;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void updateUser(){
        AuthenticatedUser.getInstance().setUser(userDAO.findByIdEager(user.getId()));
    }

}
