package com.toDoHibernate.igu.controllers;

import com.toDoHibernate.persistence.dao.ListDAOImple;
import com.toDoHibernate.persistence.entities.ListTasks;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class NewListCard {

    @FXML private AnchorPane cardListPane;
    @FXML public TextField txtListTitle;
    private final ListDAOImple listDAO = new ListDAOImple();
    private ListTasks listTasks;

    public void initialize(ListTasks listTasks) {
        setListTasks(listTasks);
        setTextListTitle(listTasks.getTitle());
        txtListTitle.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                saveText(txtListTitle);
            }
        });
        txtListTitle.setOnMouseClicked(event -> {

        });
    }

    private void setListTasks(ListTasks listTasks) {
        this.listTasks = listTasks;
    }

    private void setTextListTitle(String titleList) {
        this.txtListTitle.setPromptText(titleList);
    }

    private void saveText(TextField textField) {
        if (textField.isEditable()) {
            String text = textField.getText().trim();
            if (text.isEmpty()) {
                text = textField.getPromptText(); // Si no escribió nada, usar el placeholder
            }
            textField.setText(text);
            textField.setEditable(false); // Bloquear edición
            textField.isFocused();
        }
    }

    @FXML
    private void deleteListTask(){
        listDAO.delete(this.listTasks.getId());
        if (cardListPane.getParent() instanceof VBox vbox) {
            vbox.getChildren().remove(cardListPane);
        }
    }


}
