package com.pgaa.redhair.tools;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	private TextureRegion frames[];
	private boolean play;
	private float timer;
	private int curr_frame;
	private float speed;
	private int length;
	
	public Animation(TextureRegion frames[],float speed){
		this.frames = frames;
		this.speed = speed;
		curr_frame = 0;
		play = false;
		timer = 0;
		length = this.frames.length;
	}
	public void play(int frame){
		play = true;
		timer = 0;
		curr_frame = frame;
	}
	public void play(){
		play = true;
		timer = 0;
		curr_frame = 0;
	}
	public void resume(){
		play = true;
		timer = 0;
	}
	public void pause(){
		play = false;
		timer = 0;
	}
	public void stop(int frame){
		play = false;
		timer = 0;
		curr_frame = frame;
	}
	public void stop(){
		play = false;
		timer = 0;
		curr_frame = 0;
	}
	public void update(float delta){
		if(play){
			timer += speed*delta;
			if(timer > 50){
				timer = 0;
				curr_frame = (curr_frame == length-1) ? 0 : curr_frame+1;
			}
		}
	}
	public void setFrame(int frame){
		this.curr_frame = frame;
		timer = 0;
	}
	public TextureRegion getFrame(){
		return frames[curr_frame];
	}
	public boolean isPlaying(){
		return play;
	}
	public int getLength(){
		return length;
	}
	public void setSpeed(int speed){
		this.speed = speed;
	}
	public float getSpeed(){
		return speed;
	}	
}
