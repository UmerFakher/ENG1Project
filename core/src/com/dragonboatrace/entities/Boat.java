package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Boat extends Entity {

    protected Texture img;
    protected BoatType type;
    

    public Boat(Vector2 pos, BoatType boatType) {
        super(pos, "circle.png");
        this.type = boatType;
    }


    public Texture getImg(){return this.img;}

    public void update(float deltaTime){
        if (Gdx.input.isKeyPressed(Keys.LEFT)){


            this.vel.add(-this.type.getSpeed(), 0).scl(deltaTime);
        }
        else if (Gdx.input.isKeyPressed(Keys.RIGHT)){
            this.vel.add(this.type.getSpeed(), 0).scl(deltaTime);
        }     
        
        super.update(deltaTime);

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
