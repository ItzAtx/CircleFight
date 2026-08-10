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
        int wallsSize = 500;

        //Conteneur des cercles (arène)
        Pane container = new Pane(); 
        container.setPrefSize(wallsSize, wallsSize);
        container.setLayoutX(100);
        container.setLayoutY(300);

        //Dessin de l'arène
        Rectangle walls = new Rectangle(0, 0, wallsSize, wallsSize); //x, y, largeur, hauteur
        walls.setFill(Color.TRANSPARENT);
        walls.setStroke(Color.BLACK);
        walls.setStrokeWidth(10);

        //Creation des cercles
        Entity c1 = new Entity(150, 300, 1, 1, 100, Color.BLUE);
        Entity c2 = new Entity(300, 300, 1, 1, 100, Color.RED);

        //Ajout des éléments à la fenêtre
        root.getChildren().add(container);
        container.getChildren().add(walls);
        container.getChildren().add(c1.getVisual());
        container.getChildren().add(c2.getVisual());

        Scene scene = new Scene(root, 700, 900); //scene

        //stage
        stage.setTitle("test");
        stage.setScene(scene);
        stage.show();

        //Boucle principale du jeu
        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long now){
                //Hitbox murs
                if (c1.getX() - c1.getRadius() <= 0 || c1.getX() + c1.getRadius() >= wallsSize){
                    c1.setVx(- c1.getVx());
                }
                if (c1.getY() - c1.getRadius() <= 0 || c1.getY() + c1.getRadius() >= wallsSize){
                    c1.setVy(- c1.getVy());
                }

                if (c2.getX() - c2.getRadius() <= 0 || c2.getX() + c2.getRadius() >= wallsSize){
                    c2.setVx(- c2.getVx());
                }
                if (c2.getY() - c2.getRadius() <= 0 || c2.getY() + c2.getRadius() >= wallsSize){
                    c2.setVy(- c2.getVy());
                }

                //Mise à jours des positions
                c1.update(1);
                c1.updateVisual(c1.getX(), c1.getY());
                c2.update(1);
                c2.updateVisual(c2.getX(), c2.getY());

            }
        };

        loop.start();

        

    }

    
}