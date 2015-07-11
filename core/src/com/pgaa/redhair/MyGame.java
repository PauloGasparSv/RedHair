package com.pgaa.redhair;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.pgaa.redhair.states.GameStateManager;

public class MyGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameStateManager gsm;
	private OrthographicCamera camera;
	ShapeRenderer s;
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager(GameStateManager.TEST);
		camera = new OrthographicCamera(800,600);
		camera.translate(0,0);
		s = new ShapeRenderer();
		Gdx.input.setCursorCatched(true);
		Gdx.input.setCursorPosition(-100, -200);
		
		
		
	}
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gsm.update(Gdx.graphics.getDeltaTime(),camera);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		gsm.draw(batch,camera);
		batch.end();
		
		//s.setProjectionMatrix(camera.combined);
		//s.begin(ShapeType.Filled);
		//s.end();
		
		
	}
}
