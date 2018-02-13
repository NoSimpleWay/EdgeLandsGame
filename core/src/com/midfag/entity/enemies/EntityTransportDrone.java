package com.midfag.entity.enemies;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.AnimationEffectExpl;
import com.midfag.entity.Entity;
import com.midfag.entity.Shd;
import com.midfag.entity.ShdFire;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Phys;

import com.midfag.game.Main;


public class EntityTransportDrone extends Entity {
	
	//Sprite spr=new Sprite(new Texture(Gdx.files.internal("barrel.png")));
	

	
	Texture tex=Assets.transport_drone;
	
	public boolean container[]={true, true, true};
	
	public static Sound engine=Gdx.audio.newSound(Gdx.files.internal("big_drone_engine.wav"));;
	long engine_id;
	
	public float timer=1f;

	//private int body_rotate;
	
	public void drop_container(int _num)
	{
		container[_num]=false;
		
		Entity en=new EntityTransportDroneContainer(new Vector2(pos.x-399,pos.y+200));
		GScreen.add_entity_to_map(en);
		en.target=target;
	}
	
	public EntityTransportDrone(Vector2 _v)
	{
		super (_v);
		target=GScreen.pl;
		spr.setTexture(Assets.transport_drone);
		pos=_v;
		
		icon=Assets.transport_drone_icon;
		
		id=this.getClass().getName();
		
		is_decor=true;
		is_AI=false;
		
		

		armored[0]=null;
		armored[1]=null;
		
		z=712;
		

		spr.setOrigin(spr.getTexture().getWidth()/2f, 0);
		//offset.y=50;
		can_rotate=false;
		
		
		armored_shield.total_value*=5;
		armored_shield.value=armored_shield.total_value;
		
		
		speed=500;
		
		/*
		constant_move_x=1000;
		constant_speed_x=60;*/
	}
	
	
	@Override
	public void sound_init()
	{
		engine_id=engine.play(0.01f);
		engine.setLooping(engine_id, true);
	}
	
	
	@Override
	public void some_update(float _d)
	{
		float dstx=GScreen.camera.position.x-pos.x;
		float dsty=GScreen.camera.position.y-pos.y;
		float dst=(float) Math.sqrt(dstx*dstx+dsty+dsty)-150;
		float volum=Math.min(1, 1f-dst/3000f);
		volum=Math.max(0, volum);
		
		engine.setVolume(engine_id, volum);
		
		
		
		if (timer>0)
		{
			timer-=_d;
			if (timer<=0)
			{
				timer=0;
		//}
				drop_container(0);
			}
		}

		//Helper.log("volume:"+volum);
	}
	@Override
	public void draw_action(float _d) {

		//GL20.glBlendFunc(GL_ONE, GL_ONE); 02.07.2017 06:07:36

		
		GScreen.batch.draw(Assets.transport_drone_shadow, pos.x-581, pos.y);
		for (int i=0; i<3; i++)
		if (container[i])
		{GScreen.batch.draw(Assets.dron_container, pos.x-581+391*i, pos.y+z-105);}
		
		GScreen.batch.draw(Assets.transport_drone, pos.x-581, pos.y+z);
		
		
		
	}
	

	
	
	
	
}
