package ui;
import model.*;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    private Scanner sc;
    private DirectedListGraph<Integer> graph;
    public Main(){
        sc = new Scanner(System.in);
        graph = new DirectedListGraph<>(5);
    }

    public static void main(String[] args) {
        Main m = new Main();
        //m.isConnected();
        m.runBFS();
        m.runFloydWarshall();
    }

    public void isConnected(){

        String option = "0";
        int vertexes = 0;
        int edges = 0;
        String vertexKey = "";
        while(!option.equalsIgnoreCase("1")){
            System.out.println("Please enter the number of vertexes of the directed graph: ");
            vertexes = sc.nextInt();
            DirectedListGraph<Integer> graph = new DirectedListGraph<>(vertexes);
            System.out.println("Adding all these vertexes to our directer graph...");
            for (int i = 0; i < vertexes; i++) {
                //Vertex<Integer> vertex = new Vertex<>(i+1);
                graph.addVertex((i+1));
            }
            System.out.println("Now, please enter the number of edges of the directed graph: ");
            edges = sc.nextInt();
            System.out.println("Now lets add all these edges to our directer graph");
            System.out.println("For example: 1 (enter) 2 (enter)");
            for (int i = 0; i < edges; i++) {
                System.out.println("Edge #"+(i+1)+": ");
                graph.addEdge(sc.nextInt(), sc.nextInt());
            }
            System.out.println("Is it full connected?: "+graph.isFullConnected());
            System.out.println("Type 1 if you want to exit the program. Else, type anything else");
            sc.nextLine(); //clear input buffer
            option = sc.nextLine();
        }


    }

    public void buildGraph() {

    }

    public void runBFS() {
        System.out.println("Running BFS traversal:");

        // Crear una instancia de DirectedListGraph
        DirectedListGraph<Integer> graph = new DirectedListGraph<>(5); // Puedes especificar el número de vértices según tus necesidades

        // Añadir vértices y aristas al grafo
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        // Obtener un vértice de inicio (puedes modificar esto según tus necesidades)
        Vertex<Integer> startVertex = graph.getVertexOfData(1);

        // Ejecutar BFS en el grafo
        graph.bfs(startVertex);

        // Imprimir los resultados de BFS
        System.out.println(graph.bfsResult());
    }

    public void runFloydWarshall() {
        System.out.println("Running Floyd-Warshall algorithm:");


        DirectedListGraph<Integer> graph = new DirectedListGraph<>(5);


        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);


        // Ejecutar el algoritmo de Floyd-Warshall en el grafo
        int[][] dist = graph.floydWarshall();

        // Imprimir la matriz de distancias mínimas
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[i].length; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

}





/*
        // Crear un grafo no dirigido con lista de adyacencia
        UndirectedListGraph<String> graph = new UndirectedListGraph<>();

        // Agregar vértices al grafo
        Vertex<String> vertex1 = new Vertex<>("u");
        Vertex<String> vertex2 = new Vertex<>("v");
        Vertex<String> vertex3 = new Vertex<>("y");
        Vertex<String> vertex4 = new Vertex<>("x");
        Vertex<String> vertex5 = new Vertex<>("w");
        Vertex<String> vertex6 = new Vertex<>("z");

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);
        graph.addVertex(vertex6);

        // Agregar aristas al grafo
        graph.addEdge(vertex1, vertex2, 0);
        graph.addEdge(vertex1, vertex4, 0);
        graph.addEdge(vertex2, vertex3, 0);
        graph.addEdge(vertex3, vertex4, 0);
        graph.addEdge(vertex4, vertex2, 0);
        graph.addEdge(vertex5, vertex6, 0);
        graph.addEdge(vertex5, vertex3, 0);
        graph.addEdge(vertex6, vertex6, 0);

        // Realizar un recorrido BFS desde el vértice 1
        System.out.println("Recorrido BFS desde el vértice 1:");
        graph.bfs(vertex1);
        System.out.println("Recorrido DFS del grafo:");
        System.out.println(graph.dfsResult());
        graph.dfs();
        System.out.println(graph.dfsResult());

*/