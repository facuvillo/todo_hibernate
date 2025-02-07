package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.entities.ListTasks;
import com.toDoHibernate.persistence.entities.Task;

import java.util.List;

// TODO Agregar el CRUD básico completo
public interface ListDAO {

    ListTasks findByIdEager(Long id);

    ListTasks findById(Long id);

    ListTasks update(ListTasks listTasks);

    List<Task> findByImportance(Boolean isImportant);

    ListTasks create(ListTasks listTasks);

    boolean delete(Long id);

}
