package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;

public class BoatSelectScreen implements Screen {

    Button fastButton, agileButton, strongButton, enduranceButton;
    DragonBoatRace game;
    BoatType boatChosen;

    public BoatSelectScreen(DragonBoatRace game){

        this.game = game;
        this.boatChosen = null;

        float s = (Gdx.graphics.getWidth() - EntityType.BUTTON.getWidth()*4)/5;
        this.fastButton = new Button(new Vector2(s, 100), "fast.png", "fast.png");
        this.agileButton = new Button(new Vector2(s + (EntityType.BUTTON.getWidth() + s), 100), "agile.png", "agile.png");
        this.strongButton = new Button(new Vector2(s + (EntityType.BUTTON.getWidth()+s)*2, 100), "strong.png", "strong.png");
        this.enduranceButton = new Button(new Vector2(s + (EntityType.BUTTON.getWidth()+ s)*3, 100), "endurance.png", "endurance.png");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        BitmapFont font = new BitmapFont(Gdx.files.internal("default.fnt"), false);
        font.getData().setScale(10);
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, "Choose your Boat:");
        this.game.getBatch().begin();

        font.draw(this.game.getBatch(), "Choose your Boat:", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 100);

        this.fastButton.render(this.game.getBatch());
        this.agileButton.render(this.game.getBatch());
        this.strongButton.render(this.game.getBatch());
        this.enduranceButton.render(this.game.getBatch());

        if (this.fastButton.isHovering() && Gdx.input.isTouched()){
            this.game.setScreen(new MainGameScreen(this.game, BoatType.FAST));
        } else if (this.agileButton.isHovering() && Gdx.input.isTouched()){
            this.game.setScreen(new MainGameScreen(this.game, BoatType.AGILE));
        } else if (this.strongButton.isHovering() && Gdx.input.isTouched()){
            this.game.setScreen(new MainGameScreen(this.game, BoatType.STRONG));
        } else if (this.enduranceButton.isHovering() && Gdx.input.isTouched()){
            this.game.setScreen(new MainGameScreen(this.game, BoatType.ENDURANCE));
        }


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
