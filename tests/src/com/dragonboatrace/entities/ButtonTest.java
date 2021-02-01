package com.dragonboatrace.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class ButtonTest {

    @Test
    public void isHovering() {
        // mouse is at 0,0 during testing, but a check just in case
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getY();

        // test_texture is 16:16px
        Button bHover = new Button(new Vector2(mouseX-8, mouseY-8),
                "../tests/res/test_texture.png",
                "../tests/res/test_texture.png");

        Button bNotHover = new Button(new Vector2(mouseX+30, mouseX+30),
                "../tests/res/test_texture.png",
                "../tests/res/test_texture.png");

        assertTrue(bHover.isHovering());
        assertFalse(bNotHover.isHovering());
    }
}