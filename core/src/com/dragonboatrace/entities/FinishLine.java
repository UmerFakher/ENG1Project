package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.Boat;

public class FinishLine extends Entity{

    private int width;

    public FinishLine(Vector2 pos, int width) {
        super(pos,new Vector2(),EntityType.FINISH, "finish.png");
        this.width = width;

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
        // TODO: Change render to stretch to width.
        super.render(batch);
    }
}
