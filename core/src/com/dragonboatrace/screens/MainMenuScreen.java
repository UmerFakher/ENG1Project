package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.tools.Settings;

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
        this.logo = new Texture("dragon.png");
        logoXOffset = 680f / Settings.SCALAR;
        logoYOffset = 600f / Settings.SCALAR;
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
            game.setScreen(new BoatSelectScreen(this.game));
        }
        helpButton.render(this.game.getBatch());
        if (this.helpButton.isHovering() && Gdx.input.isTouched()) {
            game.setScreen(new HelpScreen(this));
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

}
