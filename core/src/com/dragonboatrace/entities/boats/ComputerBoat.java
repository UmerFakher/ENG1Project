package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.tools.Hitbox;
import com.dragonboatrace.tools.Lane;
import com.dragonboatrace.tools.Settings;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerBoat extends Boat {


    /* Move area is the area in which the boat is looking for obstacles to avoid */
    private final Hitbox moveArea;
    private final int xOffset;
    private final int yOffset;
    private final Texture up;
    private final Texture down;
    private float randomWait;

    public ComputerBoat(BoatType boat, Lane lane, int raceDistance, String name, int pickSpeedValue) {
        super(boat, lane, raceDistance, name);
        this.speed = this.pickSpeed(pickSpeedValue);
        this.xOffset = this.getHitBox().getWidth() / pickSpeedValue;
        this.yOffset = this.getHitBox().getHeight() / pickSpeedValue;
        this.moveArea = new Hitbox(this.pos.x - xOffset, this.pos.y, this.getHitBox().getWidth() + 2 * xOffset, this.getHitBox().getHeight() + 2 * yOffset);
        this.up = new Texture("up_arrow.png");
        this.down = new Texture("down_arrow.png");
        this.randomWait = 0;
    }

    public void update(float deltaTime) {
        if (!recentCollision) {
            Obstacle closest = checkObstacles();
            if (closest != null) {
                this.vel.set(this.speed * moveFromClosest(closest), this.speed);
                this.stamina = (this.stamina < this.maxStamina) ? this.regenerateStamina() + this.stamina : this.maxStamina;
            } else {
                if (this.stamina >= this.randomWait) {
                    this.vel.set(0, this.vel.y);
                    float diff = this.useStamina() * deltaTime;
                    if (this.stamina - diff > 0) {
                        this.stamina -= diff;
                        this.vel.set(this.vel.x, (this.speed + this.speed * this.velocityPercentage()));
                    } else {
                        this.randomWait = waitForRandomStamina();
                    }
                } else {
                    this.vel.set(this.vel.x, this.speed);
                    this.stamina = (this.stamina < this.maxStamina) ? this.regenerateStamina() + this.stamina : this.maxStamina;
                }
            }
            if (checkCollisions()) {
                //this.distanceTravelled -= 200;
                System.out.println("Collision!");
                recentCollision = true;
            }
        } else {
            this.vel.set(0, Settings.OBSTACLE_COLLISION_PENALTY);
            collisionTime += deltaTime;
            if (collisionTime > Settings.OBSTACLE_COLLISION_TIME) {
                collisionTime = 0;
                recentCollision = false;
            }
        }

        this.moveArea.move(pos.x - this.xOffset, pos.y);
        super.update(deltaTime);
    }

    public void render(SpriteBatch batch) {
        if (this.pos.y > Gdx.graphics.getHeight()) {
            batch.draw(up, this.pos.x, Gdx.graphics.getHeight() - this.texture.getHeight(), this.texture.getWidth(), this.texture.getHeight());
        } else if (this.pos.y < 0) {
            batch.draw(down, this.pos.x, 0, this.texture.getWidth(), this.texture.getHeight());
        } else {
            batch.draw(this.texture, this.pos.x, this.pos.y);
        }
        super.render(batch);
    }

    public void updateYPosition(float playerY, float playerDistance) {
        float c = 100 - (playerDistance - this.distanceTravelled);
        if (playerY == 100) {
            this.pos.y = c;
        } else {
            this.pos.y = playerY + c / 2;
        }
    }

    public float waitForRandomStamina() {
        return (float) ThreadLocalRandom.current().nextDouble((double) this.maxStamina / 2, this.maxStamina);
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
        double multi;
        switch (pos) {
            case 2:
                multi = ThreadLocalRandom.current().nextDouble(0.7, 1);
                break;
            case 3:
                multi = ThreadLocalRandom.current().nextDouble(0.3, 0.6);
                break;
            default:
                multi = ThreadLocalRandom.current().nextDouble(0.2, 0.4);
        }
        System.out.println("Boat:" + this.name + ":" + multi);
        return this.speed * (float) multi;
    }

}
