package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Boat extends Entity {

    private float health, stamina, agility, speed;

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
        
        if (Gdx.input.isKeyPressed(Keys.LEFT)){
            this.vel.add(-this.speed*deltaTime, 0);
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)){
            this.vel.add(this.speed*deltaTime, 0);
        }

        /*(if (Gdx.input.isKeyPressed(Keys.UP)){
            this.vel.add(0, this.speed*deltaTime);
        }

        if (Gdx.input.isKeyPressed(Keys.DOWN)){
            this.vel.add(0, -this.speed*deltaTime);
        }*/

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


}
