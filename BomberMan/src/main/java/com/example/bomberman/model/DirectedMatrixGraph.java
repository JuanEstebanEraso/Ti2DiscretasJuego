package com.example.bomberman.model;
    import java.util.*;
    public class DirectedMatrixGraph<T> implements IGraph<T>{

        private ArrayList<T> vertices;
        private int[][] adjacencyMatrix;

        public DirectedMatrixGraph(int numVertices) {
            vertices = new ArrayList<>(numVertices);
            adjacencyMatrix = new int[numVertices][numVertices];
        }

        public void addVertex(T vertex) {
            vertices.add(vertex);
        }

        @Override
        public void addEdge(Integer source, Integer destination) {

        }

        @Override
        public Vertex<T> getVertexOfData(Integer data) {
            return null;
        }

        public void addEdge(T source, T destination) {
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

        public ArrayList<T> getVertices() {
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


        public List<T> bfs(T start) {
            List<T> result = new ArrayList<>();
            Set<T> visited = new HashSet<>();
            Queue<T> queue = new LinkedList<>();

            int startIndex = vertices.indexOf(start);
            queue.add(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                T current = queue.poll();
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

        public List<T> dfs(T start) {
            List<T> result = new ArrayList<>();
            Set<T> visited = new HashSet<>();

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
    }


