package com.midfag.entity.decorations;


import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;

public class DecorStoneWall extends Entity {

	public DecorStoneWall(Vector2 _v) {
		
		super(_v);

		custom_phys=true;
		
		is_AI=false;
		is_player=false;
		
		icon=Assets.decoration_stone_wall_01_icon;
		
		armored_shield.value=10000;
		armored_shield.total_value=10000;
		armored_shield.total_regen_speed=0;
		armored_shield.total_reflect=0;
		
		is_decor=true;
		diagonal=false;
		
		spr.setTexture(Assets.stone_wall_01);
		spr.setSize(spr.getTexture().getWidth(), spr.getTexture().getHeight());
		spr.setOrigin(50.0f, 10f);
		id=this.getClass().getName();
		
		path_offset_x=0f;
		
		path=true;
		path_x=2;
		path_y=0;
		

		
		
		//shield=999999;
		// TODO Auto-generated constructor stub
	}
	

	
	
	
	@Override
	public Entity put() {
		// TODO Auto-generated method stub
		return new DecorStoneWall(new Vector2());
	}
	
	@Override
	public void do_custom_phys()
	{
		int x=(int)(pos.x/300);
		int y=(int)(pos.y/300);
		
		
		Phys p=new Phys(new Vector2(pos.x-60,pos.y-10),new Vector2(pos.x+60,pos.y-10),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x+60,pos.y+30),new Vector2(pos.x+60,pos.y-10),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-60,pos.y+30),new Vector2(pos.x+60,pos.y+30),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
		p=new Phys(new Vector2(pos.x-60,pos.y+30),new Vector2(pos.x-60,pos.y-10),true,this,true);
		GScreen.cluster[x][y].Phys_list.add(p);
		Phys_list_local.add(p);
		
	}
	


}
