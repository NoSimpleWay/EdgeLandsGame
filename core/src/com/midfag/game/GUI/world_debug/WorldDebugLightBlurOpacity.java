package com.midfag.game.GUI.world_debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.midfag.game.GScreen;

public class WorldDebugLightBlurOpacity extends WorldDebug  {

	boolean release=false;
	
	@Override
	public String get_debug_text()
	{
		return "light_blur_opacity="+GScreen.light_blur_power;
	}
	
	@Override
	public void update(float _d)
	{
		if (
				(!Gdx.input.isKeyPressed(Keys.DOWN))
				&&
				(!Gdx.input.isKeyPressed(Keys.UP))
			)
			{release=true;}
		
		if (Gdx.input.isKeyPressed(Keys.DOWN)&(release))
		{
			GScreen.light_blur_power-=0.05f;
			GScreen.light_blur_power=Math.max(0, GScreen.light_blur_power);
			
			release=false;
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP)&(release))
		{
			GScreen.light_blur_power+=0.05f;
			GScreen.light_blur_power=Math.min(1, GScreen.light_blur_power);
			
			release=false;
		}
		
		if (!release)
		{
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
			GScreen.need_shadow_update=true;
		}

	}
	
}
