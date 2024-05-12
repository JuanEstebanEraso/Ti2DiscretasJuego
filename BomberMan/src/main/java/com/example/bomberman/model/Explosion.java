package com.example.bomberman.model;

import java.util.ArrayList;

import com.example.bomberman.screens.ScreenA;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Explosion extends Position {


    private  Canvas canvas;
    private long lastTime;
    private static final long DAMAGE_INTERVAL = 2000;
    private BomberMan avatar;

    public Explosion(BomberMan avatar, double x, double y, Canvas canvas, double width, double height) {
        super(x, y, canvas);
        this.avatar = avatar;
        this.canvas = canvas;

        this.lastTime = System.currentTimeMillis();

    }

    public void paint(ArrayList<Image> bombas) {

        drawBombs(bombas);
    }

    private void drawBombs(ArrayList<Image> bombas) {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (Image bomba : bombas) {

            double x = 100;
            double y = 100;

            gc.drawImage(bomba, x, y);
        }
    }




    private boolean checkCollisionWithObject(double objectX, double objectY, double objectWidth,
            double objectHeight) {

        double explosionX = getPosition().getX();
        double explosionY = getPosition().getY();



        return explosionX < objectX + objectWidth &&

                explosionY < objectY + objectHeight;

    }

    // ----
    private void collisionBetweenExplotionAndEnemy() {
        long currentTime = System.currentTimeMillis();
        for (int i = ScreenA.shadows.size() - 1; i >= 0; i--) {
            Enemigos enemy = ScreenA.shadows.get(i);
            if (checkCollisionWithObject(enemy.getPosition().getX(), enemy.getPosition().getY(),
                    enemy.getImageWidth(), enemy.getImageHeight())) {
                ScreenA.shadows.get(i).die();
                if (currentTime - lastTime >= 100000) {
                    ScreenA.shadows.remove(i);
                    lastTime = currentTime;
                }
            }
        }
    }



}
