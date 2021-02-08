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
import com.dragonboatrace.tools.Configuration;

/**
 * Represents the Main Game Screen where the game actually happens.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class MainGameScreen implements Screen {

    /**
     * The game instance.
     */
    private final DragonBoatRace game;
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
     * GlyphLayout used for centering fonts
     */
    private final GlyphLayout layout;
    /**
     * Font used for rendering to screen
     */
    private final BitmapFont font;
    /**
     * Used to make sure the countdown happens at equal intervals.
     */
    private final Timer timer;
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
     * Creates a new game screen with a game instance.
     *
     * @param game       The game instance.
     * @param boatChosen The {@link BoatType} that the player chose.
     */
    public MainGameScreen(DragonBoatRace game, BoatType boatChosen) {
        this.game = game;

        this.logger = new FPSLogger();

        this.race = new Race(10000, boatChosen, this.game.getRound(), this.game.getDifficulty());
        this.background = new ScrollingBackground();
        this.background.resize(Gdx.graphics.getWidth());

        System.out.println(this.game.getRound());


        /* Font related items */
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size *= 10.0 / Configuration.SCALAR;
        parameter.color = Color.BLACK;
        this.font = generator.generateFont(parameter);
        this.layout = new GlyphLayout();

        /* Countdown initialisation */
        Timer.Task countDownTask = new Timer.Task() {
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
     *
     * @param deltaTime The time since the last frame.
     */
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.getBatch().begin();
        if (!paused) {
            //this.logger.log();
            this.background.update(deltaTime * this.race.getPlayer().getVelocity().y);
            this.background.render(game.getBatch());
            this.race.update(deltaTime, this.game, this);
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

    public Race getRace() {
        return race;
    }
}
