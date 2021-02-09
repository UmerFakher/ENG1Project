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
import com.dragonboatrace.tools.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays the screen that allows the player to choose a boat at the beginning of the game.
 *
 * @author Benji Garment, Joe Wrieden
 * <p>
 * NFR_Attributes Extensions made by Team 12
 * @author Umer Fakher
 */
public class BoatSelectScreen implements Screen {

    /**
     * Bitmap font for stats.
     * <p>
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
     * Textures of all Boat previews
     */
    private final List<Texture> boatImages;
    /**
     * Buttons to select all Boats
     */
    private final List<Button> boatButtons;

    /**
     * Creates a new screen to display the boat options to the player.
     *
     * @param game The instance of the {@link DragonBoatRace} game.
     */
    public BoatSelectScreen(DragonBoatRace game) {

        this.game = game;

        this.buttonWidth = EntityType.BUTTON.getWidth();
        float spacing = (Gdx.graphics.getWidth() - buttonWidth * 4.0f) / 5.0f;

        boatImages = new ArrayList<>();
        boatButtons = new ArrayList<>();

        boatButtons.add(new Button(new Vector2(spacing, 100),
                "fast_button_active.png", "fast_button_inactive.png"));
        boatButtons.add(new Button(new Vector2(spacing + (buttonWidth + spacing), 100),
                "agile_button_active.png", "agile_button_inactive.png"));
        boatButtons.add(new Button(new Vector2(spacing + (buttonWidth + spacing) * 2, 100),
                "strong_button_active.png", "strong_button_inactive.png"));
        boatButtons.add(new Button(new Vector2(spacing + (buttonWidth + spacing) * 3, 100),
                "endurance_button_active.png", "endurance_button_inactive.png"));

        boatImages.add(new Texture("fast.png"));
        boatImages.add(new Texture("agile.png"));
        boatImages.add(new Texture("strong.png"));
        boatImages.add(new Texture("endurance.png"));


        /* Font related items */
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size *= 10.0 / Configuration.SCALAR;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        layout = new GlyphLayout();
        layout.setText(font, "Choose your Boat:");


        // NFR_Attributes Extensions made by Team 12 - Umer Fakher

        parameter.size *= 0.3 / Configuration.SCALAR;
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

        font.draw(this.game.getBatch(), "Choose your Boat:", (Gdx.graphics.getWidth() - layout.width) / 2,
                Gdx.graphics.getHeight() - 100);

        float scale = ((float) this.buttonWidth / EntityType.BOAT.getWidth()) / 2.0f;

        for (Button b : boatButtons)
            b.render(game.getBatch());

        for (int i = 0; i < boatImages.size(); i++){
            this.game.getBatch().draw(boatImages.get(i),
                    boatButtons.get(i).getHitBox().getX() + ((boatButtons.get(i).getHitBox().getWidth() - this.buttonWidth / 2f) / 2f),
                    150 + EntityType.BUTTON.getHeight(), this.buttonWidth / 2f,
                    EntityType.BOAT.getHeight() * scale);

            // NFR_Attributes Extensions made by Team 12 - Umer Fakher

            // Calculate the X coordinate to show the stats
            float x = boatButtons.get(i).getHitBox().getX() + ((boatButtons.get(i).getHitBox().getWidth() - this.buttonWidth / 2f) / 2f);
            // Calculate Y coordinate based on how much space left on screen between title and boat type icon for stats
            float yBelowTitle = (Gdx.graphics.getHeight() - boatButtons.get(i).getHitBox().getY()) - 150;
            float yAboveBoatType = 150 + EntityType.BUTTON.getHeight() + EntityType.BOAT.getHeight() * scale;
            float yStep = (yBelowTitle - yAboveBoatType) / 4;

            // Draw stats above boat
            this.drawAttribute(BoatType.values()[i].getHealth(), 0, Color.GREEN, "Health: ", x, yBelowTitle - yStep * 0);
            this.drawAttribute(BoatType.values()[i].getStamina(), 0, Color.YELLOW, "Stamina: ", x, yBelowTitle - yStep * 1);
            this.drawAttribute(BoatType.values()[i].getAgility(), 0, Color.ORANGE, "Agility: ", x, yBelowTitle - yStep * 2);
            this.drawAttribute(BoatType.values()[i].getSpeed(), 0, Color.RED, "Speed: ", x, yBelowTitle - yStep * 3);

            // NFR_Attributes Extensions made by Team 12 - Umer Fakher END
        }

        if (boatButtons.get(0).isHovering() && Gdx.input.isTouched()) {
            this.game.setScreen(new MainGameScreen(this.game, BoatType.FAST));
        } else if (boatButtons.get(1).isHovering() && Gdx.input.isTouched()) {
            this.game.setScreen(new MainGameScreen(this.game, BoatType.AGILE));
        } else if (boatButtons.get(2).isHovering() && Gdx.input.isTouched()) {
            this.game.setScreen(new MainGameScreen(this.game, BoatType.STRONG));
        } else if (boatButtons.get(3).isHovering() && Gdx.input.isTouched()) {
            this.game.setScreen(new MainGameScreen(this.game, BoatType.ENDURANCE));
        }


        this.game.getBatch().end();

    }

    // NFR_Attributes Extensions made by Team 12 - Umer Fakher

    /**
     * Draws attribute statistic for a given BoatType.
     * <p>
     * NFR_Attributes Extensions made by Team 12
     *
     * @param boatTypeStat         A statistic from BoatType Enum e.g. "BoatType.STRONG.getSpeed()".
     * @param roundToDecimalPlaces Rounds float value boatTypeStat to a int number of decimal places.
     * @param fontColor            Colour of text for the statistic.
     * @param preText              String of text before the boatTypeStat e.g. "Speed: ".
     * @param x                    x position for text to be drawn.
     * @param y                    y position for text to be drawn.
     * @author Umer Fakher
     */
    public void drawAttribute(Float boatTypeStat, int roundToDecimalPlaces, Color fontColor, String preText, float x, float y) {
        font2.setColor(fontColor);
        String floatRoundFormat = "%" + "." + roundToDecimalPlaces + "f";
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
