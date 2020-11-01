package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Entity;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.entities.boats.Boat;

import java.awt.*;
import java.util.ArrayList;

public class Race extends Entity {

    int length;
    ArrayList<Boat> boats;
    Texture image;
    float finishY;

    public Race(Boat myBoat){
        super(new Vector2(0, Gdx.graphics.getHeight()), new Vector2(), EntityType.FINISH, "finish.png");
        this.length = 40000;
        this.boats = new ArrayList<Boat>();
        this.boats.add(myBoat);
    }

    public void update(){
        for (Boat boat : this.boats){
            if (boat.getDistance() > this.length){
                this.pos.add(new Vector2(0, -boat.getVelocity().y));
                this.box.move(this.pos.x, this.pos.y);
            }
        }
    }
    @Override
    public void render(SpriteBatch batch){
        batch.draw(this.texture, this.pos.x, this.pos.y);
    }

    public void checkWinner(SpriteBatch batch){
        update();
        render(batch);
        for (Boat boat : boats){
            if (boat.getHitBox().collidesWith(this.getHitBox())){
                System.out.println("Winner!!");
                Gdx.app.exit();
            }
        }
    }
}
