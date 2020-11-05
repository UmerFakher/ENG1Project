package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.tools.Lane;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerBoat extends Boat{

    private float counter = 0;
    private float startSpeed = pickSpeed(1);

    public ComputerBoat(Vector2 pos, BoatType boat, String texture, Lane lane, String name) {
        super(pos, boat, texture, lane, name);
    }

    public ComputerBoat(BoatType boat, String texture, Lane lane, String name) {
        super(boat, texture, lane, name);
        this.vel = new Vector2(0, startSpeed);
    }

    public void update(float deltaTime){
        //this.counter += deltaTime;
        /*if ((int)this.counter % 2 == 0){
            this.vel.add(-this.speed*deltaTime, 0);
        }
        else{
            this.vel.add(this.speed*deltaTime, 0);
        }*/

        this.vel.add(this.speed*deltaTime*moveFromClosest(findLowest(this.lane.getObstacles())),0);
        float x = this.vel.x;
        this.vel = new Vector2(x, startSpeed);
        checkCollisions();
        super.update(deltaTime);
    }

    private Obstacle findLowest(ArrayList<Obstacle> list){
        Obstacle closest = list.get(0);
        float smallest = closest.getPos().y;

        for(Obstacle obs : list){
            float bottomY = obs.getPos().y;
            if(bottomY < smallest){
                closest = obs;
                smallest = bottomY;
            }
        }
        return closest;
    }

    private int moveFromClosest(Obstacle closest){
        float obstacleLeft = closest.getPos().x;
        float boatLeft = this.pos.x;
        /* If the boat and obstacle are aligned vertically */
        if(Float.compare(obstacleLeft, boatLeft) == 0){
            return 0;
        }
        /* If the obstacle is more to the left of the boat */
        else if(obstacleLeft < boatLeft){
            return -1;
        }
        /* If the obstacle is more the right of the boat */
        else{
            return 1;
        }
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
            return (float)720/speed;
    }


}
