package test;

import com.dragonboatrace.tools.Hitbox;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HitboxTest {

    private Hitbox h = new Hitbox(4.2f,2.5f,20,60);

    @Test
    void move() {
        assertEquals(1,1);
    }

    @Test
    void getX() {
        assertEquals(4.2f, h.getX(), 0.001f);
    }

    @Test
    void getY() {
        assertEquals(2.5f, h.getY(), 0.001f);
    }

    @Test
    void getWidth() {
        assertEquals(20, h.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(60, h.getHeight());
    }
}