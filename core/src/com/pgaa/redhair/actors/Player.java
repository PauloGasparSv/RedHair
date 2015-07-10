package com.pgaa.redhair.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.pgaa.redhair.tools.Animation;

public class Player {
	public static final int LEFT=0,RIGHT=1,UP=2,DOWN=3,DOWNLEFT=4,DOWNRIGHT=5,UPLEFT=6,UPRIGHT=7;
	public static final int IDLE=-1,WALK=0;
	private Animation animation[][];
	private TextureRegion image_idle[];
	private int action;
	private int facing;
	private int position[];
	private Rectangle destination;
	private Rectangle myHitArea;
	private boolean mousePress;
	private int mousePosition[];
	private float speed[];
	public Player(){
		Texture temp_img = new Texture("player/sheet.png");
		//LOADING THE IDLE IMAGES
		image_idle = new TextureRegion[8];
		image_idle[UP] = new TextureRegion(temp_img,0,0,40,88);
		image_idle[DOWN] = new TextureRegion(temp_img,0,88,40,88);
		image_idle[DOWNLEFT] = new TextureRegion(temp_img,0,176,40,88);
		image_idle[LEFT] = new TextureRegion(temp_img,0,263,40,88);
		image_idle[UPLEFT] = new TextureRegion(temp_img,0,352,40,88);
		image_idle[DOWNRIGHT] = new TextureRegion(temp_img,0,176,40,88);
		image_idle[DOWNRIGHT].flip(true, false);
		image_idle[UPRIGHT] = new TextureRegion(temp_img,0,352,40,88);
		image_idle[UPRIGHT].flip(true, false);
		image_idle[RIGHT] = new TextureRegion(temp_img,0,263,40,88);
		image_idle[RIGHT].flip(true, false);
		
		//LOADING THE WALKING TO THE LEFT CYCLE
		animation = new Animation[1][8];
		TextureRegion temp [] = new TextureRegion[6];
		for(int i=0; i<6; i++)
			temp[i] = new TextureRegion(temp_img,40*i,704,40,87);
		animation[0][LEFT] = new Animation(temp,500);
		//LOADING THE WALKING TO THE RIGTH CYCLE
		temp = new TextureRegion[6];
		for(int i=0; i<6; i++){
			temp[i] = new TextureRegion(temp_img,40*i,704,40,87);
			temp[i].flip(true,false);
		}
		animation[0][RIGHT] = new Animation(temp,500);
		//LOADING THE WALKING TO THE UP CYCLE
		temp = new TextureRegion[6];
		for(int i=0; i<6; i++){
			temp[i] = new TextureRegion(temp_img,40*i,88*5,40,87);
			temp[i].flip(true,false);
		}
		animation[0][UP] = new Animation(temp,500);
		//LOADING THE WALKING TO THE DOWN CYCLE
		temp = new TextureRegion[6];
		for(int i=0; i<6; i++){
			temp[i] = new TextureRegion(temp_img,40*i,88*6,40,88);
		}
		animation[0][DOWN] = new Animation(temp,500);
		//LOADING THE WALKING TO THE DOWNLEFT CYCLE
		temp = new TextureRegion[6];
		for(int i=0; i<6; i++){
			temp[i] = new TextureRegion(temp_img,40*i,88*7,40,88);
		}
		animation[0][DOWNLEFT] = new Animation(temp,500);
		//LOADING THE WALKING TO THE UPLEFT CYCLE
		temp = new TextureRegion[6];
		for(int i=0; i<6; i++){
			temp[i] = new TextureRegion(temp_img,40*i,88*9,40,88);
		}
		animation[0][UPLEFT] = new Animation(temp,500);
		//LOADING THE WALKING TO THE DOWNRIGHT CYCLE
		temp = new TextureRegion[6];
		for(int i=0; i<6; i++){
			temp[i] = new TextureRegion(temp_img,40*i,88*7,40,88);
			temp[i].flip(true,false);
		}
		animation[0][DOWNRIGHT] = new Animation(temp,500);
		//LOADING THE WALKING TO THE UPRIGHT CYCLE
		temp = new TextureRegion[6];
		for(int i=0; i<6; i++){
			temp[i] = new TextureRegion(temp_img,40*i,88*9,40,88);
			temp[i].flip(true,false);
		}
		animation[0][UPRIGHT] = new Animation(temp,500);
		
		init();
	}
	
	public void init(){
		speed = new float[2];
		speed[0] = 180;
		speed[1] = 0;
		action = 0;
		facing = RIGHT;
		position = new int[2];
		destination = new Rectangle(-1, -1, 15, 20);
		myHitArea = new Rectangle(position[0],position[1],80,55);
		mousePosition = new int[2];
		position[0] = 50;
		position[1] = 50;
		
		mousePosition[0] = 0;
		mousePosition[1] = 0;
		mousePress = false;
	}
	
	public void update(float delta,OrthographicCamera camera,Rectangle rects[]) {
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			camera.translate(delta*200, 0);
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
			camera.translate(-delta*200, 0);
		
		if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mousePress){
			mousePress = false;
		}
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && !mousePress){
			int x = Gdx.input.getX()+(int)camera.position.x-400-(int)destination.width/2;
			int y = 300-Gdx.input.getY()+(int)camera.position.y-(int)destination.height;
			if(canWalk(x, y,rects)){
				action = WALK;
				if(x > position[0]+myHitArea.width+40){
					if(y >= position[1]+myHitArea.height-myHitArea.height/10){
						facing = UPRIGHT;
						destination.x = x;
						destination.y = y;
						speed[1] = (float)((speed[0]) * (y - position[1]))/(x - position[0]);
					}
					else if(y < position[1]-25){
						facing = DOWNRIGHT;
						destination.x = x;
						destination.y = y;
						speed[1] = ((speed[0]) * (y - position[1]))/(x - position[0]);
					}
					else{
						facing = RIGHT;
						destination.x = x;
						destination.y = position[1];
						speed[1] = 0;
					}
				}
				else if(x < position[0]-40){
					if(y >= position[1]+myHitArea.height-myHitArea.height/10){
						facing = UPLEFT;
						destination.x = x;
						destination.y = y;
						speed[1] = ((speed[0]) * (y - position[1]))/(x - position[0]);
					}
					else if(y < position[1]-25){
						facing = DOWNLEFT;
						destination.x = x;
						destination.y = y;
						speed[1] = ((speed[0]) * (y - position[1]))/(x - position[0]);
					}
					else{
						facing = LEFT;
						destination.x = x;
						destination.y = position[1];
						speed[1] = 0;
					}
				}
				else{
					if(y < myHitArea.y){
						facing = DOWN;
						speed[1] = -speed[0];
					}
					else{
						facing = UP;
						speed[1] = speed[0];
					}
					destination.y = y;
					destination.x = position[0];
				}
				animation[action][facing].resume();
				mousePress = true;
			}
		}
		if(destination.x > 0 || destination.y >0){
			if(facing == RIGHT)
				position[0] += Gdx.graphics.getDeltaTime()*speed[0];
			else if(facing == LEFT)
				position[0] += Gdx.graphics.getDeltaTime()*-speed[0];
			else if(facing == DOWN || facing == UP)
				position[1] += Gdx.graphics.getDeltaTime()*speed[1];
			else if(facing == DOWNRIGHT || facing == UPRIGHT){
				position[0] += Gdx.graphics.getDeltaTime()*speed[0];
				position[1] += Gdx.graphics.getDeltaTime()*speed[1];
			}
			else{
				position[0] += Gdx.graphics.getDeltaTime()*-speed[0];
				position[1] += Gdx.graphics.getDeltaTime()*-speed[1];
			}
			myHitArea.x = position[0];
			myHitArea.y = position[1]+10;
			if(myHitArea.overlaps(destination)){
				destination.x = destination.y = -1;
				animation[action][facing].stop();
				action = IDLE;
			}
		}
		if(destination.x == destination.y && destination.x == -1)
			action = IDLE;
		if(action != IDLE)
			animation[action][facing].update(Gdx.graphics.getDeltaTime());
	}
	
	private boolean canWalk(int px,int py,Rectangle rects[]){
		boolean ok = false;
		for(Rectangle r:rects)
			if(r.contains(px,py))
				ok = true;
		return ok;
		
	}

	public void draw(SpriteBatch batch) {
		if(action!=IDLE)
			batch.draw(animation[action][facing].getFrame(),position[0],position[1],80,176);
		else
			batch.draw(image_idle[facing],position[0],position[1],80,176);
	}

}
