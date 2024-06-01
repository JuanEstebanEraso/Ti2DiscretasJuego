package com.example.bomberman.model;
import java.util.*;
public class MatrizDirigido<V>{

    private ArrayList<V> vertices;
    private int[][] adjacencyMatrix;

    public MatrizDirigido(int numVertices) {
        vertices = new ArrayList<>(numVertices);
        adjacencyMatrix = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            Arrays.fill(adjacencyMatrix[i], Integer.MAX_VALUE / 2);
            adjacencyMatrix[i][i] = 0;
        }
    }
    public void addVertex(V vertex) {
        vertices.add(vertex);
    }

    public void addEdge(V source, V destination,int weight) {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);
        if (sourceIndex == -1 || destinationIndex == -1) {
            throw new IllegalArgumentException("Attempt to add a connection between two vertices, one of them doesn't exist in the graph.\n" +
                    "Source vertex: " + source + "\n" +
                    "Destination vertex: " + destination + "\n" +
                    "One of the vertices does not exist inside the graph.");
        }

        adjacencyMatrix[sourceIndex][destinationIndex] = 1;
    }

    public ArrayList<V> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Vertices: " + vertices + "\n");
        result.append("Adjacency Matrix:\n");
        for (int[] row : adjacencyMatrix) {
            for (int value : row) {
                result.append(value).append("\t");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public List<V> bfs(V start) {
        List<V> result = new ArrayList<>();
        Set<V> visited = new HashSet<>();
        Queue<V> queue = new LinkedList<>();

        int startIndex = vertices.indexOf(start);
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            result.add(current);

            int currentIndex = vertices.indexOf(current);

            for (int i = 0; i < vertices.size(); i++) {
                if (adjacencyMatrix[currentIndex][i] == 1 && !visited.contains(vertices.get(i))) {
                    queue.add(vertices.get(i));
                    visited.add(vertices.get(i));
                }
            }
        }

        return result;
    }


    public List<V> dfs(V start) {
        List<V> result = new ArrayList<>();
        Set<V> visited = new HashSet<>();

        int startIndex = vertices.indexOf(start);
        visited.add(vertices.get(startIndex));
        result.add(vertices.get(startIndex));

        Stack<Integer> stack = new Stack<>();
        stack.push(startIndex);

        while (!stack.isEmpty()) {
            int currentIndex = stack.pop();
            for (int i = 0; i < vertices.size(); i++) {
                if (adjacencyMatrix[currentIndex][i] == 1 && !visited.contains(vertices.get(i))) {
                    visited.add(vertices.get(i));
                    result.add(vertices.get(i));
                    stack.push(i);
                }
            }
        }

        return result;
    }
    public boolean hasEdge(V source, V destination) {
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);
        if (sourceIndex == -1 || destinationIndex == -1) {
            throw new IllegalArgumentException("Uno o ambos vértices no existen en el grafo.");
        }
        return adjacencyMatrix[sourceIndex][destinationIndex] == 1;
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

    public ArrayList<Integer> dijkstraM(V sourceVertex) {
        int size = vertices.size();
        int[] distance = new int[size];
        boolean[] visited = new boolean[size];
        Arrays.fill(distance, Integer.MAX_VALUE / 2);
        Arrays.fill(visited, false);

        int sourceIndex = vertices.indexOf(sourceVertex);
        if (sourceIndex == -1) {
            throw new IllegalArgumentException("El vértice fuente no existe en el grafo.");
        }
        distance[sourceIndex] = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(i -> distance[i]));
        priorityQueue.add(sourceIndex);

        while (!priorityQueue.isEmpty()) {
            int currentIndex = priorityQueue.poll();

            if (visited[currentIndex]) continue;
            visited[currentIndex] = true;

            for (int i = 0; i < size; i++) {
                if (adjacencyMatrix[currentIndex][i] != Integer.MAX_VALUE / 2) {
                    int newDistance = distance[currentIndex] + adjacencyMatrix[currentIndex][i];
                    if (newDistance < distance[i]) {
                        distance[i] = newDistance;
                        priorityQueue.add(i);
                    }
                }
            }
        }

        ArrayList<Integer> distances = new ArrayList<>();
        for (int dist : distance) {
            distances.add(dist);
        }

        return distances;
    }
}