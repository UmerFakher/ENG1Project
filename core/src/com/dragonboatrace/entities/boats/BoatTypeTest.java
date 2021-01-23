package com.dragonboatrace.entities.boats;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoatTypeTest {

    @Test
    void getSaveString() {
        BoatType b = BoatType.FAST;
        assertEquals("0\n",b.getSaveString());
        b = BoatType.AGILE;
        assertEquals("1\n",b.getSaveString());
        b = BoatType.ENDURANCE;
        assertEquals("2\n",b.getSaveString());
        b = BoatType.STRONG;
        assertEquals("3\n",b.getSaveString());
    }
}