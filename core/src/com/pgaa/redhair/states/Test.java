package com.pgaa.redhair.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.pgaa.redhair.actors.Player;

public class Test implements State{
	private int currentRoom;
	private Player player;
	private Rectangle rects [];
	public Test(){
		player = new Player();
		init();
	}
	public void init() {
		currentRoom = 0;
		
	}
	public void update(float delta,OrthographicCamera camera) {
		player.update(delta,camera,rects);
		if(camera.position.x<400)
			camera.translate(400-camera.position.x, 0);
		if(camera.position.x >getSize())
			camera.translate(getSize()-camera.position.x,0);
	}

	public void draw(SpriteBatch batch) {
		player.draw(batch);
	}

	public int getSize() {
		return currentRoom == 0 ? 1320:0;
	}
	public Rectangle[] getRects() {
		return null;
	}

}
