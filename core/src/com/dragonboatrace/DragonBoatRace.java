package com.dragonboatrace;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Boat;
import com.dragonboatrace.entities.BoatType;

public class DragonBoatRace extends ApplicationAdapter {
	SpriteBatch batch;
	//Texture img;
	Boat myBoat;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		myBoat = new Boat(BoatType.FAST, "circle.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		myBoat.update(Gdx.graphics.getDeltaTime());
		myBoat.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}

}
