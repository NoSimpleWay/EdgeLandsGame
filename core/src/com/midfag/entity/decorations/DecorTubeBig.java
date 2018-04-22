package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;
import com.midfag.game.Enums.EntityType;

public class DecorTubeBig extends DecorStoneWall {

	public DecorTubeBig(Vector2 _v) {
		
		super(_v);


		custom_phys=true;
		icon=Assets.decoration_tube_big_icon;
		
		spr.setTexture(Assets.decor_tube_big);

		id=this.getClass().getName();
		uid="507840bf";
		type=EntityType.INDUSTRIAL;
		diagonal=false;
		
		spr.setSize(153, 43);
		spr.setOrigin(77, 0f);
		
		path=true;
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorTubeBig(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{

		
	}
	

	

}
