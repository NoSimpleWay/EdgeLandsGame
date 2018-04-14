package com.midfag.game.GUI.world_debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.midfag.game.GScreen;

public class WorldDebugIlluminationPower extends WorldDebug  {

	@Override
	public String get_debug_text()
	{
		return "illumination_blur_power="+GScreen.lightmap_spread_power;
	}
	
	@Override
	public void update(float _d)
	{
		if (Gdx.input.isKeyPressed(Keys.DOWN))
		{
			GScreen.lightmap_spread_power-=_d/10f;
			
			GScreen.lightmap_spread_power=Math.max(0, GScreen.lightmap_spread_power);
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP))
		{
			GScreen.lightmap_spread_power+=_d/10f;
			
			GScreen.lightmap_spread_power=Math.min(1, GScreen.lightmap_spread_power);
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
		}
		

	}
	
}
