package com.dragonboatrace.entities;

public enum EntityType {
    
    BOAT(10, 10),
    OBSTACLE(5, 5);

    private int width, height;

    EntityType(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
}
