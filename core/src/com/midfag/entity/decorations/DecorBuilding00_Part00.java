package com.midfag.entity.decorations;


import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

public class DecorBuilding00_Part00 extends Entity {

	public DecorBuilding00_Part00(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();
		uid="28fe494f";
		
		spr.setTexture(Assets.decor_building_00_part_00);
		
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		icon=Assets.decor_building_00_part_00_icon;
		
		//Helper.log("ID="+id);
		
		diagonal=false;
		
		is_AI=false;
		is_player=false;
		is_enemy=false;
		is_decor=true;
		
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(40, 0);
		
		friction=0.95f;
		
		/*
		constant_move_x=100;
		constant_move_y=0;
		
		constant_speed_x=-10;
		constant_speed_y=0;
		*/
		
		path=true;
		path_x=1;
		path_y=1;
		
		path_offset_y=30;
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	public void some_update(float _d)
	{
		//add_impulse(-30f, 0, _d);
	}

			
	
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorBuilding00_Part00(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{

		
	}
	

	

}
