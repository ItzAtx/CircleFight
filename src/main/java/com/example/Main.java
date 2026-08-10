package com.example;

import javafx.animation.AnimationTimer;
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
        Pane root = new Pane(); //Fenetre

        //Conteneur des cercles (arène)
        Pane container = new Pane(); 
        container.setPrefSize(500, 500);
        container.setLayoutX(100);
        container.setLayoutY(300);

        //Dessin de l'arène
        Rectangle walls = new Rectangle(0, 0, 500, 500); //x, y, largeur, hauteur
        walls.setFill(Color.TRANSPARENT);
        walls.setStroke(Color.BLACK);
        walls.setStrokeWidth(10);

        Entity test = new Entity(250, 250, 1, 1, 100, Color.BLUE);

        //Ajout des éléments à la fenêtre
        root.getChildren().add(container);
        container.getChildren().add(walls);
        container.getChildren().add(test.getVisual());

        Scene scene = new Scene(root, 700, 900); //scene

        //stage
        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();

        //Boucle principale du jeu
        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long now){
                test.update(1);
                test.updateVisual(test.getX(), test.getY());
            }
        };

        loop.start();

        

    }

    
}