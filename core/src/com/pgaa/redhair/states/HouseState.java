package com.pgaa.redhair.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.pgaa.redhair.actors.Player;

public class HouseState implements State{
	
	private Player player;
	private Rectangle walking_areas[];
	private Rectangle object_areas[];
	
	private Texture background[];
	private int currentRoom;
	
	public HouseState(Player player){
		this.player = player;
		background = new Texture[2];
		background[0] = new Texture("background/corridor.png");
		background[1] = new Texture("background/corridor2.png");
		
		init();
	}
	@Override
	public void init() {
		walking_areas = new Rectangle[1];
		walking_areas[0] = new Rectangle(0,0,0,0);
		currentRoom = 0;
	}

	@Override
	public void update(float delta, OrthographicCamera camera) {
		player.update(delta, camera);
		
		
		if(Gdx.input.isKeyPressed(Input.Keys.D))
			camera.translate(delta*300, 0);
		if(Gdx.input.isKeyPressed(Input.Keys.A))
			camera.translate(-delta*300, 0);
		
		
		//RESTRAINING CAMERA
		if(camera.position.x<0)
			camera.translate(-camera.position.x,0);
		if(camera.position.x>background[currentRoom].getWidth()-800)
			camera.translate((background[currentRoom].getWidth()-800) - camera.position.x,0);
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(background[currentRoom],-400,-300);
		player.draw(batch);
	}


}
