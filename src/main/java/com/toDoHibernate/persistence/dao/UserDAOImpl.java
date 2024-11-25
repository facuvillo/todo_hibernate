package com.toDoHibernate.persistence.dao;

import com.toDoHibernate.igu.dto.UserLoginDTO;
import com.toDoHibernate.persistence.util.HibernateUtil;
import com.toDoHibernate.persistence.entities.User;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
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
    public UserLoginDTO findByEmailOrNickname(String emailOrUsername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        NativeQuery<UserLoginDTO> query = session.createNativeQuery("select email,nickname,password from users where email = :emailOrNickname or nickname = :emailOrNickname", UserLoginDTO.class);
        query.setParameter("emailOrNickname", emailOrUsername);
        try{
            UserLoginDTO userLoginDTO = query.getSingleResult();
            session.close();
            return userLoginDTO;
        } catch (Exception e) {
            session.close();
            return null;
        }
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
