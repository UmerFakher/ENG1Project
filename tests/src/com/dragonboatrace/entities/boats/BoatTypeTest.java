package com.dragonboatrace.entities.boats;

import com.dragonboatrace.tools.ScrollingBackground;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * TUA_BOAT_TYPE - Tests to check all boat type variables are initialised to the correct values.
 */
public class BoatTypeTest {

    /**
     * Initial test to check save string returned for each boat type is as expected.
     */
    @Test
    public void getSaveStringTest() {
        BoatType b = BoatType.FAST;
        assertEquals("0\n",b.getSaveString());
        b = BoatType.AGILE;
        assertEquals("1\n",b.getSaveString());
        b = BoatType.ENDURANCE;
        assertEquals("2\n",b.getSaveString());
        b = BoatType.STRONG;
        assertEquals("3\n",b.getSaveString());
    }

    /**
     * Check attribute values of this boat type are correctly stored in the Enum.
     */
    @Test
    public void fastBoatTypeTest() {
        BoatType b = BoatType.FAST;

        assertEquals(b.getHealth(), 50, 0.01f);
        assertEquals(b.getStamina(), 120, 0.01f);
        assertEquals(b.getAgility(), 85, 0.01f);
        assertEquals(b.getSpeed(), 275, 0.01f);
        assertEquals(b.getImageSrc(), "fast.png");

        // template index tested through BoatTypeTest.getSaveString()
    }

    /**
     * Check attribute values of this boat type are correctly stored in the Enum.
     */
    @Test
    public void agileBoatTypeTest() {
        BoatType b = BoatType.AGILE;

        assertEquals(b.getHealth(), 50, 0.01f);
        assertEquals(b.getStamina(), 120, 0.01f);
        assertEquals(b.getAgility(), 95, 0.01f);
        assertEquals(b.getSpeed(), 245, 0.01f);
        assertEquals(b.getImageSrc(), "agile.png");

        // template index tested through BoatTypeTest.getSaveString()
    }

    /**
     * Check attribute values of this boat type are correctly stored in the Enum.
     */
    @Test
    public void enduranceBoatTypeTest() {
        BoatType b = BoatType.ENDURANCE;

        assertEquals(b.getHealth(), 70, 0.01f);
        assertEquals(b.getStamina(), 180, 0.01f);
        assertEquals(b.getAgility(), 90, 0.01f);
        assertEquals(b.getSpeed(), 245, 0.01f);
        assertEquals(b.getImageSrc(), "endurance.png");

        // template index tested through BoatTypeTest.getSaveString()
    }

    /**
     * Check attribute values of this boat type are correctly stored in the Enum.
     */
    @Test
    public void strongBoatTypeTest() {
        BoatType b = BoatType.STRONG;

        assertEquals(b.getHealth(), 100, 0.01f);
        assertEquals(b.getStamina(), 105, 0.01f);
        assertEquals(b.getAgility(), 98, 0.01f);
        assertEquals(b.getSpeed(), 200, 0.01f);
        assertEquals(b.getImageSrc(), "strong.png");

        // template index tested through BoatTypeTest.getSaveString()
    }
}