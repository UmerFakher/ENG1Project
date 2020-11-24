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
import com.dragonboatrace.tools.Settings;

import java.util.ArrayList;
import java.util.Collections;

public class RoundsScreen implements Screen {

    private DragonBoatRace game;
    private int currentRound;
    private Boat playerBoat;
    private String reason;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private FreeTypeFontGenerator generator;
    private BitmapFont leaderBoardFont;
    private BitmapFont font;
    private GlyphLayout layout;

    public RoundsScreen(DragonBoatRace game, Boat playerBoat, String reason){
        this.game = game;
        this.currentRound = this.game.getRound();
        this.playerBoat = playerBoat;
        this.reason = reason;
        this.generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        this.parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 75 / Settings.SCALAR;
        parameter.color = Color.WHITE;
        this.font = generator.generateFont(parameter);
        this.leaderBoardFont = generator.generateFont(parameter);
        this.layout = new GlyphLayout();
        layout.setText(leaderBoardFont, this.reason);
        if (layout.height > Gdx.graphics.getHeight()/2) {
            int a = 75/Settings.SCALAR;
            int c = Gdx.graphics.getHeight()/2;
            float b = layout.height / c;

            parameter.size =  (int)(a/b) ;
            leaderBoardFont = generator.generateFont(parameter);
            layout.setText(leaderBoardFont, this.reason);
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.getBatch().begin();

        layout.setText(font,"Well done for completing round "+(this.currentRound-1) + " in "+this.playerBoat.getTime()+"s");
        font.draw(this.game.getBatch(), "Well done for completing round "+(this.currentRound-1) + " in "+this.playerBoat.getTime()+"s", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 75/Settings.SCALAR);

        layout.setText(font,"With "+this.playerBoat.getPenaltyTime() + "s of that in penalties");
        font.draw(this.game.getBatch(), "With " +this.playerBoat.getPenaltyTime() + "s of that in penalties", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 175/Settings.SCALAR);


        layout.setText(leaderBoardFont, this.reason);
        leaderBoardFont.draw(this.game.getBatch(), this.reason, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2 - 75/Settings.SCALAR);


        layout.setText(font, (this.currentRound == 4)? "Press Space to see if you made it to the final" : "Press Space to continue to round "+(this.currentRound));
        font.draw(this.game.getBatch(), (this.currentRound == 4)? "Press Space to see if you made it to the final" : "Press Space to continue to round "+(this.currentRound), (Gdx.graphics.getWidth() - layout.width) / 2, 100 + layout.height);

        this.game.getBatch().end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            if (this.game.getRound() > 3){
                ArrayList<Float> temp = this.game.getTotalTimes();
                Collections.sort(temp);
                ArrayList<Float> topPlayers = new ArrayList<Float>(temp.subList(0, 4));
                if (topPlayers.contains(this.game.getPlayerTotalTime())) {
                    this.game.setScreen(new FinalScreen(this.game, this.playerBoat));
                } else {
                    this.game.setScreen(new GameOverScreen(this.game, "You were not fast enough. Better luck next time!"));
                }
            } else {
                this.game.setScreen(new MainGameScreen(this.game, this.playerBoat.getBoatType()));
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
}
