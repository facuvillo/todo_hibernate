package com.toDoHibernate.igu.controllers;

import com.toDoHibernate.persistence.dao.ListDAOImple;
import com.toDoHibernate.persistence.dao.TaskDAOImple;
import com.toDoHibernate.persistence.dao.UserDAO;
import com.toDoHibernate.persistence.dao.UserDAOImpl;
import com.toDoHibernate.persistence.entities.ListTasks;
import com.toDoHibernate.persistence.entities.Task;
import com.toDoHibernate.persistence.entities.User;
import javafx.application.Platform;
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
    @FXML private Button btnImportantListTasks;
    @FXML private TextField inputNewTask;
    @FXML private VBox vboxCardsTasks;
    @FXML private VBox vboxNewList;

    private final ListDAOImple listDAO = new ListDAOImple();
    private final TaskDAOImple taskDAO = new TaskDAOImple();
    private final UserDAOImpl userDAO = new UserDAOImpl();
    private User currentUser;
    private ListTasks currentListTask;

    public void initialize(User currentUser) throws IOException {
        setCurrentUser(currentUser);
        setUserLabels();
        setCurrentList(listDAO.findByIdEager(currentUser.getListTasks().getFirst().getId()));
        setCardsTasks();
        setListLabel(currentListTask.getTitle());
        initialSetNewLists();
        //setButtonGroup(btnGeneralList);
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

//    private void setButtonGroup(Button buttonGroup){
//        buttonGroup.setDisable(true);
//        buttonGroup.getStyleClass().add("current-group");
//    }

    @FXML
    private void addNewTask () throws IOException {
        String titleNewTask = this.inputNewTask.getText();
        if(!titleNewTask.isEmpty()){
            Task newTask = new Task(titleNewTask,null);
            newTask.setList(currentListTask);
            currentListTask.getTasks().add(newTask);
            taskDAO.create(newTask);
            setCurrentList(listDAO.findByIdEager(currentUser.getId()));
            addNewCardTask(newTask);
            inputNewTask.requestFocus();
        }
    }

    private void setCardsTasks() throws IOException {

        if (!currentListTask.getTasks().isEmpty()){
            for (Task task : currentListTask.getTasks()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/card-task.fxml"));
                Pane pane = loader.load();
                CardTaskController cardTaskController = loader.getController();
                cardTaskController.initialize(task.getTitle(),task);
                vboxCardsTasks.getChildren().add(pane);
            }
        }
    }

    private void addNewCardTask(Task task) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/card-task.fxml"));
        Pane pane = loader.load();
        CardTaskController cardTaskController = loader.getController();
        cardTaskController.initialize(task.getTitle(),task);
        vboxCardsTasks.getChildren().add(pane);
        inputNewTask.setText("");
    }

    // Change to general list
    @FXML
    private void changeGeneralListTasks() throws IOException {
        if (this.currentListTask.getTitle().equals("General")){
            return;
        }
        if (!vboxCardsTasks.getChildren().isEmpty()){
            vboxCardsTasks.getChildren().clear();
        }
        setCurrentList(listDAO.findByIdEager(currentUser.getListTasks().getFirst().getId()));
        setCardsTasks();
        setListLabel(currentListTask.getTitle());
    }

    // Change to Important List
    @FXML
    private void changeImportantList() throws IOException {
        if (this.currentListTask.getTitle().equals("Importante")){
            return;
        }
        if (!vboxCardsTasks.getChildren().isEmpty()){
            vboxCardsTasks.getChildren().clear();
        }
        this.currentListTask = new ListTasks("Importante");
        this.currentListTask.setTasks(listDAO.findByImportance(true));
        setCardsTasks();
        setListLabel(this.currentListTask.getTitle());
        //setButtonGroup(btnImportantListTasks);
    }

    private void updateCurrentUser(){
        this.currentUser = userDAO.findByIdEager(currentUser.getId());
    }

    // Add new list
    private void initialSetNewLists() throws IOException {
        if (!this.currentUser.getListTasks().isEmpty()){
            for (ListTasks listTasks : this.currentUser.getListTasks()){
                if (!listTasks.getTitle().equals("General")){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/list-card.fxml"));
                    Pane pane = loader.load();
                    NewListCard NewListCard = loader.getController();
                    NewListCard.initialize(this.currentUser.getListTasks().getLast());
                    vboxNewList.getChildren().add(pane);
                }
            }
        }
    }

    private void createNewList(){
        ListTasks newListTasks = new ListTasks("New List");
        this.currentUser.getListTasks().add(newListTasks);
        userDAO.update(this.currentUser);
        updateCurrentUser();
    }

    @FXML
    private void addNewListTasks() throws IOException {
        createNewList();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/list-card.fxml"));
        Pane pane = loader.load();
        NewListCard newListCard = loader.getController();
        newListCard.initialize(this.currentUser.getListTasks().getLast());
        Platform.runLater(() -> newListCard.txtListTitle.requestFocus());
        vboxNewList.getChildren().add(pane);
    }
}
