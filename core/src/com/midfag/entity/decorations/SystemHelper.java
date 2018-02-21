package com.midfag.entity.decorations;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;

public class SystemHelper extends Entity {

	public SystemHelper(Vector2 _v) {
		super(_v);
		
		custom_phys=true;
		
		id=this.getClass().getName();

		
		spr.setTexture(Assets.helper);
		icon=Assets.helper_icon;
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(spr.getTexture().getWidth()/2f, 00f);

		is_AI=false;
		is_decor=true;
		
		armored_shield.value=999999;
		armored_shield.total_value=999999;
		armored_shield.total_regen_speed=999;
		
		diagonal=true;
		//spr.setOrigin(10.0f, 65);
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void do_custom_phys()
	{
		//DO NOTHING
	}
	
	@Override
	public void draw_action(float _d, float _siz) {
		
		if (GScreen.show_edit)
		{spr.setScale(_siz);
		spr.draw(GScreen.batch);}

	}

}
