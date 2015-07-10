package com.pgaa.redhair.objects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.pgaa.redhair.states.State;

public interface Interactive {
	public Rectangle getArea();
	public TextureRegion getBoxImage();
	public boolean isActive();
	public void setActive(boolean a);
	public void update(float delta,State s);
	public void draw(SpriteBatch batch, OrthographicCamera camera);
}
