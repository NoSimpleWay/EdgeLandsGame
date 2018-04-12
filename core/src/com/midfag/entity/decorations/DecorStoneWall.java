package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

public class DecorStoneWall extends Entity {

	public DecorStoneWall(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		is_AI=false;
		is_player=false;
		
		icon=Assets.decoration_stone_wall_01_icon;
		
		armored_shield.value=10000;
		armored_shield.total_value=10000;
		armored_shield.total_regen_speed=0;
		armored_shield.total_reflect=0;
		
		is_decor=true;
		diagonal=false;
		
		spr.setTexture(Assets.stone_wall_01);
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(spr.getTexture().getWidth()/2.0f, 10f);
		id=this.getClass().getName();
		
		path_offset_x=0f;
		
		path=true;
		path_x=1;
		path_y=0;
		mass=999999;
		
		collision_size_x=45;
		collision_size_y=10;
		
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	

	
	
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorStoneWall(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{

		
	}
	


}
