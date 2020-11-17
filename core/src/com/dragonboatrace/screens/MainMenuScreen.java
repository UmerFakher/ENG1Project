package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.EntityType;


public class MainMenuScreen implements Screen {


    Button exitButton;
    Button playButton;
    Texture logo;

    DragonBoatRace game;

    public MainMenuScreen(DragonBoatRace game) {
        this.game = game;

        this.exitButton = new Button(new Vector2((Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth()) / 2.0f, 100), "exit_button_active.png", "exit_button_inactive.png");
        this.playButton = new Button(new Vector2((Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth()) / 2.0f, 300), "play_button_active.png", "play_button_inactive.png");
        this.logo = new Texture("dragon.png");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.game.getBatch().begin();

        this.game.getBatch().draw(logo, (Gdx.graphics.getWidth() - 680) / 2.0f, Gdx.graphics.getHeight() - 550, 680, 600);

        exitButton.render(this.game.getBatch());
        if (this.exitButton.isHovering() && Gdx.input.isTouched()) {
            Gdx.app.exit();
        }
        playButton.render(this.game.getBatch());
        if (this.playButton.isHovering() && Gdx.input.isTouched()) {
            game.setScreen(new MainGameScreen(this.game));
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
