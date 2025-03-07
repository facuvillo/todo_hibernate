package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.config.HibernateUtil;
import com.toDoHibernate.persistence.entities.User;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class UserDAOImpl implements UserDAO {


    @Override
    public User findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        NativeQuery<User> query = session.createNativeQuery("select user_id,email,username,password from users where email = :email", User.class);
        query.setParameter("email", email);
        try{
            User user = query.getSingleResult();
            session.close();
            return user;
        } catch (Exception e) {
            session.close();
            return null;
        }
    }

    @Override
    public User findByIdEager(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<User> query = session.createQuery("select distinct e from User e join fetch e.listTasks where e.id = :id", User.class);
        query.setParameter("id", id);
        User  user = query.getSingleResult();
        session.close();
        return user;
    }

    @Override
    public User create(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }catch(PersistenceException e){
            System.out.println("Error: "+e.getMessage());
            session.getTransaction().rollback();
        }

        session.close();

        return user;
    }

    @Override
    public User update(User user) {
        // 1
        Session session = HibernateUtil.getSessionFactory().openSession();
        // 2
        try{
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        }catch(PersistenceException e){
            System.out.println("Error: "+e.getMessage());
            session.getTransaction().rollback();
        }
        // 3
        session.close();
        // 4
        return user;
    }
}
