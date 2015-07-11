package com.pgaa.redhair.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.pgaa.redhair.tools.Animation;

public class Player implements Actor{
	private final int UP=4,DOWN=0,LEFT=6,RIGHT=2,UPRIGHT=3,UPLEFT=5,DOWNRIGHT=1,DOWNLEFT=7;
	private final int IDLE=0,WALK=1;
	
	private int action;
	private int direction;
	private Vector2 position;	
	private TextureRegion idle[];
	private Animation walk[];
	
	public Player(){
		Texture temp = new Texture("char/idle.png");
		idle = new TextureRegion[8];
		for(int i=0;i<8;i++)
			idle[i] = new TextureRegion(temp,i*40,0,40,86);
		
		//LOADING ANIMATIONS
		walk = new Animation[8];
		
		temp = new Texture("char/walk_down.png");
		TextureRegion tempregion[] = new TextureRegion[6];
		for(int i=0;i<6;i++)
			tempregion[i] = new TextureRegion(temp,i*40,0,40,86);
		walk[DOWN] = new Animation(tempregion,350);
		
		temp = new Texture("char/walk_downleft.png");
		tempregion = new TextureRegion[6];
		for(int i=0;i<6;i++)
			tempregion[i] = new TextureRegion(temp,i*40,0,40,86);
		walk[DOWNLEFT] = new Animation(tempregion,350);
		
		temp = new Texture("char/walk_downright.png");
		tempregion = new TextureRegion[6];
		for(int i=0;i<6;i++)
			tempregion[i] = new TextureRegion(temp,i*40,0,40,86);
		walk[DOWNRIGHT] = new Animation(tempregion,350);
		
		temp = new Texture("char/walk_left.png");
		tempregion = new TextureRegion[6];
		for(int i=0;i<6;i++)
			tempregion[i] = new TextureRegion(temp,i*40,0,40,86);
		walk[LEFT] = new Animation(tempregion,350);
		
		temp = new Texture("char/walk_right.png");
		tempregion = new TextureRegion[6];
		for(int i=0;i<6;i++)
			tempregion[i] = new TextureRegion(temp,i*40,0,40,86);
		walk[RIGHT] = new Animation(tempregion,350);
		
		temp = new Texture("char/walk_up.png");
		tempregion = new TextureRegion[6];
		for(int i=0;i<6;i++)
			tempregion[i] = new TextureRegion(temp,i*40,0,40,86);
		walk[UP] = new Animation(tempregion,350);
		
		temp = new Texture("char/walk_upleft.png");
		tempregion = new TextureRegion[6];
		for(int i=0;i<6;i++)
			tempregion[i] = new TextureRegion(temp,i*40,0,40,86);
		walk[UPLEFT] = new Animation(tempregion,350);
		
		temp = new Texture("char/walk_upright.png");
		tempregion = new TextureRegion[6];
		for(int i=0;i<6;i++)
			tempregion[i] = new TextureRegion(temp,i*40,0,40,86);
		walk[UPRIGHT] = new Animation(tempregion,350);
		
		init();
	}
	public void init(){
		action = WALK;
		direction = LEFT;
		position = new Vector2(60,-160);
		
		walk[direction].play();
		
	}
	public void update(float delta,OrthographicCamera camera){
		if(action == WALK){
			walk[direction].update(delta);
		}
		
	}
	public void draw(SpriteBatch batch){
		if(action == IDLE)
			batch.draw(idle[direction],position.x,position.y,80,172);
		else
			batch.draw(walk[direction].getFrame(),position.x,position.y,80,172);
		
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
