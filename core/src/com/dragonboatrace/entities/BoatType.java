package com.dragonboatrace.entities;


public enum BoatType{
    /* ENUM(health, stamina, agility, speed) */
    
    FAST(50, 80, 60, 100),
    AGILE(50, 80, 100, 60),
    ENDURANCE(70, 100, 50, 50),
    STRONG(100, 70, 40, 40);

    private float health, stamina, agility, speed;

    BoatType(float health, float stamina, float agility, float speed){
        this.health = health;
        this.stamina = stamina;
        this.agility = agility;
        this.speed = speed;
    }
 
    public float getHealth(){return this.health;}
    public float getStamina(){return this.stamina;}
    public float getAgility(){return this.agility;}
    public float getSpeed(){return this.speed;}
    
}
