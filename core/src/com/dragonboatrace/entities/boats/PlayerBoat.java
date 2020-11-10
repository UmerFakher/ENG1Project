package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.tools.Lane;

public class PlayerBoat extends Boat{

    public PlayerBoat(BoatType boat, String texture, Lane lane, String name) {
        super(boat, texture, lane, name);
    }

    public void update(float deltaTime){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && this.pos.x > this.lane.getHitbox().getX()){
            this.vel.add(-this.speed*deltaTime, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (this.pos.x+this.type.getWidth()) < this.lane.getHitbox().getWidth() + this.lane.getHitbox().getX()){
            this.vel.add(this.speed*deltaTime, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            this.vel.add(0, this.speed*deltaTime/2);
        }
        checkCollisions();
        super.update(deltaTime);
    }

    public void checkCollisions(){
        super.checkCollisions();

    }

    public boolean isDead(){
        return this.health <=0;
    }


}
