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
import com.dragonboatrace.entities.ObstacleType;

import java.util.Iterator;


public class DragonBoatRace extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Boat myBoat;

	ArrayList<Obstacle> obstacleList;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		obstacleList = new ArrayList<Obstacle>();
		myBoat = new Boat(new Vector2(50, 50), BoatType.FAST);
		obstacleList.add(new Obstacle(ObstacleType.ROCK));
	}

	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Iterator<Obstacle> eIter = obstacleList.iterator();
		while (eIter.hasNext()){
			Obstacle ent = eIter.next();
			if (ent.getPos().y+ent.getType().getHeight() <= 0){
				eIter.remove();
				ent.dispose();
			} else if (myBoat.collision(ent)){
				eIter.remove();
				ent.dispose();
			} else{
				ent.update(deltaTime);
				ent.render(batch);
			}
		}


		myBoat.update(deltaTime);
		myBoat.render(batch);

		System.out.println(obstacleList.size());
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Entity ent : obstacleList){
			ent.dispose();
		}
		myBoat.dispose();
	}

}
