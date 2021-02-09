package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.entities.ObstacleType;
import com.dragonboatrace.screens.GameOverScreen;
import com.dragonboatrace.screens.MainGameScreen;
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

/**
 * TI_BOAT_DAMAGE - Check that the boat takes damage when hitting an obstacle.
 * TI_BOAT_BREAKING - Check the boat breaks when it has 0 health.
 * TI_BOAT_STAMINA - Check that moving reduces stamina.
 * TI_NEW_BOAT - Check that when new boat is created with a BoatType, the correct attributes are assigned.
 */
@RunWith(GdxTestRunner.class)
public class BoatObstacleTest {

    Lane l = new Lane(new Vector2(0, 0), 50, 0, 0);

    /**
     * TI_NEW_BOAT - Check that when new boat is created with a BoatType, the correct attributes are assigned.
     *
     * A Boat of type FAST should have stats: health=50, stamina=120, agility=85, speed=275.
     * A Boat of type AGILE should have stats: health=50, stamina=120, agility=95, speed=245.
     * A Boat of type ENDURANCE should have stats: health=70, stamina=180, agility=90, speed=245.
     * A Boat of type STRONG should have stats: health=100, stamina=105, agility=98, speed=200.
     */
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

    /**
     * TI_BOAT_STAMINA - Check that moving reduces stamina.
     *
     * After moving, the stamina of a boat should always be less than before it moved unless the boat has had time to rest.
     */
    @Test
    public void staminaUsageTest() {
        ComputerBoat boat = new ComputerBoat(BoatType.FAST, l, "", 1);
        float oldStamina = boat.getStamina();
        for (int i = 0; i < 5; i++)
            boat.update(1);

        Assert.assertTrue(oldStamina > boat.getStamina());
    }

    /**
     * TI_BOAT_DAMAGE - Check that the boat takes damage when hitting an obstacle.
     *
     * The boat takes health is less after hitting an obstacle.
     */
    @Test
    public void collisionTest() {
        Boat b = new Boat(BoatType.FAST, l, "");
        float initialHealth = b.getHealth();
        final float y = b.getPosition().y;
        final float x = b.getPosition().x;
        b.checkCollisions(new ArrayList<Obstacle>() {{
            add(new Obstacle(ObstacleType.ROCK, new Vector2(x, y)));
        }});

        Assert.assertTrue(initialHealth > b.getHealth());
    }

    /**
     *  TI_BOAT_BREAKING - Check the boat breaks when it has 0 health.
     *
     *  The boat gives a game over screen when it has 0 health.
     */
    @Test
    public void gameOverTest() {
        DragonBoatRace game = new DragonBoatRace();
        Race race = new Race(10000, BoatType.AGILE, 0, 0);
        final float y = race.getPlayer().getPosition().y;
        final float x = race.getPlayer().getPosition().x;
        race.getPlayer().checkCollisions(new ArrayList<Obstacle>() {{
            add(new Obstacle(ObstacleType.ROCK, new Vector2(x, y)));
        }});
        race.getPlayer().checkCollisions(new ArrayList<Obstacle>() {{
            add(new Obstacle(ObstacleType.ROCK, new Vector2(x, y)));
        }});
        race.getPlayer().checkCollisions(new ArrayList<Obstacle>() {{
            add(new Obstacle(ObstacleType.ROCK, new Vector2(x, y)));
        }});
        race.getPlayer().checkCollisions(new ArrayList<Obstacle>() {{
            add(new Obstacle(ObstacleType.ROCK, new Vector2(x, y)));
        }});
        race.update(1, game, null);

        Assert.assertTrue(game.getScreen() instanceof GameOverScreen);
    }
}
