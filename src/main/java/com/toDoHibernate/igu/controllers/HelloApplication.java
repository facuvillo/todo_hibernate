package com.toDoHibernate.igu.controllers;


import com.toDoHibernate.utilities.Paths;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/"+Paths.LOGIN.getView().getFileName()));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("HiToDo");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
