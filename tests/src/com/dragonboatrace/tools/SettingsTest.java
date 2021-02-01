package com.dragonboatrace.tools;

import org.junit.Test;

import static org.junit.Assert.*;

public class SettingsTest {

    @Test
    public void setResolution() {
        Settings.setResolution(10, 10);
        assertEquals(10, Settings.WIDTH);
        assertEquals(10, Settings.HEIGHT);
        assertEquals(192, Settings.SCALAR);
    }

    @Test
    public void setFULLSCREEN() {
        Settings.setFULLSCREEN(true);
        assertTrue(Settings.FULLSCREEN);
        Settings.setFULLSCREEN(false);
        assertFalse(Settings.FULLSCREEN);

        // test that window is full screen?
    }

    @Test
    public void setPlayerCount() {
        assertEquals(8, Settings.PLAYER_COUNT);
        Settings.setPlayerCount(30);
        assertEquals(30, Settings.PLAYER_COUNT);
    }

    @Test
    public void setStaminaSpeedDivision() {
        assertEquals(2, Settings.STAMINA_SPEED_DIVISION);
        Settings.setStaminaSpeedDivision(30);
        assertEquals(30, Settings.STAMINA_SPEED_DIVISION);
    }

    @Test
    public void setObstacleCollisionPenalty() {
        assertEquals(-20, Settings.OBSTACLE_COLLISION_PENALTY);
        Settings.setObstacleCollisionPenalty(-10);
        assertEquals(-10, Settings.OBSTACLE_COLLISION_PENALTY);
    }

    @Test
    public void setObstacleCollisionTime() {
        assertEquals(0.5f, Settings.OBSTACLE_COLLISION_TIME, 0.001);
        Settings.setObstacleCollisionTime(0.6f);
        assertEquals(0.6f, Settings.OBSTACLE_COLLISION_TIME, 0.001);
    }
}