package com.midfag.entity.decorations;


import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;
import com.midfag.game.Enums.EntityType;

public class DecorTrain extends Entity {

	public DecorTrain(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();
		uid="18b24a7f";
		type=EntityType.VEHICLE;
		spr.setTexture(Assets.decoration_train);
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		icon=Assets.decoration_train_icon;
		
		//Helper.log("ID="+id);
		
		diagonal=false;
		
		is_AI=false;
		is_player=false;
		is_enemy=false;
		is_decor=true;
		
		spr.setSize(200, 200);
		spr.setOrigin(100, 75);
		
		friction=0.95f;
		
		/*
		constant_move_x=100;
		constant_move_y=0;
		
		constant_speed_x=-10;
		constant_speed_y=0;
		*/
		
		path=true;
		path_x=2;
		path_y=0;
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
		return new DecorTrain(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{

		
	}
	

	

}
