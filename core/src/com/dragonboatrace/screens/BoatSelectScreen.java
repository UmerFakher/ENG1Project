package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.entities.boats.BoatType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dragonboatrace.tools.Settings;


public class BoatSelectScreen implements Screen {

    Button fastButton, agileButton, strongButton, enduranceButton;
    DragonBoatRace game;
    BoatType boatChosen;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font;
    GlyphLayout layout;

    public BoatSelectScreen(DragonBoatRace game){

        this.game = game;
        this.boatChosen = null;

        int buttonWidth = EntityType.BUTTON.getWidth();
        float s = (Gdx.graphics.getWidth() - buttonWidth * 4)/5;
        this.fastButton = new Button(new Vector2(s, 100), "fast_button_active.png", "fast_button_inactive.png");
        this.agileButton = new Button(new Vector2(s + (buttonWidth + s), 100), "agile_button_active.png", "agile_button_inactive.png");
        this.strongButton = new Button(new Vector2(s + (buttonWidth+s)*2, 100), "strong_button_active.png", "strong_button_inactive.png");
        this.enduranceButton = new Button(new Vector2(s + (buttonWidth+ s)*3, 100), "endurance_button_active.png", "endurance_button_inactive.png");

        generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size *= 10 / Settings.SCALAR;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        layout = new GlyphLayout();
        layout.setText(font, "Choose your Boat:");

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
