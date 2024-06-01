package com.example.bomberman.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Vertex<T> implements Comparable<Vertex<T>>{

    private T data;

    private int x;
    private int y;


    private Color color;

    private Vertex<T> pi; //predecessor of the vertex

    private int d; //Node level in BFS, discover time in DFS.

    private int f; //Time when we end the exploration of the vertex. Only used in DFS.

    //private ArrayList<Vertex<T>> adjacencyList;


    public Vertex() {
        this.data = null;
        this.color = Color.WHITE;
        this.pi = null;
        this.d = Integer.MAX_VALUE;
        this.f = 0;
    }

    public Vertex(T data) {
        this.data = data;
        this.color = Color.WHITE;
        this.pi = null;
        this.d = Integer.MAX_VALUE;
        this.f = 0;
    }

    public Vertex(T data, int x, int y) {
        this.data = data;
        this.color = Color.WHITE;
        this.pi = null;
        this.d = Integer.MAX_VALUE;
        this.f = 0;
        this.x = x;
        this.y = y;
    }

    public T getData() {
        return data;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vertex<T> getPi() {
        return pi;
    }

    public void setPi(Vertex<T> pi) {
        this.pi = pi;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    @Override
    public String toString(){
        return "Vertex value: "+this.data;
    }

    @Override
    public int compareTo(Vertex<T> other) {
        return Integer.compare(this.d, other.getD());
    }
}
