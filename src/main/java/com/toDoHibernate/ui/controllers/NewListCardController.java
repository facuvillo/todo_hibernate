package com.toDoHibernate.ui.controllers;

import com.toDoHibernate.persistence.dao.ListDAOImple;
import com.toDoHibernate.persistence.dao.UserDAOImpl;
import com.toDoHibernate.persistence.entities.ListTasks;
import com.toDoHibernate.persistence.entities.User;
import com.toDoHibernate.security.AuthenticatedUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class NewListCardController {

    @FXML
    private AnchorPane cardListPane;
    @FXML
    private Label lblTitleList;
    @FXML
    public TextField txtListTitle;
    @FXML
    public Button btnNewList;
    private final ListDAOImple listDAO = new ListDAOImple();
    private final UserDAOImpl userDAO = new UserDAOImpl();
    private ListTasks listTasks;
    private boolean isSaved = false;

    public void initializeNewListCard() {
        txtListTitle.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !isSaved) {
                saveText();
            }
        });

        txtListTitle.setOnAction(event -> {
            saveText();
            isSaved = true;
        });
    }

    public ListTasks getListTasks() {
        return listTasks;
    }

    @FXML
    private void saveText() {
        if (isSaved) return;

        String titleList = txtListTitle.getText();
        if (titleList.isEmpty()) {
            txtListTitle.setText("New List");
        }
        createNewList(titleList);
        setLabel(titleList);

        isSaved = false;
    }

    private void createNewList(String title) {
        this.listTasks = new ListTasks(title);
        AuthenticatedUser.getInstance().getUser().getListTasks().add(this.listTasks);
        userDAO.update(AuthenticatedUser.getInstance().getUser());
        AuthenticatedUser.getInstance().setUser(userDAO.findByIdEager(AuthenticatedUser.getInstance().getUser().getId()));
        this.listTasks = AuthenticatedUser.getInstance().getUser().getListTasks().getLast();
    }

    public void setLabel(String title) {
        lblTitleList.setText(title);
        lblTitleList.setVisible(true);
        txtListTitle.setVisible(false);
    }

    @FXML
    private void deleteListTask() {
        listDAO.delete(this.listTasks.getId());
        if (cardListPane.getParent() instanceof VBox vbox) {
            vbox.getChildren().remove(cardListPane);
        }
    }
}
