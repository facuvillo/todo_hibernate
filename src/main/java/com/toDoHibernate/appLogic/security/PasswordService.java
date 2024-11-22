package com.toDoHibernate.appLogic.security;
import org.mindrot.jbcrypt.BCrypt;


public class PasswordService implements hashService{

    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    @Override
    public boolean verifyPassword(String userPassword, String encryptPassword) {
        return BCrypt.checkpw(userPassword,encryptPassword);
    }

    @Override
    public PassowordConditions isValidPassword(String password) {
        if (password.length() < 8) {return PassowordConditions.SHORT;}

        if (!password.matches(".*[A-Z].*")) {return PassowordConditions.CAPS;}

        if (!password.matches(".*\\d.*")) {return PassowordConditions.NUMBERS;}

        //if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {throw new IllegalArgumentException("carácter especial");}

        return PassowordConditions.ALL_GOOD;
    }
}
