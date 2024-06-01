package com.example.bomberman.model;
import java.util.*;
public class MatrizDirigido<T> {

    private ArrayList<T> vertices;
    private int[][] adjacencyMatrix;

    public MatrizDirigido(int numVertices) {
        vertices = new ArrayList<>(numVertices);
        adjacencyMatrix = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            Arrays.fill(adjacencyMatrix[i], Integer.MAX_VALUE / 2);
            adjacencyMatrix[i][i] = 0;
        }
    }

    public void addVertex(T vertex) {
        if (vertices.contains(vertex)) {
            throw new IllegalArgumentException("Vertex already exists in the graph.");
        }
        vertices.add(vertex);
    }

    public void addEdge(T source, T destination) {
        addEdge(source, destination, 1);
    }

    public void addEdge(T source, T destination, int weight) {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);
        if (sourceIndex == -1 || destinationIndex == -1) {
            throw new IllegalArgumentException("One or both vertices do not exist in the graph.");
        }
        adjacencyMatrix[sourceIndex][destinationIndex] = weight;
    }

    public boolean hasEdge(T source, T destination) {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);
        if (sourceIndex == -1 || destinationIndex == -1) {
            throw new IllegalArgumentException("One or both vertices do not exist in the graph.");
        }
        return adjacencyMatrix[sourceIndex][destinationIndex] != Integer.MAX_VALUE / 2;
    }

    public int getEdgeWeight(T source, T destination) {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);
        if (sourceIndex == -1 || destinationIndex == -1) {
            throw new IllegalArgumentException("One or both vertices do not exist in the graph.");
        }
        return adjacencyMatrix[sourceIndex][destinationIndex];
    }

    public ArrayList<T> getVertices() {
        return vertices;
    }

    public ArrayList<Integer> bfs(T start) {
        ArrayList<Integer> result = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            result.add(vertices.indexOf(current));

            int currentIndex = vertices.indexOf(current);
            for (int i = 0; i < vertices.size(); i++) {
                if (adjacencyMatrix[currentIndex][i] != Integer.MAX_VALUE / 2 && !visited.contains(vertices.get(i))) {
                    queue.add(vertices.get(i));
                    visited.add(vertices.get(i));
                }
            }
        }

        return result;
    }

    public ArrayList<ArrayList<Integer>> floydWarshallM() {
        int size = vertices.size();
        ArrayList<ArrayList<Integer>> dist = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(adjacencyMatrix[i][j]);
            }
            dist.add(row);
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist.get(i).get(j) > dist.get(i).get(k) + dist.get(k).get(j)) {
                        dist.get(i).set(j, dist.get(i).get(k) + dist.get(k).get(j));
                    }
                }
            }
        }

        return dist;
    }
}
