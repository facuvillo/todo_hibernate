package com.toDoHibernate.ui.utilities;


public enum Paths {

    LOGIN("login-view.fxml"),
    REGISTER("register-view.fxml"),
    MAIN_VIEW("main-view.fxml");

    private final View view;

    Paths(String path) {
        this.view = new View(path);
    }

    public View getView() {
        return view;
    }
}
