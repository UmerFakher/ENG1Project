package com.dragonboatrace.tools;

import de.tomgrill.gdxtesting.GdxTestRunner;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

@RunWith(GdxTestRunner.class)
public class ScrollingBackgroundTest extends TestCase {

    @Test
    public void testUpdate() throws NoSuchFieldException, IllegalAccessException {
        ScrollingBackground s = new ScrollingBackground();

        Field fy1 = ScrollingBackground.class.getDeclaredField("y1");
        Field fy2 = ScrollingBackground.class.getDeclaredField("y2");
        Field fxPosition = ScrollingBackground.class.getDeclaredField("xPosition");

        fy1.setAccessible(true);
        fy2.setAccessible(true);
        fxPosition.setAccessible(true);


        float y1 = (float) fy1.get(s);
        float y2 = (float) fy2.get(s);
        float xPosition = (float) fxPosition.get(s);
    }

    @Test
    public void testResize() {
    }
}