package com.midfag.entity.decorations;


import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;
import com.midfag.game.Enums.EntityType;

public class DecorPowerLine extends DecorStoneWall {

	public DecorPowerLine(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();
		uid="6f9cdff9";
		type=EntityType.INDUSTRIAL;
		spr.setTexture(Assets.decoration_power_line);
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		icon=Assets.decoration_power_line_icon;
		
		//Helper.log("ID="+id);
		
		diagonal=false;
		
		spr.setSize(150, 300);
		spr.setOrigin(75, 22);
		
		friction=0.95f;
		
		path=true;
		path_x=1;
		path_y=1;
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	

	


	

}
