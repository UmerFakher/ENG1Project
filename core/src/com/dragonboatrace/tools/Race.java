package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Entity;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.entities.FinishLine;
import com.dragonboatrace.entities.boats.Boat;

import java.awt.*;
import java.util.ArrayList;

public class Race {

    int length;
    ArrayList<Boat> boats;
    ArrayList<FinishLine> finishLines;
    Texture image;
    float finishY;

    public Race(ArrayList<Boat> boats){
        this.length = 40000;
        this.boats = boats;
        for (Boat boat : boats){
            boat.setFinish(new FinishLine(new Vector2(boat.getLane().getHitbox().getX(), Gdx.graphics.getHeight())));
        }
    }


    public void checkWinner(SpriteBatch batch){
        for (Boat boat : boats){
            if (boat.getDistance() > this.length) {
                boat.getFinish().update(boat.getVelocity().y);
            }
            boat.getFinish().render(batch);
            if (boat.getHitBox().collidesWith(boat.getFinish().getHitBox())){
                System.out.println("Winner is "+boat.getName());
                Gdx.app.exit();
            }
        }
    }
}
