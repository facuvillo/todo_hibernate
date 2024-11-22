package com.toDoHibernate.appLogic.security;

public interface hashService {
    String hashPassword(String password);
    boolean verifyPassword(String userPassword, String encryptPassword);
    PassowordConditions isValidPassword(String password);}
