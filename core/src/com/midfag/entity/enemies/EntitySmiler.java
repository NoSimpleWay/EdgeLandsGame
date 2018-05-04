package com.midfag.entity.enemies;




import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;


import com.midfag.equip.weapon.*;


import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;


public class EntitySmiler extends Entity {
	
	//Sprite spr=new Sprite(new Texture(Gdx.files.internal("barrel.png")));
	

	private float rotate_cooldown;
	private float body_rotate_cooldown;
	private float animation_timer=0;
	public int animation=0;
	
	public float push_cooldown=1f;
	public float push_time=0f;
	
	public Entity catch_target=null;
	
	public float catch_x;
	public float catch_y;
	private float damage_cooldown;
	
	//private int body_rotate;
	
	public EntitySmiler(Vector2 _v)
	{
		super (_v);
		
		//spr.setTexture(Assets.pyra_body[0]);
		main_tex=Assets.smiler;
		pos=_v;
		
		id=this.getClass().getName();
		uid="entsmiler";
		//Helper.log ("THIS ID="+id);
		icon=Assets.entity_pyra_icon;
		
		/*armored[0]=new WeaponRobofirle();
		armored[0].generate();
		armored[1]=null;*/
		armored[0]=null;
		armored[1]=null;
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
		
		spr.setOrigin(45, 1);
		offset.y=10;
		can_rotate=false;
		
		friction=0.07f;
		speed*=10f;
		
		mass=50;
	}
	
	@Override
	public void draw_action(float _d) {

		

		
		
		
		if ((rotate_cooldown<=0))
		{
			rotate_cooldown=0.1f;
			
			if ((is_see))
			{
				float dst=pos.dst(target.pos);
				
				
				
				if ((catch_target==null))
				{
					float a=target.pos.x+target.impulse.x*dst/750f-(pos.x);
			    	float b=target.pos.y+target.impulse.y*dst/750f-(pos.y)-offset.y;
			    	//float c=(float) Math.sqrt((a*a)+(b*b));
			    	float c=(float) Math.toDegrees(Math.atan2(a, b));
			    	rot=180-c+180;
				
					//if ((impulse.x>0)&&(bottom_draw>=0)&&(bottom_draw<=3)){bottom_draw++;}
					if (c<0){c=360+c;}
			    	
			    	if (c>360){c=c-360;}
			    	//pl.spr.setRotation(360-c);
			    	if (c>347)
					{bottom_draw=0;}
			    	else
			    	{bottom_draw=(int) Math.round(c/22.5);}
		    	}
			}
			
			
		}
		else
		{
			rotate_cooldown-=_d;
		}
		
		
		float cold_rating=1.0f-buff_cold/(buff_cold+100.0f);
		
		GScreen.batch.setColor(color_total_R*cold_rating,color_total_G*cold_rating,color_total_B,1f);
		
		//spr.setSize(90, 90);
		
		if (catch_target==null)
		{GScreen.batch.draw(main_tex,pos.x-45,pos.y,1440-90-bottom_draw*90,90*animation,90,90);}
		else
		{GScreen.batch.draw(main_tex,(float) (pos.x-45+Math.random()*8f-4f),(float)(pos.y+Math.random()*8f-4f),1440-90-bottom_draw*90,90*animation,90,90);}
		//spr.draw(GScreen.batch);
		
		draw_hp();
		
		

	}
	
	@Override
	public void post_update (float _d)
	{
		//;
		if (catch_target!=null)
		{
			
			
			//mass=1;
			animation_timer+=_d;
			
			
			if(animation_timer>0.1f) {animation_timer=0; animation++; if (animation>4) {animation=3;}}
		
			
			//hard_move(catch_target.impulse.x,catch_target.impulse.y,_d, "");
			impulse.x=0;
			impulse.y=0;
			
			float dx=0;
			float dy=0;
			
			float mx=catch_target.impulse.x;
			float my=catch_target.impulse.y;
			
			if (Math.abs(my)<0.1f) {dx=99999;}else{dx=mx/my;}
			if (Math.abs(mx)<0.1f) {dy=99999;}else{dy=my/mx;}
			
			
			//near_object=null;
			//near_object=GScreen.get_collision(pos.x+catch_x,pos.y+catch_y,pos.x+mx*_d+catch_x,pos.y+my*_d+catch_y,dx,dy,size);
			
			//if ((near_object==null)||(near_object!=catch_target))
			//{
				reposition (catch_target.pos.x+catch_x,catch_target.pos.y+catch_y);
				pos.x=catch_target.pos.x+catch_x;
				pos.y=catch_target.pos.y+catch_y;
				
				Assets.smiler_saw.resume();
				
			//}
			//else
			//{
			//	catch_target=null;
			//	Assets.smiler_saw.pause();
			//	mass=50;
			//}
			
			
			
			if (damage_cooldown>0)
			{
				damage_cooldown-=_d;
				if (damage_cooldown<=0)
				{
					if (catch_target!=null)
					{catch_target.hit_action(10f, false);}
					damage_cooldown=0.2f;
					
					//Helper.log("try damage");
				}
			}
			
			if ((catch_target!=null)&&(catch_target.need_remove)) {catch_target=null;}
			//can_rotate=false;

		}
		else
		{
			animation_timer-=_d;
			if(animation_timer<-0.1f) {animation_timer=0; animation--; if (animation<0) {animation=0;}}
		}
		
		if (!is_enemy)
		{
			spr.setColor(Color.GREEN);
		}
		
		
		if ((push_cooldown>0)&&(catch_target==null)&&(target!=null))
		{
			push_cooldown-=_d;
			if (push_cooldown<=0)
			{
				push_cooldown=5f;
				push_time=1.0f;
				rotate_cooldown=push_time;
			}
					
		}
		

		
		if (push_time>0)
		{
			push_time-=_d;
			float sx=1500f*GScreen.sinR(360-rot);
			float sy=1500f*GScreen.cosR(360-rot);
			impulse.set(sx,sy);
			
			if (near_object!=null)
			{
				if ((near_object.is_enemy!=is_enemy)&&(!near_object.is_decor))
				{
					catch_target=near_object;
					
					
					sx=30f*GScreen.sinR(360f-rot);
					sy=30f*GScreen.cosR(360f-rot);
					catch_x=-sx;
					catch_y=-sy;
					
					
					Assets.smiler_saw_play=true;
					Assets.smiler_saw.play();
					
				}
				
				Helper.log("CATCH:"+near_object);
				
				push_time=0;
				rotate_cooldown=0;
				damage_cooldown=0.2f;
				
				Assets.crash.play(0.2f);
			}
		}
	}
	
	
	@Override
	public void pre_death_action(boolean need_dead_anim) {
		// TODO Auto-generated method stub
		Assets.smiler_saw.pause();
		
	}

	
	
	
	
}
