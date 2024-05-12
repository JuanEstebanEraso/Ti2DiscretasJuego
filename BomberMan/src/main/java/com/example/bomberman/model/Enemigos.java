package com.example.bomberman.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import java.util.TimerTask;
import java.util.concurrent.*;

public class Enemigos extends Position {

    private ArrayList<Image> move;

    private Position position;
    private int frame;

    private double x;
    private double y;
    private double width;
    private double height;

    private Timer timer;

    private double escala = 0.20;

    private BomberMan objetivo;
    private int speed;
    private List<Obstacle> obstacles;
    private List<ObstacleToBreak> obstaclesToBreak;
    private ArrayList<Obstacle> obstacle;
    private boolean invulnerable = false;
    private Rectangle hitbox;
    private boolean isAlive;
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public Enemigos(Canvas canvas, String image, int x, int y, BomberMan objetivo, List<Obstacle> obstacles, List<ObstacleToBreak> obstaclesToBreak) {
        super(x, y, canvas);
        this.objetivo = objetivo;
        this.obstacles = obstacles;
        this.obstaclesToBreak = obstaclesToBreak;
        isAlive = true;


        this.speed = 10;

        this.frame = 0;

        this.move = new ArrayList<>();

        this.position = new Position(x, y, canvas);

        Image idleImage = new Image(getClass().getResourceAsStream(image));
        this.move.add(idleImage);

        executorService.scheduleAtFixedRate(this::moveAutomatically, 0, 500, TimeUnit.MILLISECONDS);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void paint(GraphicsContext graphicsContext) {
        double width = getImageWidth() * escala;
        double height = getImageHeight() * escala;
        double hitboxWidth = getImageWidth() * escala * 0.5;
        double hitboxHeight = getImageHeight() * escala * 0.5;
        double hitboxX = position.getX() + hitboxWidth / 2;
        double hitboxY = position.getY() + hitboxHeight / 2;
        this.hitbox = new Rectangle(position.getX(), position.getY(), width, height);
        graphicsContext.drawImage(move.get(frame % move.size()), position.getX(), position.getY(), width, height);
        frame++;
    }

    private void moveAutomatically() {
        double targetX = objetivo.getPosition().getX();
        double targetY = objetivo.getPosition().getY();

        double distanceToTarget = Math.sqrt(Math.pow(position.getX() - targetX, 2) + Math.pow(position.getY() - targetY, 2));

        double detectionRange = 1100.0;

        double deltaX = 0;
        double deltaY = 0;


        if (distanceToTarget < detectionRange) {

            if (Math.abs(position.getX() - targetX) > speed) {
                if (position.getX() < targetX) {

                    deltaX = speed;
                } else {

                    deltaX = -speed;
                }
            }

            if (Math.abs(position.getY() - targetY) > speed) {
                if (position.getY() < targetY) {

                    deltaY = speed;
                } else {

                    deltaY = -speed;
                }
            }


            if (checkCollisionWithBomberman(position.getX() + deltaX, position.getY() + deltaY)) {

                if (!invulnerable && isAlive()) {
                    objetivo.menosUnaVida();
                    System.out.println("Bomberman hit! Remaining lives: " + objetivo.getLives());
                    invulnerable = true;


                    Timer invulnerabilityTimer = new Timer();
                    invulnerabilityTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            invulnerable = false;
                            invulnerabilityTimer.cancel();
                        }
                    }, 10000);
                }
            } else {

                invulnerable = false;


                if (!checkCollisionWithObstacles(deltaX, deltaY) && !checkCollisionWithObstaclesToBreak(deltaX, deltaY)) {
                    position.setX(position.getX() + deltaX);
                    position.setY(position.getY() + deltaY);
                } else {
                    position.setX(position.getX() - deltaX);
                    position.setY(position.getY() - deltaY);
                }
            }
        }
    }


    private boolean checkCollisionWithBomberman(double newX, double newY) {
        double bombermanX = objetivo.getPosition().getX();
        double bombermanY = objetivo.getPosition().getY();
        double bombermanWidth = objetivo.getImageWidth() * objetivo.getEscala();
        double bombermanHeight = objetivo.getImageHeight() * objetivo.getEscala();

        double enemyX = position.getX();
        double enemyY = position.getY();
        double enemyWidth = getImageWidth() * escala;
        double enemyHeight = getImageHeight() * escala;

        return newX < bombermanX + bombermanWidth &&
                newX + enemyWidth > bombermanX &&
                newY < bombermanY + bombermanHeight &&
                newY + enemyHeight > bombermanY;
    }


    private boolean checkCollisionWithObstaclesToBreak(double deltaX, double deltaY) {
        double newX = position.getX() + deltaX;
        double newY = position.getY() + deltaY;

        for (ObstacleToBreak obstacle : obstaclesToBreak) {
            if (obstacle.intersects(newX, newY, obstacle.getWidth(), obstacle.getHeight())) {

                return true;
            }
        }

        return false;
    }

    double getImageWidth() {
        return move.get(0).getWidth();
    }

    double getImageHeight() {
        return move.get(0).getHeight();
    }

    public void stopMovement() {
        timer.cancel();
    }

    private boolean isEnemyTouchingObstacle(ArrayList<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            if (checkCollisionWithObject(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight())) {
                return true;
            }
        }
        return false;
    }

    boolean checkCollisionWithObject(double objectX, double objectY, double objectWidth, double objectHeight) {
        double enemyX = getPosition().getX();
        double enemyY = getPosition().getY();
        double enemyWidth = getImageWidth();
        double enemyHeight = getImageHeight();

        return enemyX < objectX + objectWidth && enemyX + enemyWidth > objectX && enemyY < objectY + objectHeight
                && enemyY + enemyHeight > objectY;
    }

    private boolean checkCollisionWithObstacles(double deltaX, double deltaY) {
        double newX = position.getX() + deltaX;
        double newY = position.getY() + deltaY;

        System.out.println("New Position: (" + newX + ", " + newY + ")");

        for (Obstacle obstacle : obstacles) {
            if (obstacle.intersects(newX, newY, obstacle.getWidth(), obstacle.getHeight())) {
                System.out.println("Collision with obstacle at: (" + obstacle.getX() + ", " + obstacle.getY() + ")");
                return true;
            }
        }

        return false;
    }

    public void die() {
        System.out.println("Die method");
        this.isAlive = false;
        executorService.shutdown();
    }

    public boolean intersects(double otherX, double otherY, double otherWidth, double otherHeight) {
        return x < otherX + otherWidth &&
                x + width > otherX &&
                y < otherY + otherHeight &&
                y + height > otherY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

}