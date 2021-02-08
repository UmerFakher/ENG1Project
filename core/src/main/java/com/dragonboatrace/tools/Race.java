package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.FinishLine;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.entities.boats.ComputerBoat;
import com.dragonboatrace.entities.boats.PlayerBoat;
import com.dragonboatrace.screens.GameOverScreen;
import com.dragonboatrace.screens.MainGamePauseScreen;
import com.dragonboatrace.screens.MainGameScreen;
import com.dragonboatrace.screens.RoundsScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a Race.
 *
 * @author Benji Garment, Joe Wrieden, William Walton
 */
public class Race {
    /**
     * The length of the race.
     */
    private final int length;
    /**
     * The list of boats in the race, not including the player.
     */
    private final List<Boat> boats;
    /**
     * The players boat.
     */
    private final Boat player;
    /**
     * The finish line.
     */
    private final FinishLine theFinish;
    /**
     * The separator between each lane.
     */
    private final Texture barrier;

    /**
     * The timer for the race.
     */
    private float timer;

    /**
     * Creates a new race of a specified length.
     *
     * @param raceLength The length of the race.
     * @param boatChosen The {@link BoatType} that the player chose.
     * @param round      The current round of the race.
     * @param difficulty The difficulty of the race {@link DragonBoatRace.difficulty} for FR_DIFFICULTY_SELECTION
     */
    public Race(int raceLength, BoatType boatChosen, int round, int difficulty) {
        this.length = raceLength;
        this.theFinish = new FinishLine(new Vector2(0, Gdx.graphics.getHeight()), Gdx.graphics.getWidth());
        int size = Gdx.graphics.getWidth() / Configuration.PLAYER_COUNT;
        this.timer = 0;

        player = new PlayerBoat(boatChosen, new Lane(new Vector2(0, 0), size, round, difficulty), "Player");

        this.barrier = new Texture("line.png");

        boats = new ArrayList<>();
        for (int i = 1; i < Configuration.PLAYER_COUNT; i++) {
            int rand = ThreadLocalRandom.current().nextInt(0, BoatType.values().length);
            boats.add(new ComputerBoat(BoatType.values()[rand], new Lane(new Vector2(size * i, 0), size, round, difficulty), "COMP" + i, i));
        }
        this.timer = System.nanoTime();
    }

    /**
     * Update the race in respects to the amount of time passed since the last frame.
     *
     * @param deltaTime The time since the last frame.
     * @param game      The instance of the game.
     * @param screen    The screen the race is being ran from
     */
    public void update(float deltaTime, DragonBoatRace game, MainGameScreen screen) {
        player.updateYPosition(this.theFinish.getHitBox().getHeight(), length);
        player.update(deltaTime);
        theFinish.update(player.getDistanceTravelled(), this.length, deltaTime, player.getVelocity().y);
        if (player.getHealth() <= 0) {
            game.setScreen(new GameOverScreen(game, "Your boat is broken. Better luck next time!"));
        }
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
            ArrayList<Float> dnfList = new ArrayList<>();
            for (Boat boat : boats) {
                if (boat.getTime() == 0) {
                    dnfList.add(boat.getDistanceTravelled());
                }
            }
            Collections.sort(dnfList);
            Collections.reverse(dnfList);
            for (float dist : dnfList) {
                for (Boat boatN : boats) {
                    if (boatN.getDistanceTravelled() == dist) {
                        switch (dnfList.indexOf(dist) + 1) {
                            case 1:
                                boatN.setTime(player.getTime() + 1);
                                break;
                            case 2:
                                boatN.setTime(player.getTime() + 3);
                                break;
                            case 3:
                                boatN.setTime(player.getTime() + 5);
                                break;
                            default:
                                boatN.setTime(player.getTime() + ThreadLocalRandom.current().nextInt(6, 30));
                        }

                    }
                }
            }
            getLeaderBoard(game);
        }

        // NEW CODE for UR_SAVE_RESUME_GAME
        // Game is stopped on ESC and sent to a blank leaderboard
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainGamePauseScreen(game, screen));
        }
        // END NEW CODE
    }

    /**
     * Render the boats in the race and the player boat.
     *
     * @param batch The SpriteBatch to be added to.
     */
    public void render(SpriteBatch batch) {
        theFinish.render(batch);
        player.render(batch);
        for (Boat boat : this.boats) {
            boat.render(batch);
        }
        for (int i = 0; i < Configuration.PLAYER_COUNT; i++) {
            batch.draw(this.barrier, ((float) Gdx.graphics.getWidth() / Configuration.PLAYER_COUNT) * i, 0, 5, Toolkit.getDefaultToolkit().getScreenSize().height);
        }
    }

    /**
     * Generate the leaderboard from the race that just occurred and then show the next round screen.
     *
     * @param game The instance of the game.
     */
    public void getLeaderBoard(DragonBoatRace game) {
        List<Float> times = new ArrayList<>();
        StringBuilder reason = new StringBuilder();
        player.setTime(this.player.getPenaltyTime());

        times.add(player.getTime());
        for (Boat boatN : boats) {
            times.add(boatN.getTime());
        }

        game.setPlayerTotalTime(times.get(0));
        for (int i = 0; i < Configuration.PLAYER_COUNT; i++) {
            game.setTimeAt(i, times.get(i));
        }
        boats.add(player);
        Collections.sort(times);
        List<Float> dup = new ArrayList<>(findDuplicates(times));
        if (dup.size() != 0) {
            times.set(times.indexOf(dup.get(0)), (float) (times.get(times.indexOf(dup.get(0))) + 0.02));
        }

        for (float time : times) {
            for (Boat boatN : boats) {
                if (boatN.getTime() == time) {
                    switch (times.indexOf(time) + 1) {
                        case 1:
                            if (game.getRound() == 4)
                                reason.append("Gold Medal:      ").append(boatN.getName()).append("\n");
                            else
                                reason.append("1st: ").append(boatN.getName()).append("\n");
                            break;
                        case 2:
                            if (game.getRound() == 4)
                                reason.append("Silver Medal:    ").append(boatN.getName()).append("\n");
                            else
                                reason.append("2nd: ").append(boatN.getName()).append("\n");
                            break;
                        case 3:
                            if (game.getRound() == 4)
                                reason.append("Bronze Medal:    ").append(boatN.getName()).append("\n");
                            else
                                reason.append("3rd: ").append(boatN.getName()).append("\n");
                            break;
                        default:
                            if (game.getRound() != 4)
                                reason.append(times.indexOf(time)+1).append("th: ").append(boatN.getName()).append("\n");
                    }
                }
            }
        }
        boats.remove(player);
        this.dispose();
        game.upRound();
        if (game.getRound() != 5) {
            game.setScreen(new RoundsScreen(game, this.player, reason.toString()));
        } else {
            game.setScreen(new GameOverScreen(game, reason.toString()));
        }
    }

    /**
     * Find any duplicates in an arraylist of floats.
     *
     * @param list An {@link List} of floats to be combed through.
     * @return An {@link Set} of type float containing unique values.
     */
    public Set<Float> findDuplicates(List<Float> list) {
        final Set<Float> setToReturn = new HashSet<>();
        final Set<Float> set1 = new HashSet<>();

        for (Float yourFloat : list) {
            if (!set1.add(yourFloat)) {
                setToReturn.add(yourFloat);
            }
        }
        return setToReturn;
    }

    /**
     * Get the players boat.
     *
     * @return A {@link Boat} representing the players boat.
     */
    public Boat getPlayer() {
        return this.player;
    }

    public void dispose() {
        for (Boat boat : this.boats) {
            boat.dispose();
        }
        this.theFinish.dispose();
        this.barrier.dispose();
    }
}
