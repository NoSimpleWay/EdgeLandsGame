package com.midfag.equip.energoshield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnergoshieldStone extends Energoshield {
	

	
	public EnergoshieldStone()
	{
		base_value=1000;
		base_regen_speed=25;
		base_reflect=5;
		
		name="Stone shield";
		
		level=1;
		
		spr=new Sprite(new Texture(Gdx.files.internal("icon_shield_stone.png")));
		
		generate();
		update_attributes_bonus();
		
	}
	

}
