package com.midfag.entity.decorations;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.Enums.EntityType;

public class DecorBuildingRoofLong extends DecorBuilding {

	public DecorBuildingRoofLong(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();
		uid="cfd50a7b";
		type=EntityType.BUILDING;
		spr.setTexture(Assets.building_roof_long);
		icon=Assets.building_roof_long_icon;
		
		
		spr.setSize(100, 58);
		spr.setOrigin(50f, 0);
		
		path_x=-1;
		path_y=-1;
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}


}
