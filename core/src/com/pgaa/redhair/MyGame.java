package com.pgaa.redhair;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pgaa.redhair.states.GameStateManager;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	GameStateManager gsm;
	
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager(GameStateManager.TEST);
	}
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		batch.begin();
		gsm.draw(batch);
		batch.end();
	}
}
