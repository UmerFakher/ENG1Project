package com.dragonboatrace.tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Hitbox {

    private final int width;
    private final int height;
    private final ShapeRenderer renderer;
    private float x, y;

    public Hitbox(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        renderer = new ShapeRenderer();
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void render() {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.RED);
        renderer.rect(this.x, this.y, this.width, this.height);
        renderer.end();
    }

    public boolean collidesWith(Hitbox box) {
        return this.x + this.width > box.getX() && this.x < box.getX() + box.getWidth() && this.y < box.getY() + box.getHeight() && this.y + this.height > box.getY();
    }

    public boolean leaves(Hitbox box) {
        return this.x + this.width > box.getX() + box.getWidth() || this.x < box.getX() || this.y < box.getY() && this.y + this.height > box.getY();
    }


    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
