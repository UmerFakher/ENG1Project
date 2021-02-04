package com.dragonboatrace.entities.boats;

import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.entities.ObstacleType;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class PowerUpTest {

    @Test
    public void powerUpValueTest() {
        Obstacle puHe = new Obstacle(ObstacleType.PU_HEALTH,0.01f,2);

        assertEquals(true,puHe.getDamage() == -20);

        Obstacle puSta = new Obstacle(ObstacleType.PU_STAMINA,0,2);
        assertEquals(true,puSta.getStaminaMod() == 20);

        Obstacle puAg = new Obstacle(ObstacleType.PU_AGILITY,0,2);
        assertEquals(true,puAg.getAgilityMod() == 1);

        Obstacle puSpe = new Obstacle(ObstacleType.PU_SPEED,0,2);
        assertEquals(true,puSpe.getSpeedMod() == 20);

        Obstacle puAll = new Obstacle(ObstacleType.PU_ALL,0,2);
        assertEquals(true,puAll.getDamage() == -20);
        assertEquals(true,puAll.getStaminaMod() == 20);
        assertEquals(true,puAll.getAgilityMod() == 1);
        assertEquals(true,puAll.getSpeedMod() == 20);


    }

    //TODO: Add another test in here to test for if Powerup's affect attributes of boat


    //Temp change
}