package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import de.tomgrill.gdxtesting.GdxTestRunner;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

/**
 * TUA_SCROLLINGBG - Check ScrollingBackground is created with the correct values.
 * TU_SCROLLINGBG_UPDATE - Check the background moves on an update.
 */
@RunWith(GdxTestRunner.class)
public class ScrollingBackgroundTest extends TestCase {

    /**
     * TUA_SCROLLINGBG - Check ScrollingBackground is created with the correct values.
     *
     * The attributes are initialised correctly.
     *
     * @throws NoSuchFieldException No field found
     * @throws IllegalAccessException Not able to access
     */
    @Test
    public void backgroundValuesTest() throws NoSuchFieldException, IllegalAccessException {
        ScrollingBackground s = new ScrollingBackground();

        Field fy1 = ScrollingBackground.class.getDeclaredField("y1");
        Field fy2 = ScrollingBackground.class.getDeclaredField("y2");
        Field fxPosition = ScrollingBackground.class.getDeclaredField("xPosition");
        Field fimagescale = ScrollingBackground.class.getDeclaredField("imageScale");

        fy1.setAccessible(true);
        fy2.setAccessible(true);
        fxPosition.setAccessible(true);
        fimagescale.setAccessible(true);

        float y1 = (float) fy1.get(s);
        float y2 = (float) fy2.get(s);
        float xPosition = (float) fxPosition.get(s);
        float imageScale = (float) fimagescale.get(s);

        assertEquals(0f, y1, 0.001);
        assertEquals(480f, y2, 0.001);
        assertEquals(0f, xPosition,0.001);
        assertEquals(1f, imageScale,0.001);

    }

    /**
     * TU_SCROLLINGBG_UPDATE - Check the background moves on an update.
     *
     * The background should move x amount when updated with a delta of x.
     * @throws NoSuchFieldException No field found
     * @throws IllegalAccessException Not able to access
     */
    @Test
    public void updateTest() throws NoSuchFieldException, IllegalAccessException {
        ScrollingBackground s = new ScrollingBackground();

        Field fy1 = ScrollingBackground.class.getDeclaredField("y1");
        Field fy2 = ScrollingBackground.class.getDeclaredField("y2");

        fy1.setAccessible(true);
        fy2.setAccessible(true);

        float y1 = (float) fy1.get(s);
        float y2 = (float) fy2.get(s);

        assertEquals(0f, y1, 0.001);
        assertEquals(480f, y2, 0.001);
        s.update(10);

        y1 = (float) fy1.get(s);
        y2 = (float) fy2.get(s);

        assertEquals(-10f, y1, 0.001);
        assertEquals(470f, y2, 0.001);
    }

}