package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.tools.Hitbox;

/** Represents a UI Button.
 * @author Benji Garment, Joe Wrieden
 */
public class Button extends Entity {
    /**
     * The texture to use when the button is being hovered over.
     */
    private Texture activeTexture;

    /**
     * Create a new button at a position with an inactive texture and an active texture.
     * @param pos The position of the button on-screen.
     * @param inactiveTexture The texture of the button when not hovered over.
     * @param activeTexture The texture of the button when it is hovered over.
     */
    public Button(Vector2 pos, String activeTexture, String inactiveTexture) {
        super(pos, new Vector2(), EntityType.BUTTON, inactiveTexture);
        this.activeTexture = new Texture(activeTexture);
    }

    /**
     * Render the button on the screen.
     * @param batch The SpriteBatch to be added to.
     */
    public void render(SpriteBatch batch) {
        if (this.isHovering()) {
            batch.draw(this.activeTexture, this.position.x, this.position.y, this.type.getWidth(), this.type.getHeight());
        } else {
            batch.draw(this.texture, this.position.x, this.position.y, this.type.getWidth(), this.type.getHeight());
        }
    }

    /**
     * If the mouse is hovering over the button.
     * @return A boolean of if the mouse is hovering over the button.
     */
    public boolean isHovering() {
        return (Gdx.input.getX() < this.position.x + this.type.getWidth() && Gdx.input.getX() > this.position.x
                && Gdx.graphics.getHeight() - Gdx.input.getY() < this.position.y + this.type.getHeight() && Gdx.graphics.getHeight() - Gdx.input.getY() > this.position.y);
    }

}
