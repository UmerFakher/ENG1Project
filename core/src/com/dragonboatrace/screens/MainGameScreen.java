package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Timer;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.Race;
import com.dragonboatrace.tools.ScrollingBackground;

/** Represents the Main Game Screen where the game actually happens.
 * @author Benji Garment, Joe Wrieden
 */
public class MainGameScreen implements Screen {

    /**
     * The game instance.
     */
    private final DragonBoatRace game;
    /**
     * The function used to countdown when the race first starts.
     */
    private final Timer.Task countDownTask;
    /**
     * Used to make sure the countdown happens at equal intervals.
     */
    private final Timer timer;
    /**
     * The race instance.
     */
    private final Race race;
    /**
     * The background of the window.
     */
    private final ScrollingBackground background;
    /**
     * Use to log the FPS for debugging.
     */
    private final FPSLogger logger;
    /**
     * Pause game, starts true.
     */
    private boolean paused = true;
    /**
     * The time left on the initial countdown.
     */
    private int countDownRemaining = 3;
    /**
     * The String being displayed in the countdown.
     */
    private String countDownString = "";

    /**
     * The Type of Boat chosen in the BoatSelect Screen
     */
    private BoatType boatChosen;

    /**
     * FreeTypeFontGenerator for generating fonts
     */
    private FreeTypeFontGenerator generator;

    /**
     * FreeTypeFontParameter for modifying font
     */
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    /**
     * GlyphLayout used for centering fonts
     */
    private GlyphLayout layout;

    /**
     * Font used for rendering to screen
     */
    private BitmapFont font;

    /**
     * Creates a new game screen with a game instance.
     * @param game The game instance.
     */
    public MainGameScreen(DragonBoatRace game, BoatType boatChosen) {
        this.game = game;
        this.boatChosen = boatChosen;
        this.logger = new FPSLogger();

        this.race = new Race(10000, this.boatChosen, this.game.getRound());
        this.background = new ScrollingBackground();
        this.background.resize(Gdx.graphics.getWidth());

        this.generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        this.parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size *= 10;
        parameter.color = Color.ORANGE;
        this.font = generator.generateFont(parameter);
        this.layout = new GlyphLayout();

        /* Countdown initialisation */
        countDownTask = new Timer.Task() {
            @Override
            public void run() {
                paused = true;
                if (countDownRemaining == 3) {
                    countDownString = "READY";
                    countDownRemaining--;
                } else if (countDownRemaining == 2) {
                    countDownString = "STEADY";
                    countDownRemaining--;
                } else if (countDownRemaining == 1) {
                    countDownString = "GO";
                    countDownRemaining--;
                } else {
                    countDownString = "";
                    paused = false;
                    this.cancel();
                }
            }
        };
        timer = new Timer();
        timer.scheduleTask(countDownTask, 0, 1);
        // We don't want the countdown to start before the screen has displayed.
        timer.stop();
    }

    /**
     * Runs when the window first starts. Runs the countdown starter.
     */
    public void show() {
        timer.start();
    }

    /**
     * Render the main game window. Includes rendering the background and the {@link Race}.
     * @param deltaTime The time since the last frame.
     */
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.getBatch().begin();
        if (!paused) {
            this.logger.log();
            this.background.update(deltaTime * this.race.getPlayer().getVelocity().y);
            this.background.render(game.getBatch());
            this.race.update(deltaTime, this.game);
            this.race.render(game.getBatch());
        } else {
            this.background.render(game.getBatch());
            this.race.render(game.getBatch());
            displayCountDown();
        }
        this.game.getBatch().end();
    }

    /**
     * Render the current status of the countdown.
     */
    private void displayCountDown() {
        layout.setText(font, this.countDownString);
        font.draw(game.getBatch(), this.countDownString, (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() / 2.0f);
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
        this.game.getBatch().dispose();
    }
}
