package com.midfag.equip.energoshield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnergoshieldStone extends Energoshield {
	

	
	public EnergoshieldStone()
	{
		base_value=650;
		base_regen_speed=8;
		base_reflect=2f;
		
		name="Stone shield";
		
		
		spr=new Sprite(new Texture(Gdx.files.internal("icon_shield_stone.png")));
		
		generate();
		update_attributes_bonus();
		
	}
	

}
