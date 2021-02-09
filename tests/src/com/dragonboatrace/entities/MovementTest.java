package com.dragonboatrace.entities;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.Lane;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * TI_ENTITY_MOVEMENT - Check that entities move the expected amount.
 */
@RunWith(GdxTestRunner.class)
public class MovementTest {

    Lane l = new Lane(new Vector2(0, 0), 50, 0, 0);

    /**
     * TI_ENTITY_MOVEMENT - Check that entities move the expected amount.
     *
     * When moved at a velocity of 10m/s for 1s, the boat should move 10m.
     */
    @Test
    public void boatMovementTest() {
        Boat b = new Boat(BoatType.FAST, l, "");
        float initialDistance = b.getDistanceTravelled();
        b.addVelocity(0,10);
        b.update(1);
        Assert.assertEquals(b.getDistanceTravelled()-initialDistance, 10f, 0.01f);
    }
}
