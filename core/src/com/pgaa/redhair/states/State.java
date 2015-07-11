package com.pgaa.redhair.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface State {
	public void init();
	public void update(float delta,OrthographicCamera camera);
	public void draw(SpriteBatch batch);
}
