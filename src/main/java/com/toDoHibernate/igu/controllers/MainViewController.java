package com.toDoHibernate.igu.controllers;

import com.toDoHibernate.persistence.dao.ListDAOImple;
import com.toDoHibernate.persistence.entities.ListTasks;
import com.toDoHibernate.persistence.entities.Task;
import com.toDoHibernate.persistence.entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainViewController {

    @FXML private Label lblUserEmail;
    @FXML private Label lblUserName;
    @FXML private Label lblListName;
    @FXML private Button btnGeneralList;
    @FXML private TextField inputNewTask;
    @FXML private VBox vboxCardsTasks;

    private final ListDAOImple listDAO = new ListDAOImple();
    private User currentUser;
    private ListTasks currentListTask;

    public void initialize(User currentUser) throws IOException {
        setCurrentUser(currentUser);
        setUserLabels();
        setCurrentList(listDAO.findByIdEager(currentUser.getListTasks().getFirst().getId()));
        setCardsTasks();
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

    // Add new task
    @FXML
    private void addNewTask (){
        String titleNewTask = this.inputNewTask.getText();
        Task newTask = new Task(null,titleNewTask,"",null,false);
        currentListTask.getTasks().add(newTask);
        listDAO.update(currentListTask);
    }

    // Set tasks cards
    private void setCardsTasks() throws IOException {

        if (!currentListTask.getTasks().isEmpty()){
            for (Task task : currentListTask.getTasks()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/card-task.fxml"));
                Pane pane = loader.load();
                CardTaskController cardTaskController = loader.getController();
                cardTaskController.setLabelTitle(task.getTitle());
                vboxCardsTasks.getChildren().add(pane);
            }
        }

    }

}
