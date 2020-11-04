package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.tools.Lane;

import java.util.concurrent.ThreadLocalRandom;

public class ComputerBoat extends Boat{

    private float counter = 0;
    private float startSpeed = pickSpeed(1);

    public ComputerBoat(Vector2 pos, BoatType boat, String texture, Lane lane) {
        super(pos, boat, texture, lane);
    }

    public ComputerBoat(BoatType boat, String texture, Lane lane) {
        super(boat, texture, lane);
        this.vel = new Vector2(0, startSpeed);
    }

    public void update(float deltaTime){
        this.counter += deltaTime;
        if ((int)this.counter % 2 == 0){
            this.vel.add(-this.speed*deltaTime, 0);
        }
        else{
            this.vel.add(this.speed*deltaTime, 0);
        }
        float x = this.vel.x;
        this.vel = new Vector2(x, startSpeed);
        checkCollisions();
        super.update(deltaTime);
    }

    public float pickSpeed(int pos){
        int speed;
        switch (pos) {
            case 2:
                speed = ThreadLocalRandom.current().nextInt(65, 85);
                break;
            case 3:
                speed = ThreadLocalRandom.current().nextInt(75, 95);
                break;
            default:
                speed = ThreadLocalRandom.current().nextInt(55, 75);
        }
            return (float)710/speed;
        }
}
