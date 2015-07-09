package com.pgaa.redhair;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pgaa.redhair.tools.Animation;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Animation walk[];
	int current;
	int pos[],dest[];
	boolean mPress;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("player/character.png");
		TextureRegion temp [] = new TextureRegion[6];
		for(int i=0; i<6; i++)
			temp[i] = new TextureRegion(img,40*i,704,40,88);
		walk = new Animation[2];
		walk[0] = new Animation(temp,500);
		temp = new TextureRegion[6];
		for(int i=0; i<6; i++){
			temp[i] = new TextureRegion(img,40*i,704,40,88);
			temp[i].flip(true,false);
		}
		walk[1] = new Animation(temp,500);
		current = 1;
		pos = new int[2];
		dest = new int [2];
		pos[0] = pos[1] = 50;
		dest[0] = dest[1] = -1;
		mPress = false;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mPress)
			mPress = false;
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !mPress){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			current = (x>pos[0]+44)?1:0;
			walk[current].resume();
			dest[0] = x;
			dest[1] = y;
			mPress = true;
		}
		if(dest[0] != -1 && dest[1] != -1){
			if(pos[0] > dest[0] -5 && pos[0] < dest[0] + 5){
				dest[0] = dest[1] = -1;
				walk[current].stop();
			}
			pos[0] += Gdx.graphics.getDeltaTime()*(dest[0]>pos[0]?200:-175);
		}
		
		
		walk[current].update(Gdx.graphics.getDeltaTime());
		
		batch.begin();
		batch.draw(walk[current].getFrame(),pos[0],pos[1],80,176);
		
		batch.end();
	}
}
