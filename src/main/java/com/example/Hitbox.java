package com.example;

public class Hitbox{

    static double calculateDx(Entity e, Entity current){
        //Calcul des vecteurs
        return e.getX() - current.getX();
    }

    static double calculateDy(Entity e, Entity current){
        //Calcul des vecteurs
        return e.getY() - current.getY();
    }

    static double calculateDistance(double dx, double dy){
        //Calcul de la distance entre 2 points : √((x2 - x1)² + (y2 - y1)²)
        return Math.sqrt(dx * dx + dy * dy);
    }

    static double calculateNx(double dx, double distance){
        //Vecteurs normalisés
        return dx / distance;
    }

    static double calculateNy(double dy, double distance){
        //Vecteurs normalisés
        return dy / distance;
    }

    static double calculateSumRadius(Entity e, Entity current){
        //Calcul de la somme des deux rayons
        return current.getRadius() + e.getRadius();
    }

    static double calculateOverlap(Entity e, Entity current){
        //Calcul du chevauchement

        double radius = calculateSumRadius(e, current);
        double distance = calculateDistance(calculateDx(e, current), calculateDy(e, current));
        return radius - distance;
    }

    static void handleCircleCollision(Entity e, Entity current){
        double overlap = calculateOverlap(e, current);

        //Si il y a un chevauchement alors
        if (overlap > 0){

            double dx = calculateDx(e, current);
            double dy = calculateDy(e, current);
            double distance = calculateDistance(dx, dy);

            double nx = calculateNx(dx, distance);
            double ny = calculateNy(dy, distance);

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
                e.setKnockbackVx(e.getKnockbackVx() - vitesse * nx);
                e.setKnockbackVy(e.getKnockbackVy() - vitesse * ny);
            }
        }
    }

    static double calculateWeaponDx(Entity e, Entity current){
        return e.getX() - current.getWeaponX();
    }

    static double calculateWeaponDy(Entity e, Entity current){
        return e.getY() - current.getWeaponY();
    }

    static void handleWeaponCollision(Entity e, Entity current){
        double weaponDx = calculateWeaponDx(e, current);
        double weaponDy = calculateWeaponDy(e, current);
        double weaponDistance = calculateDistance(weaponDx, weaponDy);

        //Vecteurs normalisés
        double weaponNx = calculateNx(weaponDx, weaponDistance);
        double weaponNy = calculateNy(weaponDy, weaponDistance);

        //Si l'arme est dans le cercle alors isTouchingNow prend true
        boolean isTouchingNow = (weaponDistance <= e.getRadius() + 15); // +15 POUR AUGMENTER HITBOX A REVOIR

        //Si l'arme touche et qu'elle n'as pas deja touché dans la même frame
        if (isTouchingNow && !current.getWeaponHit()){
            //Mise à jour des hp
            e.setHp(e.getHp());
            current.onHit();
            current.bounceWeapon();

            //Knockback
            e.setKnockbackVx(current.getKnockbackForce() * weaponNx);
            e.setKnockbackVy(current.getKnockbackForce() * weaponNy);
        }

        current.setWeaponHit(isTouchingNow); //L'arme a déjà touchée sur cette frame
    }

    static void handleWallCollision(Entity e, int wallsSize){
        //Gauche
        if (e.getX() - e.getRadius() <= 0){
            e.setX(e.getRadius());
            e.setVx(-e.getVx());
            e.setKnockbackVx(-e.getKnockbackVx());
        }
        //Droit
        if (e.getX() + e.getRadius() >= wallsSize){
            e.setX(wallsSize - e.getRadius());
            e.setVx(-e.getVx());
            e.setKnockbackVx(-e.getKnockbackVx());
        }
        //Haut
        if (e.getY() - e.getRadius() <= 0){
            e.setY(e.getRadius());
            e.setVy(-e.getVy());
            e.setKnockbackVy(-e.getKnockbackVy());
        }
        //Bas
        if (e.getY() + e.getRadius() >= wallsSize){
            e.setY(wallsSize - e.getRadius());
            e.setVy(-e.getVy());
            e.setKnockbackVy(-e.getKnockbackVy());
        }
    }

    
}