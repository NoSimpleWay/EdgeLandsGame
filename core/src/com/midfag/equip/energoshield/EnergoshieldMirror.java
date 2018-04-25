package com.midfag.equip.energoshield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.midfag.game.Assets;

public class EnergoshieldMirror extends Energoshield {
	

	
	public EnergoshieldMirror()
	{
		base_value=400;
		base_regen_speed=10;
		base_reflect=8;
		
		name="Mirror shield";
		
		
		spr=new Sprite(Assets.load("icon_shield_mirror"));
		
		generate();
		update_attributes_bonus();
		
	}
	

}
