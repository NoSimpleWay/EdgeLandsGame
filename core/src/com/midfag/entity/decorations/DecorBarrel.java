package com.midfag.entity.decorations;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.LightSource;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;

public class DecorBarrel extends Entity {

	public float color_cooldown=0;
	
	public DecorBarrel(Vector2 _v) {
		super(_v);
		
		custom_phys=true;
		
		id=this.getClass().getName();
		//uid="749f99770458c816";
		uid="df238eb5";
		
		spr.setTexture(Assets.barrel);
		icon=Assets.barrel_icon;
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(spr.getTexture().getWidth()/2f, 00f);

		is_AI=false;
		is_decor=true;
		
		armored_shield.value=50;
		armored_shield.total_value=50;
		armored_shield.total_reflect=0;
		armored_shield.total_regen_speed=0;
		
		diagonal=true;
		
		light_source=new LightSource();
		light_source.light_power=1.0f;
		light_source.R=0.01f;
		light_source.G=0.00f;
		light_source.B=1.0f;
		light_source.is_static=true;
		
		path=true;
		path_x=0;
		path_y=0;
		
		//light_source.update_light_position((pos.x), (pos.y));
		//spr.setOrigin(10.0f, 65);
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(float _d)
	{
		
		/*
		color_cooldown+=_d;
		
		//if (color_cooldown<=0)	
		{
			//color_cooldown=1f;
			
			light_source.R=((float) Math.sin(color_cooldown/2f)+1f)/2f;
			light_source.G=((float) Math.sin(color_cooldown/2f)+1f)/2f;
			light_source.B=((float) Math.sin(color_cooldown/2f)+1f)/2f;
			
			GScreen.need_light_update=true;
		}*/
		
	}
	
	@Override
	public void pre_death_action(boolean need_dead_anim) {
		// TODO Auto-generated method stub
		
		GScreen.add_entity_to_map(new DecorBarrelCrash(pos));
		
	}

}
