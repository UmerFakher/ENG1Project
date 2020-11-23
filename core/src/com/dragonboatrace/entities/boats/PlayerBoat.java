package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dragonboatrace.tools.Lane;
import com.dragonboatrace.tools.Settings;
/** Represents a specific Player controlled Boat.
 * @author Benji Garment, Joe Wrieden
 */
public class PlayerBoat extends Boat {

    /**
     * Creates a player boat with values from boat, in Lane lane, and an identifying name.
     * @param boat The BoatType to get values from.
     * @param lane The lane the boat is racing in.
     * @param name The name of the boat.
     */
    public PlayerBoat(BoatType boat, Lane lane, String name) {
        super(boat, lane, name);
    }

    /**
     * Update the boats position in respects to the time passed since the last frame.
     * @param deltaTime The time passed since the last frame.
     */
    public void update(float deltaTime) {
        if (!recentCollision) {
            if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) && this.position.x > this.lane.getHitbox().getX()) {
                this.velocity.set(-this.speed, this.velocity.y);
            }

            if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) && (this.position.x + this.type.getWidth()) < this.lane.getHitbox().getWidth() + this.lane.getHitbox().getX()) {
                this.velocity.set(this.speed, this.velocity.y);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                float diff = this.useStamina() * deltaTime;
                if (this.stamina - diff > 0) {
                    this.stamina -= diff;
                    this.velocity.set(this.velocity.x, (this.speed + this.speed * this.velocityPercentage()));
                } else {
                    this.velocity.set(this.velocity.x, this.speed);
                }
            } else {
                this.stamina = (this.stamina < this.maxStamina) ? this.regenerateStamina() + this.stamina : this.maxStamina;
                this.velocity.set(this.velocity.x, this.speed);
            }
            if (this.position.y < Gdx.graphics.getHeight() *0.75f) {
                if (checkCollisions()) {
                    System.out.println("Collision!");
                    recentCollision = true;
                }
            }
        } else {
            this.velocity.set(0, Settings.OBSTACLE_COLLISION_PENALTY);
            collisionTime += deltaTime;
            if (collisionTime > Settings.OBSTACLE_COLLISION_TIME) {
                collisionTime = 0;
                recentCollision = false;
            }
        }
        super.update(deltaTime);
    }

    /**
     * Update the vertical position of the boat on-screen. Is related to the distance travelled in the race.
     * @param lineHeight The height of the finish line Entity.
     * @param raceDistance The length of the race.
     */
    public void updateYPosition(int lineHeight, int raceDistance) {
        /*float distanceRatio = this.distanceTravelled / (this.raceDistance);
        this.pos.y = (this.raceDistance - this.distanceTravelled > Gdx.graphics.getHeight())?100:distanceRatio * (Gdx.graphics.getHeight() - lineHeight);*/
        if (this.distanceTravelled / (raceDistance) < 0.8f) {
            this.position.y = 100;
        } else {
            this.position.y = (this.distanceTravelled - raceDistance * 0.8f) / (raceDistance - lineHeight - raceDistance * 0.8f) * (Gdx.graphics.getHeight() - 100) + 100;
            //this.pos.y = 100;
        }
    }

    /**
     * If the player boat has no health left.
     * @return A boolean of if the boat is dead.
     */
    public boolean isDead() {
        return this.health <= 0;
    }

}
