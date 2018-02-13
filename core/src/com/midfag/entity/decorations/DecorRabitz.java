package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

public class DecorRabitz extends Entity {

	public DecorRabitz(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		is_AI=false;
		is_player=false;
		
		spr.setTexture(Assets.rabitz_01);
		icon=Assets.rabitz_01_icon;
		
		armored_shield.value=10000;
		armored_shield.total_value=10000;
		armored_shield.total_regen_speed=0;
		armored_shield.total_reflect=0;
		
		is_decor=true;
		diagonal=false;
		
		
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(spr.getTexture().getWidth()/2f, 3);
		id=this.getClass().getName();
		
		path=true;
		

		
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	

	
	
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorRabitz(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{
		int x=(int)(pos.x/300);
		int y=(int)(pos.y/300);
		
		
		Phys p=new Phys(new Vector2(pos.x-60,pos.y-10),new Vector2(pos.x+60,pos.y-10),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x+60,pos.y+20),new Vector2(pos.x+60,pos.y-10),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-60,pos.y+20),new Vector2(pos.x+60,pos.y+20),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-60,pos.y+20),new Vector2(pos.x-60,pos.y-10),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
	}
	


}
