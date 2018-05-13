package com.midfag.entity.enemies;




import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;


import com.midfag.equip.weapon.*;


import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Main;


public class EntitySpawnTower extends Entity {
	
	//Sprite spr=new Sprite(new Texture(Gdx.files.internal("barrel.png")));
	

	private float rotate_cooldown;
	private float body_rotate_cooldown;
	private float spawn_cooldown=1f;
	
	private int bomb_count=0;
	private float bomb_timer=0.5f;
	//private int body_rotate;
	
	public EntitySpawnTower(Vector2 _v)
	{
		super (_v);
		
		main_tex=Assets.spawn_tower;
		
		pos=_v;
		
		id=this.getClass().getName();
		uid="spwntwr";
		//Helper.log ("THIS ID="+id);
		icon=Assets.entity_pyra_icon;
		
		//armored[0]=new WeaponRobofirle();
		//armored[0].generate();
		
		armored[0]=null;
		armored[1]=null;
		
		armored_shield.total_value=15000;
		armored_shield.value=15000;
		
		if (armored[0]!=null)
		{
			armored[0].cd=(float) (Math.random()*1);
			armored[0].ammo=(int) armored[0].total_ammo_size;
		}
		
		if (armored[1]!=null)
		{
			armored[1].cd=(float) (Math.random()*1);
			armored[1].ammo=(int) armored[1].total_ammo_size;
		}	
		
		collision_size_x=120;
		collision_size_y=80;
		size=100;
		
		spr.setOrigin(50, 0);
		offset.y=10;
		can_rotate=false;
		
		mass=10000;
		friction=0.01f;
		speed=0.0f;
	}
	
	@Override
	public void some_update(float _d)
	{
		if (spawn_cooldown>0) {spawn_cooldown-=_d;}
		
		if ((GScreen.enemy_see_player)&&(spawn_cooldown<=0))
		{
			spawn_cooldown=20;
			
			for (int sp=0; sp<8; sp++)
			{
				float px=100f*GScreen.sinR(sp*80);
				float py=100f*GScreen.cosR(sp*80);
				
				Random rn=new Random();
				
				switch (rn.nextInt(3))
	        	{
	        		case 0: GScreen.add_entity_to_map(new EntityPyra(new Vector2(pos.x+px, pos.y+py)));	break;
	        		case 1: GScreen.add_entity_to_map(new EntitySmiler(new Vector2(pos.x+px, pos.y+py)));	break;
	        		case 2: GScreen.add_entity_to_map(new EntityRaiderTank(new Vector2(pos.x+px, pos.y+py)));	break;
	        	}
				//GScreen.add_entity_to_map(new EntityPyra(new Vector2(pos.x+px, pos.y+py)));
				
			}
			
			bomb_count=8;
			

			Gdx.audio.newSound(Gdx.files.internal("data/robo.wav")).play(1f);
			
			
		}
		
		if (bomb_count>0)
		{
			bomb_timer-=_d;
			if (bomb_timer<=0)
			{
				bomb_timer=0.5f;
				bomb_count--;
				GScreen.add_entity_to_map(new EntityMine(new Vector2((float) (GScreen.pl.pos.x+Math.random()*200f-400f), (float) (GScreen.pl.pos.y+Math.random()*200f-400f))));
			}
				
		}
	}
	
	@Override
	public void bottom_draw (float _d)
	{
		float cold_rating=1.0f-buff_cold/(buff_cold+100.0f);
		GScreen.batch.setColor(color_total_R*cold_rating,color_total_G*cold_rating,color_total_B,1f);
		GScreen.batch.draw(Assets.spawn_tower_bottom, pos.x-150, pos.y-80);
	}
	
	@Override
	public void draw_action(float _d) {




		float cold_rating=1.0f-buff_cold/(buff_cold+100.0f);
		GScreen.batch.setColor(color_total_R*cold_rating,color_total_G*cold_rating,color_total_B,1f);
		GScreen.batch.draw(main_tex, pos.x-150, pos.y-80);
		//GScreen.batch.setColor(Color.WHITE);
		//GScreen.batch.draw(Assets.spawn_tower_light, pos.x-150, pos.y-80);
		  

		
		draw_hp();
		
		

	}
	

	
	
	
	
}
