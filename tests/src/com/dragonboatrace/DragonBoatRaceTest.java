package com.dragonboatrace;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.screens.FinalScreen;
import com.dragonboatrace.screens.GameOverScreen;
import com.dragonboatrace.screens.MainGameScreen;
import com.dragonboatrace.screens.RoundsScreen;
import com.dragonboatrace.tools.Lane;
import com.dragonboatrace.tools.Race;
import com.dragonboatrace.tools.Settings;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class DragonBoatRaceTest {
    @Test // created to test the gradle test system works, useless otherwise
    public void testTesting() throws Exception {
        Assert.assertEquals(0, 0);
    }

    @Test
    public void testFinishLine() throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        // create a simple game version
        DragonBoatRace game = new DragonBoatRace();
        game.setDifficulty(0);
        //Race gameRace = new Race(1000, BoatType.FAST, 1, 1);
        MainGameScreen gameScreen = new MainGameScreen(game, BoatType.FAST);
        Race gameRace = gameScreen.getRace();
        // Run a short game
        gameRace.getPlayer().setDistanceTravelled(10000);
        gameRace.update(1, game, gameScreen);
        //TimeUnit.SECONDS.sleep(1);

        gameRace.update((float) 0.01, game, gameScreen);

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
        Boat playerBoat = new Boat(BoatType.FAST,
                new Lane(new Vector2(),
                        10,
                        4,
                        0),
                "");
        RoundsScreen gameScreen = new RoundsScreen(game, playerBoat, "");
        // we now need to edit the times in the Total times in game

        for (int boat = 0; boat < Settings.PLAYER_COUNT; boat++) {
            game.setTimeAt(boat, boat + 10);
        }
        game.setPlayerTotalTime(5);

        // check if the player is in first place
        gameScreen.moveToNextRound();
        assertTrue(game.getScreen() instanceof GameOverScreen);
    }

    @Test
    public void testResultPosition() throws NoSuchFieldException, IllegalAccessException {
        DragonBoatRace game = new DragonBoatRace();
        game.setDifficulty(0);
        game.setRound(1);
        //Race gameRace = new Race(1000, BoatType.FAST, 1, 1);
        MainGameScreen gameScreen = new MainGameScreen(game, BoatType.FAST);
        game.setScreen(gameScreen);
        for (int boat = 1; boat < Settings.PLAYER_COUNT; boat++) {
            game.setTimeAt(boat, boat + 10);
        }
        game.setTimeAt(0, 5);
        game.setPlayerTotalTime(5);
        ((MainGameScreen)game.getScreen()).getRace().getPlayer().setDistanceTravelled(10000);
        ((MainGameScreen)game.getScreen()).getRace().update(1 ,game, gameScreen);
        
        assertTrue(((RoundsScreen)game.getScreen()).getReason().contains("1st: Player"));
    }

}