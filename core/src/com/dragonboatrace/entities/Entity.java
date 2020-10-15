package com.dragonboatrace.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public abstract class Entity{

    protected Vector2 pos;
    protected Vector2 vel;
    protected Texture img;

    public Entity(Vector2 pos, Texture img){
        this.pos = pos;
        this.vel = new Vector2(0,0);
        this.img = img;
    }

    public void update(float deltaTime){
        float dampen = (float)0.8;

        if (!(this.vel.isZero((float)0.001))){
            this.pos.add(this.vel);
            this.vel.scl(dampen);
        }

    }

    public void render(SpriteBatch batch){
        batch.begin();
        batch.draw(this.img, this.pos.x, this.pos.y);
        batch.end();
    }

    public void dispose() {
        this.img.dispose();
    }
    
    public Vector2 getPos(){return this.pos;}

}