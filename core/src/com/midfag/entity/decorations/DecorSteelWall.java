package com.midfag.entity.decorations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;

public class DecorSteelWall extends Entity {

	public DecorSteelWall(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		id=this.getClass().getName();

		
		spr.setTexture(new Texture(Gdx.files.internal("decor_steel_wall.png")));
		spr.setSize(155, 44);
		spr.setOrigin(155/2, 44/2f);
		
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorSteelWall(new Vector2());
	}

}
