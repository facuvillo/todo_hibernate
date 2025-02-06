package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.entities.ListTasks;
import com.toDoHibernate.persistence.entities.Task;
import com.toDoHibernate.persistence.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;

public class TaskDAOImple implements TaskDAO {

    @Override
    public Task findById(Long id) {
        // 1
        Session session = HibernateUtil.getSessionFactory().openSession();
        // 2
        Task task = session.find(Task.class, id);
        // 3
        session.close();
        //4
        return task;
    }

    @Override
    public Task create(Task task) {
        // 1
        Session session = HibernateUtil.getSessionFactory().openSession();
        // 2
        try{
            session.beginTransaction();
            session.persist(task);
            session.getTransaction().commit();
        }catch(PersistenceException e){
            System.out.println("Error: "+e.getMessage());
            session.getTransaction().rollback();
        }
        // 3
        session.close();
        // 4
        return task;
    }

    @Override
    public Boolean taskDelete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Task task = session.get(Task.class, id);
            if (task != null) {
                // Remover la tarea de la lista asociada
                ListTasks list = task.getList();
                if (list != null) {
                    list.getTasks().remove(task);
                }
                session.remove(task);
            }
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public Task update(Task task) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.merge(task);
            session.getTransaction().commit();
        }catch(PersistenceException e){
            System.out.println("Error: "+e.getMessage());
            session.getTransaction().rollback();
        }
        session.close();
        return task;
    }
}
