package com.dragonboatrace.entities;

public enum ObstacleType {
    /* ENUM(texture, speed, damage)*/
    ROCK("rock.png", 50, 20),
    BRANCH("branch.png", 60, 10),
    LEAF("leaf.png", 75, 5);

    private final String texture;
    private final float speed;
	private final float damage;

    ObstacleType(String texture, float speed, float damage) {
        this.texture = texture;
        this.speed = speed;
        this.damage = damage;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getDamage() {
        return this.damage;
    }

    public String getTexture() {
        return this.texture;
    }
}