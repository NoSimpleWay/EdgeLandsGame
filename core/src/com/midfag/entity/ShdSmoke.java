package com.midfag.entity;


import com.badlogic.gdx.math.Vector2;

import com.midfag.game.Main;

public class ShdSmoke extends Shd {

	
	public ShdSmoke(Vector2 _s, Vector2 _e)
	{
		super (_s,_e);
		
		start=_s;
		end=_e;
		
		
		
	}
	
	@Override
	public void draw()
	{
		Main.shapeRenderer.setColor(0.2f,0.18f,0.20f,lifetime*lifetime);
		
		Main.shapeRenderer.rectLine(start, end, 1);
	}
	
	
	
}
