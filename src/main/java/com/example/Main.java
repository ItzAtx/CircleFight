package com.example;

import java.util.ArrayList;
import java.util.List;

import static com.example.Hitbox.handleCircleCollision;
import static com.example.Hitbox.handleWallCollision;
import static com.example.Hitbox.handleWeaponCollision;
import com.example.types.Brute;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    int i, j;

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
        List<Entity> entities = new ArrayList<>();
        entities.add(new Brute(120, 120, 1.4, 0.9, 100, 15, Color.BLUE));
        entities.add(new Brute(380, 350, -1.1, 1.3, 100, 15, Color.RED));

        //Ajout des éléments à la fenêtre
        root.getChildren().add(container);
        container.getChildren().add(walls);
        for (Entity e : entities) {
            container.getChildren().add(e.getVisual());
        }

        Scene scene = new Scene(root, 700, 900); //scene

        //stage
        stage.setTitle("test");
        stage.setScene(scene);
        stage.show();

        //Boucle principale du jeu
        AnimationTimer loop = new AnimationTimer() {
            List<Entity> toRemove = new ArrayList<>();

            @Override
            public void handle(long now){
                //Hitbox cercles
                for (i = 0; i < entities.size(); i++){
                    handleWallCollision(entities.get(i), wallsSize);
                    for (j = i + 1; j < entities.size(); j++){
                        Entity current = entities.get(i);
                        Entity e = entities.get(j);

                        handleCircleCollision(e, current);
                        handleWeaponCollision(e, current);
                        handleWeaponCollision(current, e);
                        
                    }
                }

                //Stockage des noeuds à supprimer
                for (Entity e : entities){
                    if (e.getHp() <= 0){
                        toRemove.add(e);
                    }
                }

                //Suppression des noeuds
                for (Entity dead : toRemove){
                    entities.remove(dead);
                    container.getChildren().remove(dead.getVisual());
                }

                toRemove.clear();

                for (Entity e : entities){
                    //Mise à jours des positions
                    e.update(1);
                    handleWallCollision(entities.get(i), wallsSize);
                    e.updateWeapon(0.05);
                    e.updateVisual(e.getX(), e.getY());
                }

            }
        };

        loop.start();

        

    }

    
}