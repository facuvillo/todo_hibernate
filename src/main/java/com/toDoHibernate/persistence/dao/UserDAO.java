package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.entities.User;

import java.util.List;

// TODO Agregar el CRUD básico completo
public interface UserDAO {

    User findById(Long id);

    User findByEmail(String email);

    User create(User user);

    User update(User user);

    boolean delete(User user);

}
