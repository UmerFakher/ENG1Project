package com.dragonboatrace.entities;

/**
 * Represents a type of obstacle.
 *
 * @author Benji Garment, Joe Wrieden
 */
public enum ObstacleType {
    /* ENUM(texture, speed, damage)*/
    LEAF("leaf.png", 75, 5, 0, 0, 0),
    ROCK("rock.png", 50, 20, 0, 0, 0),
    BRANCH("branch.png", 60, 10, 0, 0, 0),
    PU_HEALTH("health_power_up.png", 75, -20, 0, 0, 0),
    PU_STAMINA("stamina_power_up.png", 75, 0, 20, 0, 0),
    PU_AGILITY("agility_power_up.png", 75, 0, 0, 1, 0),
    PU_SPEED("speed_power_up.png", 75, 0, 0, 0, 20),
    PU_ALL("all_power_up.png", 75, -20, 20, 1, 20);

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

    private final float staminaMod;
    private final float agilityMod;
    private final float speedMod;

    /**
     * Creates a new type of obstacle with a given texture, base speed ana damage value.
     *
     * @param texture The path to the obstacles texture.
     * @param speed   The speed of the obstacle type.
     * @param damage  The damage of the obstacle type.
     */
    ObstacleType(String texture, float speed, float damage, float staminaMod, float agilityMod, float speedMod) {
        this.texture = texture;
        this.speed = speed;
        this.damage = damage;
        this.staminaMod = staminaMod;
        this.agilityMod = agilityMod;
        this.speedMod = speedMod;
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

    public float getStaminaMod() {
        return staminaMod;
    }

    public float getAgilityMod() {
        return agilityMod;
    }

    public float getSpeedMod() {
        return speedMod;
    }
}