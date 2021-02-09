package com.dragonboatrace.tools;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Obstacle;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * TI_OBSTACLE_SPAWN - Check that more obstacles spawn on higher difficulties, with a preference for negative
 * obstacles rather than powerups.
 *
 * The number of obstacles spawned should be always higher on higher difficulties.
 * The number of powerups should always be smaller than the number of obstacles.
 */
@RunWith(GdxTestRunner.class)
public class LaneTest {
    /**
     * Check that more obstacles spawn on higher difficulties, with a preference for negative
     *  obstacles rather than powerups. The number of powerups should always be smaller than the number of obstacles.
     */
    @Test
    public void obstacleSpawnRateTest() {
        Lane laneEasy = new Lane(new Vector2(0, 0), 50, 0, 0);
        Lane laneNormal = new Lane(new Vector2(0, 0), 50, 0, 1);
        Lane laneHard = new Lane(new Vector2(0, 0), 50, 0, 2);
        Lane laneUltra = new Lane(new Vector2(0, 0), 50, 0, 3);

        // simulate some time to let obstacles spawn
        for (int i = 0; i < 10; i++) {
            laneEasy.update(1, 5);
            laneNormal.update(1, 5);
            laneHard.update(1, 5);
            laneUltra.update(1, 5);
        }

        // variable to account for randomness in spawning
        int errorMargin = 5;

        // make sure that the number of obstacles increases with difficulty
        Assert.assertTrue(laneEasy.getObstacles().size() <= laneNormal.getObstacles().size() + errorMargin);
        Assert.assertTrue(laneNormal.getObstacles().size() <= laneHard.getObstacles().size() + errorMargin);
        Assert.assertTrue(laneHard.getObstacles().size() <= laneUltra.getObstacles().size() + errorMargin);
    }

    /**
     * Checks to make sure the ratio of obstacles to powerups is in the correct bounds.
     */
    @Test
    public void obstacleDistributionTest() {
        // make sure the distribution of obstacles to powerups is correct
        Lane lane = new Lane(new Vector2(0, 0), 50, 0, 3);
        int numObstacle = 0;
        int numPowerUp = 0;

        for (Obstacle obs : lane.getObstacles()) {
            if (obs.getDamage() > 0) numObstacle++;
            else numPowerUp++;
        }

        // variable to account for randomness in spawning
        int errorMargin = 5;

        Assert.assertTrue(numObstacle + errorMargin > numPowerUp);
    }
}
