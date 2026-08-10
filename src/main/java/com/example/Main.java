package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args); //lancement de l'appli
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();

        Rectangle mur = new Rectangle(40, 200, 500, 500); // x, y, largeur, hauteur
        mur.setFill(Color.TRANSPARENT);
        mur.setStroke(Color.RED);
        mur.setStrokeWidth(10);

        root.getChildren().add(mur);

        Scene scene = new Scene(root); //scene (2)
        stage.setMinWidth(600);
        stage.setMinHeight(800);

        //stage (1)
        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();

    }

    
}