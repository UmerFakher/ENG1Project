package com.dragonboatrace.entities.boats;


public enum BoatType{
    /* ENUM(health, stamina, agility, speed, maxSpeed) */
    
    FAST(50, 4000, 85, 200, 10),
        AGILE(50, 4000, 95, 160, 8),
    ENDURANCE(70, 6000, 90, 100, 8),
    STRONG(100, 3500, 98, 55, 5);

    private float health, stamina, agility, speed, maxSpeed;

    BoatType(float health, float stamina, float agility, float speed, float maxSpeed){
        this.health = health;
        this.stamina = stamina;
        this.agility = agility;
        this.speed = speed;
        this.maxSpeed = maxSpeed;
    }
 
    public float getHealth() { return this.health; }
    public float getStamina() { return this.stamina; }
    public float getAgility() { return this.agility; }
    public float getSpeed() { return this.speed; }
    public float getMaxSpeed() { return this.maxSpeed; }
    
}
