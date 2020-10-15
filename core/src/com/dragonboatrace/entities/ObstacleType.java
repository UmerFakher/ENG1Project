package com.dragonboatrace.entities;

public enum ObstacleType {
    /* ENUM(ObstacleID, Width, Height, Strength, Image Source) */
    ROCK("rock", 50, 50, 2, "rock.jpg"),
    BIRD("bird", 75, 75, 1, "bird.jpg");

    private String id, imgSrc;
    private int width, height, strength;

    private ObstacleType(String id, int width, int height, int strength, String imgSrc){
        this.id = id;
        this.width = width;
        this.height = height;
        this.strength = strength;
        this.imgSrc = imgSrc;
    }

    public String getID(){return this.id;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
    public int getStrength(){return this.strength;}
    public String getImgSrc(){return this.imgSrc;}
}
