package com.midfag.entity.decorations;


import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Phys;

public class DecorSteelBoxDoor extends DecorBuilding {

	public DecorSteelBoxDoor(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();
		
		spr.setTexture(Assets.decoration_steel_box_door);
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		icon=Assets.decoration_steel_box_door_icon;
		
		//Helper.log("ID="+id);
		
		diagonal=false;
		is_decor=true;
		
		spr.setSize(81, 71);
		spr.setOrigin(40, 0);
		
		friction=0.95f;
		
		/*
		constant_move_x=100;
		constant_speed_x=10;*/
		
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
		//Helper.log("WTF "+constant_speed_y);
	}

			
	
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorSteelBoxDoor(new Vector2());
	}
	

	

	

}
