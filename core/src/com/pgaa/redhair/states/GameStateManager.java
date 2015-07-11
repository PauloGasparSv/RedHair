package com.pgaa.redhair.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pgaa.redhair.actors.Player;

public class GameStateManager {
	public static final int TEST = 1, MENU = 0, CREDITS = -1;
	
	private State state;
	//private int currentState;
	
	private Player player;
		
	public GameStateManager(int currentState){
		player = new Player();
		//this.currentState = currentState;
		this.state = new HouseState(player);
	}
	public void update(float delta,OrthographicCamera camera){
		state.update(delta,camera);
	}
	public void draw(SpriteBatch batch,OrthographicCamera camera){
		state.draw(batch);
	}
	
	
}
