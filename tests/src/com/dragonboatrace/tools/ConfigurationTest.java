package com.dragonboatrace.tools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigurationTest {

    @Test
    public void setResolution() {
        Configuration.setResolution(10, 10);
        assertEquals(10, Configuration.WIDTH);
        assertEquals(10, Configuration.HEIGHT);
        assertEquals(192, Configuration.SCALAR);
    }

    @Test
    public void setFULLSCREEN() {
        Configuration.setFULLSCREEN(true);
        assertTrue(Configuration.FULLSCREEN);
        Configuration.setFULLSCREEN(false);
        assertFalse(Configuration.FULLSCREEN);

        // test that window is full screen?
    }

    @Test
    public void setPlayerCount() {
        assertEquals(8, Configuration.PLAYER_COUNT);
        Configuration.setPlayerCount(30);
        assertEquals(30, Configuration.PLAYER_COUNT);
    }

    @Test
    public void setStaminaSpeedDivision() {
        assertEquals(2, Configuration.STAMINA_SPEED_DIVISION);
        Configuration.setStaminaSpeedDivision(30);
        assertEquals(30, Configuration.STAMINA_SPEED_DIVISION);
    }

    @Test
    public void setObstacleCollisionPenalty() {
        assertEquals(-20, Configuration.OBSTACLE_COLLISION_PENALTY);
        Configuration.setObstacleCollisionPenalty(-10);
        assertEquals(-10, Configuration.OBSTACLE_COLLISION_PENALTY);
    }

    @Test
    public void setObstacleCollisionTime() {
        assertEquals(0.5f, Configuration.OBSTACLE_COLLISION_TIME, 0.001);
        Configuration.setObstacleCollisionTime(0.6f);
        assertEquals(0.6f, Configuration.OBSTACLE_COLLISION_TIME, 0.001);
    }
}