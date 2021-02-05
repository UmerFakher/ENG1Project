package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.entities.ObstacleType;
import com.dragonboatrace.screens.GameOverScreen;
import com.dragonboatrace.tools.Lane;
import com.dragonboatrace.tools.Race;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Computer;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class BoatObstacleTest {

    Lane l = new Lane(new Vector2(0, 0), 50, 0, 0);

    @Test
    public void creationAttributeTest() {
        Boat fastBoat = new Boat(BoatType.FAST, l, "");
        assertEquals(fastBoat.getHealth(), 50, 0.01f);
        assertEquals(fastBoat.getStamina(), 120, 0.01f);
        assertEquals(fastBoat.getAgility(), 85, 0.01f);
        assertEquals(fastBoat.getSpeed(), 275, 0.01f);

        Boat agileBoat = new Boat(BoatType.AGILE, l, "");
        assertEquals(agileBoat.getHealth(), 50, 0.01f);
        assertEquals(agileBoat.getStamina(), 120, 0.01f);
        assertEquals(agileBoat.getAgility(), 95, 0.01f);
        assertEquals(agileBoat.getSpeed(), 245, 0.01f);

        Boat enduranceBoat = new Boat(BoatType.ENDURANCE, l, "");
        assertEquals(enduranceBoat.getHealth(), 70, 0.01f);
        assertEquals(enduranceBoat.getStamina(), 180, 0.01f);
        assertEquals(enduranceBoat.getAgility(), 90, 0.01f);
        assertEquals(enduranceBoat.getSpeed(), 245, 0.01f);

        Boat strongBoat = new Boat(BoatType.STRONG, l, "");
        assertEquals(strongBoat.getHealth(), 100, 0.01f);
        assertEquals(strongBoat.getStamina(), 105, 0.01f);
        assertEquals(strongBoat.getAgility(), 98, 0.01f);
        assertEquals(strongBoat.getSpeed(), 200, 0.01f);
    }

    @Test
    public void staminaUsageTest() {
        ComputerBoat boat = new ComputerBoat(BoatType.FAST, l, "", 1);
        float oldStamina = boat.getStamina();
        for (int i = 0; i < 5; i++)
            boat.update(1);

        Assert.assertTrue(oldStamina > boat.getStamina());
    }

    // These tests have been verified in every members IDE however they fail on github so assertions have been removed.
    @Test
    public void collisionTest() {
        Boat b = new Boat(BoatType.FAST, l, "");
        float initialHealth = b.getHealth();
        final float y = b.position.y;
        b.checkCollisions(new ArrayList<Obstacle>(){{add(new Obstacle(ObstacleType.ROCK, new Vector2(0,y)));}});

        Assert.assertTrue(initialHealth > b.getHealth());
    }

    // These tests have been verified in every members IDE however they fail on github so assertions have been removed.
    @Test
    public void gameOverTest() {
        DragonBoatRace game = new DragonBoatRace();
        Race race = new Race(10000, BoatType.AGILE, 0, 0);
        final float y = race.getPlayer().position.y;
        final float x = race.getPlayer().position.x;
        race.getPlayer().checkCollisions(new ArrayList<Obstacle>(){{add(new Obstacle(ObstacleType.ROCK, new Vector2(x, y)));}});
        race.getPlayer().checkCollisions(new ArrayList<Obstacle>(){{add(new Obstacle(ObstacleType.ROCK, new Vector2(x, y)));}});
        race.getPlayer().checkCollisions(new ArrayList<Obstacle>(){{add(new Obstacle(ObstacleType.ROCK, new Vector2(x, y)));}});
        race.getPlayer().checkCollisions(new ArrayList<Obstacle>(){{add(new Obstacle(ObstacleType.ROCK, new Vector2(x, y)));}});
        race.update(1, game);

        Assert.assertTrue(game.getScreen() instanceof GameOverScreen);
    }
}
