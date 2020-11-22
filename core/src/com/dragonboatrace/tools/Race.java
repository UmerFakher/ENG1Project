package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.FinishLine;
import com.dragonboatrace.entities.ObstacleType;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.entities.boats.ComputerBoat;
import com.dragonboatrace.entities.boats.PlayerBoat;
import com.dragonboatrace.screens.GameOverScreen;
import com.dragonboatrace.screens.RoundsScreen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

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
     * The timer for the race
     */
    private float timer;

    /**
     * Creates a new race of a specified length.
     * @param raceLength The length of the race.
     */
    public Race(int raceLength, BoatType boatChosen) {
        this.length = raceLength;
        this.theFinish = new FinishLine(new Vector2(0, this.length), Gdx.graphics.getWidth());
        int size = Gdx.graphics.getWidth() / Settings.PLAYER_COUNT;
        this.timer = 0;

        //player = new PlayerBoat(BoatType.FAST, new Lane(new Vector2(0, 0), size), length, "ME");
        player = new PlayerBoat(boatChosen, new Lane(new Vector2(0, 0), size), "ME");

        this.barrier = new Texture("line.png");

        boats = new ArrayList<>();
        for (int i = 1; i < Settings.PLAYER_COUNT; i++) {
            int rand = ThreadLocalRandom.current().nextInt(0, BoatType.values().length);
            boats.add(new ComputerBoat(BoatType.values()[rand], new Lane(new Vector2(size * i, 0), size), "COMP" + i, i));
        }
        this.timer = System.nanoTime();
    }

    /**
     * Update the race in respects to the amount of time passed since the last frame.
     * @param deltaTime The time since the last frame.
     */
    public void update(float deltaTime, DragonBoatRace game) {
        theFinish.update(deltaTime, player.getVelocity().y);
        player.updateYPosition(this.theFinish.getHitBox().getHeight(), length);
        player.update(deltaTime);
        for (Boat boat : this.boats) {

            ((ComputerBoat) boat).updateYPosition(player.getHitBox().getY(), player.getDistanceTravelled());
            boat.update(deltaTime);
            if (boat.getDistanceTravelled() + this.theFinish.getHitBox().getHeight() >= this.length && boat.getTime() == 0) {
                boat.setTime(Math.round((System.nanoTime() - this.timer) / 10000000) / (float) 100);
                boat.setTotalTime(boat.getTime());
            }
        }
        if (player.getDistanceTravelled() + this.theFinish.getHitBox().getHeight() >= this.length) {
            player.setTime(Math.round((System.nanoTime() - this.timer) / 10000000) / (float) 100);
            player.setTotalTime(player.getTime());
            ArrayList<Float> dnfList = new ArrayList<Float>();
            for (Boat boat : boats) {
                if (boat.getTime() == 0) {
                    dnfList.add(boat.getDistanceTravelled());
                }
            }
            Collections.sort(dnfList);
            Collections.reverse(dnfList);
            for (float dist : dnfList) {
                for (Boat boatn : boats) {
                    if (boatn.getDistanceTravelled() == dist) {
                        switch (dnfList.indexOf(dist) + 1) {
                            case 1:
                                boatn.setTime(player.getTime() + 1);
                                break;
                            case 2:
                                boatn.setTime(player.getTime() + 3);
                                break;
                            case 3:
                                boatn.setTime(player.getTime() + 5);
                                break;
                            default:
                                boatn.setTime(player.getTime() + ThreadLocalRandom.current().nextInt(6, 30));
                        }

                    }
                }
            }
            getLeaderBoard(game);
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

    public void getLeaderBoard(DragonBoatRace game) {
        ArrayList<Float> times = new ArrayList<Float>();
        String reason = "";
        player.setTime(this.player.getPenaltyTime());
        System.out.println(this.player.getPenaltyTime());
        boats.add(player);
        for (Boat boatn : boats) {
            times.add(boatn.getTime());
        }

        Collections.sort(times);

        for (float time : times) {
            for (Boat boatn : boats) {
                if (boatn.getTime() == time) {
                    switch (times.indexOf(time)+1) {
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
                            reason += times.indexOf(time)+1+"th: " + boatn.getName() + "\n";
                    }
                }
            }
        }
        boats.remove(player);
        game.upRound();
        game.setScreen(new RoundsScreen(game, this.player, reason));
    }

    /**
     * Get the players boat.
     * @return A {@link Boat} representing the players boat.
     */
    public Boat getPlayer() {
        return this.player;
    }
}
