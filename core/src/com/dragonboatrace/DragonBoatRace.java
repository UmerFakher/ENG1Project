package com.dragonboatrace;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DragonBoatRace extends ApplicationAdapter {
	
	SpriteBatch batch;
	Boat myBoat;
	Hitbox screen;

	/* Lists for current obstacles, and ones to remove/add in the next loop */
	ArrayList<Obstacle> obstacles;
	//ArrayList<Obstacle> obstaclesToRemove;
	//ArrayList<Obstacle> obstaclesToAdd;
	
	@Override
	public void create () {
		obstacles = new ArrayList<>();
		//obstaclesToRemove = new ArrayList<>();
		//obstaclesToAdd = new ArrayList<>();

		batch = new SpriteBatch();
		screen = new Hitbox(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 200);
		myBoat = new Boat(new Vector2(Gdx.graphics.getWidth()/2 - EntityType.BOAT.getWidth() / 2, 0), BoatType.ENDURANCE, "circle.png");

		/* Add 10 random Obstacles */
		for(int i = 0; i < 10; i++){
			obstacles.add(new Obstacle(randomObstacle()));
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float dt = Gdx.graphics.getDeltaTime();
		batch.begin();

		updateEntities(dt);
		checkCollisions();
		drawEntities(this.batch);

		/* Remove off screen/collided Obstacles */
		//obstacles.removeAll(obstaclesToRemove);

		/* Add new ones to replace collided/hidden ones */
		//obstacles.addAll(obstaclesToAdd);

		/* Clear lists for next run */
		//obstaclesToRemove.removeAll(obstaclesToRemove);
		//obstaclesToAdd.removeAll(obstaclesToAdd);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for(Obstacle obstacle : obstacles){
			obstacle.dispose();
		}
		myBoat.dispose();
	}

	/* Update entity positions without rendering */
	public void updateEntities(float deltaTime){

		/* Currently only one boat to render/update */
		myBoat.update(deltaTime);

		for(Obstacle ob : obstacles){
			ob.update(deltaTime);
		}
	}

	/* Check for obstacle collisions with boat or out of bounds */
	public void checkCollisions(){

		/* Method using mutliple lists to remove and add objects */
		/* Useful for not instantly adding a new object when an old one is destroyed */

		//for(Obstacle ob : obstacles){
			/* Check for boat collision */
		//	if(myBoat.getHitBox().collidesWith(ob.getHitBox())){
				/* Prepare to replace the old obstacle */
		//		obstaclesToAdd.add(new Obstacle(randomObstacle()));
		//		obstaclesToRemove.add(ob);
		//		ob.dispose();
		//		System.out.println("Collision with obstacle");
			/* If the obstacle is not colliding with the screen it is off the screen */
		//	}else if(!screen.collidesWith(ob.getHitBox())){
		//		obstaclesToAdd.add(new Obstacle(randomObstacle()));
		//		obstaclesToRemove.add(ob);
		//		ob.dispose();
		//		System.out.println("Out of bounds");
		//	}
		//}

		/* Single list variant of removing and adding */
		/* More efficient as only one list is required */
		for(int i = 0; i < obstacles.size(); i++){
			Obstacle ob = obstacles.get(i);
			if(myBoat.getHitBox().collidesWith(ob.getHitBox())){
				obstacles.set(i, new Obstacle(randomObstacle()));
				ob.dispose();
				System.out.println("Collision with obstacle");
			}else if(!screen.collidesWith(ob.getHitBox())){
				obstacles.set(i, new Obstacle(randomObstacle()));
				ob.dispose();
				System.out.println("Out of bounds");
			}
		}
	}

	/* Render the entities */
	public void drawEntities(SpriteBatch batch){
		myBoat.render(batch);

		for(Obstacle ob : obstacles){
			ob.render(batch);
		}
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
