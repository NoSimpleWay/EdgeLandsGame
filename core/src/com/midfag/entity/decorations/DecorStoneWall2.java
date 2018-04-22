package com.midfag.entity.decorations;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;
import com.midfag.game.Enums.EntityType;


public class DecorStoneWall2 extends DecorStoneWall {

	public DecorStoneWall2(Vector2 _v) {
		
		super(_v);


		custom_phys=true;
		
		spr.setTexture(Assets.stone_wall_02);
		type=EntityType.WALL;
		icon=Assets.decoration_stone_wall_02_icon;
		uid="d5cf250b";
		
		
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(10.0f, 28f);
		diagonal=true;
		
		path_x=0;
		path_y=1;
		
		path_offset_y=15f;
		
		id=this.getClass().getName();
		
		collision_size_x=10;
		collision_size_y=30;
		mass=999999;
		//spr.setOrigin(10.0f, 65);
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorStoneWall2(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{

		
	}
	

	

}
