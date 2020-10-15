package com.dragonboatrace;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.Boat;
import com.dragonboatrace.entities.BoatType;
import com.dragonboatrace.entities.Entity;
import com.dragonboatrace.entities.Obstacle;

public class DragonBoatRace extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	ArrayList<Entity> entityList;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		entityList = new ArrayList<Entity>();
		entityList.add(new Boat(new Vector2(50, 50), BoatType.FAST));
		entityList.add(new Obstacle());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		for (Entity ent : entityList){
			ent.update(Gdx.graphics.getDeltaTime());
			ent.render(batch);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Entity ent : entityList){
			ent.dispose();
		}
	}

}
