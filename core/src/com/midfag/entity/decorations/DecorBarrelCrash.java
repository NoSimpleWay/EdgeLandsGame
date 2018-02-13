package com.midfag.entity.decorations;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;

public class DecorBarrelCrash extends Entity {

	public DecorBarrelCrash(Vector2 _v) {
		super(_v);
		
		custom_phys=true;
		
		id=this.getClass().getName();

		
		spr.setTexture(Assets.barrel_crash);
		icon=Assets.barrel_crash_icon;
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(spr.getTexture().getWidth()/2f, 00f);

		is_AI=false;
		is_decor=true;
		
		armored_shield.value=500000;
		armored_shield.total_value=500000;
		armored_shield.total_reflect=0;
		armored_shield.total_regen_speed=0;
		
		diagonal=true;
		//spr.setOrigin(10.0f, 65);
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
		
		// TODO Auto-generated constructor stub
	}

}
