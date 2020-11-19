package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.FinishLine;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.entities.boats.ComputerBoat;
import com.dragonboatrace.entities.boats.PlayerBoat;

import java.util.ArrayList;

/** Represents a Race.
 * @author Benji Garment, Joe Wrieden
 */
public class Race {
    /**
     * The length of the race.
     */
    private final int length;
    /**
     * The list of boats in the race, not including the player.
     */
    private final ArrayList<Boat> boats;
    /**
     * The players boat.
     */
    private final Boat player;
    /**
     * The finish line
     */
    private final FinishLine theFinish;
    /**
     * The separator between each lane.
     */
    private final Texture barrier;

    /**
     * Creates a new race of a specified length.
     * @param raceLength The length of the race.
     */
    public Race(int raceLength, BoatType boatChosen) {
        this.length = raceLength;
        this.theFinish = new FinishLine(new Vector2(0, this.length), Gdx.graphics.getWidth());
        int size = Gdx.graphics.getWidth() / Settings.PLAYER_COUNT;

        //player = new PlayerBoat(BoatType.FAST, new Lane(new Vector2(0, 0), size), length, "ME");
        player = new PlayerBoat(boatChosen, new Lane(new Vector2(0, 0), size), "ME");

        this.barrier = new Texture("line.png");

        boats = new ArrayList<>();
        for (int i = 1; i < Settings.PLAYER_COUNT; i++) {
            boats.add(new ComputerBoat(BoatType.FAST, new Lane(new Vector2(size * i, 0), size), "COMP" + i, i));
        }
    }

    /**
     * Update the race in respects to the amount of time passed since the last frame.
     * @param deltaTime The time since the last frame.
     */
    public void update(float deltaTime) {
        theFinish.update(deltaTime, player.getVelocity().y);
        player.updateYPosition(this.theFinish.getHitBox().getHeight(), length);
        player.update(deltaTime);
        for (Boat boat : this.boats) {

            ((ComputerBoat) boat).updateYPosition(player.getHitBox().getY(), player.getDistanceTravelled());
            boat.update(deltaTime);
        }
        if (player.getDistanceTravelled() + this.theFinish.getHitBox().getHeight() >= this.length) {
            Gdx.app.exit();
        }
    }

    /**
     * Render the boats in the race and the player boat.
     * @param batch The SpriteBatch to be added to.
     */
    public void render(SpriteBatch batch) {
        theFinish.render(batch);
        player.render(batch);
        for (Boat boat : this.boats) {
            boat.render(batch);
        }
        for (int i = 0; i < Settings.PLAYER_COUNT; i++) {
            batch.draw(this.barrier, ((float) Gdx.graphics.getWidth() / Settings.PLAYER_COUNT) * i, 0);
        }
    }

    /**
     * Get the players boat.
     * @return A {@link Boat} representing the players boat.
     */
    public Boat getPlayer() {
        return this.player;
    }
}
