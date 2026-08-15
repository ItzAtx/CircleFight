package com.example;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


abstract public class Entity {
    //Coordonnées/Vitesse + vitesse bonus par knockback/Angle de l'arme
    double x;
    double y;
    double vx;
    double vy;
    double knockbackVx = 0;
    double knockbackVy = 0;
    double angle = 0;
    
    //Caractéristique
    double radius = 30.0;
    int hp;
    int damages = 1;
    double attackSpeed = 1;
    double knockbackForce;

    //Test si déjà touché
    boolean weaponHit;
    
    //Visuel
    Circle circle;
    StackPane visual = new StackPane();
    Text hpText;

    //constructeur
    public Entity(double _x, double _y, double _vx, double _vy, int _hp, double _knockbackForce, Color color){
        x = _x;
        y = _y;
        vx = _vx;
        vy = _vy;
        hp = _hp;
        knockbackForce = _knockbackForce;


        circle = new Circle(0, 0, radius);
        circle.setFill(color);
        hpText = new Text("" + hp);
        visual.getChildren().addAll(circle, hpText);
    }

    //setter
    public void setX(double _x){
        this.x = _x;
    }   
    public void setY(double _y){
        this.y = _y;
    }
    public void setVx(double _vx){
        this.vx = _vx;
    }
    public void setVy(double _vy){
        this.vy = _vy;
    }
    public void setHp(int _hp){
        this.hp = _hp;
    }
    public void setDamages(int _damages){
        this.damages = _damages;
    }
    public void setAngle(double _angle){
        this.angle = _angle;
    }
    public void setAttackSpeed(double _attackSpeed){
        this.attackSpeed = _attackSpeed;
    }
    public void setWeaponHit(boolean _weaponHit) {
        this.weaponHit = _weaponHit;
    }
    public void setKnockbackForce(double _knockbackForce) {
        this.knockbackForce = _knockbackForce;
    }
    public void setKnockbackVx(double _knockbackVx) {
        this.knockbackVx = _knockbackVx;
    }
    public void setKnockbackVy(double _knockbackVy) {
        this.knockbackVy = _knockbackVy;
    }

    //getter
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getVx(){
        return this.vx;
    }
    public double getVy(){
        return this.vy;
    }
    public double getRadius(){
        return this.radius;
    }
    public int getHp(){
        return this.hp;
    }
    public int getDamages(){
        return this.damages;
    }
    public double getAngle(){
        return this.angle;
    }
    public double getAttackSpeed() {
        return attackSpeed;
    }
    public StackPane getVisual(){
        return this.visual;
    }
    public double getWeaponX(){
        double weaponDistance = getRadius() * 2;
        return getX() + weaponDistance * Math.cos(getAngle());
    }
    public double getWeaponY(){
        double weaponDistance = getRadius() * 2;
        return getY() + weaponDistance * Math.sin(getAngle());
    }
    public boolean getWeaponHit() {
        return weaponHit;
    }
    public double getKnockbackForce() {
        return knockbackForce;
    }
    public double getKnockbackVx() {
        return knockbackVx;
    }
    public double getKnockbackVy() {
        return knockbackVy;
    }


    //update des positions
    public void update(double dt){
        this.x += (vx + knockbackVx) * dt;
        this.y += (vy + knockbackVy) * dt;
        
        this.knockbackVx *= 0.99;
        this.knockbackVy *= 0.99;
    }

    //update des visuels selon la position
    public void updateVisual(double x, double y){
        visual.setLayoutX(x - radius);
        visual.setLayoutY(y - radius);
        hpText.setText("" + hp);
    }

    public void bounceWeapon(){
        setAttackSpeed((-getAttackSpeed()));
    }

    abstract public void onHit();
    abstract public void updateWeapon(double dt);

    

    
    
}