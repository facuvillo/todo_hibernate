package com.toDoHibernate.igu.controllers;

import com.toDoHibernate.igu.dto.UserLoginDTO;
import com.toDoHibernate.persistence.entities.ListTasks;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainViewController {

    @FXML private Label lblUserEmail;
    @FXML private Label lblUserName;
    @FXML private Label lblListName;
    @FXML private Button btnGeneralList;

    private UserLoginDTO currentUser;

    public void initialize(UserLoginDTO currentUser) {
        setCurrentUser(currentUser);
        setUserLabels();
        setListLabel("General");
        setButtonGroup(btnGeneralList);
    }

    private void setUserLabels(){
        lblUserName.setText(this.currentUser.getUserName());
        lblUserEmail.setText(this.currentUser.getEmail());
    }

    private void setCurrentUser(UserLoginDTO user) {
        this.currentUser = user;
    }

    private void setListLabel(String nameList){
        lblListName.setText(nameList);
    }

    private void setButtonGroup(Button buttonGroup){
        buttonGroup.setDisable(true);
        buttonGroup.getStyleClass().add("current-group");
    }
}
