package com.dragonboatrace.tools;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Obstacle;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class LaneTest {
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

        // make sure the distribution of obstacles to powerups is correct
        int numObstacle = 0;
        int numPowerUp = 0;

        for(Obstacle obs : laneUltra.getObstacles()){
            if (obs.getDamage() > 0) numObstacle++;
            else numPowerUp++;
        }

        Assert.assertTrue(numObstacle + errorMargin> numPowerUp);
    }
}
