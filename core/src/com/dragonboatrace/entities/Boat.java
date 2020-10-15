package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Boat extends Entity {

    private float health, stamina, agility, speed;

    public Boat(BoatType boat, String texture){
        super(new Vector2(), new Vector2(), EntityType.BOAT, texture);
        this.health = boat.getHealth();
        this.stamina = boat.getStamina();
        this.agility = boat.getAgility();
        this.speed = boat.getSpeed();
    }

    public Boat(float health, float stamina, float agility, float speed, String texture){
        super(new Vector2(), new Vector2(), EntityType.BOAT, texture);
        this.health = health;
        this.stamina = stamina;
        this.agility = agility;
        this.speed = speed;
    }

    public void update(float deltaTime){
        
        if (Gdx.input.isKeyPressed(Keys.LEFT)){
            this.vel.add(-this.speed*deltaTime, 0);
        }
        else if (Gdx.input.isKeyPressed(Keys.RIGHT)){
            this.vel.add(this.speed*deltaTime, 0);
        }     
        
        super.update(deltaTime);

    }

    public void render(SpriteBatch batch){
        //batch.begin();
        batch.draw(this.texture, this.pos.x, this.pos.y);
        //batch.end();
    }


}
