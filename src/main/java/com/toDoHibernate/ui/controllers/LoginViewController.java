package com.toDoHibernate.ui.controllers;

import com.toDoHibernate.security.PasswordService;
import com.toDoHibernate.persistence.dao.UserDAO;
import com.toDoHibernate.persistence.dao.UserDAOImpl;
import com.toDoHibernate.persistence.entities.User;
import com.toDoHibernate.security.AuthService;
import com.toDoHibernate.ui.utilities.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.toDoHibernate.ui.utilities.SwitcherView.switcher;

public class LoginViewController {

    @FXML private TextField txtEmailOrName;
    @FXML private Label lblUserNotFound;
    @FXML private TextField txtPassword;
    @FXML private Label lblIncorretPassword;

    private final UserDAO userDAO = new UserDAOImpl();

    @FXML
    void login(ActionEvent event) throws IOException {
        hideLabels();
        User user = userDAO.findByEmail(txtEmailOrName.getText());

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

        AuthService authService = new AuthService();
        authService.login(user.getId());

        switcher(event, Paths.MAIN_VIEW);
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
