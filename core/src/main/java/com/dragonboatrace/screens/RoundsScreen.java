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
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.Settings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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

        this.saveMessage = "\nPress Esc to save and return to the main menu";

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60 / Settings.SCALAR;
        parameter.color = Color.WHITE;

        this.font = generator.generateFont(parameter);
        this.leaderBoardFont = generator.generateFont(parameter);
        this.layout = new GlyphLayout();

        layout.setText(leaderBoardFont, this.reason);

        /* If the leaderboard doesnt fit on the screen */
        if (layout.height > Gdx.graphics.getHeight() / 2f) {
            /* Scale the font to fit on the screen. */
            int a = 75 / Settings.SCALAR;
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

        if (!reason.equals("")) {
            layout.setText(font, "Well done for completing round " + (this.currentRound - 1) + " in " + this.playerBoat.getTime() + "s");
            font.draw(this.game.getBatch(), "Well done for completing round " + (this.currentRound - 1) + " in " + this.playerBoat.getTime() + "s", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 75 / Settings.SCALAR);

            layout.setText(font, "With " + this.playerBoat.getPenaltyTime() + "s of that in penalties");
            font.draw(this.game.getBatch(), "With " + this.playerBoat.getPenaltyTime() + "s of that in penalties", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 175f / Settings.SCALAR);


            layout.setText(leaderBoardFont, this.reason);
            leaderBoardFont.draw(this.game.getBatch(), this.reason, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2 - 75f / Settings.SCALAR);


            layout.setText(font, (this.currentRound == 4) ? "Press Space to see if you made it to the final " + saveMessage : "Press Space to continue to round " + (this.currentRound) + saveMessage);
            font.draw(this.game.getBatch(), (this.currentRound == 4) ? "Press Space to see if you made it to the final " + saveMessage : "Press Space to continue to round " + (this.currentRound) + saveMessage, (Gdx.graphics.getWidth() - layout.width) / 2, 50 + layout.height);
        } else {
            // CHANGED CODE
            // Custom message text if the game was aborted before finishing
            layout.setText(font, "Press Space to quit without saving " + saveMessage);
            font.draw(this.game.getBatch(), "Press Space to quit without saving " + saveMessage, (Gdx.graphics.getWidth() - layout.width) / 2, 50 + layout.height);
            // END CHANGED CODE
        }

        this.game.getBatch().end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            if (reason.equals("")) {
                // CHANGED CODE
                // If game was aborted early, quit to main menu instead of continuing to next round
                this.game.setRound(1);
                this.game.setScreen(new MainMenuScreen(this.game));
                // END CHANGED CODE
            } else {
                if (this.game.getRound() > 3) {
                    ArrayList<Float> temp = this.game.getTotalTimes();
                    Collections.sort(temp);
                    ArrayList<Float> topPlayers = new ArrayList<>(temp.subList(0, 4));
                    if (topPlayers.contains(this.game.getPlayerTotalTime())) {
                        this.game.setScreen(new FinalScreen(this.game, this.playerBoat));
                    } else {
                        this.game.setScreen(new GameOverScreen(this.game, "You were not fast enough. Better luck next time!"));
                    }
                } else {
                    this.game.setScreen(new MainGameScreen(this.game, this.playerBoat.getBoatType()));
                }
            }

        // CHANGED CODE
        // Added saving on ESC
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            saveToFile("savefile.txt", playerBoat.getBoatType(), this.game.getPlayerTotalTime(), this.game.getRound(), this.game.getDifficulty());

            //reset the rounds
            this.game.setRound(1);
            this.game.setScreen(new MainMenuScreen(this.game));
        }
        // END CHANGED CODE
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

    // CHANGED CODE
    // Saving function
    public static void saveToFile(String filename, BoatType boatType, float totalTime, int round, int difficulty) {
        File oldFile = new File(filename);

        try {
            File newFile = new File(filename);
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(filename, false);
            myWriter.write(boatType.getSaveString()
                    + Float.toString(totalTime) + "\n"
                    + Integer.toString(round) + "\n"
                    + Integer.toString(difficulty) + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    // CHANGED CODE
}
