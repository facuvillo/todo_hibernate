package com.toDoHibernate.igu.controllers;

import com.toDoHibernate.persistence.dao.ListDAOImple;
import com.toDoHibernate.persistence.entities.ListTasks;
import com.toDoHibernate.persistence.entities.Task;
import com.toDoHibernate.persistence.entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Date;

public class MainViewController {

    @FXML private Label lblUserEmail;
    @FXML private Label lblUserName;
    @FXML private Label lblListName;
    @FXML private Button btnGeneralList;

    private ListDAOImple listDAO = new ListDAOImple();
    private User currentUser;
    private ListTasks currentListTask;

    public void initialize(User currentUser) {
        setCurrentUser(currentUser);
        setUserLabels();
        setCurrentList(listDAO.findByIdEager(currentUser.getListTasks().getFirst().getId()));
        setListLabel(currentListTask.getTitle());
        setButtonGroup(btnGeneralList);
    }

    private void setUserLabels(){
        lblUserName.setText(this.currentUser.getUsername());
        lblUserEmail.setText(this.currentUser.getEmail());
    }

    private void setCurrentUser(User user) {
        this.currentUser = user;
    }

    private void setCurrentList(ListTasks currentListTask) {
        this.currentListTask = currentListTask;
    }

    private void setListLabel(String nameList){
        lblListName.setText(nameList);
    }

    private void setButtonGroup(Button buttonGroup){
        buttonGroup.setDisable(true);
        buttonGroup.getStyleClass().add("current-group");
    }
}
