package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.tools.Hitbox;
import com.dragonboatrace.tools.Lane;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerBoat extends Boat {


    /* Move area is the area in which the boat is looking for obstacles to avoid */
    private final Hitbox moveArea;
    private final int xOffset;
    private final int yOffset;
    private final float startSpeed;
    private boolean wait;

    public ComputerBoat(BoatType boat, Lane lane, String name, int pickSpeedValue) {
        super(boat, lane, name);
        this.startSpeed = this.pickSpeed(pickSpeedValue);
        this.vel = new Vector2(0, startSpeed);
        this.wait = false;
        this.xOffset = this.getHitBox().getWidth() / pickSpeedValue;
        this.yOffset = this.getHitBox().getHeight() / pickSpeedValue;
        this.moveArea = new Hitbox(this.pos.x - xOffset, this.pos.y, this.getHitBox().getWidth() + 2 * xOffset, this.getHitBox().getHeight() + 2 * yOffset);
    }

    public void update(float deltaTime, float currDistance) {
        if (this.getHealth() > 0) {
            if (this.stamina - 5 > 0) {
                Obstacle closest = checkObstacles();
                if (closest != null) {
                    this.vel.add(this.speed * deltaTime * moveFromClosest(closest), 0);
                } else {
                    this.vel.add(this.speed * 0, 0);
                }
                this.moveArea.move(pos.x - this.xOffset, pos.y);

                if (this.wait) {
                    this.stamina += 25;
                    if (this.stamina > ThreadLocalRandom.current().nextInt((int) this.maxStamina / 2, 2 * (int) this.maxStamina / 3))
                        this.wait = false;
                } else if (this.vel.y < startSpeed) {
                    this.vel.add(new Vector2(0, this.speed * deltaTime / 2));
                }
                this.stamina -= 8;
            } else {
                this.wait = true;
            }
        } else {
            this.health = 0;
        }
        if (this.stamina + 3 < this.maxStamina)
            this.stamina += 3;
        else
            this.stamina = this.maxStamina;
        super.update(deltaTime, currDistance);
    }


    private Obstacle checkObstacles() {
        ArrayList<Obstacle> obstacles = this.lane.getObstacles();
        int size = obstacles.size();
        Obstacle closest = null;
        float smallest = Gdx.graphics.getHeight();
        for (int i = 0; i < size; i++) {
            Obstacle obstacle = obstacles.get(i);
            if (obstacle.getHitBox().collidesWith(this.box)) {
                obstacle.dispose();
                this.lane.removeObstacle(obstacle);
                size--;
                this.health -= obstacle.getDamage();
                this.vel.y = -54;
            } else if (obstacle.getHitBox().collidesWith(this.moveArea)) {
                float bottomY = obstacle.getPos().y;
                if (bottomY < smallest) {
                    closest = obstacle;
                    smallest = bottomY;
                }
            }
        }
        return closest;
    }

    private int moveFromClosest(Obstacle closest) {
        float obstacleLeft = closest.getPos().x;
        float boatLeft = this.pos.x;

        /* Staying away from the edges */
        if (boatLeft - 100.0f < laneBox.getX()) {
            return 1;
        } else if (boatLeft + this.getHitBox().getWidth() + 100.0f > laneBox.getX() + laneBox.getWidth()) {
            return -1;
        }

        /* If the boat and obstacle are aligned vertically */
        if (Float.compare(obstacleLeft, boatLeft) == 0) {
            return 0;
        }
        /* If the obstacle is more to the left of the boat */
        else if (obstacleLeft < boatLeft) {
            return 1;
        }
        /* If the obstacle is more the right of the boat */
        else {
            return -1;
        }
    }

    public float pickSpeed(int pos) {
        int speed;
        switch (pos) {
            case 2:
                speed = ThreadLocalRandom.current().nextInt(65, 75);
                break;
            case 3:
                speed = ThreadLocalRandom.current().nextInt(75, 85);
                break;
            default:
                speed = ThreadLocalRandom.current().nextInt(55, 65);
        }
        return (3 * this.getSpeed()) / speed;
    }

}
