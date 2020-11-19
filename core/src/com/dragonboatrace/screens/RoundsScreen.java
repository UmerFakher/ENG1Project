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

public class RoundsScreen implements Screen {

    private DragonBoatRace game;
    private int currentRound;
    private Boat playerBoat;

    public RoundsScreen(DragonBoatRace game, Boat playerBoat){
        this.game = game;
        this.currentRound = this.game.getRound();
        this.playerBoat = playerBoat;
    }


    @Override
    public void show() {
        if (this.currentRound == 4){
            this.game.setScreen(new FinalScreen(this.game, this.playerBoat));
        }

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

        layout.setText(font,"Well done for completing round 1");
        font.draw(this.game.getBatch(), "Well done for completing round  "+(this.currentRound-1), (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 100);

        layout.setText(font, "Your current total time is: XXXX");
        font.draw(this.game.getBatch(), "Your current total time is: XXXX", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight()/2);

        layout.setText(font, "Press Space to continue to round "+this.currentRound);
        font.draw(this.game.getBatch(), "Press Space to continue to round "+this.currentRound, (Gdx.graphics.getWidth() - layout.width) / 2, 100 + layout.height);

        this.game.getBatch().end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            this.game.setScreen(new MainGameScreen(this.game, this.playerBoat.getBoatType()));



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
