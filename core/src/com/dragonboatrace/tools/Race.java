package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.FinishLine;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.entities.boats.ComputerBoat;

import java.util.ArrayList;

public class Race {

    private final int length;
    private final ArrayList<Boat> boats;
    private final Boat player;
    private final FinishLine theFinish;

    public Race(int raceLength) {
        this.length = raceLength;
        this.theFinish = new FinishLine(new Vector2(0, this.length), Gdx.graphics.getWidth());
        int size = Gdx.graphics.getWidth() / Settings.PLAYER_COUNT;

        //player = new PlayerBoat(BoatType.FAST, new Lane(new Vector2(0, 0), size), length, "ME");
        player = new ComputerBoat(BoatType.FAST, new Lane(new Vector2(0, 0), size), length, "ME", 3);

        boats = new ArrayList<>();
        for (int i = 1; i < Settings.PLAYER_COUNT; i++) {
            boats.add(new ComputerBoat(BoatType.FAST, new Lane(new Vector2(size * i, 0), size), length, "COMP" + i, i));
        }
    }

    public void update(float deltaTime) {
        theFinish.update(deltaTime, player.getVelocity().y);
        player.updateYPosition(this.theFinish.getHitBox().getHeight());
        player.update(deltaTime);
        for (Boat boat : this.boats) {

            ((ComputerBoat) boat).updateYPosition(player.getHitBox().getY(), player.getDistanceTravelled());
            boat.update(deltaTime);
        }
        if (player.getDistanceTravelled() + this.theFinish.getHitBox().getHeight() >= this.length) {
            Gdx.app.exit();
        }
    }

    public void render(SpriteBatch batch) {
        theFinish.render(batch);
        player.render(batch);
        for (Boat boat : this.boats) {
            boat.render(batch);
        }
    }

    public void checkWinner(SpriteBatch batch, DragonBoatRace game) {

    }

    public Boat getPlayer() {
        return this.player;
    }
}
