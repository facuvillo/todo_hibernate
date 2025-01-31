package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.entities.ListTasks;
import com.toDoHibernate.persistence.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ListDAOImple implements ListDAO {

    @Override
    public ListTasks findByIdEager(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<ListTasks> query = session.createQuery("select distinct e from ListTasks e join fetch e.tasks where e.id = :id", ListTasks.class);
        query.setParameter("id", id);
        List<ListTasks> listTasks = query.getResultList();
        if (listTasks.isEmpty()){
            ListTasks result = findById(id);
            result.setTasks(new ArrayList<>());
            session.close();
            return result;
        }
        return listTasks.get(0);
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
