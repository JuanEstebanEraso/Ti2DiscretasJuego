    package com.example.bomberman.model;

    import java.util.ArrayList;

    import javafx.scene.canvas.Canvas;
    import javafx.scene.canvas.GraphicsContext;
    import javafx.scene.image.Image;

    public class Live {

        private Canvas canvas;
        private int frame = 0;
        private Position position;
        private GraphicsContext CP;
        private ArrayList<Image> lifes;
        private double escala = 0.3;

        public Live(Canvas canvas, double x, double y, String image, int a) {
            this.canvas = canvas;
            this.CP = this.canvas.getGraphicsContext2D();
            this.frame = 0;
            this.position = new Position(x + a, y, canvas);
            this.lifes = new ArrayList<>();

            Image idleImage = new Image(getClass().getResourceAsStream(image));
            this.lifes.add(idleImage);
        }

        public void paint() {
            double width = getImageWidth() * escala * 0.3;
            double height = getImageHeight() * escala * 0.3;

            CP.drawImage(lifes.get(frame % lifes.size()), position.getX(), position.getY(), width, height);
            frame++;
        }

        private double getImageWidth() {
            return lifes.get(0).getWidth();
        }

        private double getImageHeight() {
            return lifes.get(0).getHeight();
        }

        public void setImage(String path) {
        }
    }
