package com.nanotechnologies.jfxtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Application");
            stage.setScene(scene);
            stage.show() ;
            stage.setResizable(false);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}