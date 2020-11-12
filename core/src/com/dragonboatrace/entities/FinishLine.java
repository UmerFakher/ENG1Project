package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.Boat;

public class FinishLine extends Entity{

    private float widthScale;

    public FinishLine(Vector2 pos, int width) {
        super(pos,new Vector2(),EntityType.FINISH, "finish.png");
        this.widthScale = (float) width / this.texture.getWidth();
    }

    public void update(float vel){
        if (vel > 3) {
            this.pos.add(new Vector2(0, -vel));
        }else{
            this.pos.add(new Vector2(0, -3));
        }
        this.box.move(this.pos.x, this.pos.y);
    }

    public void render(SpriteBatch batch){
        batch.draw(this.texture, this.pos.x, this.pos.y, this.texture.getWidth() * this.widthScale, this.texture.getHeight());
    }
}
