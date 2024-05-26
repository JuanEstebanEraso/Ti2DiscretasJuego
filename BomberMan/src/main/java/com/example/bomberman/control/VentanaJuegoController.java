package com.example.bomberman.control;

import com.example.bomberman.model.DirectedListGraph;
import com.example.bomberman.model.Edge;
import com.example.bomberman.model.Enemy;
import com.example.bomberman.model.Vertex;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Random;

public class VentanaJuegoController {

    @FXML
    private Canvas canvas;

    private DirectedListGraph<Integer> graph;
    private Vertex<Integer> characterPosition;
    private Enemy enemy;
    private int[][] distances;
    private int[][] next;
    private Image backgroundImage; // Imagen de fondo
    private Image playerImage; // Imagen del jugador
    private Image enemyImage; // Imagen del enemigo
    private int playerEnergy = 100;
    private Vertex<Integer> victoryVertex;

    @FXML
    public void initialize() {
        createGraph();
        characterPosition = graph.getVertexOfData(1); // Colocar al personaje en el vértice 1 al inicio
        enemy = new Enemy(graph.getVertexOfData(20)); // Colocar al enemigo en el vértice 20 al inicio
        victoryVertex = graph.getVertexOfData(50); // Definir el vértice de victoria
        loadBackgroundImage(); // Cargar la imagen de fondo
        loadPlayerImage(); // Cargar la imagen del jugador
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
                int weight = random.nextInt(10) + 1; // Peso aleatorio entre 1 y 10
                graph.addEdge(i, i + 1, weight);
                graph.addEdge(i + 1, i, weight);
            }
            if (i + 5 <= 50) {
                int weight = random.nextInt(10) + 1;
                graph.addEdge(i, i + 5, weight);
                graph.addEdge(i + 5, i, weight);
            }
        }
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
    private void calculateShortestPaths() {
        int size = graph.getVertices().size();
        distances = new int[size + 1][size + 1];
        next = new int[size + 1][size + 1];

        // Inicializar matrices
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

        // Configurar distancias y siguientes
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

    private void drawGraph() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Dibujar imagen de fondo si está disponible
        if (backgroundImage != null) {
            gc.drawImage(backgroundImage, 0, 0, canvas.getWidth(), canvas.getHeight());
        } else {
            // Fondo gris si la imagen no está disponible
            gc.setFill(Color.GRAY);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }

        gc.setFill(Color.WHITE);
        for (Vertex<Integer> vertex : graph.getVertices()) {
            int row = (vertex.getData() - 1) / 5;
            int col = (vertex.getData() - 1) % 5;
            int x = col * 100 + 50;
            int y = row * 100 + 50;
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

    private void drawCharacter(GraphicsContext gc) {
        int row = (characterPosition.getData() - 1) / 5;
        int col = (characterPosition.getData() - 1) % 5;
        int x = col * 100 + 50;
        int y = row * 100 + 50;

        gc.drawImage(playerImage, x, y, 60, 60); // Dibujar la imagen del jugador
    }

    private void drawEnemy(GraphicsContext gc) {
        int row = (enemy.getPosition().getData() - 1) / 5;
        int col = (enemy.getPosition().getData() - 1) % 5;
        int x = col * 100 + 40;
        int y = row * 100 + 40;

        gc.drawImage(enemyImage, x, y, 75, 60); // Dibujar la imagen del enemigo
    }

    private void handleCanvasClick(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        for (Vertex<Integer> vertex : graph.getVertices()) {
            int row = (vertex.getData() - 1) / 5;
            int col = (vertex.getData() - 1) % 5;
            int x = col * 100 + 50;
            int y = row * 100 + 50;
// probando hacerlo con gasolina
            if (isPointInCircle(mouseX, mouseY, x + 20, y + 20, 20)) {
                for (Edge<Integer> edge : graph.getEdges(characterPosition)) {
                    if (edge.getDestinationVertex().equals(vertex)) {
                        int cost = edge.getWeight();
                        if (cost <= playerEnergy) {
                            characterPosition = vertex;
                            playerEnergy -= cost;
                            if (characterPosition.equals(victoryVertex)) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Victoria");
                                alert.setHeaderText(null);
                                alert.setContentText("¡Has ganado el juego!");
                                alert.showAndWait();
                                return;
                            } else {
                                updateEnemyPosition();
                                drawGraph();
                                System.out.println(playerEnergy);
                            }
                            break;
                        } else {
                            System.out.println("¡No tienes suficiente energía para moverte por este camino!");
                            return; // El jugador no puede moverse
                        }
                    }
                }
            }
        }
    }

    private void updateEnemyPosition() {
        int start = enemy.getPosition().getData();
        int end = characterPosition.getData();
        if (next[start][end] != -1) {
            enemy.setPosition(graph.getVertexOfData(next[start][end]));
        }
    }

    private boolean isPointInCircle(double px, double py, double cx, double cy, double radius) {
        double dx = px - cx;
        double dy = py - cy;
        return dx * dx + dy * dy <= radius * radius;
    }
}
