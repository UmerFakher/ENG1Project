package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

    Texture fastImage, agileImage, strongImage, enduranceImage;
    Button fastButton, agileButton, strongButton, enduranceButton;
    DragonBoatRace game;
    BoatType boatChosen;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font;
    GlyphLayout layout;
    float spacing;
    int buttonWidth;

    public BoatSelectScreen(DragonBoatRace game){

        this.game = game;
        this.boatChosen = null;

        this.buttonWidth = EntityType.BUTTON.getWidth();
        this.spacing = (Gdx.graphics.getWidth() - buttonWidth * 4)/5;
        this.fastButton = new Button(new Vector2(this.spacing, 100), "fast_button_active.png", "fast_button_inactive.png");
        this.agileButton = new Button(new Vector2(this.spacing + (buttonWidth + this.spacing), 100), "agile_button_active.png", "agile_button_inactive.png");
        this.strongButton = new Button(new Vector2(this.spacing + (buttonWidth+this.spacing)*2, 100), "strong_button_active.png", "strong_button_inactive.png");
        this.enduranceButton = new Button(new Vector2(this.spacing + (buttonWidth+ this.spacing)*3, 100), "endurance_button_active.png", "endurance_button_inactive.png");

        this.fastImage = new Texture("fast.png");
        this.agileImage = new Texture("agile.png");
        this.strongImage = new Texture("strong.png");
        this.enduranceImage = new Texture("endurance.png");




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

        float scale = (this.buttonWidth / EntityType.BOAT.getWidth())/2f;

        this.game.getBatch().draw(this.fastImage, this.fastButton.getHitBox().getX() + ((this.fastButton.getHitBox().getWidth() - this.buttonWidth/2)/2f), 200 + EntityType.BUTTON.getHeight(), this.buttonWidth/2, EntityType.BOAT.getHeight()*scale);
        this.fastButton.render(this.game.getBatch());

        this.game.getBatch().draw(this.agileImage, this.agileButton.getHitBox().getX() + ((this.agileButton.getHitBox().getWidth() - this.buttonWidth/2)/2f) , 200 + EntityType.BUTTON.getHeight(), this.buttonWidth/2, EntityType.BOAT.getHeight()*scale);
        this.agileButton.render(this.game.getBatch());

        this.game.getBatch().draw(this.strongImage, this.strongButton.getHitBox().getX() + ((this.strongButton.getHitBox().getWidth() - this.buttonWidth/2)/2f) , 200 + EntityType.BUTTON.getHeight(), this.buttonWidth/2, EntityType.BOAT.getHeight()*scale);
        this.strongButton.render(this.game.getBatch());

        this.game.getBatch().draw(this.enduranceImage, this.enduranceButton.getHitBox().getX() + ((this.enduranceButton.getHitBox().getWidth() - this.buttonWidth/2)/2f) , 200 + EntityType.BUTTON.getHeight(), this.buttonWidth/2, EntityType.BOAT.getHeight()*scale);
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
