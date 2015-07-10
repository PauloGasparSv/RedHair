package com.pgaa.redhair.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.pgaa.redhair.actors.Player;
import com.pgaa.redhair.objects.Interactive;
import com.pgaa.redhair.objects.WallCrack;

public class Test implements State{
	private int currentRoom;
	private Player player;
	private Rectangle rects [][];
	private Interactive interactive[][];
	private boolean mPress;
	private Interactive box;
	private Texture boxImage;
	
	private Texture inventory;
	private Texture flash;
	private Texture banana;
	private Texture bananaFlower;
	private Texture page;
	boolean hasFlash;
	boolean hasBanana;
	boolean hasBananaFlower;
	boolean hasPage;
	int currentItem;
	private boolean escape;
	private boolean showItens;
	
	public Test(){
		interactive = new Interactive[2][1];
		interactive[0][0] = new WallCrack(new Texture("bg/hole.png"));
		boxImage = new Texture("Item/box.png");
		flash = new Texture("Item/flash.png");
		banana = new Texture("Item/banana.png");
		bananaFlower = new Texture("Item/bflower.png");
		page = new Texture("Item/page.png");
		inventory = new Texture("bg/itensMenu.png");
		player = new Player();
		init();
	}
	public void init() {
		currentRoom = 0;
		rects = new Rectangle[2][1];
		rects[0][0] = new Rectangle(460,0,1110,120);
		mPress = false;	
		box = null;
		hasFlash = hasBanana = hasBananaFlower = hasPage = false;
		escape = false;
	}
	public void update(float delta,OrthographicCamera camera) {
		if(box == null){
			player.update(delta,camera,rects[currentRoom]);
			if(player.getX()+120>camera.position.x+580-400){
				camera.translate(delta*180, 0);
			}
			if(player.getX()<camera.position.x+200-400){
				camera.translate(-delta*180, 0);
			}
			
		}
		if(!Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && escape)
			escape = false;
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && !escape){
			escape = true;
			if(box == null)
				showItens = !showItens;
		}
		if(camera.position.x<400)
			camera.translate(400-camera.position.x, 0);
		if(camera.position.x >getSize())
			camera.translate(getSize()-camera.position.x,0);
		
		if(!Gdx.input.isButtonPressed(Input.Buttons.RIGHT)&&mPress)
			mPress = false;
		if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)&&!mPress){
			int x = Gdx.input.getX()+(int)camera.position.x-400;
			int y = 300-Gdx.input.getY()+(int)camera.position.y;
			System.out.println("X: "+x+" Y: "+y);
			for(Interactive i:interactive[currentRoom]){
				if(i.getArea().contains(x,y)){
					box = i;
					box.setActive(true);
				}
			}
			mPress = true;
		}
		if(box != null){
			box.update(delta,this);
			if(!box.isActive()){
				box = null;
			}
		}
	}

	public void draw(SpriteBatch batch,OrthographicCamera camera) {
		player.draw(batch);
		if(box != null){
			batch.draw(boxImage,camera.position.x-400,camera.position.y-300);

			box.draw(batch, camera);
		}
		if(showItens){
			batch.draw(inventory,camera.position.x-400,camera.position.y-300);
		}
	}

	public int getSize() {
		return currentRoom == 0 ? 1320:0;
	}
	public Rectangle[] getRects() {
		return null;
	}

}
