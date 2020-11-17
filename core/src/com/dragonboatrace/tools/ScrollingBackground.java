package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ScrollingBackground {

    private final Texture image;
    private final float defaultSpeed;
    private final Vector2 position;
    private final Texture barrier;
    private float y1, y2;
    private float imageScale;
    private float distance = 0;

    /* Position the scrolling background at a location */
    /* Will usually be given the lanes pos vector */
    public ScrollingBackground() {
        this.image = new Texture("background.png");
        this.barrier = new Texture("line.png");
        this.y1 = 0;
        this.y2 = image.getHeight();
        this.imageScale = 1;
        this.defaultSpeed = 3;
        this.position = new Vector2();
    }

    public void update(float deltaDistance) {
        distance += deltaDistance;
        y1 -= deltaDistance;
        y2 -= deltaDistance;
        // Push the second image to the top if at the bottom
        //   and the first image to the top if at the bottom.
        if (y1 + image.getHeight() * imageScale <= 0)
            y1 = y2 + image.getHeight() * imageScale;

        if (y2 + image.getHeight() * imageScale <= 0)
            y2 = y1 + image.getHeight() * imageScale;
    }

    public void render(SpriteBatch batch) {
        /* render background at the position given by the vector */
        batch.draw(image, this.position.x, y1, image.getWidth() * imageScale, image.getHeight() * imageScale);
        batch.draw(image, this.position.x, y2, image.getWidth() * imageScale, image.getHeight() * imageScale);

        for (int i = 0; i < Settings.PLAYER_COUNT; i++) {
            batch.draw(this.barrier, ((float) Gdx.graphics.getWidth() / Settings.PLAYER_COUNT) * i, 0);
        }
    }

    public void resize(int width) {
        //TODO: Get global scale so that all Entities get scaled according to resolution.
        imageScale = (float) width / image.getWidth();
        y1 = 0;
        y2 = image.getHeight() * imageScale;
    }
}
