package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Obstacle;
import com.dragonboatrace.entities.ObstacleType;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a Lane in a {@link Race}.
 *
 * @author Benji Garment, Joe Wrieden, William Walton
 */
public class Lane {

    /**
     * The hit box of the lane, used to check if a boat is in the lane.
     */
    private final Hitbox area;
    /**
     * A list of the obstacles currently in the lane.
     */
    private final List<Obstacle> obstacles;
    /**
     * A list of times to wait before adding a new obstacle to the lane.
     */
    private final List<Float> randomWaitTimes;
    /**
     * The difficulty of the game added for FR_DIFFICULTY_SELECTION
     */
    private final int difficulty;

    /**
     * Creates a new lane at a position and with a width and uses the round number to change the number of obstacles.
     *
     * @param pos   The position of the lane in the screen.
     * @param width The width of the lane.
     * @param round The current round, used to increase difficulty.
     */
    public Lane(Vector2 pos, int width, int round, int difficulty) {
        this.area = new Hitbox(pos.x, pos.y, width, Gdx.graphics.getHeight() + 200);
        this.obstacles = new ArrayList<>();
        this.randomWaitTimes = new ArrayList<>();
        this.difficulty = difficulty;
        populateList(round);
    }

    /**
     * Update the obstacles in the lane, remove any that are no longer on screen and replace them at a random time.
     *
     * @param deltaTime The time since the last frame.
     * @param velY      The y-velocity of the boat in the lane.
     */
    public void update(float deltaTime, float velY) {

        /* Check for collisions */
        ListIterator<Obstacle> iter = obstacles.listIterator();
        while (iter.hasNext()) {
            Obstacle obstacle = iter.next();
            obstacle.update(deltaTime, velY);
            if (obstacle.getHitBox().leaves(this.area)) {
                iter.remove();
                replaceObstacle();
            }
        }

        /* Randomly replace obstacles */
        ListIterator<Float> times = randomWaitTimes.listIterator();
        while (times.hasNext()) {
            float time = times.next() - deltaTime;
            if (time > 0) {
                times.set(time);
            } else {
                obstacles.add(randomObstacle());
                times.remove();
            }
        }
    }

    /**
     * Render the obstacles in the lane.
     *
     * @param batch The SpriteBatch to be added to.
     */
    public void render(SpriteBatch batch) {
        for (Obstacle obstacle : obstacles) {
            obstacle.render(batch);
        }
    }

    /**
     * Get the list of all obstacles in the lane.
     *
     * @return An {@link ArrayList} of type {@link Obstacle} with all the obstacles in the lane.
     */
    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }

    /**
     * Get the lanes hit box
     *
     * @return A {@link Hitbox} representing the lanes area.
     */
    public Hitbox getHitbox() {
        return this.area;
    }

    /**
     * Remove an {@link Obstacle} from the list of obstacles, and randomly replace it.
     *
     * @param toRemove The obstacle to remove from the lane.
     */
    public void removeObstacle(Obstacle toRemove) {
        obstacles.remove(toRemove);
        replaceObstacle();
    }

    /**
     * Create a random time at which to add an {@link Obstacle} to the lane.
     */
    public void replaceObstacle() {
        randomWaitTimes.add(1.0f + 2 * ThreadLocalRandom.current().nextFloat());
    }

    /**
     * Create a new random {@link Obstacle}
     *
     * @return a new {@link Obstacle} in the lanes area.
     */
    private Obstacle randomObstacle() {
        // CHANGED CODE
        // Made the first 3 elements in the ObstacleType class more likely to generate as these are the obstacles.
        // The remaining elements are ordered in rarity, with the 4th element being the least powerful power-up, hence
        // the most common, and the last being the most powerful, and least common
        int randNumber = (ThreadLocalRandom.current().nextInt(0, 100));  // random variable from 0 to 99
        int i = 0;  // index of new obstacle to make

        //convert the random number into an index
        if (randNumber < 20)
            i = 0; // 20% chance of LEAF
        else if (randNumber < 40)
            i = 1; // 20% chance of ROCK
        else if (randNumber < 60)
            i = 2; // 20% chance of BRANCH
        else if (randNumber < 70)
            i = 3; // 10% chance of PU_HEALTH
        else if (randNumber < 80)
            i = 4; // 10% chance of PU_STAMINA
        else if (randNumber < 90)
            i = 5; // 10% chance of PU_AGILITY
        else if (randNumber < 97)
            i = 4; // 7% chance of PU_SPEED
        else if (randNumber < 100)
            i = 4; // 3% chance of PU_ALL

        return new Obstacle(ObstacleType.values()[i], this.area.getX(), this.area.getWidth());
        // END CHANGED CODE
    }

    /**
     * Fill the list with obstacles that will start at random times.
     *
     * @param round The current round increases the number of obstacles.
     */
    private void populateList(int round) {
        // CHANGED CODE for FR_DIFFICULTY_SELECTION
        // Changed the rate at which new obstacles are spawned to be dependant on the difficulty as well as existing
        // factors such as round
        int difficulty_mod = 0;

        switch (difficulty) {
            case 0:
                difficulty_mod = 0;
                break;
            case 1:
                difficulty_mod = 2;
                break;
            case 2:
                difficulty_mod = 10;
                break;
            case 3:
                difficulty_mod = 30;
                break;
        }

        for (int i = 0; i < (11 - Configuration.PLAYER_COUNT + round - 1 + difficulty_mod); i++) {
            replaceObstacle();
        }
        // END CHANGED CODE
    }

    public void dispose() {
        for (Obstacle obst : obstacles) {
            obst.dispose();
        }
    }

}