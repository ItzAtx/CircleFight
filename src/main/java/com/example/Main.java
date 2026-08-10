package com.example;

import java.util.ArrayList;
import java.util.List;

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
        List<Entity> entities = new ArrayList<>();
        entities.add(new Entity(150, 300, 0, 1, 100, Color.BLUE));
        entities.add(new Entity(150, 150, 0, 1, 100, Color.RED));

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
            @Override
            public void handle(long now){

                //Hitbox cercles
                //Calcul des vecteurs
                double dx = entities.get(1).getX() - entities.get(0).getX();
                double dy = entities.get(1).getY() - entities.get(0).getY();
                //Calcul de la distance entre 2 points : √((x2 - x1)² + (y2 - y1)²)
                double distance = Math.sqrt(dx * dx + dy * dy);
                //Vecteurs normalisés
                double nx = dx / distance;
                double ny = dy / distance;
                //Calcul de la somme des deux rayons
                double sumRadius = entities.get(0).getRadius() + entities.get(1).getRadius();
                //Calcul du chevauchement (pour pouvoir coller les cercles à la limite)
                double overlap = sumRadius - distance;

                //Si il y a un chevauchement alors
                if (overlap > 0){

                    //Calcul des vitesses relatives
                    double relVx = entities.get(1).getVx() - entities.get(0).getVx();
                    double relVy = entities.get(1).getVy() - entities.get(0).getVy();

                    //Produit scalaire (à quel point deux vecteurs vont dans la même direction) positif : même direction, négatif  : direction opposées, 0 : perpendiculaire
                    double vitesse = relVx * nx + relVy * ny;

                    //Mise à jour si collision
                    if (vitesse <= 0 ){
                        entities.get(0).setVx(entities.get(0).getVx() + vitesse * nx);
                        entities.get(0).setVy(entities.get(0).getVy() + vitesse * ny); 
                        entities.get(1).setVx(entities.get(1).getVx() - vitesse * nx);
                        entities.get(1).setVy(entities.get(1).getVy() - vitesse * ny); 
                    }
                }

                for (Entity e : entities){
                    //Hitbox murs
                    if (e.getX() - e.getRadius() <= 0 || e.getX() + e.getRadius() >= wallsSize){
                        e.setVx(- e.getVx());
                    }
                    if (e.getY() - e.getRadius() <= 0 || e.getY() + e.getRadius() >= wallsSize){
                        e.setVy(- e.getVy());
                    }

                    //Mise à jours des positions
                    e.update(1);
                    e.updateVisual(e.getX(), e.getY());
                }

            }
        };

        loop.start();

        

    }

    
}