package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.Text;

import java.lang.Math;

public class Obstacle extends Entity{

    protected ObstacleType type;

	public Obstacle(ObstacleType type) {
        super(new Vector2((int)(Math.random()*(Gdx.graphics.getWidth())), Gdx.graphics.getHeight()), new Texture(type.getImgSrc()));
        this.type = type;        
    }


    public void update(float deltaTime){
        this.vel = new Vector2(0, -100).scl(deltaTime);
        super.update(deltaTime);
        
    }

    public ObstacleType getType(){return this.type;}
    public Texture getImg(){return this.img;}

}

