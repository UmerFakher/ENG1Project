package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Entity;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.entities.FinishLine;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.tools.Hitbox;
import com.dragonboatrace.tools.Lane;

import java.util.ArrayList;


public class Boat extends Entity {

    protected float health, stamina, agility, speed, maxSpeed;
    protected Lane lane;
    protected Hitbox laneBox;
    protected int distance;
    protected FinishLine finish;
    protected String name;
    protected BitmapFont font;

    /* No need for specific position specified as boat is put in the middle of the lane. */
    public Boat(BoatType boat, String texture, Lane lane, String name){
        /* Get boat position from the position of the lane. */
        super(new Vector2(lane.getHitbox().getX() + lane.getHitbox().getWidth() /2 , lane.getHitbox().getY()), new Vector2(), EntityType.BOAT, texture);
        this.health = boat.getHealth();
        this.stamina = boat.getStamina();
        this.agility = boat.getAgility();
        this.speed = boat.getSpeed();
        this.maxSpeed = boat.getMaxSpeed();
        this.lane = lane;
        this.distance = 0;
        this.name = name;

        this.font = new BitmapFont(Gdx.files.internal("default.fnt"),false);
        this.font.setColor(Color.RED);
        this.font.getData().setScale(3);

        /* Store the lanes hitbox to save time on using Getters. */
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
        /* Moved collision check to player boat to be able to check if the player has no health. */
        //checkCollisions();

        /* Check if boat is still in the lane */
        if(this.box.leaves(laneBox)){
            if (this.pos.x < this.laneBox.getX()){
                this.pos.x = this.laneBox.getX();
            } else{
                this.pos.x = this.laneBox.getX() + this.laneBox.getWidth() - this.type.getWidth();
            }
            this.vel.scl(new Vector2(0, 1));
        }
        /* Update lane contents */
        if (this.getVelocity().y > this.maxSpeed)
            this.vel.y = this.maxSpeed;

        this.lane.update(deltaTime, this.vel.y);


        float dampen = agility/100;

        if (!(this.vel.isZero((float)0.001))){
            this.pos.x += this.vel.x;
            this.vel.scl(dampen);
        }
        this.distance += this.vel.y;

        /* The hitbox needs moving to keep at the same pos as the boat */
        this.box.move(pos.x, pos.y);
    }

    public void render(SpriteBatch batch){
        this.lane.render(batch);
        font.draw(batch, "Health: " + (int)this.getHealth(), this.lane.getHitbox().getX(), Gdx.graphics.getHeight());
        batch.draw(this.texture, this.pos.x, this.pos.y);
    }

    protected void checkCollisions(){
        ArrayList<Obstacle> obstacles = this.lane.getObstacles();
        int size = obstacles.size();
        for(int i = 0; i < size; i++){
            Obstacle obstacle = obstacles.get(i);
            if(obstacle.getHitBox().collidesWith(this.box)){
                obstacle.dispose();
                this.lane.removeObstacle(obstacle);
                size--;
                this.health -= obstacle.getDamage();
                this.vel.y = -5;

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

    public int getDistance() { return this.distance; }

    public Lane getLane() { return this.lane; }

    public FinishLine getFinish() { return this.finish; }

    public void setFinish(FinishLine fin) { this.finish = fin; }

    public String getName() { return this.name; }

    /* Temporary function to simulate hitting edge of lane */
    public void setPos(float x, float y){
        this.pos.set(x,y);
    }

    public void dispose(){
        font.dispose();
        super.dispose();
    }
}
