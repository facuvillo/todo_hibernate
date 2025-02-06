package com.toDoHibernate.igu.controllers;

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
    private final TaskDAO taskDAO = new TaskDAOImple();
    private Task task;

    public void setLabelTitle(String title) {
        lblTitleTask.setText(title);
    }

    public void setTask(Task task) {
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
            removeCompleteTaskStyles();
            this.task.setCompleted(false);
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

    private void removeCompleteTaskStyles(){
        lblCompletedTaskLine.setVisible(false);
        lblTitleTask.getStyleClass().remove("cardTaskLabelCompleted");
        btnCompleteTask.getStyleClass().remove("buttonCompletedIcon");
        btnCompleteTask.getStyleClass().add("btnCheckTaskIcon");

    }
}
