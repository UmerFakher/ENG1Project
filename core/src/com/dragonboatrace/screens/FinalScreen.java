package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.boats.Boat;

public class FinalScreen implements Screen {


    private DragonBoatRace game;
    private Boat playerBoat;

    public FinalScreen(DragonBoatRace game, Boat playerBoat){
        this.game = game;
        this.playerBoat = playerBoat;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        BitmapFont font = new BitmapFont(Gdx.files.internal("default.fnt"), false);
        font.setColor(Color.WHITE);
        font.getData().setScale(6);
        GlyphLayout layout = new GlyphLayout();
        this.game.getBatch().begin();

        layout.setText(font,"You have made it into the final!");
        font.draw(this.game.getBatch(), "You have made it into the final!", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 100);

        layout.setText(font, "Press Space to continue to the final!");
        font.draw(this.game.getBatch(), "Press Space to continue to the final!", (Gdx.graphics.getWidth() - layout.width) / 2, 100 + layout.height);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            this.game.setScreen(new MainGameScreen(this.game, this.playerBoat.getBoatType()));

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
