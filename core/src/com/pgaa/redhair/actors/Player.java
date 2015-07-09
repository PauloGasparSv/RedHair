package com.pgaa.redhair.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pgaa.redhair.tools.Animation;

public class Player implements Member {
	public static final int WALK_LEFT = 0, WALK_RIGHT = 1;
	private Animation animation[];
	private int action;
	private int position[];
	private int destination[];
	private boolean mousePress;
	private int mousePosition[];
	
	public Player(){
		Texture temp_img = new Texture("player/character.png");
		//LOADING THE WALKING TO THE LEFT CYCLE
		TextureRegion temp [] = new TextureRegion[6];
		for(int i=0; i<6; i++)
			temp[i] = new TextureRegion(temp_img,40*i,704,40,88);
		animation = new Animation[2];
		animation[0] = new Animation(temp,500);
		//LOADING THE WALKING TO THE RIGTH CYCLE
		temp = new TextureRegion[6];
		for(int i=0; i<6; i++){
			temp[i] = new TextureRegion(temp_img,40*i,704,40,88);
			temp[i].flip(true,false);
		}
		animation[1] = new Animation(temp,500);
		
		init();
	}
	
	public void init(){
		action = WALK_RIGHT;
		position = new int[2];
		destination = new int [2];
		mousePosition = new int[2];
		position[0] = 50;
		position[1] = 50;
		destination[0] = -1;
		destination[1] = -1;
		mousePosition[0] = 0;
		mousePosition[1] = 0;
		mousePress = false;
	}
	
	public void update(float delta) {
		if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mousePress){
			mousePress = false;
		}
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !mousePress){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			action = (x>position[0]+44)?1:0;
			animation[action].resume();
			destination[0] = x;
			destination[1] = y;
			mousePress = true;
		}
		if(destination[0] != -1 && destination[1] != -1){
			if(position[0] > destination[0] -5 && position[0] < destination[0] + 5){
				destination[0] = destination[1] = -1;
				animation[action].stop();
			}
			position[0] += Gdx.graphics.getDeltaTime()*(destination[0]>position[0]?200:-175);
		}
		
		
		animation[action].update(Gdx.graphics.getDeltaTime());
	}

	public void draw(SpriteBatch batch) {
		batch.draw(animation[action].getFrame(),position[0],position[1],80,176);

	}

}
