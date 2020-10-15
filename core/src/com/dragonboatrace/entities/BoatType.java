package com.dragonboatrace.entities;


public enum BoatType{
    /* ENUM(BoatID, Speed, Manoverablilty, Condition) */
    FAST("Fast Boat", 100, 100, 200, 5, 5),
    NORMAL("Normal Boat", 100, 100,130, 7, 7),
    BIG("Big Boat", 100, 100, 90, 5, 10);

    private String id;
    private int width, height, speed, manoverablilty, condition;

    private BoatType(String id, int width, int height, int speed, int manoverablilty, int condition){
        this.id = id;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.manoverablilty = manoverablilty;
        this.condition = condition;
    }
 
    
    public String getID(){return this.id;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
    public int getSpeed(){return this.speed;}
    public int getManoverability(){return this.manoverablilty;}
    public int getCondition(){return this.condition;}
}
