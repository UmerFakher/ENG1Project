package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.concurrent.ThreadLocalRandom;

public class Obstacle extends Entity {

    private final float speed;
    private final float damage;

    public Obstacle(ObstacleType type, float startX, int width) {
        /* Entity creation */
        /* First vector is long as to start it at a random x position within the bounds of the screen */
        /* Form of Entity(Vector2 pos, Vector2 vel, EntityType type, String texture) */
        super(new Vector2(((int) startX + width) / 2.0f + ThreadLocalRandom.current().nextInt(-((int) startX + width) / 2 + EntityType.OBSTACLE.getWidth() / 2, ((int) startX + width) / 2 + EntityType.OBSTACLE.getWidth() / 2), Gdx.graphics.getHeight()), new Vector2(), EntityType.OBSTACLE, type.getTexture());
        this.speed = type.getSpeed();
        this.damage = type.getDamage();
    }

    public void update(float deltaTime, float velY) {
        this.pos.add(0, -1 * (velY + this.speed)*deltaTime);
        this.box.move(this.pos.x, this.pos.y);
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getDamage() {
        return this.damage;
    }

    public Vector2 getPos() {
        return this.pos;
    }

}
