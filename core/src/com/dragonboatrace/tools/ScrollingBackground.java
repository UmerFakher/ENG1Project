package com.dragonboatrace.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonboatrace.entities.boats.Boat;

public class ScrollingBackground {
    


    Texture image;
    float y1, y2;
    float imageScale;
    float defaultSpeed = (float)1.5;

    public ScrollingBackground() {
        image = new Texture("background.png");
        y1 = 0;
        y2 = image.getHeight();
        imageScale = 1;
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

        batch.draw(image, 0, y1, image.getWidth() * imageScale, image.getHeight() * imageScale);
        batch.draw(image, 0, y2, image.getWidth() * imageScale, image.getHeight() * imageScale);


    }

    public void resize(int width, int height){
        imageScale = width / image.getWidth();
        y1 = 0;
        y2 = image.getHeight()*imageScale;
    }
}
