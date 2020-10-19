package com.dragonboatrace.tools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.entities.boats.ComputerBoat;
import com.dragonboatrace.entities.boats.PlayerBoat;

import java.util.ArrayList;

public class Lane {

    private ArrayList<Obstacle> obstacles;
    private Boat boat;
    private Hitbox area;

    /* Each lane is given a position, size, the boat that is in the lane and if it is player controlled or computer controlled */
    public Lane(Vector2 pos, int width, int height, BoatType boat, boolean isComputer){
        this.area = new Hitbox(pos.x, pos.y, width, height);
        this.boat = (isComputer)?new ComputerBoat(new Vector2(this.area.getWidth() / 2 + this.area.getX(),0), boat, "circle.png"):new PlayerBoat(new Vector2(this.area.getWidth() / 2 + this.area.getX(),0), boat, "circle.png");
        obstacles = new ArrayList<>();
    }

    /* Update the positions of the boat and the obstacles in the lane */
    public void update(float deltaTime){
        this.boat.update(deltaTime);
        for(Obstacle obstacle : this.obstacles){
            obstacle.update(deltaTime);
        }
        checkBounds();
    }

    /* Render the boat and obstacles */
    public void render(SpriteBatch batch){
        this.boat.render(batch);
        for(Obstacle obstacle : this.obstacles){
            obstacle.render(batch);
        }
    }

    /* Check if the boat has left the lane */
    public void checkBounds(){
        if(!this.boat.getHitBox().collidesWith(this.area)){
            this.boat.setPos(this.area.getWidth() / 2 + this.area.getX(), 0);
        }
    }
}
