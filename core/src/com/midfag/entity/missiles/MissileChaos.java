package com.midfag.entity.missiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


public class MissileChaos extends Missile {
	
	public MissileChaos(Vector2 _v,float _a, float _s, boolean _b)
	{
		super(_v, _a, _s, _b);
		
		spr.setTexture(new Texture(Gdx.files.internal("missile_chaos.png")));
	}
	
	@Override
	public void preupdate(float _d)
	{
		//GScreen.tile_map_overlay[(int)(pos.x/30)][(int)(pos.y/30)]=9+(int)(Math.random()*3);
	}

}
