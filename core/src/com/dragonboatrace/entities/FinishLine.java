package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/** Represents the finish line of the race.
 * @author Benji Garment, Joe Wrieden
 */
public class FinishLine extends Entity {

    /**
     * The scale at which to render at.
     */
    private final float widthScale;

    /**
     * Creates a new Finish Line at a position and with a width which it will span.
     * @param pos The position of the finish line.
     * @param width The width of the finish line.
     */
    public FinishLine(Vector2 pos, int width) {
        super(new Vector2(pos.x, pos.y - EntityType.FINISH.getHeight()), new Vector2(), EntityType.FINISH, "finish.png");
        this.widthScale = (float) width / this.texture.getWidth();
    }

    /**
     * Update the position of the finish line so that it moves towards the player.
     * @param deltaTime The time passed since the last frame.
     * @param velY The y-velocity of the entity it will move in respect to (Will be the player)
     */
    public void update(float deltaTime, float velY) {
        if (this.position.y + this.getHitBox().getHeight() <= Gdx.graphics.getHeight()) {
            this.position.set(this.position.x, Gdx.graphics.getHeight() - this.getHitBox().getHeight());
        } else {
            this.position.set(this.position.x, this.position.y - (velY * deltaTime));
            this.hitbox.move(this.position.x, this.position.y);
        }
    }

    /**
     * Render the texture at the position and specified width.
     * @param batch The SpriteBatch to be added to.
     */
    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.position.x, this.position.y, this.texture.getWidth() * this.widthScale, this.texture.getHeight());
    }
}
