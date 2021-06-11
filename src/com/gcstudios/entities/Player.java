package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.World;


public class Player extends Entity{
	
	public boolean isPressed = false;
	private BufferedImage sprites[];
	private int frames = 0, maxFrames = 10, index = 0, maxIndex = 1;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		sprites = new BufferedImage[2];
		sprites[0] = Game.spritesheet.getSprite(0, 0, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(16, 0, 16, 16);
	}
	
	public void tick(){
		depth = 2;
		if(!isPressed) {
			y+=2;
		}else {
			y-=2;
		}
		
		if(y > Game.HEIGHT || y < 0) {
			World.restartGame();
		}
	
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Tube)
			{
				if(Entity.isColidding(this, e)) {
					World.restartGame();
				}
			}
		}
		frames++;
		if(frames>maxFrames) {
			frames = 0;
			index++;
			if(index>maxIndex) {
				index = 0;
			}
		}
		
	}

	public void render(Graphics g) {
		g.drawImage(sprites[index], this.getX(), this.getY(), null);
	
	}
}
