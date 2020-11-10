package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Entity;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.entities.FinishLine;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.screens.MainMenuScreen;

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
            boat.setFinish(new FinishLine(new Vector2(boat.getLane().getHitbox().getX(), Gdx.graphics.getHeight()+EntityType.FINISH.getHeight()), boat.getLane().getHitbox().getWidth()));
        }
    }

    public void update(float deltatime){
        for(Boat boat: this.boats){
            boat.update(deltatime);
        }
    }

    public void render(SpriteBatch batch){
        for(Boat boat: this.boats){
            boat.render(batch);
        }
    }


    public void checkWinner(SpriteBatch batch, DragonBoatRace game){
        for (Boat boat : boats){
            if (boat.getDistance() > this.length) {
                boat.getFinish().update(boat.getVelocity().y);
            }
            boat.getFinish().render(batch);
            if (boat.getHitBox().collidesWith(boat.getFinish().getHitBox())){
                System.out.println("Winner is "+boat.getName());
                game.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }
}
