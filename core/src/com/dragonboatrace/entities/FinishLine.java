package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.Boat;

public class FinishLine extends Entity{


    public FinishLine(Vector2 pos) {
        super(pos,new Vector2(),EntityType.FINISH, "finish.png");

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
        super.render(batch);
    }
}
