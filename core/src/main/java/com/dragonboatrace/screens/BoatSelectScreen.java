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
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.Settings;

/**
 * Displays the screen that allows the player to choose a boat at the beginning of the game.
 *
 * @author Benji Garment, Joe Wrieden
 *
 * NFR_Attributes Extensions made by Team 12
 * @author Umer Fakher
 */
public class BoatSelectScreen implements Screen {

    /**
     * Texture of the fast boat preview.
     */
    private final Texture fastImage;

    /**
     * Texture of the agile boat preview.
     */
    private final Texture agileImage;

    /**
     * Texture of the strong boat preview.
     */
    private final Texture strongImage;

    /**
     * Texture of the endurance boat preview.
     */
    private final Texture enduranceImage;

    /**
     * Button to select the fast boat.
     */
    private final Button fastButton;

    /**
     * Button to select the agile boat.
     */
    private final Button agileButton;

    /**
     * Button to select the strong boat.
     */
    private final Button strongButton;

    /**
     * Button to select the endurance boat.
     */
    private final Button enduranceButton;

    /**
     * Bitmap font for stats.
     *
     * NFR_Attributes Extensions made by Team 12 - Umer Fakher
     */
    private final BitmapFont font2; // font for attributes

    /**
     * Instance of the main game, used to have a collective spritebatch which gives better performance.
     */
    private final DragonBoatRace game;


    private final BitmapFont font;
    private final GlyphLayout layout;

    private final int buttonWidth;

    /**
     * Creates a new screen to display the boat options to the player.
     *
     * @param game The instance of the {@link DragonBoatRace} game.
     */
    public BoatSelectScreen(DragonBoatRace game) {

        this.game = game;

        this.buttonWidth = EntityType.BUTTON.getWidth();
        float spacing = (Gdx.graphics.getWidth() - buttonWidth * 4.0f) / 5.0f;
        this.fastButton = new Button(new Vector2(spacing, 100), "fast_button_active.png", "fast_button_inactive.png");
        this.agileButton = new Button(new Vector2(spacing + (buttonWidth + spacing), 100), "agile_button_active.png", "agile_button_inactive.png");
        this.strongButton = new Button(new Vector2(spacing + (buttonWidth + spacing) * 2, 100), "strong_button_active.png", "strong_button_inactive.png");
        this.enduranceButton = new Button(new Vector2(spacing + (buttonWidth + spacing) * 3, 100), "endurance_button_active.png", "endurance_button_inactive.png");

        this.fastImage = new Texture("fast.png");
        this.agileImage = new Texture("agile.png");
        this.strongImage = new Texture("strong.png");
        this.enduranceImage = new Texture("endurance.png");


        /* Font related items */
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size *= 10.0 / Settings.SCALAR;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        layout = new GlyphLayout();
        layout.setText(font, "Choose your Boat:");


        // NFR_Attributes Extensions made by Team 12 - Umer Fakher

        parameter.size *= 0.3 / Settings.SCALAR;
        parameter.color = Color.WHITE;
        font2 = generator.generateFont(parameter);

        // NFR_Attributes Extensions made by Team 12 - Umer Fakher END

    }


    @Override
    public void show() {

    }

    /**
     * Renders the boat selection screen.
     *
     * @param delta The time passed since the last frame.
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.getBatch().begin();

        font.draw(this.game.getBatch(), "Choose your Boat:", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 100);

        float scale = ((float) this.buttonWidth / EntityType.BOAT.getWidth()) / 2.0f;

        this.game.getBatch().draw(this.fastImage, this.fastButton.getHitBox().getX() + ((this.fastButton.getHitBox().getWidth() - this.buttonWidth / 2f) / 2f), 150 + EntityType.BUTTON.getHeight(), this.buttonWidth / 2f, EntityType.BOAT.getHeight() * scale);
        this.fastButton.render(this.game.getBatch());

        this.game.getBatch().draw(this.agileImage, this.agileButton.getHitBox().getX() + ((this.agileButton.getHitBox().getWidth() - this.buttonWidth / 2f) / 2f), 150 + EntityType.BUTTON.getHeight(), this.buttonWidth / 2f, EntityType.BOAT.getHeight() * scale);
        this.agileButton.render(this.game.getBatch());

        this.game.getBatch().draw(this.strongImage, this.strongButton.getHitBox().getX() + ((this.strongButton.getHitBox().getWidth() - this.buttonWidth / 2f) / 2f), 150 + EntityType.BUTTON.getHeight(), this.buttonWidth / 2f, EntityType.BOAT.getHeight() * scale);
        this.strongButton.render(this.game.getBatch());

        this.game.getBatch().draw(this.enduranceImage, this.enduranceButton.getHitBox().getX() + ((this.enduranceButton.getHitBox().getWidth() - this.buttonWidth / 2f) / 2f), 150 + EntityType.BUTTON.getHeight(), this.buttonWidth / 2f, EntityType.BOAT.getHeight() * scale);
        this.enduranceButton.render(this.game.getBatch());

        // NFR_Attributes Extensions made by Team 12 - Umer Fakher

        // Statistics from BoatType
        //        ENUM(health, stamina, agility, speed, maxSpeed, image)
        //        FAST(50, 120, 85, 275, "fast.png",0),
        //        AGILE(50, 120, 95, 245, "agile.png",1),
        //        ENDURANCE(70, 180, 90, 245, "endurance.png",2),
        //        STRONG(100, 105, 98, 200, "strong.png",3);

        float x = this.fastButton.getHitBox().getX() + ((this.fastButton.getHitBox().getWidth() - this.buttonWidth / 2f) / 2f);

        // Calculate Y coordinate based on how much space left on screen between title and boat type icon for stats
        float yBelowTitle = (Gdx.graphics.getHeight()-this.fastButton.getHitBox().getY()) - 150;
        float yAboveBoatType = 150 + EntityType.BUTTON.getHeight() + EntityType.BOAT.getHeight() * scale;
        int nAttributes = 4;
        float yStep = (yBelowTitle - yAboveBoatType) / 4;

        this.drawAttribute(BoatType.FAST.getHealth(),0, Color.GREEN, "Health: ", x, yBelowTitle - yStep*0);
        this.drawAttribute(BoatType.FAST.getStamina(),0, Color.YELLOW, "Stamina: ", x, yBelowTitle - yStep*1);
        this.drawAttribute(BoatType.FAST.getAgility(),0, Color.ORANGE, "Agility: ", x,yBelowTitle - yStep*2);
        this.drawAttribute(BoatType.FAST.getSpeed(),0, Color.RED, "Speed: ", x, yBelowTitle - yStep*3);

        x = this.agileButton.getHitBox().getX() + ((this.agileButton.getHitBox().getWidth() - this.buttonWidth / 2f) / 2f);

        this.drawAttribute(BoatType.AGILE.getHealth(),0, Color.GREEN, "Health: ", x, yBelowTitle - yStep*0);
        this.drawAttribute(BoatType.AGILE.getStamina(),0, Color.YELLOW, "Stamina: ", x, yBelowTitle - yStep*1);
        this.drawAttribute(BoatType.AGILE.getAgility(),0, Color.ORANGE, "Agility: ", x,yBelowTitle - yStep*2);
        this.drawAttribute(BoatType.AGILE.getSpeed(),0, Color.RED, "Speed: ", x,yBelowTitle - yStep*3);

        x = this.enduranceButton.getHitBox().getX() + ((this.enduranceButton.getHitBox().getWidth() - this.buttonWidth / 2f) / 2f);

        this.drawAttribute(BoatType.ENDURANCE.getHealth(),0, Color.GREEN, "Health: ", x, yBelowTitle - yStep*0);
        this.drawAttribute(BoatType.ENDURANCE.getStamina(),0, Color.YELLOW, "Stamina: ", x,yBelowTitle - yStep*1);
        this.drawAttribute(BoatType.ENDURANCE.getAgility(),0, Color.ORANGE, "Agility: ", x,yBelowTitle - yStep*2);
        this.drawAttribute(BoatType.ENDURANCE.getSpeed(),0, Color.RED, "Speed: ", x,yBelowTitle - yStep*3);

        x = this.strongButton.getHitBox().getX() + ((this.strongButton.getHitBox().getWidth() - this.buttonWidth / 2f) / 2f);

        this.drawAttribute(BoatType.STRONG.getHealth(),0, Color.GREEN, "Health: ", x, yBelowTitle - yStep*0);
        this.drawAttribute(BoatType.STRONG.getStamina(),0, Color.YELLOW, "Stamina: ", x,yBelowTitle - yStep*1);
        this.drawAttribute(BoatType.STRONG.getAgility(),0, Color.ORANGE, "Agility: ", x,yBelowTitle - yStep*2);
        this.drawAttribute(BoatType.STRONG.getSpeed(),0, Color.RED, "Speed: ", x,yBelowTitle - yStep*3);


        // NFR_Attributes Extensions made by Team 12 - Umer Fakher END



        if (this.fastButton.isHovering() && Gdx.input.isTouched()) {
            this.game.setScreen(new MainGameScreen(this.game, BoatType.FAST));
        } else if (this.agileButton.isHovering() && Gdx.input.isTouched()) {
            this.game.setScreen(new MainGameScreen(this.game, BoatType.AGILE));
        } else if (this.strongButton.isHovering() && Gdx.input.isTouched()) {
            this.game.setScreen(new MainGameScreen(this.game, BoatType.STRONG));
        } else if (this.enduranceButton.isHovering() && Gdx.input.isTouched()) {
            this.game.setScreen(new MainGameScreen(this.game, BoatType.ENDURANCE));
        }


        this.game.getBatch().end();

    }

    // NFR_Attributes Extensions made by Team 12 - Umer Fakher

    /**
     * Draws attribute statistic for a given BoatType.
     *
     * NFR_Attributes Extensions made by Team 12
     * @author Umer Fakher
     *
     * @param boatTypeStat          A statistic from BoatType Enum e.g. "BoatType.STRONG.getSpeed()".
     * @param roundToDecimalPlaces  Rounds float value boatTypeStat to a int number of decimal places.
     * @param fontColor             Colour of text for the statistic.
     * @param preText               String of text before the boatTypeStat e.g. "Speed: ".
     * @param x                     x position for text to be drawn.
     * @param y                     y position for text to be drawn.
     *
     */
    public void drawAttribute(Float boatTypeStat, int roundToDecimalPlaces, Color fontColor, String preText, float x, float y) {
        font2.setColor(fontColor);
        String floatRoundFormat = "%"+"."+roundToDecimalPlaces+"f";
        font2.draw(this.game.getBatch(), preText + String.format(floatRoundFormat, boatTypeStat), x, y);

    }
    // NFR_Attributes Extensions made by Team 12 - Umer Fakher END


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
