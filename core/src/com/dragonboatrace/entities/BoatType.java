package com.dragonboatrace.entities;


public enum BoatType{
    /* ENUM(BoatID, Width, Height, Speed, Manoverablilty, Condition, Image Source) */
    FAST("Fast Boat", 100, 100, 250, 175, 3, "boat.png"),
    SMALL("Small Boat", 100, 100, 130, 250, 5, "boat.png"),
    BIG("Big Boat", 100, 100, 100, 125, 10, "boat.png");

    private String id, imgSrc;
    private int width, height, speed, manoverablilty, condition;

    private BoatType(String id, int width, int height, int speed, int manoverablilty, int condition, String imgSrc){
        this.id = id;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.manoverablilty = manoverablilty;
        this.condition = condition;
        this.imgSrc = imgSrc;
    }
 
    
    public String getID(){return this.id;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
    public int getSpeed(){return this.speed;}
    public int getManoverability(){return this.manoverablilty;}
    public int getCondition(){return this.condition;}
    public String getImgSrc(){return this.imgSrc;}
}
