package com.example.bomberman.control;

import com.example.bomberman.model.DirectedListGraph;
import com.example.bomberman.model.Edge;
import com.example.bomberman.model.Vertex;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class VentanaJuegoController {

    @FXML
    private Canvas canvas;

    private DirectedListGraph<Integer> graph;

    @FXML
    public void initialize() {
        createGraph();
        drawGraph();
        
    }

    private void createGraph() {
        graph = new DirectedListGraph<>(50);

        // Agregar vértices
        for (int i = 1; i <= 50; i++) {
            graph.addVertex(i);
        }

        // Conectar vértices en forma de cuadrícula
        for (int i = 1; i <= 50; i++) {
            if (i + 1 <= 50 && i % 5 != 0) {
                graph.addEdge(i, i + 1); // Conexión a vértice a la derecha
            }
            if (i + 5 <= 50) {
                graph.addEdge(i, i + 5); // Conexión a vértice abajo
            }
        }
    }

    private void drawGraph() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

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
    }

}
