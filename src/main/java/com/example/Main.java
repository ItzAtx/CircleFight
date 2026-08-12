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
        entities.add(new Entity(120, 120, 1.4, 0.9, 100, Color.BLUE));
        entities.add(new Entity(380, 350, -1.1, 1.3, 100, Color.RED));

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
                for (int i = 0; i < entities.size(); i++){
                    for (int j = i + 1; j < entities.size(); j++){
                        Entity current = entities.get(i);
                        Entity e = entities.get(j);


                        //Calcul des vecteurs
                        double dx = e.getX() - current.getX();
                        double dy = e.getY() - current.getY();

                        //Calcul de la distance entre 2 points : √((x2 - x1)² + (y2 - y1)²)
                        double distance = Math.sqrt(dx * dx + dy * dy);

                        //Vecteurs normalisés
                        double nx = dx / distance;
                        double ny = dy / distance;

                        //Calcul de la somme des deux rayons
                        double sumRadius = current.getRadius() + e.getRadius();
                        //Calcul du chevauchement
                        double overlap = sumRadius - distance;

                        //Si il y a un chevauchement alors
                        if (overlap > 0){

                            //Baisse des HP (à améliorer : les deux prennent des degats)
                            e.setHp(e.getHp() - 1);
                            current.setHp(current.getHp() - 1);

                            //Calcul des vitesses relatives
                            double relVx = e.getVx() - current.getVx();
                            double relVy = e.getVy() - current.getVy();

                            //Produit scalaire (à quel point deux vecteurs vont dans la même direction) positif : même direction, négatif  : direction opposées, 0 : perpendiculaire
                            double vitesse = relVx * nx + relVy * ny;

                            //Mise à jour si collision
                            if (vitesse <= 0 ){
                                current.setVx(current.getVx() + vitesse * nx);
                                current.setVy(current.getVy() + vitesse * ny); 
                                e.setVx(e.getVx() - vitesse * nx);
                                e.setVy(e.getVy() - vitesse * ny); 
                            }
                        }
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