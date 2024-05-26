package com.example.bomberman.model;

public class Enemy {
    private Vertex<Integer> position;

    public Enemy(Vertex<Integer> startPosition) {
        this.position = startPosition;
    }

    public Vertex<Integer> getPosition() {
        return position;
    }

    public void setPosition(Vertex<Integer> position) {
        this.position = position;
    }
}
