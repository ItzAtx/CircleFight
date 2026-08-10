package com.example;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Entity {
    double x;
    double y;
    double vx;
    double vy;
    double radius = 30.0;
    int hp;
    Color color;
    Circle visual;

    //constructeur
    Entity(double _x, double _y, double _vx, double _vy, int _hp, Color color){
        x = _x;
        y = _y;
        vx = _vx;
        vy = _vy;
        hp = _hp;

        visual = new Circle(x, y, radius);
        visual.setFill(color);

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
    int getHp(){
        return this.hp;
    }
    Circle getVisual(){
        return this.visual;
    }
    
}
