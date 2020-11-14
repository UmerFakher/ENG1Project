package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.entities.ObstacleType;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

public class Lane {

    public boolean finishLine;
    private final Hitbox area;
    private final ArrayList<Obstacle> obstacles;
    private final ArrayList<Float> randomWaitTimes;

    public Lane(Vector2 pos, int width) {
        this.area = new Hitbox(pos.x, pos.y, width, Gdx.graphics.getHeight() + 200);
        this.obstacles = new ArrayList<>();
        this.randomWaitTimes = new ArrayList<>();
        populateList();
        this.finishLine = false;
    }

    public void update(float deltaTime, float vel) {

        ListIterator<Obstacle> iter = obstacles.listIterator();
        while (iter.hasNext()) {
            Obstacle obstacle = iter.next();
            obstacle.update(deltaTime, vel);
            if (obstacle.getHitBox().leaves(this.area)) {
                iter.remove();
                replaceObstacle();
            }
        }

        ListIterator<Float> times = randomWaitTimes.listIterator();
        while (times.hasNext()) {
            float time = times.next() - deltaTime;
            if (time > 0) {
                times.set(time);
            } else {
                obstacles.add(randomObstacle());
                times.remove();
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Obstacle obstacle : obstacles) {
            obstacle.render(batch);
        }
    }

    public ArrayList<Obstacle> getObstacles() {
        return this.obstacles;
    }

    public Hitbox getHitbox() {
        return this.area;
    }

    public void removeObstacle(Obstacle toRemove) {
        obstacles.remove(toRemove);
        replaceObstacle();
    }

    public void replaceObstacle() {
        randomWaitTimes.add(1.0f + 2 * ThreadLocalRandom.current().nextFloat());
    }

    /* Generate a random Obstacle */
    /* Temporary for debugging speeds */
    private Obstacle randomObstacle() {
        int rand = ThreadLocalRandom.current().nextInt(0, 3);
        ObstacleType type;
        switch (rand) {
            case 0:
                type = ObstacleType.BRANCH;
                break;
            case 1:
                type = ObstacleType.LEAF;
                break;
            case 2:
                type = ObstacleType.ROCK;
                break;
            default:
                type = ObstacleType.BRANCH;
                break;
        }

        return new Obstacle(type, this.area.getX(), this.area.getWidth());
    }

    /* Populate list */
    /* Temporary for debugging */
    public void populateList() {
        for (int i = 0; i < 5; i++) {
            replaceObstacle();
        }
    }

}
