package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.tools.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the intermediary screen between rounds that shows the player their place in the previous race and waits
 * for the user to continue on to the next round.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class RoundsScreen implements Screen {
    /**
     * The instance of the game.
     */
    private final DragonBoatRace game;

    /**
     * The current round of the game.
     */
    private final int currentRound;

    /**
     * The instance of the players boat to bring through each round.
     */
    private final Boat playerBoat;

    /**
     * The leaderboard to display the places of the boats in the race.
     */
    private final String reason;
    private final String saveMessage;
    private final BitmapFont font;
    private final GlyphLayout layout;
    /* Font related items */
    private BitmapFont leaderBoardFont;

    /**
     * Creates a new screen to display the leaderboard from the previous round.
     *
     * @param game       The game instance.
     * @param playerBoat The players boat to bring through each round.
     * @param reason     The string that represents the positions of the boats in the round that just finished.
     */
    public RoundsScreen(DragonBoatRace game, Boat playerBoat, String reason) {
        this.game = game;
        this.currentRound = this.game.getRound();
        this.playerBoat = playerBoat;
        this.reason = reason;

        this.saveMessage = "\nPress Enter to save and return to the main menu";

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60 / Configuration.SCALAR;
        parameter.color = Color.WHITE;

        this.font = generator.generateFont(parameter);
        this.leaderBoardFont = generator.generateFont(parameter);
        this.layout = new GlyphLayout();

        layout.setText(leaderBoardFont, this.reason);

        /* If the leaderboard doesnt fit on the screen */
        if (layout.height > Gdx.graphics.getHeight() / 2f) {
            /* Scale the font to fit on the screen. */
            int a = 75 / Configuration.SCALAR;
            int c = Gdx.graphics.getHeight() / 2;
            float b = layout.height / c;
            parameter.size = (int) (a / b);
            leaderBoardFont = generator.generateFont(parameter);
            layout.setText(leaderBoardFont, this.reason);
        }
    }


    @Override
    public void show() {

    }

    /**
     * Render the screen to show the leaderboard of all the boats in the round and their positions.
     *
     * @param delta The time passed since the last frame.
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.getBatch().begin();

        layout.setText(font, "Well done for completing round " + (this.currentRound - 1) + " in " + this.playerBoat.getTime() + "s");
        font.draw(this.game.getBatch(), "Well done for completing round " + (this.currentRound - 1) + " in " + this.playerBoat.getTime() + "s", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 75.f / Configuration.SCALAR);

        layout.setText(font, "With " + this.playerBoat.getPenaltyTime() + "s of that in penalties");
        font.draw(this.game.getBatch(), "With " + this.playerBoat.getPenaltyTime() + "s of that in penalties", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 175f / Configuration.SCALAR);


        layout.setText(leaderBoardFont, this.reason);
        leaderBoardFont.draw(this.game.getBatch(), this.reason, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2 - 75f / Configuration.SCALAR);


        layout.setText(font, (this.currentRound == 4) ? "Press Space to see if you made it to the final " + saveMessage : "Press Space to continue to round " + (this.currentRound) + saveMessage);
        font.draw(this.game.getBatch(), (this.currentRound == 4) ? "Press Space to see if you made it to the final " + saveMessage : "Press Space to continue to round " + (this.currentRound) + saveMessage, (Gdx.graphics.getWidth() - layout.width) / 2, 50 + layout.height);


        this.game.getBatch().end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            moveToNextRound();

        // CHANGED CODE for UR_SAVE_RESUME_GAME
        // Added saving on ENTER
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            saveGame();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    // CHANGED CODE for UR_SAVE_RESUME_GAME
    // Saving function
    public void saveToFile(String filename) {
        MainGamePauseScreen.saveToFile(filename, playerBoat.getBoatType(), this.game.getPlayerTotalTime(), this.game.getRound(), this.game.getDifficulty());
    }
    // CHANGED CODE

    /**
     * Created to allow testing without rendering
     */
    public void moveToNextRound() {
            if (this.game.getRound() > 3) {
                List<Float> temp = this.game.getTotalTimes();
                Collections.sort(temp);
                List<Float> topPlayers = new ArrayList<>(temp.subList(0, Math.min(temp.size(), 4)));
                if (topPlayers.contains(this.game.getPlayerTotalTime())) {
                    this.game.setScreen(new FinalScreen(this.game, this.playerBoat));
                } else {
                    this.game.setScreen(new GameOverScreen(this.game, "You were not fast enough. Better luck next time!"));
                }
            } else {
                this.game.setScreen(new MainGameScreen(this.game, this.playerBoat.getBoatType()));
            }
    }

    /**
     * Created to allow testing without rendering
     */
    public void saveGame(){
        // CHANGED CODE for UR_SAVE_RESUME_GAME
        // Added saving on ENTER
         {
            saveToFile("savefile.txt");
            this.game.setScreen(new MainMenuScreen(this.game));
        }
        // END CHANGED CODE
    }
}
