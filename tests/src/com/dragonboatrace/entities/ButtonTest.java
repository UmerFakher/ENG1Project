package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class ButtonTest {

    @Test
    public void isHovering() {
        // mouse is at 0,0 during testing
        System.out.println(Gdx.input.getX() + "," + Gdx.input.getY());
    }
}