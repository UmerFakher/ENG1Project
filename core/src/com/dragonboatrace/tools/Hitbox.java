package com.dragonboatrace.tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Represents an Entities Hitbox.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class Hitbox {

    /**
     * The width of the hit box
     */
    private final int width;
    /**
     * The height of the hit box.
     */
    private final int height;
    /**
     * Used to render the hit boxes when debugging.
     */
    private final ShapeRenderer renderer;
    /**
     * The x position of the bottom left corner.
     */
    private float x;
    /**
     * The y position of the bottom left corner.
     */
    private float y;

    /**
     * Create a new hit box at a specified position with a width and height.
     *
     * @param x      The x position of the bottom left corner.
     * @param y      The y position of the bottom left corner.
     * @param width  The width of the hit box.
     * @param height The height of the hit box
     */
    public Hitbox(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        renderer = new ShapeRenderer();
    }

    /**
     * Move the hit box's position to the one specified, not additive.<p>
     * Used to make sure the hit boxes position is the same as the entity it represents.
     *
     * @param x The new x position.
     * @param y The new y position.
     */
    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Render the hit boxes for debugging.
     */
    public void render() {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.RED);
        renderer.rect(this.x, this.y, this.width, this.height);
        renderer.end();
    }

    /**
     * Check if the hit box is colliding with another hit box.
     *
     * @param box The hit box that the collision is being checked against.
     * @return A boolean of if the two hit boxes are intersecting.
     */
    public boolean collidesWith(Hitbox box) {
        return this.x + this.width > box.getX() && this.x < box.getX() + box.getWidth() && this.y < box.getY() + box.getHeight() && this.y + this.height > box.getY();
    }

    /**
     * Checks if the another hit box has left the area of this hit box.
     *
     * @param box The other hit box.
     * @return A boolean of if the other hit box is partially outside of this hit box
     */
    public boolean leaves(Hitbox box) {
        return this.x + this.width > box.getX() + box.getWidth() || this.x < box.getX() || this.y < box.getY() && this.y + this.height > box.getY();
    }

    /**
     * Get the x coordinate of the hit box.
     *
     * @return A float of the x position of the bottom left corner.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Get the y coordinate of the hit box.
     *
     * @return A float of the y position of the bottom left corner.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Get the width of the hit box.
     *
     * @return An int representing how wide the hit box is.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the hit box.
     *
     * @return An int representing how tall the hit box is.
     */
    public int getHeight() {
        return height;
    }
}
