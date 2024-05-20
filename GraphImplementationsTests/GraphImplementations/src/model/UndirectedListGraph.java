package model;
import java.util.*;

public class UndirectedListGraph<T> implements IGraph<T>{

    private Map<Vertex<T>, ArrayList<Edge<T>>> adjacencyList;

    private ArrayList<Vertex<T>> auxiliarList;

    public UndirectedListGraph() {
        adjacencyList = new HashMap<>();
        auxiliarList = new ArrayList<>();
    }

    @Override
    public void addVertex(T vertexData) {
        Vertex<T> vertex = new Vertex<>(vertexData);
        adjacencyList.put(vertex, new ArrayList<>());
        auxiliarList.add(vertex);
    }

    /*public void addEdge(Vertex<T> vertex1, Vertex<T> vertex2, int weight) {
        adjacencyList.get(vertex1).add(new Edge<>(vertex2, weight));
        adjacencyList.get(vertex2).add(new Edge<>(vertex1, weight));
    }*/

    @Override
    public void addEdge(Integer source, Integer destination){
        adjacencyList.get(getVertexOfData(source)).add(new Edge<>(getVertexOfData(destination),0));
        adjacencyList.get(getVertexOfData(destination)).add(new Edge<>(getVertexOfData(source),0));

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

    public ArrayList<Edge<T>> getEdges(Vertex<T> vertex) {
        return adjacencyList.get(vertex);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Vertex<T> vertex : adjacencyList.keySet()) {
            result.append(vertex).append(": ").append(adjacencyList.get(vertex)).append("\n");
        }
        return result.toString();
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

}




