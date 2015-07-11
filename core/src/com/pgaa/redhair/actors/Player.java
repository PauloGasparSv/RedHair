package com.pgaa.redhair.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player implements Actor{
	private Vector2 position;	
	
	public Player(){
		//LOADING IMAGES
		
		
	}
	public void init(){
		
	}
	public void update(float delta,OrthographicCamera camera){
		
	}
	public void draw(SpriteBatch batch){
		
	}
	
	
	public Vector2 getPosition(){
		return position;
	}
	public void setPosition(Vector2 position){
		this.position = position;
	}
	public void setPosition(float x,float y){
		this.position.x = x;
		this.position.y = y;
	}
	public void setPosition(int x,int y){
		this.position.x = x;
		this.position.y = y;
	}
}
