package com.midfag.entity.decorations;


import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Enums.EntityType;

public class DecorSteelBoxDoor extends DecorBuilding {

	public float dir=1;
	public DecorSteelBoxDoor(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();
		uid="ee7e1529";
		type=EntityType.INDUSTRIAL;
		spr.setTexture(Assets.decoration_steel_box_door);
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		icon=Assets.decoration_steel_box_door_icon;
		mass=99999999;
		//Helper.log("ID="+id);
		
		diagonal=false;
		is_decor=true;
		
		spr.setSize(81, 71);
		spr.setOrigin(40, 0);
		
		friction=0.95f;
		//interact_entry_point="#D";
		is_interact=false;
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
	public void default_interact_action(float delta) {
		// TODO Auto-generated method stub
		
		dir*=-1;
		

			if (dir==1) {constant_move_z=60-z;} else {constant_move_z=z;}
			constant_speed_z=30*dir;
		
		
		

	}
	

}
