package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

public class DecorTubeCystern extends DecorStoneWall {

	public DecorTubeCystern(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		
		spr.setTexture(Assets.decor_tube_cystern);

		id=this.getClass().getName();
		
		diagonal=false;
		
		spr.setSize(38, 81);
		spr.setOrigin(16f, 0f);
		
		path=true;
		//spr.setOrigin(80.0f, 10f);
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorTubeCystern(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{

		
	}
	
	public void fill_path()
	{
		if (path)
		for (int i=0; i<1; i++)
		for (int j=0; j<1; j++)
		{
			GScreen.path[Math.round(pos.x/30f)+j][Math.round(pos.y/30f)+i]=900;
		}
	}
	

}
