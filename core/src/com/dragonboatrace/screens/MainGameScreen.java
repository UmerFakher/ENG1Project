package com.dragonboatrace.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.entities.boats.Boat;
import com.dragonboatrace.entities.boats.BoatType;
import com.dragonboatrace.entities.boats.ComputerBoat;
import com.dragonboatrace.entities.boats.PlayerBoat;
import com.dragonboatrace.tools.Lane;
import com.dragonboatrace.tools.Race;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainGameScreen implements Screen {

    DragonBoatRace game;
    Race race;
    int players = 2;
    int size;
    ArrayList<Boat> boats;

    float c = 0;
    boolean GO = false;

    public MainGameScreen (DragonBoatRace game) {
        this.game = game;
        size = Gdx.graphics.getWidth() / players;

        /* Each successive boat is at n*size position */
        Boat boat = new PlayerBoat(BoatType.FAST, "square.png", new Lane(new Vector2(0*size,0), size), "ME");
        Boat boat2 = new ComputerBoat(BoatType.FAST, "circle.png", new Lane(new Vector2(1*size,0), size), "COMP1");

        this.boats = new ArrayList<>();
        this.boats.add(boat);
        this.boats.add(boat2);


        this.race = new Race(boats);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float dt = Gdx.graphics.getDeltaTime();

        this.game.getBatch().begin();

        if (GO)
            this.race.update(dt);

        this.race.render(this.game.getBatch());

        this.race.checkWinner(this.game.getBatch(), this.game);

        if (!GO)
            GO = readySteadyGo(dt);


        this.game.getBatch().end();

        for (Boat boat: this.boats){
            if (boat instanceof PlayerBoat)
                if (((PlayerBoat) boat).isDead())
                    this.game.setScreen(new MainMenuScreen(this.game));
        }
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.game.getBatch().dispose();
    }

    public boolean readySteadyGo(float dt){
        BitmapFont font = new BitmapFont(Gdx.files.internal("default.fnt"),false);
        font.setColor(Color.RED);
        font.getData().setScale(5);
        GlyphLayout layout = new GlyphLayout(); //dont do this every frame! Store it as member
        c+=dt;
        if (c < 1 && c > 0) {
            layout.setText(font, "READY");
            font.draw(game.getBatch(), "READY", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() / 2);
        }
        else if (c < 2 && c > 1) {
            layout.setText(font, "STEADY");
            font.draw(game.getBatch(), "STEADY", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() / 2);
        }
        else if (c < 2.3 && c > 2) {
            layout.setText(font, "GO!!!");
            font.draw(game.getBatch(), "GO!!!", (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() / 2);
        }
        else
            return true;
        return false;
    }


}
