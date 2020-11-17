package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.dragonboatrace.DragonBoatRace;

public class GameOverScreen implements Screen {

    DragonBoatRace game;
    String reason;

    public GameOverScreen(DragonBoatRace game, String reason) {
        this.game = game;
        this.reason = reason;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.getBatch().begin();
        BitmapFont font = new BitmapFont(Gdx.files.internal("default.fnt"), false);
        font.setColor(Color.WHITE);
        font.getData().setScale(6);
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, "GAME OVER!");
        font.draw(this.game.getBatch(), "GAME OVER!", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 100);
        layout.setText(font, this.reason);
        if (layout.height > Gdx.graphics.getHeight()) {
            font.getData().setScale(6 / (layout.height / 600));
            layout.setText(font, this.reason);
        }
        font.draw(this.game.getBatch(), this.reason, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2);
        font.getData().setScale(6);
        layout.setText(font, "Press Esc to return to Main Menu");
        font.draw(this.game.getBatch(), "Press Esc to return to Main Menu", (Gdx.graphics.getWidth() - layout.width) / 2, 200);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            this.game.setScreen(new MainMenuScreen(this.game));

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
