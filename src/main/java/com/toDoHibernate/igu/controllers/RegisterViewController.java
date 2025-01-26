package com.toDoHibernate.igu.controllers;


import com.toDoHibernate.appLogic.security.PassowordConditions;
import com.toDoHibernate.appLogic.security.PasswordService;
import com.toDoHibernate.persistence.dao.UserDAO;
import com.toDoHibernate.persistence.dao.UserDAOImpl;

import com.toDoHibernate.persistence.entities.ListTasks;
import com.toDoHibernate.persistence.entities.User;
import com.toDoHibernate.utilities.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.toDoHibernate.utilities.SwitcherView.switcher;

public class RegisterViewController {

    @FXML private TextField txtEmail;
    @FXML private TextField txtNickname;
    @FXML private TextField txtPassword;
    @FXML private TextField txtConfirmPassword;
    @FXML private Label lblEmailValid;
    @FXML private Label lblPasswordConditions;
    @FXML private Label lblLongUsername;
    @FXML private Label lblConfirmPassword;

    private final UserDAO userDAO = new UserDAOImpl();
    PasswordService passwordService = new PasswordService();

    boolean searchEmail(String email){
        User userDB = this.userDAO.findByEmail(email);
        return userDB != null;
    }

    boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher match = pattern.matcher(email);

        if (email.isEmpty()){
            lblEmailValid.setText("ENTER EMAIL");
            lblEmailValid.setVisible(true);
            return false;
        }
        try{
            if (searchEmail(email)){
                lblEmailValid.setText("THE EMAIL HAS ALREADY BEEN REGISTERED");
                lblEmailValid.setVisible(true);
                txtEmail.setText("");
                txtEmail.requestFocus();
                return false;
            }
        } catch (Exception _) {}


        if (!match.find()){
            lblEmailValid.setText("INVALID EMAIL");
            lblEmailValid.setVisible(true);
            return false;
        }
        return true;
    }

    boolean validatePassword(String password){
        PassowordConditions condition = passwordService.isValidPassword(password);
        switch (condition){
            case CAPS:
                lblPasswordConditions.setText("AT LEAST ONE UPPERCASE LETTER");
                lblPasswordConditions.setVisible(true);
                break;
            case SHORT:
                lblPasswordConditions.setText("AT LEAST 8 CHARACTERS");
                lblPasswordConditions.setVisible(true);
                break;
            case NUMBERS:
                lblPasswordConditions.setText("AT LEAST ONE NUMBER");
                lblPasswordConditions.setVisible(true);
                break;
        }
        return condition == PassowordConditions.ALL_GOOD;
    }

    boolean validateNickname(String nickname){
        if (nickname.isEmpty()){
            lblLongUsername.setText("ENTER NICKNAME");
            lblLongUsername.setVisible(true);
            return false;
        }
        if (nickname.length() > 20){
            lblLongUsername.setText("LESS THAN  20 CHARACTERS");
            lblLongUsername.setVisible(true);
            return false;
        }
        return true;
    }

    boolean validateConfirmPassword(String confirmPassword, String password){
        if (confirmPassword.isEmpty() || !confirmPassword.equals(password)){
            lblConfirmPassword.setText("PASSWORD NOT MATCHED");
            lblConfirmPassword.setVisible(true);
            return false;
        }
        return true;
    }



    @FXML
    void registerUser(ActionEvent event) throws IOException {
        hideLabels();

        String email = txtEmail.getText().toLowerCase();
        String nickname = txtNickname.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        // TODO Revisar si se puede hacer de una forma más eficiente

        if (!validateEmail(email)){
            return;
        }

        if (!validateNickname(nickname)){
            return;
        }

        if (!validatePassword(password)){
            return;
        }

        if (!validateConfirmPassword(confirmPassword, password)){
            return;
        }

        User newUser = new User(null, nickname, email, passwordService.hashPassword(password));
        setListsInicitial(newUser);
        try{
            userDAO.create(newUser);
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
        }

        switcher(event, Paths.LOGIN);
    }

    private void hideLabels(){
        lblEmailValid.setVisible(false);
        lblLongUsername.setVisible(false);
        lblPasswordConditions.setVisible(false);
        lblConfirmPassword.setVisible(false);
    }

    @FXML
    void switchLoginView(ActionEvent event) throws IOException {
        switcher(event, Paths.LOGIN);
    }

    private void setListsInicitial(User user){
        ListTasks generalList = new ListTasks(null,"listaGeneral",null);
        user.getListTasks().add(generalList);
    }
}
