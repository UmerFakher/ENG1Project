package com.dragonboatrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonboatrace.screens.MainMenuScreen;
import com.dragonboatrace.tools.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Game itself and holds all the screens.
 *
 * @author Benji Garment, Joe Wrieden, William Walton
 */
public class DragonBoatRace extends Game {

    /**
     * The Spritebatch used to group all renders.
     */
    protected SpriteBatch batch;

    /**
     * The current round.
     */
    protected int round = 1;

    /**
     * A list of total times for all boats.
     */
    protected List<Float> totalTimes = new ArrayList<>();

    /**
     * The players total time.
     */
    protected float playerTotalTime = 0;

    /**
     * The difficulty chosen 0 = easiest, 3 = hardest
     */
    protected int difficulty = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
        for (int i = 0; i < Configuration.PLAYER_COUNT; i++)
            totalTimes.add((float) 0);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public int getRound() {
        return this.round;
    }

    public void setRound(int i) {
        this.round = i;
    }

    public void upRound() {
        this.round += 1;
    }

    public void setTimeAt(int i, float t) {
        if(totalTimes.size() < i+1) return;
        this.totalTimes.set(i, this.totalTimes.get(i) + t);
    }

    public float getPlayerTotalTime() {
        return this.playerTotalTime;
    }

    public void setPlayerTotalTime(float t) {
        this.playerTotalTime += t;
    }

    public List<Float> getTotalTimes() {
        return this.totalTimes;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
