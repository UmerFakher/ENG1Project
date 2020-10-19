package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.tools.Lane;

public class PlayerBoat extends Boat{

    public PlayerBoat(Vector2 pos, BoatType boat, String texture, Lane lane) {
        super(pos, boat, texture, lane);
    }

    public void update(float deltaTime){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            this.vel.add(-this.speed*deltaTime, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            this.vel.add(this.speed*deltaTime, 0);
        }
        super.update(deltaTime);
    }

}
