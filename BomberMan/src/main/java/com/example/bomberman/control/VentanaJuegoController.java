package com.example.bomberman.control;

import com.example.bomberman.model.DirectedListGraph;
import com.example.bomberman.model.Edge;
import com.example.bomberman.model.Enemy;
import com.example.bomberman.model.Vertex;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VentanaJuegoController {

    @FXML
    public Canvas canvas;
    @FXML
    public Label labelGasolina;
    public DirectedListGraph<Integer> graph;
    public Vertex<Integer> characterPosition;
    public Enemy enemy;
    public int[][] distances;
    public int[][] next;
    public Image backgroundImage;
    public Image playerImage;
    public Image enemyImage;
    public int playerEnergy = 100;
    public Vertex<Integer> victoryVertex;

    @FXML
    public void initialize() {
        createGraph();
        characterPosition = graph.getVertexOfData(1); // Colocar al personaje en el vértice 1
        enemy = new Enemy(graph.getVertexOfData(20)); // enemigo en el vértice 20
        victoryVertex = graph.getVertexOfData(50); //  vértice de victoria
        loadBackgroundImage();
        loadPlayerImage();
        loadEnemyImage();
        calculateShortestPaths();
        drawGraph();

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleCanvasClick);
    }


    private void createGraph() {
        graph = new DirectedListGraph<>(50);
        Random random = new Random();

        for (int i = 1; i <= 50; i++) {
            graph.addVertex(i);
        }
        for (int i = 1; i <= 50; i++) {
            if (i + 1 <= 50 && i % 5 != 0) {
                int weight = random.nextInt(10) + 1;
                graph.addEdge(i, i + 1, weight);
                graph.addEdge(i + 1, i, weight);
            }
            if (i + 5 <= 50) {
                int weight = random.nextInt(10) + 1;
                graph.addEdge(i, i + 5, weight);
                graph.addEdge(i + 5, i, weight);
            }
        }

        // Añadir una arista extra para los vértices del medio del grafo
        for (int i = 10; i <= 15; i++) {
            int randomVertex = random.nextInt(25) + 1; // Seleccionar un vértice aleatorio en la primera mitad
            int weight = random.nextInt(10) + 1; // Generar un peso aleatorio
            graph.addEdge(i, randomVertex, weight);
            graph.addEdge(randomVertex, i, weight);
        }

        for (int i = 30; i <= 35; i++) {
            int randomVertex = random.nextInt(25) + 1; // Seleccionar un vértice aleatorio en la primera mitad
            int weight = random.nextInt(10) + 1; // Generar un peso aleatorio
            graph.addEdge(i, randomVertex, weight);
            graph.addEdge(randomVertex, i, weight);
        }

        for (int i = 40; i <= 50; i++) {
            int randomVertex = random.nextInt(25) + 1; // Seleccionar un vértice aleatorio en la primera mitad
            int weight = random.nextInt(10) + 1; // Generar un peso aleatorio
            graph.addEdge(i, randomVertex, weight);
            graph.addEdge(randomVertex, i, weight);
        }
    }

    private void drawGraph() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        if (backgroundImage != null) {
            gc.drawImage(backgroundImage, 0, 0, canvas.getWidth(), canvas.getHeight());
        } else {
            // Fondo gris si la imagen no está disponible
            gc.setFill(Color.GRAY);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }

        for (Vertex<Integer> vertex : graph.getVertices()) {
            int row = (vertex.getData() - 1) / 5;
            int col = (vertex.getData() - 1) % 5;
            int x = col * 100 + 50;
            int y = row * 100 + 50;

            if (vertex.equals(graph.getVertexOfData(1))) {
                gc.setFill(Color.RED); // Colorea el primer vértice de rojo
            } else if (vertex.equals(graph.getVertexOfData(50))) {
                gc.setFill(Color.GREEN); // Colorea el último vértice de verde
            } else {
                gc.setFill(Color.WHITE);
            }

            gc.fillOval(x, y, 40, 40);
        }

        gc.setStroke(Color.GREEN);
        for (Vertex<Integer> vertex : graph.getVertices()) {
            int row = (vertex.getData() - 1) / 5;
            int col = (vertex.getData() - 1) % 5;
            int x1 = col * 100 + 70;
            int y1 = row * 100 + 70;
            for (Edge<Integer> edge : graph.getEdges(vertex)) {
                Vertex<Integer> dest = edge.getDestinationVertex();
                int destRow = (dest.getData() - 1) / 5;
                int destCol = (dest.getData() - 1) % 5;
                int x2 = destCol * 100 + 70;
                int y2 = destRow * 100 + 70;
                gc.strokeLine(x1, y1, x2, y2);

                int weight = edge.getWeight();
                int weightX = (x1 + x2) / 2;
                int weightY = (y1 + y2) / 2;
                gc.setStroke(Color.WHITE);
                gc.strokeText(String.valueOf(weight), weightX, weightY);
            }
        }

        drawCharacter(gc);
        drawEnemy(gc);
    }



    private void loadBackgroundImage() {
        try {
            backgroundImage = new Image(getClass().getResourceAsStream("/map/Gta_02.jpg"));
            if (backgroundImage.isError()) {
                throw new Exception("Error al cargar la imagen");
            }
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen de fondo: " + e.getMessage());
        }
    }

    private void loadPlayerImage() {
        try {
            playerImage = new Image(getClass().getResourceAsStream("/map/Delorean.png"));
            if (playerImage.isError()) {
                throw new Exception("Error al cargar la imagen del jugador");
            }
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen del jugador: " + e.getMessage());
        }
    }
    private void loadEnemyImage() {
        try {
            enemyImage = new Image(getClass().getResourceAsStream("/map/Police.png"));
            if (enemyImage.isError()) {
                throw new Exception("Error al cargar la imagen del enemigo");
            }
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen del enemigo: " + e.getMessage());
        }
    }
    private void calculateShortestPaths() { //Floyd-Warshall algorithm
        int size = graph.getVertices().size();
        distances = new int[size + 1][size + 1];
        next = new int[size + 1][size + 1];

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (i == j) {
                    distances[i][j] = 0;
                } else {
                    distances[i][j] = Integer.MAX_VALUE;
                }
                next[i][j] = -1;
            }
        }


        for (Vertex<Integer> vertex : graph.getVertices()) {
            for (Edge<Integer> edge : graph.getEdges(vertex)) {
                int u = vertex.getData();
                int v = edge.getDestinationVertex().getData();
                int weight = edge.getWeight();
                distances[u][v] = weight;
                next[u][v] = v;
            }
        }

        for (int k = 1; k <= size; k++) {
            for (int i = 1; i <= size; i++) {
                for (int j = 1; j <= size; j++) {
                    if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE && distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    private void drawCharacter(GraphicsContext gc) {
        int row = (characterPosition.getData() - 1) / 5;
        int col = (characterPosition.getData() - 1) % 5;
        int x = col * 100 + 50;
        int y = row * 100 + 50;

        gc.drawImage(playerImage, x, y, 60, 60);
    }

    private void drawEnemy(GraphicsContext gc) {
        int row = (enemy.getPosition().getData() - 1) / 5;
        int col = (enemy.getPosition().getData() - 1) % 5;
        int x = col * 100 + 40;
        int y = row * 100 + 40;

        gc.drawImage(enemyImage, x, y, 75, 60);
    }

    private void handleCanvasClick(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        if (!checkPlayerAndEnemyPosition()) {
            for (Vertex<Integer> vertex : graph.getVertices()) {
                int row = (vertex.getData() - 1) / 5;
                int col = (vertex.getData() - 1) % 5;
                int x = col * 100 + 50;
                int y = row * 100 + 50;

                if (isPointInCircle(mouseX, mouseY, x + 20, y + 20, 20)) {
                    for (Edge<Integer> edge : graph.getEdges(characterPosition)) {
                        if (edge.getDestinationVertex().equals(vertex)) {
                            int cost = edge.getWeight();
                            if (cost <= playerEnergy) {
                                characterPosition = vertex;
                                playerEnergy -= cost;
                                if (characterPosition.equals(victoryVertex)) {
                                    characterPosition = victoryVertex;
                                    drawGraph();
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Victoria");
                                    alert.setHeaderText(null);
                                    alert.setContentText("¡Has ganado el juego!");
                                    alert.showAndWait();
                                } else {
                                    updateEnemyPosition();
                                    drawGraph();
                                    labelGasolina.setText("Gasolina restante: " + playerEnergy);
                                }
                                break;
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                if (this.playerEnergy <= 0) {
                                    alert.setTitle("GameOver");
                                    alert.setHeaderText(null);
                                    alert.setContentText("¡Te has quedado sin gasolina! Se acabó el juego.");
                                    alert.showAndWait();
                                } else {
                                    alert.setHeaderText(null);
                                    alert.setContentText("No tienes suficiente gasolina para moverte por este camino");
                                    alert.showAndWait();
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
    }


    @FXML
    private void handleMostrarCaminoClick(ActionEvent event) {
        // Reducir la gasolina del jugador en 40 unidades
        playerEnergy -= 20;
        labelGasolina.setText("Gasolina restante: " + playerEnergy);

        // Obtener el camino desde la posición actual del jugador hasta el vértice de victoria
        List<Vertex<Integer>> path = graph.bfsPath(characterPosition, victoryVertex);

        // Dibujar el camino en el lienzo en color morado
        drawPath(path);
    }

    private void drawPath(List<Vertex<Integer>> path) {
        if (path != null) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setStroke(Color.PURPLE);
            gc.setLineWidth(3); // Grosor de la línea del camino

            Vertex<Integer> prevVertex = null;
            for (Vertex<Integer> vertex : path) {
                if (prevVertex != null) {
                    int row1 = (prevVertex.getData() - 1) / 5;
                    int col1 = (prevVertex.getData() - 1) % 5;
                    int x1 = col1 * 100 + 70;
                    int y1 = row1 * 100 + 70;

                    int row2 = (vertex.getData() - 1) / 5;
                    int col2 = (vertex.getData() - 1) % 5;
                    int x2 = col2 * 100 + 70;
                    int y2 = row2 * 100 + 70;

                    gc.strokeLine(x1, y1, x2, y2);
                }
                prevVertex = vertex;
            }
        }
    }

    private boolean checkPlayerAndEnemyPosition() {
        boolean gameOver = false;
        if (characterPosition.equals(enemy.getPosition())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("¡El policía te ha atrapado! Juego terminado.");
            alert.showAndWait();
            gameOver = true;
        }
        return gameOver;
    }

    private void updateEnemyPosition() {
        int start = enemy.getPosition().getData();
        int end = characterPosition.getData();
        if (next[start][end] != -1) {
            enemy.setPosition(graph.getVertexOfData(next[start][end]));
        }
        checkPlayerAndEnemyPosition();
    }

    private boolean isPointInCircle(double px, double py, double cx, double cy, double radius) {
        double dx = px - cx;
        double dy = py - cy;
        return dx * dx + dy * dy <= radius * radius;
    }
}
