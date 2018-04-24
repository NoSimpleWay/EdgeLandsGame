package com.midfag.entity.decorations;


import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.LightSource;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;
import com.midfag.game.Enums.EntityType;

public class DecorSteelBox extends DecorStoneWall {

	public DecorSteelBox(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();
		uid="e109337d";
		type=EntityType.INDUSTRIAL;
		spr.setTexture(Assets.decoration_steel_box);
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		icon=Assets.decoration_steel_box_icon;
		
		//Helper.log("ID="+id);
		
		diagonal=false;
		
		spr.setSize(40, 40);
		spr.setOrigin(20, 20);
		
		friction=0.95f;
		
		light_source=new LightSource();
		light_source.light_power=3.0f;
		light_source.R=1.0f;
		light_source.G=1.0f;
		light_source.B=1.0f;
		light_source.is_static=true;
		
		collision_size_x=10;
		collision_size_y=10;
		
		path_x=0;
		path_y=0;
		/*
		constant_move_x=100;
		constant_move_y=0;
		
		constant_speed_x=-10;
		constant_speed_y=0;
		*/
		
		path=true;
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
		return new DecorSteelBox(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{

		

		
	}
	

	

}
