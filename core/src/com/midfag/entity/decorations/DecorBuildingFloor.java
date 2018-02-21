package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;

public class DecorBuildingFloor extends DecorBuilding {

	public DecorBuildingFloor(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();

		
		spr.setTexture(Assets.building_floor);
		icon=Assets.building_floor_icon;
		
		
		spr.setSize(100, 10);
		spr.setOrigin(50, 0);
		
		path_x=-1;
		path_y=-1;
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorBuildingFloor(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{

		
	}

}
