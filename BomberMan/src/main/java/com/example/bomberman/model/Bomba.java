package com.example.bomberman.model;

import com.example.bomberman.screens.ScreenA;
import com.example.bomberman.screens.ScreenB;
import com.example.bomberman.screens.ScreenC;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.*;

import static com.example.bomberman.screens.ScreenA.bomberMan;
import static com.example.bomberman.screens.ScreenA.bombermans;


public class Bomba {

    private final Punto punto;

    private ArrayList<Image> bombIdle;
    private ArrayList<Image> explosionUp;
    private ArrayList<Image> explosionDown;
    private ArrayList<Image> explosionLeft;
    private ArrayList<Image> explosionRight;
    private ArrayList<Image> explosionCenter;
    private int frame;
    private Canvas canvas;
    private GraphicsContext graphicsContext;

    private Image bombImage;
    private Image explosionImage;
    private ArrayList<RangeExplosion> expRadio = new ArrayList<>();
    private List<ObstacleToBreak> obstaclesToBreak;
    private ArrayList<Obstacle> obstacles;




    public Bomba(Canvas canvas, Punto punto, List<ObstacleToBreak> obstaclesToBreak,ArrayList<Obstacle> obstacles, ArrayList<Enemigos> enemigos) {
        this.canvas = canvas;
        this.punto = punto;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.obstaclesToBreak = obstaclesToBreak;
        this.obstacles = obstacles;





        this.bombImage = new Image(getClass().getResourceAsStream("/EXPLOSION/BOOM_00.png"));
        this.explosionImage = new Image(getClass().getResourceAsStream("/EXPLOSION/BOOM_01.png"));


        bombIdle = new ArrayList<>();
        explosionUp = new ArrayList<>();
        explosionDown = new ArrayList<>();
        explosionLeft = new ArrayList<>();
        explosionRight = new ArrayList<>();
        explosionCenter = new ArrayList<>();


        bombIdle.add(new Image(getClass().getResourceAsStream("/EXPLOSION/BOOM_00.png")));

        for (int i = 1; i <= 4; i++) {
            Image image = new Image(getClass().getResourceAsStream("/EXPLOSION/BOOM_0"+i+".png"));
            explosionUp.add(image);
        }

        for (int i = 1; i <= 4; i++) {
            Image image = new Image(getClass().getResourceAsStream("/EXPLOSION/BOOM_0"+i+".png"));
            explosionCenter.add(image);
        }

        for (int i = 1; i <= 4; i++) {
            Image image = new Image(getClass().getResourceAsStream("/EXPLOSION/BOOM_0"+i+".png"));
            explosionLeft.add(image);
        }

        for (int i = 1; i <= 4; i++) {
            Image image = new Image(getClass().getResourceAsStream("/EXPLOSION/BOOM_0"+i+".png"));
            explosionRight.add(image);
        }

        for (int i = 1; i <= 4; i++) {
            Image image = new Image(getClass().getResourceAsStream("/EXPLOSION/BOOM_0"+i+".png"));
            explosionDown.add(image);
        }

    }
    public Punto getCentralPosition() {
        double centerX = (punto.getX() + explosionImage.getWidth() / 2)-190;
        double centerY = (punto.getY() + explosionImage.getHeight() / 2)-190;
        return new Punto(centerX, centerY);
    }

    public double getPositionX() {
        return punto.getX();
    }

    public double getPositionY() {
        return punto.getY();
    }




    public void explode() {
        Timer timer = new Timer();
        int frameDelay = 50; // milisegundos entre cada frame
        int totalFrames = 4; // número total de frames de explosión
        timer.schedule(new TimerTask() {
            int currentFrame = 0;

            @Override
            public void run() {
                Platform.runLater(() -> {
                    double x = getPositionX();
                    double y = getPositionY();

                    Punto punto = new Punto(x, y);

                    // Dibujar la animación de la explosión si no choca con un obstáculo
                    graphicsContext.drawImage(explosionCenter.get(currentFrame), punto.getX()+10, punto.getY()+10, 40, 40);
                    expRadio.add(new RangeExplosion(punto.getX(), punto.getY(), 40, 40));

                    graphicsContext.drawImage(explosionCenter.get(currentFrame), punto.getX()+10, punto.getY()+10, 40, 40);
                    expRadio.add(new RangeExplosion(punto.getX(), punto.getY(), 40, 40));

                    graphicsContext.drawImage(explosionUp.get(currentFrame), punto.getX()+10, punto.getY() - 30, 40, 40);
                    expRadio.add(new RangeExplosion(punto.getX(), punto.getY() - 40, 40, 40));

                    graphicsContext.drawImage(explosionDown.get(currentFrame), punto.getX()+10, punto.getY() + 50, 40, 40);
                    expRadio.add(new RangeExplosion(punto.getX(), punto.getY() + 40, 40, 40));

                    graphicsContext.drawImage(explosionLeft.get(currentFrame), punto.getX() - 30, punto.getY()+10, 40, 40);
                    expRadio.add(new RangeExplosion(punto.getX() - 40, punto.getY(), 40, 40));

                    graphicsContext.drawImage(explosionRight.get(currentFrame), punto.getX() + 70, punto.getY()+10, 40, 40);
                    expRadio.add(new RangeExplosion(punto.getX() + 80, punto.getY()+20, 40, 40));
                    currentFrame++;

                    if (currentFrame == totalFrames) {
                        timer.cancel();
                        checkCollisionWithObstaclesToBreak();
                        checkCollisionWithEnemiesToKill();
                        checkCollisionWithEnemiesToKillB();
                        checkCollisionWithEnemiesToKillC();
                        checkCollisionWithBombExplosion();

                    }
                });
            }
        }, 0, frameDelay);
    }


    private void checkCollisionWithObstaclesToBreak() {
        double explosionX = getPositionX();
        double explosionY = getPositionY();

        double explosionSize =100;

        List<ObstacleToBreak> obstaclesToRemove = new ArrayList<>();

        for (ObstacleToBreak obstacle : obstaclesToBreak) {
            if (obstacle.intersects(explosionX - explosionSize, explosionY - explosionSize,
                    explosionSize * 2, explosionSize * 3)) {
                obstaclesToRemove.add(obstacle);
            }
        }

        obstaclesToBreak.removeAll(obstaclesToRemove);
    }




    public void paint() {
        graphicsContext.drawImage(bombIdle.get(frame%4), punto.getX(), punto.getY(), 18, 27);
        frame++;
    }



    public void setPunto(double x, double y) {
        this.punto.setX(x);
        this.punto.setY(y);
    }


    private void checkCollisionWithEnemiesToKill() {
        double explosioninX = getPositionX();
        double explosioninY = getPositionY();
        double explosionSize = 120.5;

        Iterator<Enemigos> iterator = ScreenA.shadows.iterator();
        ArrayList<Enemigos> enemiesToRemove = new ArrayList<>();

        while (iterator.hasNext()) {
            Enemigos enemy = iterator.next();
            if (enemy.isAlive() && enemy.intersects(explosioninX - explosionSize, explosioninY - explosionSize,
                    explosionSize * 2, explosionSize * 3)) {
                enemy.setAlive(false);
                enemiesToRemove.add(enemy);
                iterator.remove();
                // Elimina la posición del enemigo de expRadio
                expRadio.removeIf(rangeExplosion ->
                        rangeExplosion.intersects(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight()));

                // Aquí puedes agregar un código adicional si es necesario
                // por ejemplo, restar vidas al jugador o realizar otras acciones relacionadas con el daño.
            }
        }

        // Llama al método para eliminar los enemigos de las listas
        removeHitEnemies(enemiesToRemove);
    }

    private void checkCollisionWithEnemiesToKillB() {
        double explosioninX = getPositionX();
        double explosioninY = getPositionY();
        double explosionSize = 120.5;

        Iterator<Enemigos> iterator = ScreenB.shadows.iterator();
        ArrayList<Enemigos> enemiesToRemove = new ArrayList<>();

        while (iterator.hasNext()) {
            Enemigos enemy = iterator.next();
            if (enemy.isAlive() && enemy.intersects(explosioninX - explosionSize, explosioninY - explosionSize,
                    explosionSize * 2, explosionSize * 3)) {
                enemy.setAlive(false);
                enemiesToRemove.add(enemy);
                iterator.remove();
                // Elimina la posición del enemigo de expRadio
                expRadio.removeIf(rangeExplosion ->
                        rangeExplosion.intersects(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight()));

                // Aquí puedes agregar un código adicional si es necesario
                // por ejemplo, restar vidas al jugador o realizar otras acciones relacionadas con el daño.
            }
        }

        // Llama al método para eliminar los enemigos de las listas
        removeHitEnemies(enemiesToRemove);
    }


    private void checkCollisionWithEnemiesToKillC() {
        double explosioninX = getPositionX();
        double explosioninY = getPositionY();
        double explosionSize = 120.5;

        Iterator<Enemigos> iterator = ScreenC.shadows.iterator();
        ArrayList<Enemigos> enemiesToRemove = new ArrayList<>();

        while (iterator.hasNext()) {
            Enemigos enemy = iterator.next();
            if (enemy.isAlive() && enemy.intersects(explosioninX - explosionSize, explosioninY - explosionSize,
                    explosionSize * 2, explosionSize * 3)) {
                enemy.setAlive(false);
                enemiesToRemove.add(enemy);
                iterator.remove();
                expRadio.removeIf(rangeExplosion ->
                        rangeExplosion.intersects(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight()));

            }
        }

        removeHitEnemies(enemiesToRemove);
    }

    public void removeHitEnemies(ArrayList<Enemigos> enemiesToRemove) {
        if (enemiesToRemove != null) {
            enemiesToRemove.removeAll(enemiesToRemove);
        } else {

            System.out.println("La lista de sombras (shadows) es nula.");
        }
    }

    private void checkCollisionWithBombExplosion() {
        double explosionX = getPositionX();
        double explosionY = getPositionY();
        double explosionSize = 120.5;

        if (bomberMan.isAlive() && bomberMan.intersects(explosionX - explosionSize, explosionY - explosionSize,
                explosionSize * 2, explosionSize * 3)) {
            bomberMan.menosUnaVida();
        }
    }



}