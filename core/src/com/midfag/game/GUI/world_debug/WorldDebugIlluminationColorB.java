package com.midfag.game.GUI.world_debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.midfag.game.GScreen;
import com.midfag.game.Localisation;

public class WorldDebugIlluminationColorB extends WorldDebug  {

	boolean release=false;
	
	@Override
	public String get_debug_text()
	{
		return Localisation.get_value_from_id("debug_info_ILLB")+" "+GScreen.global_illumination.b;
	}
	
	@Override
	public void update(float _d)
	{
		if (
				(!Gdx.input.isKeyPressed(Keys.DOWN))
				&&
				(!Gdx.input.isKeyPressed(Keys.UP))
				&&
				(!Gdx.input.isKeyPressed(Keys.LEFT))
				&&
				(!Gdx.input.isKeyPressed(Keys.RIGHT))
			)
			{release=true;}
		
		if (Gdx.input.isKeyPressed(Keys.DOWN)&(release))
		{
			GScreen.global_illumination.b-=0.02f;
			GScreen.global_illumination.b=Math.max(0, GScreen.global_illumination.b);
			
			release=false;
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP)&(release))
		{
			GScreen.global_illumination.b+=0.02f;
			GScreen.global_illumination.b=Math.min(1, GScreen.global_illumination.b);
			
			release=false;
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
		}
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)&(release))
		{
			GScreen.global_illumination.b-=0.20f;
			GScreen.global_illumination.b=Math.max(0, GScreen.global_illumination.b);
			
			release=false;
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
		}
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)&(release))
		{
			GScreen.global_illumination.b+=0.20f;
			GScreen.global_illumination.b=Math.min(1, GScreen.global_illumination.b);
			
			release=false;
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
		}

	}
	
}
