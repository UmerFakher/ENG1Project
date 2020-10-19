package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.tools.Hitbox;

import java.util.concurrent.ThreadLocalRandom;

public class Obstacle extends Entity{

	private float speed, damage;

    public Obstacle(ObstacleType type, float startX, int width){
        /* Entity creation */
        /* First vector is long as to start it at a random x position within the bounds of the screen */
        /* Form of Entity(Vector2 pos, Vector2 vel, EntityType type, String texture) */
    	super(new Vector2(((int)startX+width)/2 + ThreadLocalRandom.current().nextInt(-((int)startX+width)/2 + EntityType.OBSTACLE.getWidth() /2, ((int)startX+width)/2 + EntityType.OBSTACLE.getWidth() /2), Gdx.graphics.getHeight()), new Vector2(), EntityType.OBSTACLE, type.getTexture());
        this.speed = type.getSpeed();
        this.damage = type.getDamage();
    }

    /* For debugging with custom values instead of ObstacleType */
    public Obstacle(float speed, float damage, String texture){
        super(new Vector2(Gdx.graphics.getWidth()/2 + ThreadLocalRandom.current().nextInt(-Gdx.graphics.getWidth()/2 + EntityType.OBSTACLE.getWidth() /2, Gdx.graphics.getWidth()/2 + EntityType.OBSTACLE.getWidth() /2), Gdx.graphics.getHeight()), new Vector2(), EntityType.OBSTACLE,texture);
    	this.speed = speed;
        this.damage = damage;
    }

    @Override
    public void update(float deltaTime){
    	this.pos.add(0,-this.speed*deltaTime);
    	this.box.move(this.pos.x, this.pos.y);
    }

    public float getSpeed() { return this.speed; }
    public float getDamage() { return this.damage; }

}
