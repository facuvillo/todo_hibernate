package com.toDoHibernate.igu.controllers;

import com.toDoHibernate.persistence.dao.TaskDAO;
import com.toDoHibernate.persistence.dao.TaskDAOImple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.print.DocFlavor;

public class CardTaskController {

    @FXML private AnchorPane cardTaskPane;
    @FXML private Label lblTitleTask;
    @FXML private Label lblCompletedTaskLine;
    @FXML private Button btnCompleteTask;
    private final TaskDAO taskDAO = new TaskDAOImple();
    private Long taskId;

    public void setLabelTitle(String title) {
        lblTitleTask.setText(title);
    }

    public void setTaskId(Long id) {
        this.taskId = id;
    }

    @FXML
    private void deleteTask(){
        taskDAO.taskDelete(taskId);
        if (cardTaskPane.getParent() instanceof VBox vbox) {
            vbox.getChildren().remove(cardTaskPane);
        }
    }

    @FXML
    private void completeTask(){
        lblCompletedTaskLine.setPrefWidth(lblTitleTask.getWidth());
        lblCompletedTaskLine.setVisible(true);
        lblTitleTask.setStyle("-fx-text-fill: #737373;");
        btnCompleteTask.getStyleClass().remove("btnCheckTaskIcon");
        btnCompleteTask.getStyleClass().add("buttonCompletedIcon");
    }
}
