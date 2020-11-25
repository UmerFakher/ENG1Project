package com.dragonboatrace.entities;

import com.dragonboatrace.tools.Settings;

/**
 * Represents a Type of Entity.
 *
 * @author Benji Garment, Joe Wrieden
 */
public enum EntityType {

    BOAT(50, 80),
    OBSTACLE(40, 40),
    FINISH(960, 241),
    BUTTON(400, 140);

    private final int width;
    private final int height;

    /**
     * Creates anew entity type with dimensions.
     *
     * @param width  The width of the entity.
     * @param height The height of the entity.
     */
    EntityType(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Get the width of the entity type.
     *
     * @return An int representing the width.
     */
    public int getWidth() {
        return this.width / Settings.SCALAR;
    }

    /**
     * Get the height of the entity type.
     *
     * @return An int representing the height.
     */
    public int getHeight() {
        return this.height / Settings.SCALAR;
    }
}
