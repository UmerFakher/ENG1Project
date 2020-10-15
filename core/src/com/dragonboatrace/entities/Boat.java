package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Boat extends Entity {

    private Texture img;
    private BoatType boatType;
    

    public Boat(Vector2 pos, BoatType boatType, String imgPath) {
        super(pos, new Vector2(0, 0), EntityType.BOAT);
        this.boatType = boatType;
        this.img = new Texture(imgPath);
    }


    public Texture getImg(){return this.img;}

    public void update(float deltaTime){
        if (Gdx.input.isKeyPressed(Keys.LEFT)){
            this.pos.add(-this.boatType.getSpeed()*deltaTime, 0);
        }
        else if (Gdx.input.isKeyPressed(Keys.RIGHT)){
            this.pos.add(this.boatType.getSpeed()*deltaTime, 0);
        }
        

    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(this.img, this.pos.x, this.pos.y);
        batch.end();
    }


}
