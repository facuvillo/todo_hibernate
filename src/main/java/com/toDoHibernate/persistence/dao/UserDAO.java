package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.entities.User;

import java.util.List;

// TODO Agregar el CRUD básico completo
public interface UserDAO {

    User findByEmail(String email);

    User create(User user);

}
