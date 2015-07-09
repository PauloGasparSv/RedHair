package com.pgaa.redhair.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Member {
	public void update(float delta);
	public void draw(SpriteBatch batch);
	public void init();
}
