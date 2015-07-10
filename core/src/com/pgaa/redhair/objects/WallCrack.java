package com.pgaa.redhair.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.pgaa.redhair.states.State;

public class WallCrack implements Interactive{
	private TextureRegion boxImage [];
	private boolean active;
	private int state;
	private BitmapFont fonte;
	private String text;
	public WallCrack(Texture boxImage){ 
		this.boxImage = new TextureRegion[3];
		this.boxImage[0] = new TextureRegion(boxImage,0,0,82,82);
		this.boxImage[1] = new TextureRegion(boxImage,82,0,82,82);
		this.boxImage[2] = new TextureRegion(boxImage,164,0,82,82);
		active = false;
		fonte = new BitmapFont();
		fonte.setColor(Color.BLUE);
		text = null;
		state = 0;
	}

	public Rectangle getArea() {
		return new Rectangle(494,122,60,80);
	}

	public TextureRegion getBoxImage() {
		return boxImage[state];
	}

	public boolean isActive() {
		return active;
	}

	public void update(float delta,State fase) {
		if((Gdx.input.isButtonPressed(Input.Buttons.LEFT)||Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) && !new Rectangle(200,120,400,400).contains(Gdx.input.getX(), Gdx.input.getY()) ||Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			active = false;
			text = null;
		}
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)&& new Rectangle(280,190,250,210).contains(Gdx.input.getX(), Gdx.input.getY())){
			text = "I think there is something inside this hole, but it is to dark";
		}
		
	}
	public void draw(SpriteBatch batch, OrthographicCamera camera){
		batch.draw(boxImage[state],camera.position.x-200,camera.position.y-180,400,400);
		if(text!=null){
			fonte.draw(batch,text,camera.position.x-300+100,camera.position.y-200);
		}
	}
	public void setActive(boolean a){
		active = a;
	}
}
