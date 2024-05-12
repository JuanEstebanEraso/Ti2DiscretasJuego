package com.example.bomberman.screens;

import com.example.bomberman.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class ScreenA {

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    public static BomberMan bomberMan;
    public static ArrayList<BomberMan> bombermans;

    public static ArrayList<Enemigos> shadows;


    private Button button;
    private ItemSpeed itemspeed;


    private int gateWidth = 60;
    private int gateHeight = 60;

    private ArrayList<Obstacle> obstacles;
    private ArrayList<ObstacleToBreak> obstaclesToBreak;

    private static ArrayList<Image> bombas;

    private Enemigos enemigos;
    private Bomba bomba;

    private static Explosion explosion;


    public ScreenA(Canvas canvas) {
        this.canvas = canvas;

        this.obstacles = new ArrayList<Obstacle>();
        obstaclesToBreak = new ArrayList<>();
        this.bomberMan = new BomberMan(this.canvas, this.obstacles, new Position(2, 2, canvas), button, this.obstaclesToBreak, 10, itemspeed);
        this.graphicsContext = this.canvas.getGraphicsContext2D();

        shadows = new ArrayList<>();

        bombas = new ArrayList<>();

        explosion = new Explosion(bomberMan, bomberMan.getPosition().getX(), bomberMan.getPosition().getY(), canvas, bomberMan.getWidth(), bomberMan.getHeight());




        createEnemigos();


        initializeObstacles();
        initializeObstaclesToBreak();

        Image buttonImage = new Image(getClass().getResourceAsStream("/Boton/Portal_02.gif"));

        button = new Button(false, 100, 180, gateWidth, gateHeight, buttonImage, false);

        button.setBomberMan(bomberMan);
        Image itemImage = new Image(getClass().getResourceAsStream("/Item/Item_01.png"));

        itemspeed = new ItemSpeed(980, 530, 60, 60, itemImage);

        itemspeed.setBomberMan(bomberMan);
        bomberMan.setItemSpeed(itemspeed);

        for (int i = 0; i < 7; i++) {

            Image bomba = new Image(getClass().getResourceAsStream("/EXPLOSION/BOOM_0" + i + ".png"));

            bombas.add(bomba);

        }
    }

    public void bombs() {

        explosion.paint(bombas);
    }


    public BomberMan getBomberman() {
        return bomberMan;
    }


    public void drawButton() {
        button.paint(graphicsContext);
    }

    public void drawItem() {
        itemspeed.paint(graphicsContext);
    }



    private void initializeObstacles() {

        obstacles.add(new Obstacle(180, 120, 40, 40, canvas));
        obstacles.add(new Obstacle(330, 120, 40, 40, canvas));
        obstacles.add(new Obstacle(490, 120, 40, 40, canvas));
        obstacles.add(new Obstacle(650, 120, 40, 40, canvas));
        obstacles.add(new Obstacle(810, 120, 40, 40, canvas));
        obstacles.add(new Obstacle(980, 120, 40, 40, canvas));
        obstacles.add(new Obstacle(180, 240, 40, 40, canvas));
        obstacles.add(new Obstacle(330, 240, 40, 40, canvas));
        obstacles.add(new Obstacle(490, 240, 40, 40, canvas));
        obstacles.add(new Obstacle(650, 240, 40, 40, canvas));
        obstacles.add(new Obstacle(810, 240, 40, 40, canvas));
        obstacles.add(new Obstacle(980, 240, 40, 40, canvas));
        obstacles.add(new Obstacle(180, 350, 40, 40, canvas));
        obstacles.add(new Obstacle(330, 350, 40, 40, canvas));
        obstacles.add(new Obstacle(490, 350, 40, 40, canvas));
        obstacles.add(new Obstacle(650, 350, 40, 40, canvas));
        obstacles.add(new Obstacle(810, 350, 40, 40, canvas));
        obstacles.add(new Obstacle(980, 350, 40, 40, canvas));
        obstacles.add(new Obstacle(180, 470, 40, 40, canvas));
        obstacles.add(new Obstacle(330, 470, 40, 40, canvas));
        obstacles.add(new Obstacle(490, 470, 40, 40, canvas));
        obstacles.add(new Obstacle(650, 470, 40, 40, canvas));
        obstacles.add(new Obstacle(810, 470, 40, 40, canvas));
        obstacles.add(new Obstacle(980, 470, 40, 40, canvas));
        obstacles.add(new Obstacle(180, 590, 40, 40, canvas));
        obstacles.add(new Obstacle(330, 590, 40, 40, canvas));
        obstacles.add(new Obstacle(490, 590, 40, 40, canvas));
        obstacles.add(new Obstacle(650, 590, 40, 40, canvas));
        obstacles.add(new Obstacle(810, 590, 40, 40, canvas));
        obstacles.add(new Obstacle(980, 590, 40, 40, canvas));

    }


    private void initializeObstaclesToBreak() {
        obstaclesToBreak.add(new ObstacleToBreak(170, 170, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(330, 290, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(330, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(330, 170, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(180, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(250, 290, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(170, 290, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(400, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(400, 110, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(400, 170, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(400, 290, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(400, 350, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(400, 410, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(400, 590, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(400, 230, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(400, 650, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(400, 460, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 110, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 170, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 290, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 350, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 410, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 590, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 230, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 460, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 525, 60, 60, graphicsContext));

        obstaclesToBreak.add(new ObstacleToBreak(890, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(890, 110, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(890, 170, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(890, 290, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(890, 350, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(890, 410, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(890, 590, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(890, 230, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(890, 460, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(890, 525, 60, 60, graphicsContext));

        obstaclesToBreak.add(new ObstacleToBreak(570, 590, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 590, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(980, 650, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 650, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(180, 650, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(250, 650, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(330, 650, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(490, 650, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(650, 650, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(720, 650, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(810, 650, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(880, 650, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 650, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(990, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(180, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(250, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(330, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(490, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(680, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(750, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(930, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(1050, 60, 60, 60, graphicsContext));

    }

    public void drawObstacles() {
        for (Obstacle obstacle : obstacles) {
            graphicsContext.setFill(obstacle.getColor());
            graphicsContext.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
        }
    }

    public void drawObstaclesToBreak() {
        for (ObstacleToBreak obstacle : obstaclesToBreak) {
            graphicsContext.drawImage(obstacle.getCurrentImage(), obstacle.getX(), obstacle.getY(),
                    obstacle.getWidth(), obstacle.getHeight());
        }
    }

    public void paint(Image mapImage) {
        drawMap(mapImage);
        drawBomberMan();
        drawEnemigos();
        drawButton();
        //drawObstacles();
        drawObstaclesToBreak();
        drawItem();

    }


    public void createEnemigos() {
        for (int i = 0; i < 1; i++) {
            Enemigos enemy = new Enemigos(this.canvas, "/Enemigos/Color_0" + i + ".png", 230, 220, bomberMan, obstacles, obstaclesToBreak);
            shadows.add(enemy);

        }
    }

    public void drawEnemigos() {


        for (Enemigos enemy : shadows) {
            enemy.paint(graphicsContext);
        }
    }

    public void drawMap(Image mapImage) {
        setMapImage(mapImage);
    }

    public void drawBomberMan() {
        bomberMan.paint();
    }


    public void setMapImage(Image mapImage) {
        double nuevoAncho = 900;
        double nuevoAlto = (nuevoAncho / mapImage.getWidth()) * mapImage.getHeight();

        canvas.setWidth(nuevoAncho + 400);
        canvas.setHeight(nuevoAlto);

        graphicsContext.drawImage(mapImage, 0, 0, nuevoAncho + 300, nuevoAlto - 3);

    }

    public void onKeyPressed(KeyEvent event) {
        this.bomberMan.onKeyPressed(event);
    }

    public void onKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT:
                bomberMan.setRightPressed(false);
                break;
            case UP:
                bomberMan.setUpPressed(false);
                break;
            case LEFT:
                bomberMan.setLeftPressed(false);
                break;
            case DOWN:
                bomberMan.setDownPressed(false);
                break;

            case N:
                if (button.isPressed(bomberMan.getPosition())) {

                    loadScreenB();
                }
                break;

            case Z:
                if (button.isPressed()) {
                    bomberMan.colocarBomba(shadows);
                    bombs();
                    Bomba bomba = new Bomba(canvas,bomberMan.getPosition().getPosition(), obstaclesToBreak,obstacles,shadows);
                }
                break;
            default:
                break;
        }

        if (!event.isControlDown() && !event.isAltDown() && !event.isMetaDown()) {
            bomberMan.onKeyReleasedAll();
        }
    }


    private void loadScreenB() {

        Stage currentStage = (Stage) canvas.getScene().getWindow();
        currentStage.close();

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/bomberman/VentanaSegundoNivel.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Nivel 1 - 2");
            stage.setScene(new Scene(loader.load(), 1200, 790));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}