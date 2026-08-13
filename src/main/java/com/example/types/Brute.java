package com.example.types;

import com.example.Entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Brute extends Entity{
    Image image = new Image(getClass().getResourceAsStream("/img/hammer.png"));
    ImageView weapon = new ImageView(image);
    
    public Brute(double _x, double _y, double _vx, double _vy, int _hp, Color _color){
        super(_x, _y, _vx, _vy, _hp, _color);
        getVisual().getChildren().add(weapon);
        weapon.setTranslateX(getRadius() * 2); //Déplacement de l'armre au bord du cercle
    }

    @Override
    public void onHit(){
        setDamages(getDamages() + 1);
    }

    @Override
    public void updateWeapon(double dt){
        setAngle(getAngle() + getAttackSpeed() * dt); //Mise à jour de l'angle
        
        //Rotation
        double rotationDegrees = Math.toDegrees(getAngle());
        getVisual().setRotate(rotationDegrees);
    }
}