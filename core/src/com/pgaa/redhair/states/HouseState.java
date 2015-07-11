package com.pgaa.redhair.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pgaa.redhair.actors.Player;

public class HouseState implements State{
	private final int POINT=0,TAKE=1,EXAMINE=2,ATTACK=3;
	private Player player;
	private Rectangle walking_areas[][];
	private Rectangle object_areas[][];
	private String object_names[][];
	
	private Texture background[];
	private int currentRoom;
	
	private TextureRegion cursor[][];
	private int cursorState;
	private boolean mPress;
	private boolean wPress;
	private String lookingAt;
	private Vector2 mousePosition;
	
	private float textTimer;
	private String message;
	private BitmapFont fonte;
	private Vector2 textPos;
	
	public HouseState(Player player){
		this.player = player;
		background = new Texture[2];
		background[0] = new Texture("background/corridor.png");
		background[1] = new Texture("background/corridor2.png");
		cursor = new TextureRegion[4][];
		cursor[0] = new TextureRegion[2];
		cursor[1] = new TextureRegion[2];
		cursor[2] = new TextureRegion[1];
		cursor[3] = new TextureRegion[1];
		Texture temp = new Texture("icons/cursor_point.png");
		cursor[POINT][0] = new TextureRegion(temp,0,0,32,32);
		cursor[POINT][1] = new TextureRegion(temp,32,0,32,32);
		temp = new Texture("icons/cursor_take.png");
		cursor[TAKE][0] = new TextureRegion(temp,0,0,32,32);
		cursor[TAKE][1] = new TextureRegion(temp,32,0,32,32);
		temp = new Texture("icons/cursor_examine.png");
		cursor[EXAMINE][0] = new TextureRegion(temp,0,0,32,32);
		temp = new Texture("icons/cursor_attack.png");
		cursor[ATTACK][0] = new TextureRegion(temp,0,0,32,32);
		fonte = new BitmapFont(Gdx.files.internal("font/fonte.fnt"),false);
		
		init();
	}
	@Override
	public void init() {
		walking_areas = new Rectangle[2][1];
		walking_areas[0][0] = new Rectangle(-65,-238,757,81);
		walking_areas[1][0] = new Rectangle(-291,-208,490,81);
		object_names = new String[2][];
		object_names[0] = new String[9];
		object_names[1] = new String[4];
		object_areas = new Rectangle[2][];
		object_areas[0] = new Rectangle[9];
		object_areas[1] = new Rectangle[4];
		//SETTING THE OBJECT AREAS
		object_areas[0][0] = new Rectangle(-400,-209,323,109);
		object_areas[0][1] = new Rectangle(-400,-100,218,137);
		object_areas[0][2] = new Rectangle(-155,-69,89,123);
		object_areas[0][3] = new Rectangle(-2,-69,89,123);
		object_areas[0][4] = new Rectangle(490,-69,82,123);
		object_areas[0][5] = new Rectangle(-59,-122,49,49);
		object_areas[0][6] = new Rectangle(200,-126,68,203);
		object_areas[0][7] = new Rectangle(366,31,31,46);
		object_areas[0][8] = new Rectangle(722,-198,45,250);
		object_areas[1][0] = new Rectangle(-26,-124,70,201);
		object_areas[1][1] = new Rectangle(-71,-68,114,126);
		object_areas[1][2] = new Rectangle(248,64,41,30);
		object_areas[1][3] = new Rectangle(-368,-194,46,247);
		//SETTING THE OBJECT NAMES
		object_names[0][0] = "rock";
		object_names[0][1] = "rock";
		object_names[0][2] = "window1";
		object_names[0][3] = "window2";
		object_names[0][4] = "window3";
		object_names[0][5] = "hole";
		object_names[0][6] = "door1";
		object_names[0][7] = "lamp";
		object_names[0][8] = "door2";
		object_names[1][0] = "door1";
		object_names[1][1] = "frame";
		object_names[1][2] = "allangay";
		object_names[1][3] = "door2";
		
		currentRoom = 0;
		cursorState = 0;
		mPress = false;
		wPress = false;
		
		mousePosition = new Vector2(0, 0);
		textPos = new Vector2(0,0);
		
		lookingAt = null;
		message = null;
		textTimer = 0;
	}

	@Override
	public void update(float delta, OrthographicCamera camera) {
		player.update(delta, camera);
		System.out.println(player.getPosition().x-camera.position.x);
		if(player.getPosition().x-camera.position.x > 100)
			camera.translate(100*delta, 0);
		if(player.getPosition().x-camera.position.x < -100)
			camera.translate(-100*delta, 0);
		
		
		if(Gdx.input.isKeyPressed(Input.Keys.D))
			camera.translate(delta*300, 0);
		if(Gdx.input.isKeyPressed(Input.Keys.A))
			camera.translate(-delta*300, 0);
		mousePosition.x = Gdx.input.getX()+camera.position.x-400-6;
		mousePosition.y = 600-Gdx.input.getY()+camera.position.y-300-32;
		
		//RESTRAINING CAMERA
		if(camera.position.x<0)
			camera.translate(-camera.position.x,0);
		if(camera.position.x>background[currentRoom].getWidth()-800)
			camera.translate((background[currentRoom].getWidth()-800) - camera.position.x,0);
		
		//CHANGING CURSOR
		int objasize = object_areas[currentRoom].length;
		cursorState = POINT;
		lookingAt = null;
		for(int obj = 0; obj < objasize; obj++){
			if(object_areas[currentRoom][obj].overlaps(new Rectangle(mousePosition.x,mousePosition.y,32,32))){
				double distance = Math.sqrt(Math.pow(object_areas[currentRoom][obj].x - player.getPosition().x,2)+Math.pow(object_areas[currentRoom][obj].y - player.getPosition().y,2));
				String temp = object_names[currentRoom][obj];
				if(distance<=260 || temp.equals("rock")){
					if(temp.equals("rock")||temp.contains("window")||temp.equals("hole")||temp.equals("door2")||temp.equals("lamp")||temp.equals("")){
						cursorState = EXAMINE;
						lookingAt = temp;
					}
				}
			}
		}
		//CLICKING OBJECTS
		if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			mPress = false;
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)&&!mPress){
			if(lookingAt!=null){
				if(lookingAt.equals("rock"))
					message = "A pile of rocks. What happened?";
				if(lookingAt.equals("window1")||lookingAt.equals("window2"))
					message = "Its dark outside... I can see my piece of shit car.";
				if(lookingAt.equals("rock"))
					message = "A pile of rocks. What happened?";
				if(lookingAt.equals("lamp"))
					message = "It is a lamp... Why the fuck did you click it?";
				if(lookingAt.equals("window3"))
					message = "Can't see anything outside, too dark.";
				if(lookingAt.equals("door2"))
					message = "It's locked... The keyhole is shaped like a flower.";
				textTimer = 500;
			}
			mPress = true;
		}
		if(message!=null){
			textPos.x = camera.position.x-380;
			textPos.y = camera.position.y-240;
			textTimer -= delta*150;
			if(textTimer<=0){
				textTimer = 0;
				message = null;
			}
		}
		if(!Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && wPress)
			wPress = false;
		if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && !wPress){
			for(Rectangle area:walking_areas[currentRoom]){
				if(area.overlaps(new Rectangle(mousePosition.x,mousePosition.y,2,2))){
					player.walkTo(mousePosition);
				}
			}
			wPress = true;
		}
	
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(background[currentRoom],-400,-300);
		player.draw(batch);
		if(message!=null)
			fonte.draw(batch,message, textPos.x, textPos.y);
		batch.draw(cursor[cursorState][(mPress)?((cursor[cursorState].length==2)?1:0):0],mousePosition.x,mousePosition.y);
	}


}
