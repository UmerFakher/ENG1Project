package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Timer;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.tools.Race;
import com.dragonboatrace.tools.ScrollingBackground;

public class MainGameScreen implements Screen {

    private final DragonBoatRace game;
    private final Timer.Task countDownTask;
    private final Timer timer;
    private final Race race;
    private final ScrollingBackground background;
    private final FPSLogger logger;
    private boolean paused = true;
    /* Initial countdown variables */
    private int countDownRemaining = 3;
    private String countDownString = "";

    public MainGameScreen(DragonBoatRace game) {
        this.game = game;

        this.logger = new FPSLogger();

        this.race = new Race(3000);
        this.background = new ScrollingBackground();
        this.background.resize(Gdx.graphics.getWidth());

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

    @Override
    public void show() {
        timer.start();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.getBatch().begin();
        if (!paused) {
            this.logger.log();
            this.background.update(delta * this.race.getPlayer().getVelocity().y);
            this.background.render(game.getBatch());
            this.race.update(delta);
            this.race.render(game.getBatch());
        } else {
            displayCountDown();
        }
        this.game.getBatch().end();
    }

    private void displayCountDown() {
        BitmapFont font = new BitmapFont(Gdx.files.internal("default.fnt"), false);
        font.setColor(Color.RED);
        font.getData().setScale(5);
        GlyphLayout layout = new GlyphLayout();
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
