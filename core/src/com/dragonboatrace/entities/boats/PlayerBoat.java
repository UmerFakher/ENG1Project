package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dragonboatrace.tools.Lane;

public class PlayerBoat extends Boat {

    public PlayerBoat(BoatType boat, Lane lane, String name) {
        super(boat, lane, name);
    }

    public void update(float deltaTime, float currDistance) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && this.pos.x > this.lane.getHitbox().getX()) {
            this.vel.add(-this.speed * deltaTime, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (this.pos.x + this.type.getWidth()) < this.lane.getHitbox().getWidth() + this.lane.getHitbox().getX()) {
            this.vel.add(this.speed * deltaTime, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (this.stamina > 5) {
                this.vel.add(0, this.speed * deltaTime / 2);
                this.stamina -= 5;
            }
        } else {
            if (this.stamina + 20 < this.maxStamina) {
                stamina += 20;
            } else {
                this.stamina = this.maxStamina;
            }
        }
        checkCollisions();
        super.update(deltaTime, currDistance);
    }

    public void checkCollisions() {
        super.checkCollisions();

    }

    public boolean isDead() {
        return this.health <= 0;
    }


}
