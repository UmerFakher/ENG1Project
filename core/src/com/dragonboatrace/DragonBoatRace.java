package com.dragonboatrace;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.entities.*;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.entities.boats.ComputerBoat;
import com.dragonboatrace.entities.boats.PlayerBoat;
import com.dragonboatrace.screens.MainGameScreen;
import com.dragonboatrace.screens.MainMenuScreen;
import com.dragonboatrace.tools.Hitbox;
import com.dragonboatrace.tools.Lane;
import com.dragonboatrace.tools.Race;
import com.dragonboatrace.tools.ScrollingBackground;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class DragonBoatRace extends Game {
	
	SpriteBatch batch;
	Boat boat, boat2, boat3, boat4, boat5, boat6, boat7;

	Race race;

	/* Players scales the width of the lane */
	int players = 2;
	int size;

	@Override
	public void create () {
		batch = new SpriteBatch();

		this.setScreen(new MainMenuScreen(this));


	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height){
		super.resize(width, height);

	}

	public SpriteBatch getBatch() { return this.batch; }
	public Race getRace() { return this.race; }

}
