package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.Entity;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.tools.Settings;

import java.util.Set;

/** Represents the Main Menu where the game first starts.
 * @author Benji Garment, Joe Wrieden
 */
public class MainMenuScreen implements Screen {


    Button exitButton;
    Button playButton;
    Texture logo;

    private final float logoXOffset, logoYOffset;


    DragonBoatRace game;

    public MainMenuScreen(DragonBoatRace game) {
        this.game = game;

        this.exitButton = new Button(new Vector2((Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth()) / 2.0f, 100/Settings.SCALAR), "exit_button_active.png", "exit_button_inactive.png");
        this.playButton = new Button(new Vector2((Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth()) / 2.0f, 300/Settings.SCALAR), "play_button_active.png", "play_button_inactive.png");
        this.logo = new Texture("dragon.png");
        logoXOffset = 680 / Settings.SCALAR;
        logoYOffset = 600 / Settings.SCALAR;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.game.getBatch().begin();



        //this.game.getBatch().draw(logo, (Gdx.graphics.getWidth() - logoOffset) / 2.0f, Gdx.graphics.getHeight() - 625/Settings.SCALAR, logoOffset, 600 / Settings.SCALAR);
        this.game.getBatch().draw(logo, (Gdx.graphics.getWidth() - logoXOffset) / 2.0f, (Gdx.graphics.getHeight() - logoYOffset + playButton.getHitBox().getHeight() + playButton.getHitBox().getY())/2.0f, logoXOffset, logoYOffset);

        exitButton.render(this.game.getBatch());
        if (this.exitButton.isHovering() && Gdx.input.isTouched()) {
            Gdx.app.exit();
        }
        playButton.render(this.game.getBatch());
        if (this.playButton.isHovering() && Gdx.input.isTouched()) {
            game.setScreen(new BoatSelectScreen(this.game));
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
