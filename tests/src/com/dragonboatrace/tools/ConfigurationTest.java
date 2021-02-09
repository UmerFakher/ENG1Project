package com.dragonboatrace.tools;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * TUA_SETTINGS - Check the default settings are correct.
 *
 * The default settings should be the same as in the design.
 */
public class ConfigurationTest {

    /**
     * Test that the resolution of the screen is as expected.
     */
    @Test
    public void setResolutionTest() {
        Configuration.setResolution(10, 10);
        assertEquals(10, Configuration.WIDTH);
        assertEquals(10, Configuration.HEIGHT);
        assertEquals(192, Configuration.SCALAR);
    }

    /**
     * Test that window is fullscreen when it should be and shouldn't be.
     */
    @Test
    public void setFullscreenTest() {
        Configuration.setFULLSCREEN(true);
        assertTrue(Configuration.FULLSCREEN);
        Configuration.setFULLSCREEN(false);
        assertFalse(Configuration.FULLSCREEN);
    }

    /**
     * Test that the number of players, including the player itself, is as expected.
     */
    @Test
    public void setPlayerCountTest() {
        assertEquals(8, Configuration.PLAYER_COUNT);
        Configuration.setPlayerCount(30);
        assertEquals(30, Configuration.PLAYER_COUNT);
    }

    /**
     * Test that that the value that reduces the scalar that stamina gives when accelerating, is as expected.
     */
    @Test
    public void setStaminaSpeedDivisionTest() {
        assertEquals(2, Configuration.STAMINA_SPEED_DIVISION);
        Configuration.setStaminaSpeedDivision(30);
        assertEquals(30, Configuration.STAMINA_SPEED_DIVISION);
    }

    /**
     *  Test that the velocity penalty given when a collision occurs, is as expected.
     */
    @Test
    public void setObstacleCollisionPenaltyTest() {
        assertEquals(-20, Configuration.OBSTACLE_COLLISION_PENALTY);
        Configuration.setObstacleCollisionPenalty(-10);
        assertEquals(-10, Configuration.OBSTACLE_COLLISION_PENALTY);
    }

    /**
     * Test that the time the boat must wait before moving again after a collision, is as expected.
     */
    @Test
    public void setObstacleCollisionTimeTest() {
        assertEquals(0.5f, Configuration.OBSTACLE_COLLISION_TIME, 0.001);
        Configuration.setObstacleCollisionTime(0.6f);
        assertEquals(0.6f, Configuration.OBSTACLE_COLLISION_TIME, 0.001);
    }
}