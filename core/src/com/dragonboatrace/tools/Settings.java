package com.dragonboatrace.tools;

public class Settings {

    public static int WIDTH = 1920;
    public static int HEIGHT = 1080;
    public static boolean FULLSCREEN = true;
    public static int SCALAR = 1;
    public static int PLAYER_COUNT = 4;
    public static int STAMINA_SPEED_DIVISION = 2;
    public static int STAMINA_RATE = 1;
    public static int OBSTACLE_COLLISION_PENALTY = -20;
    public static float OBSTACLE_COLLISION_TIME = 0.5f;

    public static void setResolution(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        SCALAR = 1920 / WIDTH + ((1920 % WIDTH == 0) ? 0 : 1);
    }

    public static void setWIDTH(int WIDTH) {
        Settings.WIDTH = WIDTH;
    }

    public static void setHEIGHT(int HEIGHT) {
        Settings.HEIGHT = HEIGHT;
    }

    public static void setFULLSCREEN(boolean FULLSCREEN) {
        Settings.FULLSCREEN = FULLSCREEN;
    }

    public static void setSCALAR(int SCALAR) {
        Settings.SCALAR = SCALAR;
    }

    public static void setPlayerCount(int playerCount) {
        PLAYER_COUNT = playerCount;
    }

    public static void setStaminaSpeedDivision(int staminaSpeedDivision) {
        STAMINA_SPEED_DIVISION = staminaSpeedDivision;
    }

    public static void setStaminaRate(int staminaRate) {
        STAMINA_RATE = staminaRate;
    }

    public static void setObstacleCollisionPenalty(int obstacleCollisionPenalty) {
        OBSTACLE_COLLISION_PENALTY = obstacleCollisionPenalty;
    }

    public static void setObstacleCollisionTime(float obstacleCollisionTime) {
        OBSTACLE_COLLISION_TIME = obstacleCollisionTime;
    }
}