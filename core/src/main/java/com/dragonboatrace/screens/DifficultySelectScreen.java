package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.tools.Settings;

/**
 * Displays the screen that allows the player to choose a difficulty level. New class created to satisfy the new customer requirement of multiple difficulties.
 *
 * @author William Walton
 */
public class DifficultySelectScreen implements Screen {

    /**
     * Texture of the fast boat preview.
     */
    private final Texture easyImage;

    /**
     * Texture of the agile boat preview.
     */
    private final Texture normalImage;

    /**
     * Texture of the strong boat preview.
     */
    private final Texture hardImage;

    /**
     * Texture of the endurance boat preview.
     */
    private final Texture ultraImage;

    /**
     * Button to select the fast boat.
     */
    private final Button easyButton;

    /**
     * Button to select the agile boat.
     */
    private final Button normalButton;

    /**
     * Button to select the strong boat.
     */
    private final Button hardButton;

    /**
     * Button to select the endurance boat.
     */
    private final Button ultraButton;

    /**
     * Instance of the main game, used to have a collective spritebatch which gives better performance.
     */
    private final DragonBoatRace game;


    private final BitmapFont font;
    private final GlyphLayout layout;

    private final int buttonWidth;

    /**
     * Creates a new screen to display the difficulty options to the player.
     *
     * @param game The instance of the {@link DragonBoatRace} game.
     */
    public DifficultySelectScreen(DragonBoatRace game) {

        this.game = game;

        this.buttonWidth = EntityType.BUTTON.getWidth();
        float spacing = (Gdx.graphics.getWidth() - buttonWidth * 4.0f) / 5.0f;

        int ySub = 500;

        this.easyButton = new Button(new Vector2(spacing, Gdx.graphics.getHeight() - 300 - ySub), "easy_button_active.png", "easy_button_inactive.png");
        this.normalButton = new Button(new Vector2(spacing + (buttonWidth + spacing), Gdx.graphics.getHeight() - 300 - ySub), "normal_button_active.png", "normal_button_inactive.png");
        this.hardButton = new Button(new Vector2(spacing + (buttonWidth + spacing) * 2, Gdx.graphics.getHeight() - 300 - ySub), "hard_button_active.png", "hard_button_inactive.png");
        this.ultraButton = new Button(new Vector2(spacing + (buttonWidth + spacing) * 3, Gdx.graphics.getHeight() - 300 - ySub), "ultra_button_active.png", "ultra_button_inactive.png");

        this.easyImage = new Texture("easy.png");
        this.normalImage = new Texture("normal.png");
        this.hardImage = new Texture("hard.png");
        this.ultraImage = new Texture("ultra.png");

        /* Font related items */
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size *= 10.0 / Settings.SCALAR;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        layout = new GlyphLayout();
        layout.setText(font, "Choose your Difficulty:");

    }


    @Override
    public void show() {

    }

    /**
     * Renders the difficulty selection screen.
     *
     * @param delta The time passed since the last frame.
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.getBatch().begin();

        font.draw(this.game.getBatch(), "Choose your Difficulty:", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 100);

        float scale = ((float) this.buttonWidth / EntityType.BOAT.getWidth()) / 2.0f;

        int ySub = -300;

        this.game.getBatch().draw(this.easyImage, this.easyButton.getHitBox().getX() + ((this.easyButton.getHitBox().getWidth() - this.buttonWidth / 2f) / 2f), 150 + EntityType.BUTTON.getHeight() - ySub, this.buttonWidth / 2f, EntityType.BOAT.getHeight() * scale);
        this.easyButton.render(this.game.getBatch());

        this.game.getBatch().draw(this.normalImage, this.normalButton.getHitBox().getX() + ((this.normalButton.getHitBox().getWidth() - this.buttonWidth / 2f) / 2f), 150 + EntityType.BUTTON.getHeight() - ySub, this.buttonWidth / 2f, EntityType.BOAT.getHeight() * scale);
        this.normalButton.render(this.game.getBatch());

        this.game.getBatch().draw(this.hardImage, this.hardButton.getHitBox().getX() + ((this.hardButton.getHitBox().getWidth() - this.buttonWidth / 2f) / 2f), 150 + EntityType.BUTTON.getHeight() - ySub, this.buttonWidth / 2f, EntityType.BOAT.getHeight() * scale);
        this.hardButton.render(this.game.getBatch());

        this.game.getBatch().draw(this.ultraImage, this.ultraButton.getHitBox().getX() + ((this.ultraButton.getHitBox().getWidth() - this.buttonWidth / 2f) / 2f), 150 + EntityType.BUTTON.getHeight() - ySub, this.buttonWidth / 2f, EntityType.BOAT.getHeight() * scale);
        this.ultraButton.render(this.game.getBatch());

        if (this.easyButton.isHovering() && Gdx.input.isTouched()) {
            this.game.setDifficulty(0);
            this.game.setScreen(new BoatSelectScreen(this.game));
        } else if (this.normalButton.isHovering() && Gdx.input.isTouched()) {
            this.game.setDifficulty(1);
            this.game.setScreen(new BoatSelectScreen(this.game));
        } else if (this.hardButton.isHovering() && Gdx.input.isTouched()) {
            this.game.setDifficulty(2);
            this.game.setScreen(new BoatSelectScreen(this.game));
        } else if (this.ultraButton.isHovering() && Gdx.input.isTouched()) {
            this.game.setDifficulty(3);
            this.game.setScreen(new BoatSelectScreen(this.game));
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
