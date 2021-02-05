package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the Main Menu where the game first starts.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class MainMenuScreen implements Screen {

    /**
     * The instance of the game.
     */
    protected final DragonBoatRace game;
    /**
     * The logo position X offset.
     */
    private final float logoXOffset;
    /**
     * The logo position Y offset.
     */
    private final float logoYOffset;
    /**
     * The button used to exit the game.
     */
    private final Button exitButton;
    /**
     * The button used to start the game.
     */
    private final Button playButton;
    /**
     * The button used to go to the help screen.
     */
    private final Button helpButton;
    /**
     * The texture of the main logo.
     */
    private final Texture logo;

    private final Button loadButton;

    /**
     * Creates a new window that shows the main menu of the game.
     *
     * @param game The instance of the game.
     */
    public MainMenuScreen(DragonBoatRace game) {
        this.game = game;

        this.exitButton = new Button(new Vector2((Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth()) / 2.0f, 100f / Settings.SCALAR), "exit_button_active.png", "exit_button_inactive.png");
        this.playButton = new Button(new Vector2((Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth()) / 2.0f, 400f / Settings.SCALAR), "play_button_active.png", "play_button_inactive.png");
        this.helpButton = new Button(new Vector2((Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth()) / 2.0f, 250f / Settings.SCALAR), "help_button_active.png", "help_button_inactive.png");
        this.loadButton = new Button(new Vector2((Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth()) / 2.0f, 550f / Settings.SCALAR), "load_button_active.png", "load_button_inactive.png");
        this.logo = new Texture("dragon.png");
        logoXOffset = 680f / Settings.SCALAR;
        logoYOffset = 600f / Settings.SCALAR;

        //reset settings
        this.game.setRound(1);
        Settings.setPlayerCount(8);
    }


    @Override
    public void show() {

    }

    /**
     * Renders the main window.
     *
     * @param delta The time passed since the last frame.
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.game.getBatch().begin();

        this.game.getBatch().draw(logo, (Gdx.graphics.getWidth() - logoXOffset) / 2.0f, (Gdx.graphics.getHeight() - logoYOffset + playButton.getHitBox().getHeight() + playButton.getHitBox().getY()) / 2.0f, logoXOffset, logoYOffset);

        exitButton.render(this.game.getBatch());
        if (this.exitButton.isHovering() && Gdx.input.isTouched()) {
            Gdx.app.exit();
        }
        playButton.render(this.game.getBatch());
        if (this.playButton.isHovering() && Gdx.input.isTouched()) {
            // CHANGED CODE
            // Changed the new game button to load the difficulty screen rather than the boat selection screen. This
            // allows the player to select a difficulty
            game.setScreen(new DifficultySelectScreen(this.game));
            // END CHANGED CODE
        }
        helpButton.render(this.game.getBatch());
        if (this.helpButton.isHovering() && Gdx.input.isTouched()) {
            game.setScreen(new HelpScreen(this));
        }
        File f = new File("./savefile.txt");
        if (f.exists() && !f.isDirectory()) {

            loadButton.render(this.game.getBatch());
            if (this.loadButton.isHovering() && Gdx.input.isTouched()) {
                loadSavefile(f);
            }
        }

        this.game.getBatch().end();
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

    public void loadSavefile(File f) {
        List<String> saveData = new ArrayList<>();
        BoatType boat;

        try {
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //System.out.println(data);
                saveData.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            switch (Integer.parseInt(saveData.get(0))) {
                case 0:
                    boat = BoatType.FAST;
                    break;
                case 1:
                    boat = BoatType.AGILE;
                    break;
                case 2:
                    boat = BoatType.ENDURANCE;
                    break;
                case 3:
                    boat = BoatType.STRONG;
                    break;
                default:
                    throw new IndexOutOfBoundsException();
            }

            game.setPlayerTotalTime(Float.parseFloat(saveData.get(1)));
            if (Integer.parseInt(saveData.get(2)) - 1 > 4) throw new IndexOutOfBoundsException();
            game.setRound(Integer.parseInt(saveData.get(2)));
            if (Integer.parseInt(saveData.get(3)) > 4) throw new IndexOutOfBoundsException();
            game.setDifficulty(Integer.parseInt(saveData.get(3)));

            game.setScreen(new MainGameScreen(game, boat, f.getName().equals("testing_file.txt")));
        } catch (Exception e) {
            System.out.println("Unable to load file");
        }
    }

}
