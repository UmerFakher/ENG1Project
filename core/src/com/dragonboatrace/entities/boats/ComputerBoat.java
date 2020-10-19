package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerBoat extends Boat{

    private float counter = 0;

    public ComputerBoat(Vector2 pos, BoatType boat, String texture) {
        super(pos, boat, texture);
    }

    public void update(float deltaTime){
        this.counter += deltaTime;
        if ((int)this.counter % 2 == 0){
            this.vel.add(-this.speed*deltaTime, 0);
        }
        else{
            this.vel.add(this.speed*deltaTime, 0);
        }
        super.update(deltaTime);
    }
}
