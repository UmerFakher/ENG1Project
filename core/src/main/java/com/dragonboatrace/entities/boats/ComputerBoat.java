package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.tools.Configuration;
import com.dragonboatrace.tools.Hitbox;
import com.dragonboatrace.tools.Lane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a specific Computer controlled Boat.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class ComputerBoat extends Boat {

    /**
     * The area in which the computer boat will look for obstacles to avoid.
     */
    private final Hitbox moveArea;

    /**
     * The x-offset of the moveArea as the area is equal on both sides of the boat.
     */
    private final int xOffset;

    /**
     * The texture for the up indicator when the Computer is off the screen but above.
     */
    private final Texture up;

    /**
     * The texture for the indicator when the Computer is off the screen but below.
     */
    private final Texture down;

    /**
     * The random amount of stamina to have before using stamina again.
     */
    private float randomWait;

    /**
     * If the ComputerBoat is currently regenerating stamina.
     */
    private boolean waiting;

    /**
     * Creates a computer boat with values from boat, in Lane lane, an identifying name and with a random speed.
     *
     * @param boat           The BoatType to get values from.
     * @param lane           The lane the boat is racing in.
     * @param name           The name of the boat.
     * @param pickSpeedValue The speed of the boat is chosen based on this value.
     */
    public ComputerBoat(BoatType boat, Lane lane, String name, int pickSpeedValue) {
        super(boat, lane, name);
        this.speed = this.pickSpeed(pickSpeedValue);
        this.xOffset = this.getHitBox().getWidth() / pickSpeedValue;
        int yOffset = this.getHitBox().getHeight() / pickSpeedValue;
        this.moveArea = new Hitbox(this.position.x - xOffset, this.position.y, this.getHitBox().getWidth() + 2 * xOffset, this.getHitBox().getHeight() + 2 * yOffset);
        this.up = new Texture("up_arrow.png");
        this.down = new Texture("down_arrow.png");
        this.randomWait = 0;
        this.waiting = false;
    }

    /**
     * Update the position of the boat in respect to the time passed since the last frame.
     *
     * @param deltaTime The time passed since the last frame.
     */
    public void update(float deltaTime) {
        if (!recentCollision) {
            /*Check for nearby obstacles */
            Obstacle closest = checkObstacles();
            /* Check obstacles will return null if no obstacles nearby */
            if (closest != null) {
                this.velocity.set(this.speed * moveFromObject(closest), this.speed);
                this.stamina = (this.stamina < this.maxStamina) ? this.regenerateStamina() + this.stamina : this.maxStamina;
            } else {
                /* Logic for if the Computer should use stamina */
                if (!this.waiting) {
                    this.velocity.set(0, this.velocity.y);
                    float diff = this.useStamina() * deltaTime;
                    if (this.stamina - diff > 0) {
                        this.stamina -= diff;
                        this.velocity.set(this.velocity.x, (this.speed + this.speed * this.velocityPercentage()));
                    } else {
                        this.randomWait = waitForRandomStamina();
                        this.waiting = true;
                    }
                } else {
                    this.velocity.set(this.velocity.x, this.speed);
                    this.stamina = (this.stamina < this.maxStamina) ? this.regenerateStamina() + this.stamina : this.maxStamina;
                }
            }
            if (this.checkCollisions()) {
                recentCollision = true;
            }
        } else {
            this.velocity.set(0, Configuration.OBSTACLE_COLLISION_PENALTY);
            collisionTime += deltaTime;
            if (collisionTime > Configuration.OBSTACLE_COLLISION_TIME) {
                collisionTime = 0;
                recentCollision = false;
            }
        }

        if (this.stamina > this.randomWait) {
            this.waiting = false;
        }

        this.moveArea.move(position.x - this.xOffset, position.y);
        super.update(deltaTime);
    }

    /**
     * Renders the boat if it is onscreen, else will render the off-screen markers at their current x value.
     * <p>
     * Also performs {@link Boat}'s render after rendering the markers.
     *
     * @param batch The SpriteBatch that the renders will be added to.
     */
    public void render(SpriteBatch batch) {
        if (this.position.y > Gdx.graphics.getHeight()) {
            batch.draw(up, this.position.x, Gdx.graphics.getHeight() - this.texture.getHeight(), 50, 50);
        } else if (this.position.y < 0) {
            batch.draw(down, this.position.x, 0, 50, 50);
        } else {
            batch.draw(this.texture, this.position.x, this.position.y);
        }
        super.render(batch);
    }

    /**
     * Update the vertical position of the boat according to its distance travelled versus the distance travelled by the player.
     *
     * @param playerY        The y position of the player on the screen.
     * @param playerDistance The distance travelled by the player.
     */
    public void updateYPosition(float playerY, float playerDistance) {
        float c = 100 - (playerDistance - this.distanceTravelled);
        if (playerY == 100) {
            this.position.y = c;
        } else {
            this.position.y = playerY + c / 2;
        }
    }

    /**
     * Wait for a random stamina value before using stamina again.
     *
     * @return A float of the value to wait for before using stamina.
     */
    private float waitForRandomStamina() {
        return (float) ThreadLocalRandom.current().nextDouble((double) this.maxStamina / 2, this.maxStamina);
    }

    /**
     * Check for obstacles in the area, specified by moveArea, to move away from.
     *
     * @return The closest Obstacle in the area or null if no obstacles are in the area.
     */
    private Obstacle checkObstacles() {
        List<Obstacle> obstacles = this.lane.getObstacles();
        Obstacle closest = null;
        float smallest = Gdx.graphics.getHeight();
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getHitBox().collidesWith(this.moveArea)) {
                float bottomY = obstacle.getPos().y;
                if (bottomY < smallest) {
                    closest = obstacle;
                    smallest = bottomY;
                }
            }
        }
        return closest;
    }

    /**
     * Move in the direction away from an object.
     *
     * @param closest The Obstacle to move away from.
     * @return The direction to move in: <ul>
     * <li>-1 if the boat should move to the left.</li>
     * <li>1 if the boat should move to the right.</li>
     * <li>0 if the boat should move neither way.</li>
     * </ul>
     */
    private int moveFromObject(Obstacle closest) {
        float obstacleLeft = closest.getPos().x;
        float boatLeft = this.position.x;

        /* Staying away from the edges */
        if (boatLeft - 10.0f < laneBox.getX()) {
            return 1;
        } else if (boatLeft + this.getHitBox().getWidth() + 10.0f > laneBox.getX() + laneBox.getWidth()) {
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

    /**
     * Pick a random speed so that the computer boat is likely to finish in the position specified.
     *
     * @param pos The position the boat should finish in.
     * @return A float of the boats speed that it will travel at.
     */
    private float pickSpeed(int pos) {
        double multi;
        switch (pos) {
            case 2:
                multi = ThreadLocalRandom.current().nextDouble(0.85, 0.88);
                break;
            case 3:
                multi = ThreadLocalRandom.current().nextDouble(0.8, 0.87);
                break;
            default:
                multi = ThreadLocalRandom.current().nextDouble(0.75, 0.8);
        }
        return this.speed * (float) multi;
    }

}
