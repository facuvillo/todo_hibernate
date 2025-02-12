package com.toDoHibernate.security;

import com.toDoHibernate.persistence.dao.UserDAO;
import com.toDoHibernate.persistence.dao.UserDAOImpl;
import com.toDoHibernate.persistence.entities.User;

public class AuthService {

    private final UserDAO userDAO = new UserDAOImpl();

    public boolean login(Long id){
        User user = userDAO.findByIdEager(id);
        if (user != null) {
            AuthenticatedUser.getInstance().setUser(user);
            return true;
        }
        return false;
    }

    public void logout(){
        AuthenticatedUser.getInstance().setUser(null);
    }

    public User getAuthenticatedUser(){
        return AuthenticatedUser.getInstance().getUser();
    }

}
