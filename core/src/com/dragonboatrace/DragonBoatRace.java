package com.dragonboatrace;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.*;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.tools.Hitbox;
import com.dragonboatrace.tools.Lane;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class DragonBoatRace extends ApplicationAdapter {
	
	SpriteBatch batch;
	Hitbox screen;
	ArrayList<Lane> lanes;

	@Override
	public void create () {
		lanes = new ArrayList<>();
		batch = new SpriteBatch();
		screen = new Hitbox(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 200);

		int laneCount = 4;

		Lane lane1 = new Lane(new Vector2(), Gdx.graphics.getWidth()/laneCount, Gdx.graphics.getHeight(), BoatType.STRONG, true);
		Lane lane2 = new Lane(new Vector2(Gdx.graphics.getWidth()/laneCount, 0), Gdx.graphics.getWidth()/laneCount, Gdx.graphics.getHeight(), BoatType.ENDURANCE, true);
		Lane lane3 = new Lane(new Vector2(2*Gdx.graphics.getWidth()/laneCount, 0), Gdx.graphics.getWidth()/laneCount, Gdx.graphics.getHeight(), BoatType.AGILE, true);
		Lane lane4 = new Lane(new Vector2(3*Gdx.graphics.getWidth()/laneCount, 0), Gdx.graphics.getWidth()/laneCount, Gdx.graphics.getHeight(), BoatType.FAST, true);

		lanes.add(lane1);
		lanes.add(lane2);
		lanes.add(lane3);
		lanes.add(lane4);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float dt = Gdx.graphics.getDeltaTime();
		batch.begin();
		for(Lane lane : lanes){
			lane.update(dt);
			lane.render(batch);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	/* Generate a random Obstacle */
	/* Temporary for debugging speeds */
	public ObstacleType randomObstacle(){
		int rand = ThreadLocalRandom.current().nextInt(0, 3);
		switch(rand){
			case 0: return ObstacleType.BRANCH;
			case 1: return ObstacleType.LEAF;
			case 2: return ObstacleType.ROCK;
			default: return ObstacleType.BRANCH;
		}
	}

}
