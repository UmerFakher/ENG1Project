package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.tools.Hitbox;

/**
 * Represents a generic Entity.
 *
 * @author Benji Garment, Joe Wrieden
 */
public abstract class Entity {
    /**
     * The position of the entity.
     */
    protected Vector2 position;
    /**
     * The velocity of the entity.
     */
    protected Vector2 velocity;
    /**
     * What kind of entity it is.
     */
    protected EntityType type;
    /**
     * The texture of the entity.
     */
    protected Texture texture;
    /**
     * The hit box of the entity.
     */
    protected Hitbox hitbox;

    /**
     * Creates a new Entity at a position with a starting velocity, a entity type and a texture.
     *
     * @param position The initial position of the entity.
     * @param velocity The initial velocity of the entity.
     * @param type     The type of entity.
     * @param texture  The texture of the entity.
     */
    public Entity(Vector2 position, Vector2 velocity, EntityType type, String texture) {
        this.position = position;
        this.velocity = velocity;
        this.type = type;

        /* Resize the texture to the bounds of the entity, defined in EntityType */
        Pixmap full = new Pixmap(Gdx.files.local(texture));
        Pixmap resize = new Pixmap(type.getWidth(), type.getHeight(), full.getFormat());
        /* Redraw texture */
        resize.drawPixmap(full, 0, 0, full.getWidth(), full.getHeight(), 0, 0, resize.getWidth(), resize.getHeight());
        this.texture = new Texture(resize);

        full.dispose();
        resize.dispose();

        /* Make a new hit box at the entities position with its width and height */
        this.hitbox = new Hitbox((int) position.x, (int) position.y, type.getWidth(), type.getHeight());
    }

    /**
     * Dispose of the texture used when finished.
     */
    public void dispose() {
        this.texture.dispose();
    }

    /**
     * Render the entities texture.
     *
     * @param batch The SpriteBatch to be added to.
     */
    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.position.x, this.position.y);
    }

    /**
     * The texture of the entity.
     *
     * @return A Texture that represents the entity.
     */
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * The hit box of the entity.
     *
     * @return A hit box of the entity.
     */
    public Hitbox getHitBox() {
        return this.hitbox;
    }
}