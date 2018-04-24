package com.midfag.game.GUI.world_debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.midfag.game.GScreen;

public class WorldDebugShadowMapOpacity extends WorldDebug  {

	boolean release=false;
	
	@Override
	public String get_debug_text()
	{
		return "shadow_map_opacity="+GScreen.sho;
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
			GScreen.sho-=0.01;
			GScreen.sho=Math.max(0, GScreen.sho);
			
			release=false;
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
			GScreen.need_shadow_update=true;
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP)&(release))
		{
			GScreen.sho+=0.01f;
			GScreen.sho=Math.min(1, GScreen.sho);
			
			release=false;
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
			GScreen.need_shadow_update=true;
		}

	}
	
}
