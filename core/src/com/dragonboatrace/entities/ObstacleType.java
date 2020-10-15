package com.dragonboatrace.entities;

public enum ObstacleType {
    
    ROCK("rock", 50, 50, 2),
    BIRD("bird", 75, 75, 1);

    private String id;
    private int width, height, strength;

    private ObstacleType(String id, int width, int height, int strength){
        this.id = id;
        this.width = width;
        this.height = height;
        this.strength = strength;
    }

    public String getID(){return this.id;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
    public int getStrength(){return this.strength;}
}
