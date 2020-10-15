package com.dragonboatrace.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity{

    protected Vector2 pos;
    protected Vector2 vel;
    protected EntityType type;

    public Entity(Vector2 pos, Vector2 vel, EntityType type){
        this.pos = pos;
        this.vel = vel;
        this.type = type;
    }

    public void update(float deltaTime){
        float dampen = (float)0.8;

        if (!(this.vel.isZero((float)0.001))){
            this.pos.add(this.vel);
            this.vel.scl(dampen);
        }

    };

}