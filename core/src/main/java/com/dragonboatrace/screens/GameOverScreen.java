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
import com.dragonboatrace.tools.Configuration;

/**
 * Displays the screen that shows the end of the game. This can be one of 3 situations: <ul>
 * <li>If the player got to the final and finished the race.</li>
 * <li>The player didn't make it to the final after the 3rd round.</li>
 * <li>The players boat ran out of health.</li>
 * </ul>
 *
 * @author Benji Garment, Joe Wrieden
 */
public class GameOverScreen implements Screen {

    /**
     * The game instance.
     */
    private final DragonBoatRace game;

    /**
     * The reason for the game to be over.
     */
    protected String reason;

    /* Font related items */
    private final BitmapFont font;
    private BitmapFont leaderBoardFont;
    private final GlyphLayout layout;

    /**
     * Creates a new screen that represents the end of the game to the player.
     *
     * @param game   The instance of the game.
     * @param reason The reason for the game to be over.
     */
    public GameOverScreen(DragonBoatRace game, String reason) {
        this.game = game;
        this.reason = reason;

        /* Font related items */
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 75 / Configuration.SCALAR;
        parameter.color = Color.WHITE;
        this.font = generator.generateFont(parameter);
        this.leaderBoardFont = generator.generateFont(parameter);
        this.layout = new GlyphLayout();
        parameter.size = 75 / Configuration.SCALAR;
        layout.setText(leaderBoardFont, this.reason);
        if (layout.height > Gdx.graphics.getHeight() / 2f) {
            int a = 75 / Configuration.SCALAR;
            int c = Gdx.graphics.getHeight() / 2;
            float b = layout.height / c;

            parameter.size = (int) (a / b);
            leaderBoardFont = generator.generateFont(parameter);
            layout.setText(leaderBoardFont, this.reason);
        }

    }

    @Override
    public void show() {
        Configuration.setPlayerCount(8);
    }

    /**
     * Renders the window to show the end of the game.
     *
     * @param delta The time passed since the last frame.
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.getBatch().begin();

        layout.setText(font, "GAME OVER!");
        font.draw(this.game.getBatch(), "GAME OVER!", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 100.f / Configuration.SCALAR);

        layout.setText(leaderBoardFont, this.reason);
        leaderBoardFont.draw(this.game.getBatch(), this.reason, (Gdx.graphics.getWidth() - layout.width) / 2, (Gdx.graphics.getHeight() + layout.height) / 2);

        layout.setText(font, "Press Esc to return to Main Menu");
        font.draw(this.game.getBatch(), "Press Esc to return to Main Menu", (Gdx.graphics.getWidth() - layout.width) / 2, 200.f / Configuration.SCALAR);

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
