package com.toDoHibernate.utilities;


import com.toDoHibernate.igu.View;

public enum Paths {

    LOGIN("login-view.fxml"),
    REGISTER("register-view.fxml");

    private final View view;

    Paths(String path) {
        this.view = new View(path);
    }

    public View getView() {
        return view;
    }
}
