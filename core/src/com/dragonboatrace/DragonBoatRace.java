package com.dragonboatrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonboatrace.screens.MainMenuScreen;
import com.dragonboatrace.tools.Settings;

import java.util.ArrayList;

/** Represents the Game itself and holds all the screens.
 * @author Benji Garment, Joe Wrieden
 */
public class DragonBoatRace extends Game {

    SpriteBatch batch;
    int round = 1;
    ArrayList<Float> totalTimes = new ArrayList<Float>();
    float playerTotalTime = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MainMenuScreen(this));
        for (int i = 0; i < Settings.PLAYER_COUNT; i++)
            totalTimes.add((float)0);
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

    public int getRound() { return this.round; }

    public void upRound() { this.round+=1; }

    public void setRound(int i) { this.round+=i; }


    public void setTimeAt(int i, float t) { this.totalTimes.set(i, this.totalTimes.get(i)+t); }

    public void setPlayerTotalTime(float t) { this.playerTotalTime += t; }

    public float getPlayerTotalTime() { return this.playerTotalTime; }

    public ArrayList<Float> getTotalTimes() { return this.totalTimes; }
}
