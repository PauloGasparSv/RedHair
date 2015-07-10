package com.pgaa.redhair.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pgaa.redhair.actors.Player;

public class GameStateManager {
	public static final int TEST = 0, MENU = 1, CREDITS = 100;
	
	private State state;
	private int currentState;
		
	public GameStateManager(int currentState){
		this.currentState = currentState;
		state = new Test();
	}
	public void update(float delta,OrthographicCamera camera){
		state.update(delta,camera);
	}
	public void draw(SpriteBatch batch){
		state.draw(batch);
	}
	
	
}
