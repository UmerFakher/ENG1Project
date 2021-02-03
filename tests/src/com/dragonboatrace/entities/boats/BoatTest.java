package com.dragonboatrace.entities.boats;

import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.tools.Lane;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class BoatTest {

    Lane l = new Lane(new Vector2(0,0), 50, 0, 0);

    @Test
    public void creationAttributeTest(){
        Boat fastBoat = new Boat(BoatType.FAST, l, "__testing_boat__");
        assertEquals(fastBoat.getHealth(), 50, 0.01f);
        assertEquals(fastBoat.getStamina(), 120, 0.01f);
        assertEquals(fastBoat.getAgility(), 85, 0.01f);
        assertEquals(fastBoat.getSpeed(), 275, 0.01f);

        Boat agileBoat = new Boat(BoatType.AGILE, l, "__testing_boat__");
        assertEquals(agileBoat.getHealth(), 50, 0.01f);
        assertEquals(agileBoat.getStamina(), 120, 0.01f);
        assertEquals(agileBoat.getAgility(), 95, 0.01f);
        assertEquals(agileBoat.getSpeed(), 245, 0.01f);

        Boat enduranceBoat = new Boat(BoatType.ENDURANCE, l, "__testing_boat__");
        assertEquals(enduranceBoat.getHealth(), 70, 0.01f);
        assertEquals(enduranceBoat.getStamina(), 180, 0.01f);
        assertEquals(enduranceBoat.getAgility(), 90, 0.01f);
        assertEquals(enduranceBoat.getSpeed(), 245, 0.01f);

        Boat strongBoat = new Boat(BoatType.STRONG, l, "__testing_boat__");
        assertEquals(strongBoat.getHealth(), 100, 0.01f);
        assertEquals(strongBoat.getStamina(), 105, 0.01f);
        assertEquals(strongBoat.getAgility(), 98, 0.01f);
        assertEquals(strongBoat.getSpeed(), 200, 0.01f);
    }
}
