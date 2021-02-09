package com.dragonboatrace;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.screens.MainGameScreen;
import com.dragonboatrace.screens.MainMenuScreen;
import com.dragonboatrace.screens.RoundsScreen;
import com.dragonboatrace.tools.Lane;
import com.dragonboatrace.tools.Race;
import com.dragonboatrace.tools.Settings;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

@RunWith(GdxTestRunner.class)
public class DragonBoatRaceTest {
    @Test // created to test the gradle test system works, useless otherwise
    public void testTesting() throws Exception {
        Assert.assertEquals(0,0);
    }

    @Test
    public void testFinishLine() throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        // create a simple game version
        DragonBoatRace game = new DragonBoatRace();
        game.setDifficulty(0);
        //Race game_race = new Race(1000, BoatType.FAST, 1, 1);
        MainGameScreen game_screen = new MainGameScreen(game, BoatType.FAST);
        Race game_race =game_screen.getRace();
        // Run a short game
        game_race.getPlayer().setDistanceTravelled(10000);
        game_race.update(1, game, game_screen);
        //TimeUnit.SECONDS.sleep(1);

        game_race.update((float) 0.01, game, game_screen);

        // The player boat has travelled passed the finish line

        // check that values have been set
        //Game has been timed
        assertTrue(game.getScreen() instanceof RoundsScreen);

    }

    @Test
    public void testJoinFinal() throws NoSuchFieldException, IllegalAccessException {
        // Create a simple game
        DragonBoatRace game = new DragonBoatRace();
        game.setDifficulty(0);
        game.setRound(4);
        game.create();
        Boat playerBoat = new Boat(BoatType.FAST,
                new Lane(new Vector2(),
                        10,
                        4,
                        0),
                "");
        RoundsScreen game_screen = new RoundsScreen(game, playerBoat, "");
        // we now need to edit the times in the Total times in game

        for (int boat = 0; boat < Settings.PLAYER_COUNT; boat++){
            game.setTimeAt(boat, boat+10);
        }
        game.setPlayerTotalTime(5);

        // check if the player is in first place
        game_screen.render((float)0.01);

    }

    @Test
    public void testResultPosition() throws NoSuchFieldException, IllegalAccessException {
        DragonBoatRace game = new DragonBoatRace();
        game.setDifficulty(0);
        game.setRound(4);
        game.create();
        Race game_race = new Race(10, BoatType.FAST, 4,0);
        for (int boat = 0; boat < Settings.PLAYER_COUNT; boat++){
            game.setTimeAt(boat, boat+10);
        }
        game.setPlayerTotalTime(5);
        game_race.getLeaderBoard(game);
    }

}