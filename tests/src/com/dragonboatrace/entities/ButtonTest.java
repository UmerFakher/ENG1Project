package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * TU_BUTTON_HOVER - Tests to check that the Button isHovering() method is accurate.
 */
@RunWith(GdxTestRunner.class)
public class ButtonTest {

    /**
     * Test to check that the Button isHovering() method is accurate.
     *
     * isHovering() is true when a mouse is hovering and false when no mouse is hovering.
     */
    @Test
    public void isHoveringTest() {
        // mouse is at 0,0 during testing, but a check just in case
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getY();

        // test_texture is 16:16px
        Button bHover = new Button(new Vector2(-8, 999),
                "../tests/res/test_texture.png",
                "../tests/res/test_texture.png");

        Button bNotHover = new Button(new Vector2(30, 30),
                "../tests/res/test_texture.png",
                "../tests/res/test_texture.png");

        assertTrue(bHover.isHovering());
        assertFalse(bNotHover.isHovering());
    }
}