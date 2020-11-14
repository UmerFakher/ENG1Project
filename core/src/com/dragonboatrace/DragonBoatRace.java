package com.dragonboatrace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonboatrace.screens.MainMenuScreen;

public class DragonBoatRace extends Game {

    SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        this.setScreen(new MainMenuScreen(this));


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
}
