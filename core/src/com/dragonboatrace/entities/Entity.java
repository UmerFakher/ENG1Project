package com.dragonboatrace.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity{

    protected Vector2 pos;
    protected Vector2 acc;
    protected EntityType type;

    public Entity(Vector2 pos, Vector2 acc, EntityType type){
        this.pos = pos;
        this.acc = acc;
        this.type = type;
    }

    public abstract void update(float deltaTime);

}