package com.dragonboatrace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Button;
import com.dragonboatrace.tools.Configuration;

/**
 * Displays the screen that shows how to play the game.
 *
 * @author Benji Garment, Joe Wrieden
 */
public class HelpScreen implements Screen {

    /**
     * The instance of the main menu.
     */
    private final MainMenuScreen mainMenu;

    /**
     * The texture of the information about the game.
     */
    private final Texture helpInfo;

    /**
     * The spritebatch used to render the game.
     */
    private final SpriteBatch batch;

    /**
     * The button used to go back to the main menu.
     */
    private final Button backButton;

    /**
     * The maximum scroll height the help image can scroll to.
     */
    private final int maxHeight;

    /**
     * The minimum scroll height the help image can scroll to.
     */
    private final int minHeight;
    /**
     * Padding around the help image.
     */
    private final int padding;
    /**
     * Scroll down indicator.
     */
    private final Texture downArrow;
    /**
     * GlyphLayout used for centering fonts
     */
    private final GlyphLayout layout;
    /**
     * The font used to display the scroll indicator.
     */
    private final BitmapFont font;
    /**
     * The current position of the help image in the window.
     */
    private int currPos;

    /**
     * Creates a new window to display the help info in.
     *
     * @param callBack A reference to the main menu window so we can return using the back button.
     */
    public HelpScreen(MainMenuScreen callBack) {
        this.mainMenu = callBack;
        this.helpInfo = new Texture(Gdx.files.local("help_screen_info.png"));
        this.backButton = new Button(new Vector2(0, 0), "back_button_active.png", "back_button_inactive.png");
        this.maxHeight = Gdx.graphics.getHeight() - this.helpInfo.getHeight() / Configuration.SCALAR;

        this.currPos = maxHeight;
        this.padding = 200 / Configuration.SCALAR;
        this.minHeight = padding;

        this.downArrow = new Texture(Gdx.files.local("down_arrow.png"));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("osaka-re.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size *= 3.0 / Configuration.SCALAR;
        parameter.color = Color.RED;
        this.font = generator.generateFont(parameter);
        this.layout = new GlyphLayout();
        layout.setText(font, "Scroll Down");

        /* Set up the ability to scroll the image */
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                int multi = 100;
                if (amount == -1) {
                    if (currPos + amount * multi > maxHeight) {
                        currPos += amount * 100;
                    } else {
                        currPos = maxHeight;
                    }
                    return true;
                } else if (amount == 1) {
                    if (currPos + amount * multi < minHeight) {
                        currPos += amount * 100;
                    } else {
                        currPos = minHeight;
                    }
                    return true;
                }
                return false;
            }
        });
        this.batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    /**
     * Renders the window used to show the help image.
     *
     * @param delta The time passed since the last frame.
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(this.helpInfo, padding, currPos, Gdx.graphics.getWidth() - 2 * padding, (float) this.helpInfo.getHeight() / Configuration.SCALAR);
        if (currPos == this.maxHeight) {
            font.draw(batch, "Scroll Down", (Gdx.graphics.getWidth() - layout.width) / 2, layout.height + 120f / Configuration.SCALAR);
            batch.draw(this.downArrow, Gdx.graphics.getWidth() / 2f - 50f / Configuration.SCALAR, 0, 100f / Configuration.SCALAR, 100f / Configuration.SCALAR);
        }
        backButton.render(batch);
        if (this.backButton.isHovering() && Gdx.input.isTouched()) {
            this.mainMenu.game.setScreen(this.mainMenu);
        }
        batch.end();
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
