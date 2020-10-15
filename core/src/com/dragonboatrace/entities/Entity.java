package com.dragonboatrace.entities;

import java.security.KeyStore.ProtectionParameter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public abstract class Entity{

    protected Vector2 pos;
    protected Vector2 vel;
    protected EntityType type;
    protected Texture img;

    public Entity(Vector2 pos, EntityType type, String imgSrc){
        this.pos = pos;
        this.vel = new Vector2(0,0);
        this.type = type;
        this.img = new Texture(imgSrc);
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

    public void dispose () {
        this.img.dispose();
	}

}