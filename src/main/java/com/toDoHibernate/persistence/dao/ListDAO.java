package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.entities.ListTasks;

// TODO Agregar el CRUD básico completo
public interface ListDAO {

    ListTasks findByIdEager(Long id);

    ListTasks findById(Long id);

    ListTasks update(ListTasks listTasks);

}
