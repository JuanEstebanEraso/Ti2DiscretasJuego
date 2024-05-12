package com.example.bomberman.model;

import javafx.scene.shape.Rectangle;

public class RangeExplosion{

    private double positionx;
    private double positiony;
    private double explosionwidth;
    private double explosionheight;
    private Rectangle hitbox;

    public RangeExplosion(double posx, double posy, double width, double height){
        this.positionx = positionx;
        this.positiony = positiony;
        this.explosionwidth = explosionwidth;
        this.explosionheight = explosionheight;
        this.hitbox = new Rectangle (positionx, positiony, explosionwidth, explosionheight);

    }


    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public double getPositionx() {
        return positionx;
    }

    public void setPositionx(double positionx) {
        this.positionx = positionx;
    }

    public double getPositiony() {
        return positiony;
    }

    public void setPositiony(double positiony) {
        this.positiony = positiony;
    }

    public double getexplosionWidth() {
        return explosionwidth;
    }
    public void setexplosionWidth(double explosionwidth) {
        this.explosionwidth = explosionwidth;
    }
    public double getexplosionHeight() {
        return explosionheight;
    }
    public void setexplosionHeight(double explosionheight) {
        this.explosionheight = explosionheight;
    }

    public boolean intersects(double x, double y, double width, double height) {
        double thisX = getPositionx();
        double thisY = getPositiony();
        double thisWidth = getexplosionWidth();
        double thisHeight = getexplosionHeight();

        // Verificar si hay intersección
        return thisX < x + width &&
                thisX + thisWidth > x &&
                thisY < y + height &&
                thisY + thisHeight > y;
    }

}
