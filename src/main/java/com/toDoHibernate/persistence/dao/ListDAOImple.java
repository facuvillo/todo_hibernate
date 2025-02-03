package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.entities.ListTasks;
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
        // 1
        Session session = HibernateUtil.getSessionFactory().openSession();
        // 2
        ListTasks result = session.find(ListTasks.class, id);
        // 3
        session.close();
        //4
        return result;
    }

    @Override
    public ListTasks update(ListTasks listTasks) {
        // 1
        Session session = HibernateUtil.getSessionFactory().openSession();
        // 2
        try{
            session.beginTransaction();
            session.merge(listTasks);
            session.getTransaction().commit();
        }catch(PersistenceException e){
            System.out.println("Error: "+e.getMessage());
            session.getTransaction().rollback();
        }
        // 3
        session.close();
        // 4
        return listTasks;
    }
}
