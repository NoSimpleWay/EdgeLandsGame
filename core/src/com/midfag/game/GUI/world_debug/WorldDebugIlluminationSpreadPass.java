package com.midfag.game.GUI.world_debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.midfag.game.GScreen;
import com.midfag.game.Localisation;

public class WorldDebugIlluminationSpreadPass extends WorldDebug  {

	boolean release=false;
	
	@Override
	public String get_debug_text()
	{
		return Localisation.get_value_from_id("debug_info_SP")+" "+GScreen.lightmap_spread_pass;
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
			GScreen.lightmap_spread_pass-=1;
			GScreen.lightmap_spread_pass=Math.max(0, GScreen.lightmap_spread_pass);
			
			release=false;
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP)&(release))
		{
			GScreen.lightmap_spread_pass+=1f;
			GScreen.lightmap_spread_pass=Math.min(1024, GScreen.lightmap_spread_pass);
			
			release=false;
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
		}

	}
	
}
