package com.toDoHibernate.appLogic.security;
import org.mindrot.jbcrypt.BCrypt;


public class Password implements hashService{

    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    @Override
    public boolean verifyPassword(String userPassword, String encryptPassword) {
        return BCrypt.checkpw(userPassword,encryptPassword);
    }

    @Override
    public boolean isValidPassword(String password) {
        if (password.length() < 8) {throw new IllegalArgumentException("menos 8 caracteres");}

        if (!password.matches(".*[A-Z].*")) {throw new IllegalArgumentException("letra mayúscula");}

        if (!password.matches(".*\\d.*")) {throw new IllegalArgumentException("un número");}

        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {throw new IllegalArgumentException("carácter especial");}

        return true;
    }
}
