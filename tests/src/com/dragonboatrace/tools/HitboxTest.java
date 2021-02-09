package com.dragonboatrace.tools;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * TU_HITBOX_POSITION - Check that the Hitbox class reports the correct position.
 * TU_HITBOX_COLLISION - Check that the Hitbox class correctly reports collisions.
 * TU_HITBOX_LEAVE - Check that the Hitbox class is able to determine if another Hitbox is colliding when moved.
 *
 * Below are functions to test moving, colliding, leaving followed by individual value tests.
 * These should be self-explanatory.
 */
public class HitboxTest {

    /**
     * TU_HITBOX_POSITION - Check that the Hitbox class reports the correct position.
     *
     * A Hitbox created at (x,y) and moved (dx,dy) should report being at (x+dx,y+dy).
     */
    @Test
    public void moveTest() {
        Hitbox box1 = new Hitbox(10, 10, 10,10);
        assertEquals(10, box1.getX(), 0.001);
        assertEquals(10, box1.getY(), 0.001);
        box1.move(20,20);
        assertEquals(20, box1.getX(), 0.001);
        assertEquals(20, box1.getY(), 0.001);
    }

    /**
     * TU_HITBOX_COLLISION - Check that the Hitbox class correctly reports collisions.
     *
     * Collisions should be reported for Hitboxes that are overlapping, but not for Hitboxes that are not overlapping.
     */
    @Test
    public void collidesWithTest() {
        Hitbox box1 = new Hitbox(5,5,10,10);
        Hitbox box2 = new Hitbox(0,0,20,20);
        // boxes colliding, testing it is reflexive aswell
        assertTrue(box1.collidesWith(box2));
        assertTrue(box2.collidesWith(box1));
        box1.move(20,0);
        // box1 is now at the very edge of box2
        assertFalse(box1.collidesWith(box2));
        assertFalse(box2.collidesWith(box1));
        box1.move(30, 30);
        // box1 is now far from box2
        assertFalse(box1.collidesWith(box2));
        assertFalse(box2.collidesWith(box1));
    }

    /**
     * TU_HITBOX_LEAVE - Check that the Hitbox class is able to determine if another Hitbox is colliding when moved.
     *
     * A previously colliding Hitbox must report no collision if it moves out of the other Hitboxes bounds.
     */
    @Test
    public void leavesTest() {
        Hitbox box1 = new Hitbox(5,5,10,10);
        Hitbox box2 = new Hitbox(0,0,20,20);
        // box1 is inside box2's area
        assertFalse(box1.leaves(box2));
        box1.move(10,10);
        // box1 is at the very edge of box2's bounds
        assertFalse(box1.leaves(box2));
        box1.move(15,15);
        // box1 is partially outside of box2's bounds
        assertTrue(box1.leaves(box2));
        box1.move(100,100);
        // box1 is now far outside box2's bounds
        assertTrue(box1.leaves(box2));
    }

    /**
     * Check x position of a Hitbox
     */
    @Test
    public void getXTest() {
        Hitbox test_hitbox = new Hitbox(10,20,30,40);
        assertEquals(10, test_hitbox.getX(), 0.001);
    }

    /**
     * Check y position of a Hitbox
     */
    @Test
    public void getYTest() {
        Hitbox test_hitbox = new Hitbox(10,20,30,40);
        assertEquals(20, test_hitbox.getY(), 0.001);
    }

    /**
     * Check width of a Hitbox
     */
    @Test
    public void getWidthTest() {
        Hitbox test_hitbox = new Hitbox(10,20,30,40);
        assertEquals(30, test_hitbox.getWidth());
    }

    /**
     * Check height of a Hitbox
     */
    @Test
    public void getHeightTest() {
        Hitbox test_hitbox = new Hitbox(10,20,30,40);
        assertEquals(40, test_hitbox.getHeight());
    }
}