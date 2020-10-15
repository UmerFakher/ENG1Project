package com.dragonboatrace.entities;

public enum EntityType {
    
    BOAT("boat", 10, 10),
    OBSTACLE("obstacle", 5, 5);

    private String id;
    private int width, height;

    private EntityType(String id, int width, int height){
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public String getID(){return this.id;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
}
