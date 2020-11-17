package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dragonboatrace.tools.Lane;
import com.dragonboatrace.tools.Settings;

public class PlayerBoat extends Boat {

    public PlayerBoat(BoatType boat, Lane lane, int raceDistance, String name) {
        super(boat, lane, raceDistance, name);
    }

    public void update(float deltaTime) {
        if (!recentCollision) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && this.pos.x > this.lane.getHitbox().getX()) {
                //this.vel.add(-this.speed * deltaTime, 0);
                this.vel.set(-this.speed, this.vel.y);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (this.pos.x + this.type.getWidth()) < this.lane.getHitbox().getWidth() + this.lane.getHitbox().getX()) {
                //this.vel.add(this.speed * deltaTime, 0);
                this.vel.set(this.speed, this.vel.y);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                float diff = this.useStamina() * deltaTime;
                if (this.stamina - diff > 0) {
                    this.stamina -= diff;
                    this.vel.set(this.vel.x, (this.speed + this.speed * this.velocityPercentage()));
                } else {
                    this.vel.set(this.vel.x, this.speed);
                }
            } else {
                this.stamina = (this.stamina < this.maxStamina) ? this.regenerateStamina() + this.stamina : this.maxStamina;
                this.vel.set(this.vel.x, this.speed);
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
        super.update(deltaTime);
    }

    public boolean checkCollisions() {
        return super.checkCollisions();
    }

    public void updateYPosition(int lineHeight) {
        /*float distanceRatio = this.distanceTravelled / (this.raceDistance);
        this.pos.y = (this.raceDistance - this.distanceTravelled > Gdx.graphics.getHeight())?100:distanceRatio * (Gdx.graphics.getHeight() - lineHeight);*/
        if (this.distanceTravelled / (this.raceDistance) < 0.5f) {
            this.pos.y = 100;
        } else {
            this.pos.y = (this.distanceTravelled - this.raceDistance / 2.0f) / (this.raceDistance - lineHeight - this.raceDistance / 2.0f) * (Gdx.graphics.getHeight() - 100) + 100;
            //this.pos.y = 100;
        }
    }

    public boolean isDead() {
        return this.health <= 0;
    }


}
