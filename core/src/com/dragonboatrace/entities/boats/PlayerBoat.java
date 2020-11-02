package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.tools.Lane;

public class PlayerBoat extends Boat{

    public PlayerBoat(Vector2 pos, BoatType boat, String texture, Lane lane) {
        super(pos, boat, texture, lane);
    }

    public PlayerBoat(BoatType boat, String texture, Lane lane) {
        super(boat, texture, lane);
    }

    public void update(float deltaTime){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && this.pos.x > 0){
            this.vel.add(-this.speed*deltaTime, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (this.pos.x+this.type.getWidth()) < this.lane.getHitbox().getWidth()){
            this.vel.add(this.speed*deltaTime, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            this.vel.add(0, this.speed*deltaTime/2);
        }
        checkCollisions();
        super.update(deltaTime);
    }

    protected void checkCollisions(){
        super.checkCollisions();
        if(this.health <= 0){
            Gdx.app.exit();
        }
    }

}
