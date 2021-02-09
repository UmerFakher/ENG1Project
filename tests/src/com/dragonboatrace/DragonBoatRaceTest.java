package com.dragonboatrace;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.screens.FinalScreen;
import com.dragonboatrace.screens.GameOverScreen;
import com.dragonboatrace.screens.MainGameScreen;
import com.dragonboatrace.screens.RoundsScreen;
import com.dragonboatrace.tools.Configuration;
import com.dragonboatrace.tools.Lane;
import com.dragonboatrace.tools.Race;
import com.dragonboatrace.tools.Configuration;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * TUA_FRAMEWORK_TEST - Make sure the unit test framework is running and accurate.
 *
 */
@RunWith(GdxTestRunner.class)
public class DragonBoatRaceTest {

    /**
     * TUA_FRAMEWORK_TEST - Make sure the unit test framework is running and accurate.
     * TI_FINISH_LINE - Check that the game finishes a round when the boat passes the finish line.
     * TI_FINAL - Check the game correctly determines if you should be in the final or not.
     * TI_RESULT_POSITIONS - Check the game gives awards to the correct boats at the end of the final race.

     * Created to test the gradle test system works.
     * @throws Exception
     */
    @Test
    public void testingFrameworkInitialTest() throws Exception {
        Assert.assertEquals(0, 0);
    }

    /**
     * TI_FINISH_LINE - Check that the game finishes a round when the boat passes the finish line.
     *
     * The game screen is  changed when a boat comes into contact with the finish line.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InterruptedException
     */
    @Test
    public void finishLineTest() throws NoSuchFieldException, IllegalAccessException, InterruptedException {
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

    /**
     * TI_FINAL - Check the game correctly determines if you should be in the final or not.
     *
     * If the player is within the top 4 the game screen is changed to the game screen.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void joinFinalTest() throws NoSuchFieldException, IllegalAccessException {
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

        for (int boat = 0; boat < Configuration.PLAYER_COUNT; boat++) {
            game.setTimeAt(boat, boat + 10);
        }
        game.setPlayerTotalTime(5);

        // check if the player is in first place
        gameScreen.moveToNextRound();
        assertTrue(game.getScreen() instanceof GameOverScreen);
    }

    /**
     * TI_RESULT_POSITIONS - Check the game gives awards to the correct boats at the end of the final race.
     *
     * If the player comes in first place in the final it reflects that in the reason string.
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void resultPositionTest() throws NoSuchFieldException, IllegalAccessException {
        DragonBoatRace game = new DragonBoatRace();
        game.setDifficulty(0);
        game.setRound(1);
        //Race gameRace = new Race(1000, BoatType.FAST, 1, 1);
        MainGameScreen gameScreen = new MainGameScreen(game, BoatType.FAST);
        game.setScreen(gameScreen);
        for (int boat = 1; boat < Configuration.PLAYER_COUNT; boat++) {
            game.setTimeAt(boat, boat + 10);
        }
        game.setTimeAt(0, 5);
        game.setPlayerTotalTime(5);
        ((MainGameScreen)game.getScreen()).getRace().getPlayer().setDistanceTravelled(10000);
        ((MainGameScreen)game.getScreen()).getRace().update(1 ,game, gameScreen);

        assertTrue(((RoundsScreen)game.getScreen()).getReason().contains("1st: Player"));
    }

}