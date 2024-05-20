package model;

import java.time.temporal.ValueRange;
import java.util.*;

public class DirectedListGraph<T> implements IGraph<T>{

    int numOfVertexes;
    private Map<Vertex<T>, ArrayList<Edge<T>>> adjacencyList;

    public ArrayList<Vertex<T>> auxiliarList;

    public DirectedListGraph(int numOfVertexes){
        this.numOfVertexes = numOfVertexes;
        adjacencyList = new HashMap<>();
        auxiliarList = new ArrayList<>();
    }

    @Override
    public void addVertex(T vertexData) {
        Vertex<T> vertex = new Vertex<>(vertexData);
        adjacencyList.put(vertex, new ArrayList<>());
        auxiliarList.add(vertex);
    }

    /*@Override
    public void addEdge(Vertex<T> source, Vertex<T> destination, int weight) {
        adjacencyList.get(source).add(new Edge<>(destination, weight));
    }*/

    public Set<Vertex<T>> getVertices() {
        return adjacencyList.keySet();
    }

    private int vertexIndex(Vertex<T> vertex) {
        int index = 0;
        for (Vertex<T> v : adjacencyList.keySet()) {
            if (v.equals(vertex)) {
                return index;
            }
            index++;
        }
        throw new IllegalArgumentException("Vertex not found in the graph: " + vertex);
    }


    @Override
    public void addEdge(Integer source, Integer destination){
        adjacencyList.get(getVertexOfData(source)).add(new Edge<>(getVertexOfData(destination),0));
    }

    public void addEdge(Integer source, Integer destination, int weight){
        adjacencyList.get(getVertexOfData(source)).add(new Edge<>(getVertexOfData(destination),weight));
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

    public ArrayList<Edge<T>> getEdges(Vertex<T> sourceVertex) {
        return adjacencyList.get(sourceVertex);
    }

    public int getEdgeWeight(Vertex<T> source, Vertex<T> destination){
        int weight = Integer.MAX_VALUE;
        boolean founded = false;
        ArrayList<Edge<T>> sourceEdges = getEdges(source);
        for (int i = 0; i < sourceEdges.size() && !founded; i++) {
            if(sourceEdges.get(i).getDestinationVertex().equals(destination)){
                weight = sourceEdges.get(i).getWeight();
            }
        }
        return weight;
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
            for (Edge<T> e: adjacencyList.get(u)) {
                Vertex<T> v = e.getDestinationVertex();
                if(v.getColor() == Color.WHITE){
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
        for (Edge<T> e: adjacencyList.get(vertex)) {
            Vertex<T> v = e.getDestinationVertex();
            if(v.getColor() == Color.WHITE){
                v.setPi(vertex);
                dfsVisit(v, time);
            }
        }
        vertex.setColor(Color.BLACK);
        time = time + 1;
        vertex.setF(time);
    }

    public String dfsResult(){
        String msg = "";
        int counter = 1;
        for (Vertex<T> v : auxiliarList) {
            msg += "Vertex #"+counter+".\n";
            msg += "D value: "+v.getD()+"\n";
            msg += "F value: "+v.getF()+"\n";
            counter++;
        }
        return msg;
    }

    public String bfsResult(){
        String msg = "";
        int counter = 1;
        for (Vertex<T> v : auxiliarList) {
            msg += "Vertex #"+counter+".\n";
            msg += "Color: "+v.getColor()+"\n";
            msg += "Lvl: "+v.getD()+"\n";
            counter++;
        }
        return msg;
    }


    /*--------------------Algorithms needed for Dijkstra--------------------*/
    public void initializeSingleSource(Vertex<T> vertex){
        for (Vertex<T> v : this.auxiliarList) {
            v.setD(Integer.MAX_VALUE);
            v.setPi(null);
        }
        vertex.setD(0);
    }

    public void relax(Vertex<T> u,Vertex<T> v){
        if(v.getD() > u.getD() + getEdgeWeight(u,v)){
            v.setD(u.getD() + getEdgeWeight(u,v));
            v.setPi(u);
        }
    }

    public Set<Vertex<T>> dijkstra(Vertex<T> source){

        initializeSingleSource(source);
        Set<Vertex<T>> set = new HashSet<Vertex<T>> ();
        PriorityQueue<Vertex<T>> prioQueue = new PriorityQueue<>(Comparator.comparingInt(Vertex::getD));
        prioQueue.addAll(this.auxiliarList);
        while(!prioQueue.isEmpty()) {
            Vertex<T> u = prioQueue.poll();
            set.add(u);
            for (Edge<T> e : adjacencyList.get(u)) {
                Vertex<T> v = e.getDestinationVertex();
                relax(u, v);
            }
        }
        //System.out.println(set);
        return set;
    }
    /*----------------------------------------------------------------------*/

    public int[][] floydWarshall(){

        int size = adjacencyList.size();
        int [][] dist = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i == j){
                    dist[i][j] = 0;
                }else{
                    dist[i][j] = Integer.MAX_VALUE/2;
                }
            }
        }

        for (Vertex<T> vertex : adjacencyList.keySet()) {
            int fromIndex = vertexIndex(vertex);
            for (Edge<T> edge : adjacencyList.get(vertex)) {
                int toIndex = vertexIndex(edge.getDestinationVertex());
                dist[fromIndex][toIndex] = edge.getWeight();
            }
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    public boolean isFullConnected(){
        for (Vertex<T> v : auxiliarList) {
            bfs(v);
            for (Vertex<T> u : auxiliarList) {
                if(u.getD() == Integer.MAX_VALUE){
                    return false;
                }
            }
        }
        return true;
    }
}

