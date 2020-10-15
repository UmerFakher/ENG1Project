package com.dragonboatrace.entities;


public enum BoatType{
    /* ENUM(BoatID, Speed, Manoverablilty, Condition) */
    FAST("Fast Boat", 200, 5, 5),
    NORMAL("Normal Boat", 130, 7, 7),
    BIG("Big Boat", 90, 5, 10);

    private String id;
    private int speed, manoverablilty, condition;

    private BoatType(String id, int speed, int manoverablilty, int condition){
        this.id = id;
        this.speed = speed;
        this.manoverablilty = manoverablilty;
        this.condition = condition;
    }
 
    
    public String getID(){return this.id;}
    public int getSpeed(){return this.speed;}
    public int getManoverability(){return this.manoverablilty;}
    public int getCondition(){return this.condition;}
}
