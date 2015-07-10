package com.pgaa.redhair.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Member {
	public void update(float delta,OrthographicCamera camera);
	public void draw(SpriteBatch batch);
	public void init();
}
