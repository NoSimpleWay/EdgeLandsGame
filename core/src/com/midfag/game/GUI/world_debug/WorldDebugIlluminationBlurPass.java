package com.midfag.game.GUI.world_debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.midfag.game.GScreen;

public class WorldDebugIlluminationBlurPass extends WorldDebug  {

	boolean release=false;
	
	@Override
	public String get_debug_text()
	{
		return "illumination_blur_pass="+GScreen.lightmap_blur_pass;
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
			GScreen.lightmap_blur_pass-=1;
			GScreen.lightmap_blur_pass=Math.max(0, GScreen.lightmap_blur_pass);
			
			release=false;
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP)&(release))
		{
			GScreen.lightmap_blur_pass+=1f;
			GScreen.lightmap_blur_pass=Math.min(1024, GScreen.lightmap_blur_pass);
			
			release=false;
			
			GScreen.need_light_update=true;
			GScreen.need_static_light_update=true;
			GScreen.need_pixmap_update=true;
		}
		
		
	}
	
}
