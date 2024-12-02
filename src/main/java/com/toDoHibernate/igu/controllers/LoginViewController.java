package com.toDoHibernate.igu.controllers;

import com.toDoHibernate.appLogic.security.PasswordService;
import com.toDoHibernate.igu.dto.UserLoginDTO;
import com.toDoHibernate.persistence.dao.UserDAO;
import com.toDoHibernate.persistence.dao.UserDAOImpl;
import com.toDoHibernate.utilities.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.toDoHibernate.utilities.SwitcherView.switcher;

public class LoginViewController {

    @FXML private TextField txtEmailOrName;
    @FXML private Label lblUserNotFound;
    @FXML private TextField txtPassword;
    @FXML private Label lblIncorretPassword;

    private final UserDAO userDAO = new UserDAOImpl();

    @FXML
    void login(ActionEvent event) throws IOException {
        hideLabels();
        UserLoginDTO user = userDAO.findByEmailOrNickname(txtEmailOrName.getText());
        if (user == null) {
            lblUserNotFound.setText("USER NOT FOUND");
            lblUserNotFound.setVisible(true);
            return;
        }
        if (!checkPassword(user.getPassword())){
            lblIncorretPassword.setText("INCORRECT PASSWORD");
            lblIncorretPassword.setVisible(true);
            return;
        }
        switcher(event, Paths.MAIN_VIEW, user);
    }

    @FXML
    public void changeRegisteView(ActionEvent event) throws IOException {
        switcher(event,Paths.REGISTER);
    }

    private boolean checkPassword(String password) {
        return new PasswordService().verifyPassword(txtPassword.getText(), password);
    }

    private void hideLabels(){
        lblUserNotFound.setVisible(false);
        lblIncorretPassword.setVisible(false);
    }
}
