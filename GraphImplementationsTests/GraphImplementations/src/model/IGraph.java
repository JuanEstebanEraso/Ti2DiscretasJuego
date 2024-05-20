package model;

public interface IGraph<T>{


    abstract void addVertex(T vertexData);

    //abstract void addEdge(Vertex<T> sourceVertex, Vertex<T> destinationVertex, int weight);

    //abstract void deleteVertex(V vertex);

    //abstract void deleteEdge(Edge<V> edge);

    abstract void addEdge(Integer source, Integer destination);

    abstract Vertex<T> getVertexOfData(Integer data);






    }
