package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;

public class Button extends Entity{

    Texture activeTexture;

    public Button(Vector2 pos, String texture, String activeTexture) {
        super(pos, new Vector2(), EntityType.BUTTON, texture);
        this.activeTexture = new Texture(activeTexture);
    }

    @Override
    public void render(SpriteBatch batch){
        if (this.isHovering()){
            batch.draw(this.texture, this.pos.x, this.pos.y, this.type.getWidth(), this.type.getHeight());

        } else {
            batch.draw(this.activeTexture, this.pos.x, this.pos.y, this.type.getWidth(), this.type.getHeight());
        }
    }

    public boolean isHovering(){
        return (Gdx.input.getX() < this.pos.x + this.type.getWidth() && Gdx.input.getX() > this.pos.x
                && Gdx.graphics.getHeight() - Gdx.input.getY() < this.pos.y + this.type.getHeight() && Gdx.graphics.getHeight() - Gdx.input.getY() > this.pos.y);
    }

}
