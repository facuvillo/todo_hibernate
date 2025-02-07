package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.entities.ListTasks;
import com.toDoHibernate.persistence.entities.Task;
import com.toDoHibernate.persistence.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ListDAOImple implements ListDAO {

    @Override
    public ListTasks findByIdEager(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Usar LEFT JOIN FETCH para cargar tareas incluso si no existen
            Query<ListTasks> query = session.createQuery(
                    "SELECT DISTINCT e FROM ListTasks e LEFT JOIN FETCH e.tasks WHERE e.id = :id",
                    ListTasks.class
            );
            query.setParameter("id", id);
            List<ListTasks> listTasks = query.getResultList();

            return listTasks.isEmpty() ? null : listTasks.get(0);
        } finally {
            session.close();
        }
    }

    @Override
    public ListTasks findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ListTasks result = session.find(ListTasks.class, id);
        session.close();
        return result;
    }

    @Override
    public ListTasks update(ListTasks listTasks) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.merge(listTasks);
            session.getTransaction().commit();
        }catch(PersistenceException e){
            System.out.println("Error: "+e.getMessage());
            session.getTransaction().rollback();
        }
        session.close();
        return listTasks;
    }

    @Override
    public List<Task> findByImportance(Boolean isImportant) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("from Task where isImportant = :isImporant", Task.class);
        query.setParameter("isImporant", isImportant);
        List<Task> importantTasks = query.list();
        session.close();
        return importantTasks;
    }

    @Override
    public ListTasks create(ListTasks listTasks) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.persist(listTasks);
            session.getTransaction().commit();
        }catch(PersistenceException e){
            System.out.println("Error: "+e.getMessage());
            session.getTransaction().rollback();
        }
        session.close();
        return listTasks;
    }

    @Override
    public boolean delete(Long id) {
        // 1
        Session session = HibernateUtil.getSessionFactory().openSession();
        // 2
        try{
            session.beginTransaction();
            ListTasks listTasks = this.findById(id);
            session.remove(listTasks);
            session.getTransaction().commit();
        }catch(PersistenceException e){
            System.out.println("Error: "+e.getMessage());
            session.getTransaction().rollback();
            return false;
        }finally {
            // 3
            session.close();
        }
        // 4
        return true;
    }
}
