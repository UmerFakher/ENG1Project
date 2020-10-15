package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Boat extends Entity {

    private Texture img;
    private BoatType boatType;
    

    public Boat(Vector2 pos, BoatType boatType) {
        super(pos, EntityType.BOAT, "circle.png");
        this.boatType = boatType;
    }


    public Texture getImg(){return this.img;}

    public void update(float deltaTime){
        if (Gdx.input.isKeyPressed(Keys.LEFT)){


            this.vel.add(-this.boatType.getSpeed(), 0).scl(deltaTime);
        }
        else if (Gdx.input.isKeyPressed(Keys.RIGHT)){
            this.vel.add(this.boatType.getSpeed(), 0).scl(deltaTime);
        }     
        
        super.update(deltaTime);

    }

}
