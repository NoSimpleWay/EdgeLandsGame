package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;
import com.midfag.game.Enums.EntityType;

public class DecorStonePilon extends DecorStoneWall {

	public DecorStonePilon(Vector2 _v) {
		
		super(_v);


		custom_phys=true;
		uid="80d2e292";
		type=EntityType.WALL;
		spr.setTexture(Assets.stone_pilon_01);
		icon=Assets.decoration_stone_pilon_icon;
		id=this.getClass().getName();
		
		diagonal=false;
		
		spr.setSize(30, 84);
		spr.setOrigin(16f, 0f);
		
		path=true;
		path_x=0;
		path_y=0;
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	

	
	@Override
	public void do_custom_phys()
	{

		
	}
	

	

}
