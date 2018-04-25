package com.midfag.game.screen_effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.midfag.equip.module.ModuleUnit;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Main;

public class ScreenEffectEXPLOSIONS extends ScreenEffect {
	
	boolean played=false;
	public Texture exp0=Assets.load("explosion0");
	public Texture exp1=Assets.load("explosion1");
	public Texture exp2=Assets.load("explosion2");
	
	public Texture big_exp[]=new Texture[11];
	
	
	
	public float progress;
	public float boom_animation;
	
	public ScreenEffectEXPLOSIONS()
	{
		sound_effect=Gdx.audio.newSound(Gdx.files.internal("data/EXPLOSIONS.wav"));
		sound_effect.play();
		
		String path="";
		for (int i=0; i<=10; i++)
		{
			if (i<10) {path="0";}else {path="";}
			
			big_exp[i]=Assets.load("effect_explosion2/explosion00"+path+i);
		}
		//GScreen.batch_static.setShader(Main.shader_time_slow);
		//Assets.music.setVolume(0.05f);
		
		//shader=Main.shader_time_slow;
	}
	
	@Override
	public void update(float _d,float _real_d)
	{
		//Helper.log(""+progress);
		progress+=_real_d;
		
		if ((progress<0.5f))
		{
			GScreen.batch_static.draw(exp0, (GScreen.scr_w-631)/2f,  (GScreen.scr_h-209)/2f);
		}
		
		if ((progress>=0.5f)&&(progress<1.0f))
		{
			GScreen.batch_static.draw(exp1, (GScreen.scr_w-631)/2f,  (GScreen.scr_h-209)/2f);
		}
		
		if (progress>=1.0f)
		{
			GScreen.batch_static.draw(exp2, (GScreen.scr_w-631)/2f,  (GScreen.scr_h-209)/2f);
		}
		
		if (progress>=1.5f)
		{
			for (int i=0; i<10; i++)
			{
				GScreen.batch_static.draw(exp2, (float) (Math.random()*GScreen.scr_w),  (float) (Math.random()*GScreen.scr_h),(float) (Math.random()*120+120),(float) (Math.random()*50+50));
			}
		}

		if ((progress>=1.5f)&&(!played)) {played=true; Gdx.audio.newSound(Gdx.files.internal("data/bomb_explosion_1.wav")).play();}
		
		if ((progress>=1.5f))
		{
			boom_animation+=_real_d*15;
			if (boom_animation>10) {boom_animation=10;}
			GScreen.batch_static.draw(big_exp[(int) boom_animation],0,0,GScreen.scr_w,GScreen.scr_w);
			
			GScreen.batch_static.draw(big_exp[(int) boom_animation],0,0,100,GScreen.scr_w);
			GScreen.batch_static.draw(big_exp[(int) boom_animation],GScreen.scr_w-50,0,100,GScreen.scr_h);
			
		}
		if (progress>=2.0f)
		{
		
			GScreen.screen_effect=null;
		}
	}
	
	@Override
	public void end_action() {
		// TODO Auto-generated method stub
		
	}
	
}
