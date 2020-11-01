package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Entity;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.tools.Hitbox;
import com.dragonboatrace.tools.Lane;

import java.util.ArrayList;


public class Boat extends Entity {

    protected float health, stamina, agility, speed;
    protected Lane lane;
    protected Hitbox laneBox;

    public Boat(Vector2 pos, BoatType boat, String texture, Lane lane){
        super(pos, new Vector2(), EntityType.BOAT, texture);
        this.health = boat.getHealth();
        this.stamina = boat.getStamina();
        this.agility = boat.getAgility();
        this.speed = boat.getSpeed();
        this.lane = lane;
        laneBox = lane.getHitbox();
    }

    public Boat(Vector2 pos, float health, float stamina, float agility, float speed, String texture, Lane lane){
        super(pos, new Vector2(), EntityType.BOAT, texture);
        this.health = health;
        this.stamina = stamina;
        this.agility = agility;
        this.speed = speed;
        this.lane = lane;
    }

    public void update(float deltaTime){

        /* Check for Collisions */
        checkCollisions();

        /* Check if boat is still in the lane */
        if(this.box.leaves(laneBox)){
            if (this.pos.x < 0){
                this.pos.x = 0;
            } else{
                this.pos.x = this.lane.getHitbox().getWidth() - this.type.getWidth();
            }
            this.vel.scl(new Vector2(0, 1));
        }

        /* Update lane contents */
        this.lane.update(deltaTime, this.getVelocity().y);

        float dampen = agility/100;

        if (!(this.vel.isZero((float)0.001))){
            this.pos.x += this.vel.x;
            this.vel.scl(dampen);
        }

        /* The hitbox needs moving to keep at the same pos as the boat */
        this.box.move(pos.x, pos.y);
    }

    public void render(SpriteBatch batch){
        this.lane.render(batch);
        batch.draw(this.texture, this.pos.x, this.pos.y);
    }

    public void checkCollisions(){
        ArrayList<Obstacle> obstacles = this.lane.getObstacles();
        int size = obstacles.size();
        for(int i = 0; i < size; i++){
            Obstacle obstacle = obstacles.get(i);
            if(obstacle.getHitBox().collidesWith(this.box)){
                obstacle.dispose();
                this.lane.removeObstacle(obstacle);
                size--;
                this.health -= obstacle.getDamage();
            }
        }
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
