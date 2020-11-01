package com.dragonboatrace.tools;

public class Hitbox {

    private float x,y;

    private int width,height;

    public Hitbox(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean collidesWith(Hitbox box){
        return this.x + this.width > box.getX() && this.x < box.getX() + box.getWidth() && this.y < box.getY() + box.getHeight() && this.y + this.height > box.getY();
    }

    public boolean leaves(Hitbox box){
        return this.x +this.width > box.getX() + box.getWidth() || this.x < box.getX()  || this.y < box.getY() && this.y + this.height > box.getY();
    }

    public float getX() { return this.x; }

    public float getY() { return this.y; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }
}
