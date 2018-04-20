package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

public class DecorStoneBarak extends Entity {

	public DecorStoneBarak(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();
		uid="7cc48571";
		
		spr.setTexture(Assets.stone_barak);
		//id="stone_barak";
		icon=Assets.decoration_stone_barak_icon;
		diagonal=false;
		
		spr.setSize(300, 300);
		spr.setOrigin(150f, 0);
		//spr.setAlpha(0.5f);
		
		is_AI=false;
		is_player=false;
		
		armored_shield.value=10000;
		armored_shield.total_value=10000;
		armored_shield.total_regen_speed=0;
		armored_shield.total_reflect=0;
		
		is_decor=true;
		diagonal=false;
		
		
		
		path=true;

		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub

		return new DecorStoneBarak(new Vector2());
		
	}
	
	@Override
	public void do_custom_phys()
	{

		
	}
	

}
