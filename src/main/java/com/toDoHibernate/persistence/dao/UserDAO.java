package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.entities.User;

public interface UserDAO {

    User findByEmail(String emailOrUsername);

    User create(User user);

    User findByIdEager(Long id);

    User update(User user);

}
