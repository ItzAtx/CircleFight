package com.example;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Entity {
    double x;
    double y;
    double vx;
    double vy;
    double radius = 30.0;
    int hp;
    Color color;
    Circle circle;
    StackPane visual = new StackPane();
    Text hpText;

    //constructeur
    Entity(double _x, double _y, double _vx, double _vy, int _hp, Color color){
        x = _x;
        y = _y;
        vx = _vx;
        vy = _vy;
        hp = _hp;


        circle = new Circle(0, 0, radius);
        circle.setFill(color);
        hpText = new Text("" + hp);
        visual.getChildren().addAll(circle, hpText);
    }

    //setter
    void setX(double _x){
        this.x = _x;
    }
    void setY(double _y){
        this.y = _y;
    }
    void setVx(double _vx){
        this.vx = _vx;
    }
    void setVy(double _vy){
        this.vy = _vy;
    }
    void setHp(int _hp){
        this.hp = _hp;
    }

    //getter
    double getX(){
        return this.x;
    }
    double getY(){
        return this.y;
    }
    double getVx(){
        return this.vx;
    }
    double getVy(){
        return this.vy;
    }
    double getRadius(){
        return this.radius;
    }
    int getHp(){
        return this.hp;
    }
    StackPane getVisual(){
        return this.visual;
    }

    //update des positions
    void update(double dt){
        this.x += vx * dt;
        this.y += vy * dt;
    }

    //update des visuels selon la position
    void updateVisual(double x, double y){
        visual.setLayoutX(x - radius);
        visual.setLayoutY(y - radius);
        hpText.setText("" + hp);
    }
    
}