package com.dragonboatrace.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity{

    protected Vector2 pos;
    protected Vector2 vel;
    protected EntityType type;
    protected Texture texture;

    public Entity(Vector2 pos, Vector2 vel, EntityType type, String texture){
        this.pos = pos;
        this.vel = vel;
        this.type = type;
        this.texture = new Texture(texture);
    }

    public void update(float deltaTime){
        float dampen = (float)0.8;

        if (!(this.vel.isZero((float)0.001))){
            this.pos.add(this.vel);
            this.vel.scl(dampen);
        }
    }

    public Texture getTexture(){return this.texture;}

    public abstract void render(SpriteBatch batch);

}