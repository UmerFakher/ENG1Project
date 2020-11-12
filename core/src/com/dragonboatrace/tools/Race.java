package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.VertexBufferObjectWithVAO;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.Entity;
import com.dragonboatrace.entities.EntityType;
import com.dragonboatrace.entities.FinishLine;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.ComputerBoat;
import com.dragonboatrace.entities.boats.PlayerBoat;
import com.dragonboatrace.screens.GameOverScreen;
import com.dragonboatrace.screens.MainMenuScreen;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Race {

    int length;
    ArrayList<Boat> boats;
    ArrayList<FinishLine> finishLines;
    Texture image;
    float finishY;
    float currDistance;
    FinishLine theFinish;

    public Race(ArrayList<Boat> boats) {
        this.length = 10000;
        this.boats = boats;
        this.theFinish = new FinishLine(new Vector2(0, 10000), Gdx.graphics.getWidth());
        for (Boat boat : boats) {
            boat.setFinish(theFinish);
        }
    }

    public void update(float deltatime) {
        for (Boat boat : this.boats) {
            if (boat.getStamina() > 0) {
                if (boat instanceof PlayerBoat) {
                    currDistance = boat.getDistance();
                }
                boat.update(deltatime, currDistance);
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Boat boat : this.boats) {
            boat.render(batch);
        }
    }


    public void checkWinner(SpriteBatch batch, DragonBoatRace game) {
        for (Boat boat : boats) {
            if (boat instanceof PlayerBoat)
                this.currDistance = boat.getDistance();
            boat.getFinish().update(boat.getVelocity().y, currDistance);

            boat.getFinish().render(batch);
            if (boat.getHitBox().collidesWith(boat.getFinish().getHitBox())) {
                ArrayList<Integer> distances = new ArrayList<Integer>();
                String reason = "";

                for (Boat boatn : boats) {
                    distances.add(boatn.getDistance());
                }

                Collections.sort(distances);
                Collections.reverse(distances);

                for (int distance : distances) {
                    for (Boat boatn : boats) {
                        if (boatn.getDistance() == distance) {
                            switch (distances.indexOf(distance)+1) {
                                case 1:
                                    reason += "1st: "+ boatn.getName() + "\n";
                                    break;
                                case 2:
                                    reason += "2nd: " + boatn.getName() + "\n";
                                    break;
                                case 3:
                                    reason += "3rd: " + boatn.getName() + "\n";
                                    break;
                                default:
                                    reason += "4th: " + boatn.getName() + "\n";
                            }
                        }
                    }
                }
                game.dispose();
                game.setScreen(new GameOverScreen(game, reason));

            }
        }
    }
}
