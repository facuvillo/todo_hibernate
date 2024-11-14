package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.entities.User;
import com.toDoHibernate.persistence.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<User> query = session.createQuery("from User", User.class);
        List<User> users = query.list();
        session.close();
        return users;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        // 1
        Session session = HibernateUtil.getSessionFactory().openSession();
        // 2
        Query<User> query = session.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        User user = query.getSingleResult();
        // 3
        session.close();
        // 4
        return user;
    }

    @Override
    public User create(User user) {
        // 1
        Session session = HibernateUtil.getSessionFactory().openSession();
        // 2
        try{
            session.beginTransaction();
            session.persist(user);
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

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}
