package com.dragonboatrace.tools;

import org.junit.Test;

import static org.junit.Assert.*;

public class HitboxTest {

    @Test
    public void move() {
        Hitbox box1 = new Hitbox(10, 10, 10,10);
        assertEquals(10, box1.getX(), 0.001);
        assertEquals(10, box1.getY(), 0.001);
        box1.move(20,20);
        assertEquals(20, box1.getX(), 0.001);
        assertEquals(20, box1.getY(), 0.001);
    }

    @Test
    public void collidesWith() {
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

    @Test
    public void leaves() {
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

    @Test
    public void getX() {
        Hitbox test_hitbox = new Hitbox(10,20,30,40);
        assertEquals(10, test_hitbox.getX(), 0.001);
    }

    @Test
    public void getY() {
        Hitbox test_hitbox = new Hitbox(10,20,30,40);
        assertEquals(20, test_hitbox.getY(), 0.001);
    }

    @Test
    public void getWidth() {
        Hitbox test_hitbox = new Hitbox(10,20,30,40);
        assertEquals(30, test_hitbox.getWidth());
    }

    @Test
    public void getHeight() {
        Hitbox test_hitbox = new Hitbox(10,20,30,40);
        assertEquals(40, test_hitbox.getHeight());
    }
}