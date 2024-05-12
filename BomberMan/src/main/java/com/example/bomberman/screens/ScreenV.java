package com.example.bomberman.screens;

import com.example.bomberman.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class ScreenV {

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private BomberMan bomberMan;

    public PasswordField passwordField;

    public static ArrayList<Enemigos> shadows;

    private int remainingEnemies;

    private Button button;
    private ItemSpeed itemspeed;
    private int x = 70;
    private int y = 20;

    private int a = 0;

    private int gateX = 100;
    private int gateY = 200;
    private int gateWidth = 60;
    private int gateHeight = 60;

    private ArrayList<Obstacle> obstacles;
    private ArrayList<ObstacleToBreak> obstaclesToBreak;

    private ArrayList<Live> live;
    private Live lifes;

    public ScreenV(Canvas canvas) {
        this.canvas = canvas;

        this.obstacles = new ArrayList<Obstacle>();
        obstaclesToBreak = new ArrayList<>();
        this.bomberMan = new BomberMan(this.canvas, this.obstacles, new Position(2, 2, canvas), button,this.obstaclesToBreak,
                10,itemspeed);
        this.graphicsContext = this.canvas.getGraphicsContext2D();

        shadows = new ArrayList<>();

        live = new ArrayList<>();

        createEnemigos();

        for (int i = 0; i < 3; i++) {

            x = 200;
            y = 8;

            a += 50;
            this.lifes = new Live(this.canvas, x, y, "/Live/Live_0" + i + ".png", a);
            live.add(lifes);
        }

        initializeObstacles();
        initializeObstaclesToBreak();

        Image buttonImage = new Image(getClass().getResourceAsStream("/Boton/Portal_02.gif"));

        button = new Button(false, gateX, gateY, gateWidth, gateHeight, buttonImage, false);

        button.setBomberMan(bomberMan);
        Image itemImage = new Image(getClass().getResourceAsStream("/Item/Item_01.png"));

        itemspeed = new ItemSpeed( 100, 300, 60, 60,itemImage);

        itemspeed.setBomberMan(bomberMan);
        bomberMan.setItemSpeed(itemspeed);
    }

    public BomberMan getBomberman() {
        return bomberMan;
    }

    public Button getButton() {
        return button;
    }

    public void drawButton() {
        button.paint(graphicsContext);
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
        drawEnemigos();
        drawButton();
        //drawObstacles();
        drawObstaclesToBreak();
        drawItem();


    }



    public void createEnemigos() {
        for (int i = 0; i < 1; i++) {
            shadows.add(new Enemigos(this.canvas, "/Enemigos/Color_0" + i + ".png",350 , 60, bomberMan, obstacles,obstaclesToBreak));
            shadows.add(new Enemigos(this.canvas, "/Enemigos/Color_0" + i + ".png",250 , 60, bomberMan, obstacles,obstaclesToBreak));

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

                    loadScreenC();
                }
                break;

            case Z:
                if (button.isPressed()) {

                    bombas();
                }
                break;
            default:
                break;
        }

        if (!event.isControlDown() && !event.isAltDown() && !event.isMetaDown()) {
            bomberMan.onKeyReleasedAll();
        }
    }

    private void bombas() {

    }

    private void loadScreenC() {

        Stage currentStage = (Stage) canvas.getScene().getWindow();
        currentStage.close();

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/bomberman/VentanaVictory.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Finaln");
            stage.setScene(new Scene(loader.load(), 1200, 790));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}