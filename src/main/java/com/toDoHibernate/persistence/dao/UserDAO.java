package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.entities.User;

// TODO Agregar el CRUD básico completo
public interface UserDAO {

    User findByEmail(String emailOrUsername);

    User create(User user);

    User findByIdEager(Long id);

}
