package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Entity;
import com.dragonboatrace.entities.EntityType;


public class Boat extends Entity {

    protected float health, stamina, agility, speed;

    public Boat(Vector2 pos, BoatType boat, String texture){
        super(pos, new Vector2(), EntityType.BOAT, texture);
        this.health = boat.getHealth();
        this.stamina = boat.getStamina();
        this.agility = boat.getAgility();
        this.speed = boat.getSpeed();
    }

    public Boat(Vector2 pos, float health, float stamina, float agility, float speed, String texture){
        super(pos, new Vector2(), EntityType.BOAT, texture);
        this.health = health;
        this.stamina = stamina;
        this.agility = agility;
        this.speed = speed;
    }

    public void update(float deltaTime){

        float dampen = agility/100;

        if (!(this.vel.isZero((float)0.001))){
            this.pos.add(this.vel);
            this.vel.scl(dampen);
        }

        /* The hitbox needs moving to keep at the same pos as the boat */
        this.box.move(pos.x, pos.y);
    }

    public void render(SpriteBatch batch){
        batch.draw(this.texture, this.pos.x, this.pos.y);
    }

    /* Adders */

    public void addVelocity(float pushX, float pushY) { this.vel.add(pushX, pushY); }

    public void addHealth(float change) { this.health += change; }

    public void addStamina(float change) { this.stamina += change; }

    /* Setters */

    public Vector2 getVelocity() { return this.vel; }

    public float getSpeed() { return this.speed; }

    public float getHealth() { return this.health; }

    public float getStamina() { return this.stamina; }

    public float getAgility() { return this.agility; }

    /* Temporary function to simulate hitting edge of lane */
    public void setPos(float x, float y){
        this.pos.set(x,y);
    }
}
