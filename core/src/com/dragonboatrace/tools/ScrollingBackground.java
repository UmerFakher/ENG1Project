package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.boats.Boat;

public class ScrollingBackground {

    private Texture image;
    private float y1, y2;
    private float imageScale;
    private float defaultSpeed;
    private Vector2 position;
    private int width;

    /* Position the scrolling background at a location */
    /* Will usually be given the lanes pos vector */
    public ScrollingBackground(Vector2 pos, int width) {
        this.image = new Texture("background.png");
        this.y1 = 0;
        this.y2 = image.getHeight();
        this.imageScale = 1;
        this.defaultSpeed = 3;
        this.position = pos;
        this.width = width;
    }


    public void update(float deltaTime, float vel){
        if (vel < defaultSpeed){
            y1 -= defaultSpeed;
            y2 -= defaultSpeed;
        }else {
            y1 -= vel;
            y2 -= vel;
        }
        if (y1 + image.getHeight() * imageScale <= 0)
            y1 = y2 + image.getHeight() * imageScale;

        if (y2 + image.getHeight() * imageScale <= 0)
            y2 = y1 + image.getHeight() * imageScale;
    }

    public void render(SpriteBatch batch) {
        /* render background at the position given by the vector */
        batch.draw(image, this.position.x, y1, image.getWidth() * imageScale, image.getHeight() * imageScale);
        batch.draw(image, this.position.x, y2, image.getWidth() * imageScale, image.getHeight() * imageScale);


    }

    public void resize(int width, int height){
        imageScale = width / image.getWidth();
        y1 = 0;
        y2 = image.getHeight()*imageScale;
    }
}
