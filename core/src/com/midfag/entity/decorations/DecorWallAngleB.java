package com.midfag.entity.decorations;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.Enums.EntityType;

public class DecorWallAngleB extends DecorBuilding {

	public DecorWallAngleB(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();

		
		spr.setTexture(Assets.wall_angle_B);
		icon=Assets.wall_angle_B;
		uid="2fdb6bec";
		type=EntityType.INDUSTRIAL;
		spr.setSize(9, 43);
		spr.setOrigin(4.5f, 0);
		
		path_x=-1;
		path_y=-1;
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	

	
	@Override
	public void do_custom_phys()
	{

		
	}

}
