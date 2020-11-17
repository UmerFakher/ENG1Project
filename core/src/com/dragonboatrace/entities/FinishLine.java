package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class FinishLine extends Entity {

    private final float widthScale;

    public FinishLine(Vector2 pos, int width) {
        super(new Vector2(pos.x, pos.y - EntityType.FINISH.getHeight()), new Vector2(), EntityType.FINISH, "finish.png");
        this.widthScale = (float) width / this.texture.getWidth();
    }

    public void update(float deltaTime, float velY) {
        if (this.pos.y + this.getHitBox().getHeight() <= Gdx.graphics.getHeight()) {
            this.pos.set(this.pos.x, Gdx.graphics.getHeight() - this.getHitBox().getHeight());
        } else {
            this.pos.set(this.pos.x, this.pos.y - (velY * deltaTime));
            this.box.move(this.pos.x, this.pos.y);
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.pos.x, this.pos.y, this.texture.getWidth() * this.widthScale, this.texture.getHeight());
    }
}
