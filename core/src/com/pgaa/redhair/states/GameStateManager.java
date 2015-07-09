package com.pgaa.redhair.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pgaa.redhair.actors.Player;

public class GameStateManager {
	public static final int TEST = 0, MENU = 1, CREDITS = 100;
	
	State state;
	int currentState;
	Player player;
	
	public GameStateManager(int currentState){
		this.currentState = currentState;
		player = new Player();
	}
	public void update(float delta){
		player.update(delta);
	}
	public void draw(SpriteBatch batch){
		player.draw(batch);
	}
	
	
}
