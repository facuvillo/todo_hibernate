package com.toDoHibernate.igu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardTaskController {

    @FXML private Label lblTitleTask;

    public void setLabelTitle(String title) {
        lblTitleTask.setText(title);
    }

}
