package com.midfag.entity.decorations;


import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;
import com.midfag.game.Enums.EntityType;

public class DecorTree extends DecorStoneWall {

	public DecorTree(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();
		uid="512c5d2e";
		type=EntityType.PLANTS;
		spr.setTexture(Assets.decoration_tree);
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		icon=Assets.decoration_tree_icon;
		
		//Helper.log("ID="+id);
		
		diagonal=false;
		
		spr.setSize(76,83);
		spr.setOrigin(38, 7);
		
		friction=0.95f;
		
		path=true;
		path_x=0;
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
		return new DecorTree(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{

		
	}
	

	

}
