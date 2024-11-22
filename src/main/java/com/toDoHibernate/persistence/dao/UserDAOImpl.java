package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.persistence.util.HibernateUtil;
import com.toDoHibernate.persistence.entities.User;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDAOImpl implements UserDAO {


    @Override
    public User findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<User> query = session.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        User user = query.getSingleResult();

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

}
