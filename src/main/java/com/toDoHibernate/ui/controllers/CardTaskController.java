package com.toDoHibernate.ui.controllers;

import com.toDoHibernate.persistence.dao.TaskDAO;
import com.toDoHibernate.persistence.dao.TaskDAOImple;
import com.toDoHibernate.persistence.entities.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class CardTaskController {

    @FXML private AnchorPane cardTaskPane;
    @FXML private Label lblTitleTask;
    @FXML private Label lblCompletedTaskLine;
    @FXML private Button btnCompleteTask;
    @FXML private Button btnImportantTask;
    private final TaskDAO taskDAO = new TaskDAOImple();
    private Task task;

    public void initialize(String title, Task task) {
        setTask(task);
        setLabelTitle(title);
        if (!this.task.getCompleted()){
            applyIncompleteTaskStyles();
        }else{
            applyCompleteTaskStyles();
        }
        if (!this.task.getImportant()){
            setUnimportantTaskIcon();
        }else{
            setImportantTaskIcon();
        }
    }

    private void setLabelTitle(String title) {
        lblTitleTask.setText(title);
    }

    private void setTask(Task task) {
        this.task = task;
    }

    @FXML
    private void deleteTask(){
        taskDAO.taskDelete(task.getId());
        if (cardTaskPane.getParent() instanceof VBox vbox) {
            vbox.getChildren().remove(cardTaskPane);
        }
    }

    @FXML
    private void completeTask(){
        if (!this.task.getCompleted()){
            applyCompleteTaskStyles();
            this.task.setCompleted(true);
        }else{
            applyIncompleteTaskStyles();
            this.task.setCompleted(false);
        }
        taskDAO.update(this.task);
    }

    @FXML
    private void markImportantTask(){
        if (!this.task.getImportant()){
            this.task.setImportant(true);
            setImportantTaskIcon();
        }else{
            this.task.setImportant(false);
            setUnimportantTaskIcon();
        }
        taskDAO.update(this.task);
    }

    private void applyCompleteTaskStyles(){
        lblCompletedTaskLine.setPrefWidth(lblTitleTask.getWidth());
        lblCompletedTaskLine.setVisible(true);
        lblTitleTask.getStyleClass().add("cardTaskLabelCompleted");
        btnCompleteTask.getStyleClass().remove("btnCheckTaskIcon");
        btnCompleteTask.getStyleClass().add("buttonCompletedIcon");
    }

    private void applyIncompleteTaskStyles(){
        lblCompletedTaskLine.setVisible(false);
        lblTitleTask.getStyleClass().remove("cardTaskLabelCompleted");
        btnCompleteTask.getStyleClass().remove("buttonCompletedIcon");
        btnCompleteTask.getStyleClass().add("btnCheckTaskIcon");

    }

    private void setImportantTaskIcon(){
        btnImportantTask.getStyleClass().remove("btnUnimportantTaskIcon");
        btnImportantTask.getStyleClass().add("btnImportanTaskIcon");
    }

    private void setUnimportantTaskIcon(){
        btnImportantTask.getStyleClass().remove("btnImportanTaskIcon");
        btnImportantTask.getStyleClass().add("btnUnimportantTaskIcon");
    }
}
