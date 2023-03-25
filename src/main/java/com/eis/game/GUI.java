package com.eis.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage){
        Button btn1 = new Button("Say Hello World");
        StackPane root = new StackPane();
        root.getChildren().add(btn1);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("First JavaFX Application");
        primaryStage.show();
    }
}
