package com.dragonboatrace.entities.boats;


public enum BoatType{
    /* ENUM(health, stamina, agility, speed) */
    
    FAST(50, 80, 90, 150),
    AGILE(50, 80, 70, 100),
    ENDURANCE(70, 100, 80, 65),
    STRONG(100, 70, 99, 50);

    private float health, stamina, agility, speed;

    BoatType(float health, float stamina, float agility, float speed){
        this.health = health;
        this.stamina = stamina;
        this.agility = agility;
        this.speed = speed;
    }
 
    public float getHealth() { return this.health; }
    public float getStamina() { return this.stamina; }
    public float getAgility() { return this.agility; }
    public float getSpeed() { return this.speed; }
    
}
