package com.pgaa.redhair.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface State {
	public void init();
	public void update(float delta);
	public void draw(SpriteBatch batch);
}
