package com.example.bomberman.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class BomberMan {

    // elementos gráficos
    private Canvas canvas;
    private boolean isalive;
    private GraphicsContext graphicsContext;
    private ArrayList<Image> idles;
    private ArrayList<Image> right;
    private ArrayList<Image> down;

    private int lives = 3;

    private boolean explode;


    private ArrayList<Image> left;

    private ArrayList<Image> up;
    private int frame;

    private ArrayList<Bomba> activeBombs;

    private Position position;
    private int state;

    private boolean rightPressed;
    private boolean upPressed;

    private boolean leftPressed;
    private boolean downPressed;
    private final double screenWidth;
    private final double screenHeight;
    private List<Obstacle> obstacles;
    private List<ObstacleToBreak> obstaclesToBreak;
    private ArrayList<Enemigos> enemigos;
    private int speed;
    private ItemSpeed itemSpeed;
    private Image itemImage;
    private ArrayList<Live> livesRepresentations;


    private double escala = 0.5;

    private ArrayList<Bomba> bombas;
    private ItemSpeedRepresentation itemSpeedRepresentation;



    public BomberMan(Canvas canvas, List<Obstacle> obstacles, Position position, Button boton,
                     List<ObstacleToBreak> obstaclesToBreak, int speed, ItemSpeed itemspeed) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.obstacles = obstacles;
        this.obstaclesToBreak = obstaclesToBreak;
        this.state = 0;
        this.frame = 0;
        this.speed = speed;
        this.itemSpeed = itemspeed;
        bombas = new ArrayList<>();
        activeBombs = new ArrayList<>();
        this.itemImage = new Image(getClass().getResourceAsStream("/Item/Item_01.png"));

        this.lives = 3;
        this.livesRepresentations = new ArrayList<>();
        for (int i = 0; i < lives; i++) {
            Live live = new Live(canvas, 260 + i * 40, 10, "/Live/Live_00.png", i);
            livesRepresentations.add(live);
        }

        this.idles = new ArrayList<>();
        this.right = new ArrayList<>();
        this.down = new ArrayList<>();
        this.up = new ArrayList<>();
        this.left = new ArrayList<>();


        this.screenWidth = canvas.getWidth() + 550;
        this.screenHeight = canvas.getHeight() + 300;

        this.position = new Position(100, 60, canvas);

        for (int i = 0; i < 3; i++) {
            Image idleImage = new Image(
                    getClass().getResourceAsStream("/animations/player/idle/klipartz.com_STOP_0" + i + ".png"));
            this.idles.add(idleImage);

        }

        Image itemImage = new Image(getClass().getResourceAsStream("/Item/Item_01.png"));


        for (int i = 0; i < 7; i++) {
            String imagePath = "/animations/player/right/klipartz.com_RIGHT_0" + i + ".png";
            Image runImage = new Image(getClass().getResourceAsStream(imagePath));
            this.right.add(runImage);
        }

        for (int i = 0; i < 8; i++) {
            String imagePath = "/animations/player/down/klipartz.com_DOWN_0" + i + ".png";
            Image downImage = new Image(getClass().getResourceAsStream(imagePath));
            this.down.add(downImage);
        }
        for (int i = 0; i < 8; i++) {
            String imagePath = "/animations/player/up/klipartz.com_UP_0" + i + ".png";
            Image upImage = new Image(getClass().getResourceAsStream(imagePath));
            this.up.add(upImage);
        }
        for (int i = 0; i < 7; i++) {
            String imagePath = "/animations/player/left/klipartz.com_LEFT_0" + i + ".png";
            Image leftImage = new Image(getClass().getResourceAsStream(imagePath));
            this.left.add(leftImage);
        }

    }


    public void paint() {
        if (itemSpeed != null) {
            itemSpeed.paint(graphicsContext);
        }
        if (itemSpeedRepresentation != null) {
            itemSpeedRepresentation.paint(graphicsContext);
        }
        double width = getImageWidth() * escala;
        double height = getImageHeight() * escala;
        for (Live live : livesRepresentations) {
            live.paint();
        }
        onMove();
        if (state == 0) {
            graphicsContext.drawImage(idles.get(frame % idles.size()), position.getX(), position.getY(), width, height);
            frame++;
        } else if (state == 1) {
            graphicsContext.drawImage(right.get(frame % right.size()), position.getX(), position.getY(), width, height);
            frame++;
        } else if (state == 2) {
            graphicsContext.drawImage(up.get(frame % up.size()), position.getX(), position.getY(), width, height);
            frame++;
        } else if (state == 3) {
            graphicsContext.drawImage(left.get(frame % left.size()), position.getX(), position.getY(), width, height);
            frame++;
        } else if (state == 4) {
            graphicsContext.drawImage(down.get(frame % down.size()), position.getX(), position.getY(), width, height);
            frame++;
        }

    }

    public void onKeyPressed(KeyEvent event) {
        if (Objects.requireNonNull(event.getCode()) == KeyCode.RIGHT && !rightPressed) {
            state = 1;
            rightPressed = true;
        } else if (event.getCode() == KeyCode.UP && !upPressed) {

            state = 2;
            upPressed = true;
        } else if (event.getCode() == KeyCode.LEFT && !leftPressed) {
            state = 3;
            leftPressed = true;
        } else if (event.getCode() == KeyCode.DOWN && !downPressed) {
            state = 4;
            downPressed = true;
        } else if (event.getCode() == KeyCode.Z) {
            explode = true;

            colocarBomba(enemigos);

        }
    }


    public void colocarBomba(ArrayList<Enemigos> enemigos) {
        if (activeBombs == null) {
            activeBombs = new ArrayList<>();
        }

        double bombermanPosX = getPosition().getX();
        double bombermanPosY = getPosition().getY();

        Punto punto = new Punto(bombermanPosX, bombermanPosY);

        Bomba bomb = new Bomba(canvas, punto, obstaclesToBreak, (ArrayList<Obstacle>) obstacles,enemigos);
        activeBombs.add(bomb);

        bomb.paint();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                bomb.explode();
                // Remove the exploded bomb
                explode = false;
                timer.cancel(); // Cancel the timer
            }
        }, 3000);

        Punto centralExplosion = bomb.getCentralPosition();
        System.out.println("Explosion at: " + centralExplosion.getX() + ", " + centralExplosion.getY());
    }


    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }


    public void onMove() {
        double deltaX = 0;
        double deltaY = 0;
        if (itemSpeed != null && !itemSpeed.isRecogido() && checkCollisionWithItem(deltaX, deltaY)) {
            itemSpeed.setRecogido(true);
            setSpeed(speed + 20);
            System.out.println("Speed increased! New speed: " + speed);
            itemSpeed.collect();
            if (itemSpeedRepresentation == null) {
                itemSpeedRepresentation = new ItemSpeedRepresentation(650, 5, 50, 50, itemImage);
            }
        }


        if (rightPressed && position.getX() + deltaX + getImageWidth() <= screenWidth) {
            deltaX = speed; // Multiplica la velocidad por la dirección positiva
        } else if (upPressed && position.getY() + deltaY - 60 >= 0) {
            deltaY = -speed; // Multiplica la velocidad por la dirección negativa
        } else if (downPressed && position.getY() + deltaY + getImageHeight() - 82 <= screenHeight) {
            deltaY = speed; // Multiplica la velocidad por la dirección positiva
        } else if (leftPressed && position.getX() + deltaX - 82 >= 0) {
            deltaX = -speed; // Multiplica la velocidad por la dirección negativa
        }

        if (!checkCollisionWithObstacles(deltaX, deltaY) && !checkCollisionWithObstaclesToBreak(deltaX, deltaY)) {
            position.setX(position.getX() + deltaX);
            position.setY(position.getY() + deltaY);
        }
    }

    public void setItemSpeed(ItemSpeed itemSpeed) {
        this.itemSpeed = itemSpeed;
    }

    private boolean checkCollisionWithItem(double deltaX, double deltaY) {
        double newX = position.getX() + deltaX;
        double newY = position.getY() + deltaY;

        if (itemSpeed != null && itemSpeed.intersects(newX, newY, itemSpeed.getWidth(), itemSpeed.getHeight())) {
            System.out.println("Collision with ItemSpeed detected!");
            return true;
        }

        return false;
    }

    public boolean intersects(double otherX, double otherY, double otherWidth, double otherHeight) {
        return position.getX() < otherX + otherWidth &&
                position.getX()  > otherX &&
                position.getY() < otherY + otherHeight &&
                position.getY()  > otherY;
    }

    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
        System.out.println("New speed: " + speed);
    }

    public void setAlive(boolean newAlive){
        this.isalive = newAlive;
    }

    private boolean checkCollisionWithObstacles(double deltaX, double deltaY) {
        double newX = position.getX() + deltaX;
        double newY = position.getY() + deltaY;


        for (Obstacle obstacle : obstacles) {
            if (obstacle.intersects(newX, newY, obstacle.getWidth(), obstacle.getHeight())) {
                System.out.println("Collision with Obstacle detected!");
                return true;
            }
        }

        return false;
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

    public double getImageWidth() {
        return ((idles.get(0)).getWidth());
    }

    public double getImageHeight() {
        return idles.get(0).getHeight();
    }

    public void onKeyReleasedAll() {
        rightPressed = false;
        upPressed = false;
        leftPressed = false;
        downPressed = false;
        state = 0;
        frame = 0;
    }


    public Position getPosition() {
        return position;
    }

    public double getHeight() {

        return idles.get(0).getHeight() * escala;
    }

    public double getWidth() {
        return idles.get(0).getWidth() * escala;
    }


    public int getLives() {
        return lives;
    }

    public void menosUnaVida() {
        if (lives > 0) {
            lives--;
            updateLiveRepresentations();

            if (lives <= 0) {
                System.out.println("Game over");
                gameOver();
            }
        }
    }

    private void gameOver() {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/example/bomberman/gameOver.fxml"));
                Stage stage = new Stage();
                stage.setTitle("gameOver");
                stage.setScene(new Scene(loader.load(), 1200, 790));

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public double getEscala() {
        return escala;
    }


    public ArrayList<Bomba> getBoombs() {
        return bombas;
    }


    private void updateLiveRepresentations() {

        livesRepresentations.clear();


        for (int i = 0; i < lives; i++) {
            Live live = new Live(canvas, 260 + i * 40, 10, "/Live/Live_00.png", i);
            livesRepresentations.add(live);
        }
    }

    public boolean getAlive(){
        return isAlive();
    }


    public boolean isAlive() {

        if(lives > 0){
            isalive = true;
            return true;
        }
        return false;
    }
}