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
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the intermediary screen when paused
 *
 * @author William Walton
 */
public class MainGamePauseScreen implements Screen {
    /**
     * The instance of the game.
     */
    private final DragonBoatRace game;
    /**
     * The instance of the screen paused from.
     */
    private final MainGameScreen save;

    /**
     * The display message.
     */
    private final String saveMessage;
    private final BitmapFont font;
    private final GlyphLayout layout;

    /**
     * Creates a new screen to display the leaderboard from the previous round.
     *
     * @param game The game instance.
     * @param save The screen that initiated the pause
     */
    public MainGamePauseScreen(DragonBoatRace game, MainGameScreen save) {
        this.game = game;
        this.save = save;

        this.saveMessage = "\nPress Enter to save and return to the main menu";

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60 / Configuration.SCALAR;
        parameter.color = Color.WHITE;

        this.font = generator.generateFont(parameter);
        this.layout = new GlyphLayout();
    }

    public static void saveToFile(String filename, BoatType boatType, float totalTime, int round, int difficulty) {
        File oldFile = new File(filename);

        try {
            File newFile = new File(filename);
            newFile.createNewFile();
        } catch (IOException e) {
            //System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(filename, false);
            myWriter.write(boatType.getSaveString()
                    + totalTime + "\n"
                    + round + "\n"
                    + difficulty + "\n");
            myWriter.close();
        } catch (IOException e) {
            //System.out.println("An error occurred.");
            //e.printStackTrace();
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

        layout.setText(font, "Press Space to quit without saving\nPress Escape to unpause the game " + saveMessage);
        font.draw(this.game.getBatch(), "Press Space to quit without saving\nPress Escape to unpause the game " + saveMessage, (Gdx.graphics.getWidth() - layout.width) / 2, 50 + layout.height);

        this.game.getBatch().end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            // quit to main menu without saving
            this.game.setScreen(new MainMenuScreen(this.game));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            //save and quit to main menu
            saveToFile("savefile.txt");
            this.game.setScreen(new MainMenuScreen(this.game));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            //resume game
            this.game.setScreen(save);
        }

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

    public void saveToFile(String filename) {
        saveToFile(filename, this.save.getRace().getPlayer().getBoatType(), this.game.getPlayerTotalTime(), this.game.getRound(), this.game.getDifficulty());
    }
}
