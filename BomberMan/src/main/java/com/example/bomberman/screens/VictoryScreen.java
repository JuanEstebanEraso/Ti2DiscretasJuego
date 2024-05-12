package com.example.bomberman.screens;

import com.example.bomberman.model.*;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
public class VictoryScreen {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private BomberMan bomberMan;

    private Enemigos enemigos;

    private ArrayList<Enemigos> shadows;



    private int x = 70;
    private int y = 20;

    private Button button;
    private ItemSpeed itemspeed;

    private int gateX = 570;
    private int gateY = 300;
    private int gateWidth = 60;
    private int gateHeight = 60;

    private ArrayList<Obstacle> obstacles;
    private ArrayList<ObstacleToBreak> obstaclesToBreak;

    public VictoryScreen(Canvas canvas) {
        this.canvas = canvas;
        this.obstacles = new ArrayList<Obstacle>();
        obstaclesToBreak = new ArrayList<>();
        this.bomberMan = new BomberMan(this.canvas, this.obstacles, new Position(2, 2, canvas), button,
                this.obstaclesToBreak, 20, itemspeed);
        this.graphicsContext = this.canvas.getGraphicsContext2D();

        shadows = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

            x += 160;
            y += 120;
            this.enemigos = new Enemigos(this.canvas, "/Enemigos/Color_0" + i + ".png", x, y, bomberMan, obstacles, obstaclesToBreak);
            shadows.add(enemigos);
        }

        initializeObstacles();
        initializeObstaclesToBreak();

        Image buttonImage = new Image(getClass().getResourceAsStream("/Boton/Portal_03.gif"));

        button = new Button(false, gateX, gateY, gateWidth, gateHeight, buttonImage, false);

        Image itemImage = new Image(getClass().getResourceAsStream("/Item/Item_01.png"));
        itemspeed = new ItemSpeed(800, 300, 60, 60, itemImage);
        itemspeed.setBomberMan(bomberMan);
        bomberMan.setItemSpeed(itemspeed);
    }

    public void drawItem() {
        itemspeed.paint(graphicsContext);
    }

    private void initializeObstacles() {

        obstacles.add(new Obstacle(180, 120, 50, 50, canvas));
        obstacles.add(new Obstacle(330, 120, 50, 50, canvas));
        obstacles.add(new Obstacle(490, 120, 50, 50, canvas));
        obstacles.add(new Obstacle(650, 120, 50, 50, canvas));
        obstacles.add(new Obstacle(810, 120, 50, 50, canvas));
        obstacles.add(new Obstacle(980, 120, 50, 50, canvas));
        obstacles.add(new Obstacle(180, 240, 50, 50, canvas));
        obstacles.add(new Obstacle(330, 240, 50, 50, canvas));
        obstacles.add(new Obstacle(490, 240, 50, 50, canvas));
        obstacles.add(new Obstacle(650, 240, 50, 50, canvas));
        obstacles.add(new Obstacle(810, 240, 50, 50, canvas));
        obstacles.add(new Obstacle(980, 240, 50, 50, canvas));
        obstacles.add(new Obstacle(180, 350, 50, 50, canvas));
        obstacles.add(new Obstacle(330, 350, 50, 50, canvas));
        obstacles.add(new Obstacle(490, 350, 50, 50, canvas));
        obstacles.add(new Obstacle(650, 350, 50, 50, canvas));
        obstacles.add(new Obstacle(810, 350, 50, 50, canvas));
        obstacles.add(new Obstacle(980, 350, 50, 50, canvas));
        obstacles.add(new Obstacle(180, 470, 50, 50, canvas));
        obstacles.add(new Obstacle(330, 470, 50, 50, canvas));
        obstacles.add(new Obstacle(490, 470, 50, 50, canvas));
        obstacles.add(new Obstacle(650, 470, 50, 50, canvas));
        obstacles.add(new Obstacle(810, 470, 50, 50, canvas));
        obstacles.add(new Obstacle(980, 470, 50, 50, canvas));
        obstacles.add(new Obstacle(180, 590, 50, 50, canvas));
        obstacles.add(new Obstacle(330, 590, 50, 50, canvas));
        obstacles.add(new Obstacle(490, 590, 50, 50, canvas));
        obstacles.add(new Obstacle(650, 590, 50, 50, canvas));
        obstacles.add(new Obstacle(810, 590, 50, 50, canvas));
        obstacles.add(new Obstacle(980, 590, 50, 50, canvas));

        for (Obstacle obstacle : this.obstacles) {


        }
    }


    private void initializeObstaclesToBreak() {
        obstaclesToBreak.add(new ObstacleToBreak(180, 170, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(330, 290, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(330, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(170, 60, 60, 60, graphicsContext));

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

        obstaclesToBreak.add(new ObstacleToBreak(250, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(250, 110, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(250, 170, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(250, 290, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(250, 350, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(250, 410, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(250, 590, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(250, 230, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(250, 460, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(250, 525, 60, 60, graphicsContext));

        obstaclesToBreak.add(new ObstacleToBreak(410, 60, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(410, 110, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(410, 170, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(410, 290, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(410, 350, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(410, 410, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(410, 590, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(410, 230, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(410, 460, 60, 60, graphicsContext));
        obstaclesToBreak.add(new ObstacleToBreak(410, 525, 60, 60, graphicsContext));
        for (ObstacleToBreak obstacle : this.obstaclesToBreak) {

        }
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

    }

    public void drawButton() {
        button.paint(graphicsContext);
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

                break;
            default:
                break;
        }

        if (!event.isControlDown() && !event.isAltDown() && !event.isMetaDown()) {
            bomberMan.onKeyReleasedAll();
        }


    }

}