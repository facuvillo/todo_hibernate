package com.toDoHibernate.igu.controllers;

import com.toDoHibernate.persistence.entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainViewController {

    @FXML private Label lblUserEmail;
    @FXML private Label lblUserName;
    @FXML private Label lblListName;
    @FXML private Button btnGeneralList;

    private User currentUser;

    public void initialize(User currentUser) {
        setCurrentUser(currentUser);
        System.out.println(currentUser.toString());
        setUserLabels();
        setListLabel("General");
        setButtonGroup(btnGeneralList);
    }

    private void setUserLabels(){
        lblUserName.setText(this.currentUser.getUsername());
        lblUserEmail.setText(this.currentUser.getEmail());
    }

    private void setCurrentUser(User user) {
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
