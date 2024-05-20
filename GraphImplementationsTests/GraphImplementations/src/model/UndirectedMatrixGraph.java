package model;
import java.util.*;

public class UndirectedMatrixGraph<T> implements IGraph<T>{

    int numOfVertexes;

    private int[][] adjMatrix;

    private ArrayList<Vertex<T>> auxiliarList;

    private int[][] dist;

    public UndirectedMatrixGraph(int numOfVertexes) {
        this.numOfVertexes = numOfVertexes;
        auxiliarList = new ArrayList<>(numOfVertexes);
        adjMatrix = new int[numOfVertexes][numOfVertexes];
        dist = new int[numOfVertexes][numOfVertexes];
        initializeWeightMatrix();
    }
    
    private void initializeWeightMatrix(){
        for (int i = 0; i < numOfVertexes; i++) {
            for (int j = 0; j < numOfVertexes; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    @Override
    public void addVertex(T vertexData) {
        Vertex<T> vertex = new Vertex<>(vertexData);
        auxiliarList.add(vertex);
    }

    @Override
    public void addEdge(Integer source, Integer destination){
        int indexSource = auxiliarList.indexOf(getVertexOfData(source));
        System.out.println("Source index: "+indexSource);
        int indexDestination = auxiliarList.indexOf(getVertexOfData(destination));
        System.out.println("Destination index: "+indexDestination);
        adjMatrix[indexSource][indexDestination] = 1;
        adjMatrix[indexDestination][indexSource] = 1;

    }
    @Override
    public Vertex<T> getVertexOfData(Integer data){
        for (Vertex<T> v : auxiliarList) {
            if(v.getData().equals(data)){
                return v;
            }
        }
        return null;
    }

    public void /*ArrayList<Vertex<T>>*/ bfs(Vertex<T> startingVertex){
        Vertex<T> u = new Vertex<>();
        for (Vertex<T> v : this.auxiliarList) {
            if(!startingVertex.equals(v)){
                v.setColor(Color.WHITE);
                v.setD(Integer.MAX_VALUE);
                v.setPi(null);
            }
        }
        startingVertex.setColor(Color.GRAY);
        startingVertex.setD(0);
        startingVertex.setPi(null);
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(startingVertex);
        while(!queue.isEmpty()){
            u = queue.poll();
            int currentIndex = auxiliarList.indexOf(u);
            for(int i = 0; i < auxiliarList.size(); i++) {
                Vertex<T> v = auxiliarList.get(i);
                if (adjMatrix[currentIndex][i] == 1 && v.getColor() == Color.WHITE){
                    v.setColor(Color.GRAY);
                    v.setD(u.getD() + 1);
                    v.setPi(u);
                    queue.add(v);
                }
            }
            u.setColor(Color.BLACK);
            //System.out.println(u);
        }
    }

    public void dfs(){
        for (Vertex<T> u : this.auxiliarList) {
            u.setColor(Color.WHITE);
            u.setPi(null);
        }
        int time = 0;
        for (Vertex<T> u : this.auxiliarList) {
            if(u.getColor() == Color.WHITE){
                dfsVisit(u, time);
            }
        }
    }
    private void dfsVisit(Vertex<T> vertex, int time){
        time = time + 1;
        vertex.setD(time);
        vertex.setColor(Color.GRAY);
        int currentIndex = auxiliarList.indexOf(vertex);
        for(int i = 0; i < auxiliarList.size(); i++) {
            Vertex<T> v = auxiliarList.get(i);
            if (adjMatrix[currentIndex][i] == 1 && v.getColor() == Color.WHITE){
                v.setPi(vertex);
                dfsVisit(v, time);
            }
        }
        vertex.setColor(Color.BLACK);
        time = time + 1;
        vertex.setF(time);
    }


    /*Algorithms needed for Dijkstra*/
    public void initializeSingleSource(Vertex<T> vertex){
        for (Vertex<T> v : this.auxiliarList) {
            v.setD(Integer.MAX_VALUE);
            v.setPi(null);
        }
        vertex.setD(0);
    }

}
