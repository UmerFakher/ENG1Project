package com.dragonboatrace.entities.boats;


public enum BoatType {
    /* ENUM(health, stamina, agility, speed, maxSpeed, image) */

    FAST(50, 120, 85, 275, "fast.png"),
    AGILE(50, 120, 95, 245, "agile.png"),
    ENDURANCE(70, 180, 90, 245, "endurance.png"),
    STRONG(100, 105, 98, 200, "strong.png");

    private final float health;
    private final float stamina;
    private final float agility;
    private final float speed;
    private final String imageSrc;

    BoatType(float health, float stamina, float agility, float speed, String imageSrc) {
        this.health = health;
        this.stamina = stamina;
        this.agility = agility;
        this.speed = speed;
        this.imageSrc = imageSrc;
    }

    public float getHealth() {
        return this.health;
    }

    public float getStamina() {
        return this.stamina;
    }

    public float getAgility() {
        return this.agility;
    }

    public float getSpeed() {
        return this.speed;
    }

    public String getImageSrc() {
        return this.imageSrc;
    }

}
