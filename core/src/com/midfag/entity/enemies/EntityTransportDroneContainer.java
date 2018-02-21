package com.midfag.entity.enemies;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;


public class EntityTransportDroneContainer extends Entity {
	
	//Sprite spr=new Sprite(new Texture(Gdx.files.internal("barrel.png")));
	

	
	Texture tex=Assets.transport_drone;
	
	
	public static Sound engine=Gdx.audio.newSound(Gdx.files.internal("big_drone_engine.wav"));;
	long engine_id;
	
	public float impulse_z=-200;

	public int z_engine=-20;
	//private int body_rotate;
	

	public EntityTransportDroneContainer(Vector2 _v)
	{
		super (_v);
		//target=GScreen.pl;
		spr.setTexture(Assets.dron_container);
		pos=_v;
		
		//icon=Assets.dron_container_icon;
		
		id=this.getClass().getName();
		
		is_decor=true;
		is_AI=false;
		
		

		armored[0]=null;
		armored[1]=null;
		
		z=407;
		

		spr.setOrigin(spr.getTexture().getWidth()/2f, 0);
		//offset.y=50;
		can_rotate=false;
		
		
		armored_shield.total_value*=5;
		armored_shield.value=armored_shield.total_value;
		
		
		speed=700;
		friction=0.1f;
		
		
	}
	
	
	@Override
	public void sound_init()
	{
		/*engine_id=engine.play(0.01f);
		engine.setLooping(engine_id, true);*/
	}
	
	
	@Override
	public void some_update(float _d)
	{
		/*float dstx=GScreen.camera.position.x-pos.x;
		float dsty=GScreen.camera.position.y-pos.y;
		float dst=(float) Math.sqrt(dstx*dstx+dsty+dsty)-150;
		float volum=Math.min(1, 1f-dst/1500f);
		volum=Math.max(0, volum);
		
		engine.setVolume(engine_id, volum);*/
		
		//Helper.log("volume:"+volum);
		
		
		if (impulse_z<-0)
		{impulse_z*=0.975;}

		float speed_x=(target.pos.x-pos.x);
		speed_x=Math.min(100, speed_x);
		speed_x=Math.max(-100, speed_x);
		
		float speed_y=(target.pos.y-pos.y);
		speed_y=Math.min(100, speed_y);
		speed_y=Math.max(-100, speed_y);
		
		impulse.x+=speed_x*_d;
		impulse.y+=speed_y*_d;
		
		z+=impulse_z*_d;
		
		if ((pos.dst(target.pos)<30)&&(z>0))
		{
			
			z-=_d*20;
			
			if (z<=0)
			{
				z=0;
				
				Entity en=new EntityPyra(new Vector2(pos.x,pos.y+30));
				en.constant_move_x=100;
				en.constant_speed_x=-30;
				GScreen.add_entity_to_map(en);
			}
			
		}
		
	}
	@Override
	public void draw_action(float _d) {

		//GL20.glBlendFunc(GL_ONE, GL_ONE); 02.07.2017 06:07:36

		
		
		
		/*for (int i=0; i<3; i++)
		if (container[i])
		{GScreen.batch.draw(Assets.dron_container, pos.x-581+391*i, pos.y+z-105);}*/
		
		GScreen.batch.draw(Assets.dron_container, pos.x-182, pos.y+z);
		
		
		
	}
	

	
	
	
	
}
