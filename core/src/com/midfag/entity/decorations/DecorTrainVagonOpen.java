package com.midfag.entity.decorations;


import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;
import com.midfag.game.Enums.EntityType;

public class DecorTrainVagonOpen extends DecorStoneWall {

	public DecorTrainVagonOpen(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();
		uid="e664d426";
		type=EntityType.VEHICLE;
		spr.setTexture(Assets.decoration_train_vagon_open);
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		icon=Assets.decoration_train_vagon_open_icon;
		
		//Helper.log("ID="+id);
		
		diagonal=false;
		
		spr.setSize(200, 200);
		spr.setOrigin(100, 75);
		
		path=true;
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	

			
	
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorTrainVagonOpen(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{

		
	}
	

	

}
