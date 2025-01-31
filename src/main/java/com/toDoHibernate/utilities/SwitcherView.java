package com.toDoHibernate.utilities;

import com.toDoHibernate.igu.controllers.MainViewController;
import com.toDoHibernate.persistence.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SwitcherView {

    private static Scene scene;
    private static Stage stage;
    private static Parent root;

    public static void switcher(ActionEvent event, Paths path) throws IOException {

        root = FXMLLoader.load(Objects.requireNonNull(SwitcherView.class.getClassLoader().getResource(path.getView().getFileName())));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void switcher(ActionEvent event, Paths path, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(SwitcherView.class.getClassLoader().getResource(path.getView().getFileName())));
        root = loader.load();
        MainViewController controller = loader.getController();
        controller.initialize(user);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void setup(Stage stage, Scene scene) {
        SwitcherView.stage = stage;
        SwitcherView.scene = scene;
    }
}
