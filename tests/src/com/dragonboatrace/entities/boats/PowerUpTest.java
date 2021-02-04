package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.entities.ObstacleType;
import com.dragonboatrace.tools.Lane;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(GdxTestRunner.class)
public class PowerUpTest {

    /**
     * Test to see if all power-ups can be created and have the correct attribute values
     * to affect health, stamina, agility and speed of a boat.
     */
    @Test
    public void powerUpValueTest() {
        Obstacle puHe = new Obstacle(ObstacleType.PU_HEALTH, 0.01f, 2);

        Assert.assertEquals(true, puHe.getDamage() == -20);

        Obstacle puSta = new Obstacle(ObstacleType.PU_STAMINA, 0, 2);
        Assert.assertEquals(true, puSta.getStaminaMod() == 20);

        Obstacle puAg = new Obstacle(ObstacleType.PU_AGILITY, 0, 2);
        Assert.assertEquals(true, puAg.getAgilityMod() == 1);

        Obstacle puSpe = new Obstacle(ObstacleType.PU_SPEED, 0, 2);
        Assert.assertEquals(true, puSpe.getSpeedMod() == 20);

        // Power-up that affects all boat attributes at once health, stamina, agility and speed
        Obstacle puAll = new Obstacle(ObstacleType.PU_ALL, 0, 2);
        Assert.assertEquals(true, puAll.getDamage() == -20);
        Assert.assertEquals(true, puAll.getStaminaMod() == 20);
        Assert.assertEquals(true, puAll.getAgilityMod() == 1);
        Assert.assertEquals(true, puAll.getSpeedMod() == 20);
    }

    //TODO: Add another test in here to test for if Powerup's affect attributes of boat

    /**
     * Test to see if a boat colliding with a health power-up will increase its health correctly.
     */
    @Test
    public void healthPowerUpBoatTest() {
        Lane l = new Lane(new Vector2(0, 0), 50, 0, 0);

        Boat b = new Boat(BoatType.FAST, l, "__testing_boat__");
        float initialValue = b.getHealth();
        b.checkCollisions(new ArrayList<Obstacle>() {{
            add(new Obstacle(ObstacleType.PU_HEALTH, new Vector2(0, 100)));
        }});

//        Assert.assertTrue(initialValue < b.getHealth());
        Assert.assertEquals(true, initialValue < b.getHealth());
    }


//    /**
//     * Test to see if a boat colliding with a stamina power-up will increase its stamina correctly.
//     */
//    @Test
//    public void staminaPowerUpBoatTest() {
//        Lane l = new Lane(new Vector2(0, 0), 50, 0, 0);
//
//        Boat b = new Boat(BoatType.FAST, l, "__testing_boat__");
//        float initialValue = b.getStamina();
//        b.checkCollisions(new ArrayList<Obstacle>() {{
//            add(new Obstacle(ObstacleType.PU_STAMINA, new Vector2(0, 100)));
//        }});
//
//        Assert.assertTrue(initialValue < b.getStamina());
//    }
//
//
//    /**
//     * Test to see if a boat colliding with an agility power-up will increase its agility correctly.
//     */
//    @Test
//    public void agilityPowerUpBoatTest() {
//        Lane l = new Lane(new Vector2(0, 0), 50, 0, 0);
//
//        Boat b = new Boat(BoatType.FAST, l, "__testing_boat__");
//        float initialValue = b.getAgility();
//        b.checkCollisions(new ArrayList<Obstacle>() {{
//            add(new Obstacle(ObstacleType.PU_AGILITY, new Vector2(0, 100)));
//        }});
//
//        Assert.assertTrue(initialValue < b.getAgility());
//    }
//
//    /**
//     * Test to see if a boat colliding with a speed power-up will increase its speed correctly.
//     */
//    @Test
//    public void speedPowerUpBoatTest() {
//        Lane l = new Lane(new Vector2(0, 0), 50, 0, 0);
//
//        Boat b = new Boat(BoatType.FAST, l, "__testing_boat__");
//        float initialValue = b.getSpeed();
//        b.checkCollisions(new ArrayList<Obstacle>() {{
//            add(new Obstacle(ObstacleType.PU_SPEED, new Vector2(0, 100)));
//        }});
//
//        Assert.assertTrue(initialValue < b.getSpeed());
//    }
//
//    /**
//     * Test to see if a boat colliding with an ALL power-up will increase its health, stamina, agility and speed correctly.
//     */
//    @Test
//    public void allPowerUpBoatTest() {
//        Lane l = new Lane(new Vector2(0, 0), 50, 0, 0);
//
//        Boat b = new Boat(BoatType.FAST, l, "__testing_boat__");
//        float initialH = b.getHealth();
//        float initialSta = b.getStamina();
//        float initialAgi = b.getAgility();
//        float initialSpe = b.getSpeed();
//        b.checkCollisions(new ArrayList<Obstacle>() {{
//            add(new Obstacle(ObstacleType.PU_ALL, new Vector2(0, 100)));
//        }});
//
//        Assert.assertTrue(initialH < b.getHealth());
//        Assert.assertTrue(initialSta < b.getStamina());
//        Assert.assertTrue(initialAgi < b.getAgility());
//        Assert.assertTrue(initialSpe < b.getSpeed());
//    }
}