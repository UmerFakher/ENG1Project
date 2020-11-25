package com.dragonboatrace.entities;

/**
 * Represents a type of obstacle.
 *
 * @author Benji Garment, Joe Wrieden
 */
public enum ObstacleType {
    /* ENUM(texture, speed, damage)*/
    ROCK("rock.png", 50, 20),
    BRANCH("branch.png", 60, 10),
    LEAF("leaf.png", 75, 5);

    /**
     * The texture of the obstacle.
     */
    private final String texture;
    /**
     * The base speed at which the obstacle moves.
     */
    private final float speed;
    /**
     * The damage the obstacle type deal at a collision.
     */
    private final float damage;

    /**
     * Creates a new type of obstacle with a given texture, base speed ana damage value.
     *
     * @param texture The path to the obstacles texture.
     * @param speed   The speed of the obstacle type.
     * @param damage  The damage of the obstacle type.
     */
    ObstacleType(String texture, float speed, float damage) {
        this.texture = texture;
        this.speed = speed;
        this.damage = damage;
    }

    /**
     * Get the base speed of the obstacle type.
     *
     * @return A float representing the speed of the obstacle type.
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Get the damage of the obstacle type.
     *
     * @return A float representing the damage dealt by the obstacle type.
     */
    public float getDamage() {
        return this.damage;
    }

    /**
     * Get the obstacle types texture
     *
     * @return A string representing the path to the obstacles texture.
     */
    public String getTexture() {
        return this.texture;
    }
}