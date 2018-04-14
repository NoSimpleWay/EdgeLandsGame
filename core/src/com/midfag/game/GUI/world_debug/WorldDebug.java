package com.midfag.game.GUI.world_debug;

import com.midfag.game.GScreen;
import com.midfag.game.Main;

public class WorldDebug {
	
	float debug_text_x=200;
	float debug_text_y=30;
	
	//String default_text="";

	public WorldDebug()
	{
		
	}
	
	public void draw(float _d)
	{
		Main.font.draw(GScreen.batch_static, get_debug_text(), debug_text_x, debug_text_y);
	}
	
	public String get_debug_text()
	{
		return "Hi!";
	}
	
	public void update(float _d)
	{
		
	}
	
}
