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
import com.dragonboatrace.tools.Settings;

/** Represents the Game Over Screen.
 * @author Benji Garment, Joe Wrieden
 */
public class GameOverScreen implements Screen {

    DragonBoatRace game;
    protected String reason;
    protected FreeTypeFontGenerator generator;
    protected FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    protected BitmapFont font;
    protected BitmapFont leaderBoardFont;
    protected GlyphLayout layout;

    public GameOverScreen(DragonBoatRace game, String reason) {
        this.game = game;
        this.reason = reason;
        this.generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        this.parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 75 / Settings.SCALAR;
        parameter.color = Color.WHITE;
        this.font = generator.generateFont(parameter);
        this.leaderBoardFont = generator.generateFont(parameter);
        this.layout = new GlyphLayout();
        parameter.size = 60 / Settings.SCALAR;
        layout.setText(leaderBoardFont, this.reason);
        if (layout.height +800 > Gdx.graphics.getHeight()) {
            parameter.size = (int)(60 / (layout.height / 600))/Settings.SCALAR;
            leaderBoardFont = generator.generateFont(parameter);
            layout.setText(leaderBoardFont, this.reason);
        }
    }

    @Override
    public void show() {
        this.game.setRound(1);
        Settings.setPlayerCount(8);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.getBatch().begin();

        layout.setText(font, "GAME OVER!");
        font.draw(this.game.getBatch(), "GAME OVER!", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 100);

        layout.setText(leaderBoardFont, this.reason);
        leaderBoardFont.draw(this.game.getBatch(), this.reason, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2);

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
