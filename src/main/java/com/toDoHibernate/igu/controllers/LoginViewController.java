package com.toDoHibernate.igu.controllers;

import com.toDoHibernate.igu.dto.UserLoginDTO;
import com.toDoHibernate.persistence.dao.UserDAO;
import com.toDoHibernate.persistence.dao.UserDAOImpl;
import com.toDoHibernate.utilities.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.toDoHibernate.utilities.SwitcherView.switcher;

public class LoginViewController {

    @FXML private TextField txtEmailOrName;
    @FXML private TextField txtPassword;
    private final UserDAO userDAO = new UserDAOImpl();

    @FXML
    void login(ActionEvent event) throws IOException {
        UserLoginDTO user = userDAO.findByEmailOrNickname(txtEmailOrName.getText());
        System.out.println(user);
    }

    @FXML
    public void changeRegisteView(ActionEvent event) throws IOException {
        switcher(event,Paths.REGISTER);
    }
}
