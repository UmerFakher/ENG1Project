package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


public class Boat extends Entity {

    protected BoatType type;
    

    public Boat(Vector2 pos, BoatType boatType) {
        super(pos, new Texture(boatType.getImgSrc()));
        this.type = boatType;
    }


    public Texture getImg(){return this.img;}

    public void update(float deltaTime){
        if (Gdx.input.isKeyPressed(Keys.LEFT) && this.pos.x >= 0){
            this.vel.add(-this.type.getManoverability(), 0).scl(deltaTime);
        }
        else if (Gdx.input.isKeyPressed(Keys.RIGHT) && (this.pos.x+this.type.getWidth()) <= Gdx.graphics.getWidth()){
            this.vel.add(this.type.getManoverability(), 0).scl(deltaTime);
        }     
        super.update(deltaTime);
        if (this.pos.x < 0){
            this.pos.x = 0;
        } else if (this.pos.x+ this.type.getWidth() > Gdx.graphics.getWidth()){
            this.pos.x = Gdx.graphics.getWidth() - this.type.getWidth();
        }
    }

    public boolean collision(Obstacle Obstacle){
        float boatX = this.pos.x;
        float boatX2 = this.pos.x+this.type.getWidth();
        float boatY = this.pos.y;
        float boatY2 = this.pos.y+this.type.getHeight();

        for (int dx = 0; dx < 2; dx++){
            for (int dy = 0; dy < 2; dy++){
                float x = Obstacle.pos.x+dx*(Obstacle.getType().getWidth());
                float y = Obstacle.pos.y+dy*(Obstacle.getType().getHeight());
                if ((x > boatX && x < boatX2) && (y > boatY && y < boatY2)){
                    return true;
                }

            }
        }       
        return false;
    }
}
