package com.toDoHibernate.igu.controllers;

import com.toDoHibernate.igu.dto.UserLoginDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainViewController {

    @FXML private Label lblUserEmail;
    @FXML private Label lblUserName;

    private UserLoginDTO currentUser;


    public void initialize(UserLoginDTO currentUser) {
        setCurrentUser(currentUser);
        setUserLabels();
    }

    private void setUserLabels(){
        lblUserName.setText(this.currentUser.getUserName());
        lblUserEmail.setText(currentUser.getEmail());
    }

    public void setCurrentUser(UserLoginDTO user) {
        this.currentUser = user;
    }



}
