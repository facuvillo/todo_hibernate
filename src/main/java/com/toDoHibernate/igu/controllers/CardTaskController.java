package com.toDoHibernate.igu.controllers;

import com.toDoHibernate.persistence.dao.TaskDAO;
import com.toDoHibernate.persistence.dao.TaskDAOImple;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class CardTaskController {

    @FXML private AnchorPane cardTaskPane;
    @FXML private Label lblTitleTask;
    private final TaskDAO taskDAO = new TaskDAOImple();
    private Long taskId;

    private static final String RESET = "\u001B[0m";   // Restablece el color
    private static final String ROJO = "\u001B[31m";

    public void setLabelTitle(String title) {
        lblTitleTask.setText(title);
    }

    public void setTaskId(Long id) {
        this.taskId = id;
    }

    @FXML
    private void deleteTask(){

        System.out.println(ROJO + "CHECK: " + taskId + RESET);

        taskDAO.taskDelete(taskId);
        if (cardTaskPane.getParent() instanceof VBox vbox) {
            vbox.getChildren().remove(cardTaskPane);
        }

    }
}
