package com.dragonboatrace.entities;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity{

    protected Vector2 pos;
    protected Vector2 vel;
    protected EntityType type;
    protected Texture texture;
    protected Hitbox box;

    public Entity(Vector2 pos, Vector2 vel, EntityType type, String texture){
        this.pos = pos;
        this.vel = vel;
        this.type = type;

        /* Resize the texture to the bounds of the entity, defined in EntityType */
        Pixmap full = new Pixmap(new FileHandle(texture));
        Pixmap resize = new Pixmap(type.getWidth(), type.getHeight(), full.getFormat());
        /* Redraw texture */
        resize.drawPixmap(full, 0, 0, full.getWidth(), full.getHeight(), 0, 0, resize.getWidth(), resize.getHeight());
        this.texture = new Texture(resize);

        full.dispose();
        resize.dispose();

        /* Make a new hitbox at the entities position with its width and height */
        this.box = new Hitbox(pos.x, pos.y, type.getWidth(), type.getHeight());
    }

    public abstract void update(float deltaTime);

    public void dispose(){
        this.texture.dispose();
    }

    public void render(SpriteBatch batch){
        batch.draw(this.texture, this.pos.x, this.pos.y);
    }

    public Texture getTexture(){ return this.texture; }

    public Hitbox getHitBox(){ return this.box; }
}