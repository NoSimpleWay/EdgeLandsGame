package com.midfag.equip.energoshield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class EnergoshieldFast extends Energoshield {
	

	
	public EnergoshieldFast()
	{
		base_value=380;
		base_regen_speed=22;
		base_reflect=3f;
		
		name="Fast shield";
		
		
		spr=new Sprite(new Texture(Gdx.files.internal("icon_shield_fast.png")));
		
		generate();
		update_attributes_bonus();
		
	}
	

}
