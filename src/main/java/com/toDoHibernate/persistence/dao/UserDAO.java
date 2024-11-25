package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.igu.dto.UserLoginDTO;
import com.toDoHibernate.persistence.entities.User;

import java.util.List;

// TODO Agregar el CRUD básico completo
public interface UserDAO {

    User findByEmail(String email);

    UserLoginDTO findByEmailOrNickname(String emailOrUsername);

    User create(User user);

}
