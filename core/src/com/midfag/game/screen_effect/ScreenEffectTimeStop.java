package com.midfag.game.screen_effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.midfag.equip.module.ModuleUnit;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Main;

public class ScreenEffectTimeStop extends ScreenEffect {
	
	boolean played=false;
	
	public ScreenEffectTimeStop()
	{
		sound_effect=Gdx.audio.newSound(Gdx.files.internal("time_stop.wav"));
		sound_effect.play();
		GScreen.batch_static.setShader(Main.shader_time_slow);
		Assets.music.setVolume(0.05f);
		
		shader=Main.shader_time_slow;
	}
	
	@Override
	public void update(float _d,float _real_d)
	{
		if (MasterModule.duration>0)
		{
			GScreen.time_speed=0.01f;
			
			if (MasterModule.total_duration-MasterModule.duration<0.5f)
			{
				GScreen.time_speed=0.5f-(MasterModule.total_duration-MasterModule.duration);
			}
			
			if (MasterModule.duration<0.5f)
			{
				GScreen.time_speed=0.5f-MasterModule.duration;
			}
			//ScreenEffectTimeStop.java
			
			GScreen.batch_static.setShader(Main.shader_time_slow);
		}
		
		if ((MasterModule.duration<1.5f)&(!played))
		{
			played=true;
			Gdx.audio.newSound(Gdx.files.internal("time_stop_reverse.wav")).play();
		}
	}
	
	@Override
	public void end_action() {
		// TODO Auto-generated method stub
		GScreen.time_speed=1.0f;
		sound_effect.stop();
		
		Assets.music.setVolume(0.5f);
		
		shader=GScreen.batch.getShader();
		GScreen.batch_static.setShader(Main.shader_bloom);
	}
	
}
