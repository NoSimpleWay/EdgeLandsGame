package com.midfag.game.GUI.world_debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.midfag.game.GScreen;

public class WorldDebugIlluminationColorG extends WorldDebug  {

	boolean release=false;
	
	@Override
	public String get_debug_text()
	{
		return "illumination_R="+GScreen.global_illumination.g;
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
			GScreen.global_illumination.g-=0.1f;
			GScreen.global_illumination.g=Math.max(0, GScreen.global_illumination.g);
			
			release=false;
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP)&(release))
		{
			GScreen.global_illumination.g+=0.1f;
			GScreen.global_illumination.g=Math.min(1, GScreen.global_illumination.g);
			
			release=false;
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
		}

	}
	
}
