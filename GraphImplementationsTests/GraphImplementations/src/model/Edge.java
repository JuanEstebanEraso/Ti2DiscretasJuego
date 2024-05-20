package model;

public class Edge<T> implements Comparable<Edge<T>> {

    private Vertex<T> destinationVertex;
    private int weight;

    public Edge(Vertex<T> destinationVertex, int weight) {
        this.destinationVertex = destinationVertex;
        this.weight = weight;
    }

    public Vertex<T> getDestinationVertex() {
        return destinationVertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + destinationVertex + ", " + weight + ")";
    }

    @Override
    public int compareTo(Edge<T> other) {
        return Integer.compare(this.weight, other.getWeight());
    }


}