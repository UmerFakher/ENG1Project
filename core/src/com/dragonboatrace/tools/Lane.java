package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.ObstacleType;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.entities.boats.ComputerBoat;
import com.dragonboatrace.entities.boats.PlayerBoat;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Lane {

    private Hitbox area;
    private ArrayList<Obstacle> obstacles;
    private ScrollingBackground background;
    public boolean finishLine;

    public Lane(Vector2 pos, int width){
        this.area = new Hitbox(pos.x, pos.y, width, Gdx.graphics.getHeight() + 200);
        this.obstacles = new ArrayList<>();
        populateList();
        this.background = new ScrollingBackground();
        this.background.resize(width, Gdx.graphics.getHeight());
        this.finishLine = false;
    }

    public void update(float deltaTime, float vel){
        for(int i = 0; i < obstacles.size(); i++){
            Obstacle obstacle = obstacles.get(i);
            obstacle.update(deltaTime, vel);
            /* If the obstacle is off the screen */
            if(obstacle.getHitBox().leaves(this.area)) {
                replaceObstacle(i);
            }
        }
        this.background.update(deltaTime, vel);
    }

    public void render(SpriteBatch batch){
        this.background.render(batch);

        for(Obstacle obstacle : obstacles){
            obstacle.render(batch);
        }
    }

    public ArrayList<Obstacle> getObstacles() { return this.obstacles; }

    public Hitbox getHitbox() { return this.area; }

    public void removeObstacle(Obstacle toRemove){
        replaceObstacle(obstacles.indexOf(toRemove));
    }

    public void replaceObstacle(int index){
        obstacles.get(index).dispose();
        obstacles.set(index, new Obstacle(randomObstacle(), this.area.getX(), this.area.getWidth()));
    }

    /* Generate a random Obstacle */
    /* Temporary for debugging speeds */
    public ObstacleType randomObstacle(){
        int rand = ThreadLocalRandom.current().nextInt(0, 3);
        switch(rand){
            case 0: return ObstacleType.BRANCH;
            case 1: return ObstacleType.LEAF;
            case 2: return ObstacleType.ROCK;
            default: return ObstacleType.BRANCH;
        }
    }

    /* Populate list */
    /* Temporary for debugging */
    public void populateList(){
        for(int i = 0; i < 10; i++){
            obstacles.add(new Obstacle(randomObstacle(), this.area.getX(), this.area.getWidth()));
        }
    }

}
