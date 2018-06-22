package com.midfag.game.GUI.world_debug;

import com.badlogic.gdx.graphics.Color;
import com.midfag.game.GScreen;
import com.midfag.game.Main;

public class WorldDebug {
	
	float debug_text_x=400;
	float debug_text_y=30;
	
	//String default_text="";

	public WorldDebug()
	{
		
	}
	
	public void draw(float _d)
	{
		GScreen.draw_shaded_text(get_debug_text(), debug_text_x, debug_text_y,Color.WHITE,450);
	}
	
	public String get_debug_text()
	{
		return "Hi!";
	}
	
	public void update(float _d)
	{
		
	}
	
}
