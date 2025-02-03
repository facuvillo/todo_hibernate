package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.entities.Task;

// TODO Agregar el CRUD básico completo
public interface TaskDAO {

    Task findById(Long id);

    Task create(Task task);

    Boolean taskDelete(Long id);

}
