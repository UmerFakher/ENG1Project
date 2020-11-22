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
    private String reason;

    public RoundsScreen(DragonBoatRace game, Boat playerBoat, String reason){
        this.game = game;
        this.currentRound = this.game.getRound();
        this.playerBoat = playerBoat;
        this.reason = reason;
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
        font.getData().setScale(5);
        GlyphLayout layout = new GlyphLayout();
        this.game.getBatch().begin();

        layout.setText(font,"Well done for completing round "+(this.currentRound-1) + " in "+this.playerBoat.getTime()+"s");
        font.draw(this.game.getBatch(), "Well done for completing round "+(this.currentRound-1) + " in "+this.playerBoat.getTime()+"s", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 75);

        layout.setText(font,"With "+this.playerBoat.getPenaltyTime() + "s of that in penalties");
        font.draw(this.game.getBatch(), "With " +this.playerBoat.getPenaltyTime() + "s of that in penalties", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 175);


        layout.setText(font, this.reason);
        if (layout.height +800 > Gdx.graphics.getHeight()) {
            font.getData().setScale(5 / (layout.height / 600));
            layout.setText(font, this.reason);
        }
        font.draw(this.game.getBatch(), this.reason, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2 - 75);
        font.getData().setScale(5);

        layout.setText(font, "Press Space to continue to round "+(this.currentRound));
        font.draw(this.game.getBatch(), "Press Space to continue to round "+(this.currentRound), (Gdx.graphics.getWidth() - layout.width) / 2, 100 + layout.height);

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
