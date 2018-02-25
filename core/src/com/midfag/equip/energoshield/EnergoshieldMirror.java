package com.midfag.equip.energoshield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnergoshieldMirror extends Energoshield {
	

	
	public EnergoshieldMirror()
	{
		base_value=250;
		base_regen_speed=25;
		base_reflect=20;
		
		name="Mirror shield";
		
		level=1;
		
		spr=new Sprite(new Texture(Gdx.files.internal("icon_shield_mirror.png")));
		
		generate();
		update_attributes_bonus();
		
	}
	

}
