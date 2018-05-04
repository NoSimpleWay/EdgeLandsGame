package com.midfag.entity.enemies;




import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;


import com.midfag.equip.weapon.*;


import com.midfag.game.Assets;
import com.midfag.game.GScreen;


public class EntityRaiderTank extends Entity {
	
	//Sprite spr=new Sprite(new Texture(Gdx.files.internal("barrel.png")));
	

	private float rotate_cooldown;
	private float body_rotate_cooldown;
	//private int body_rotate;
	
	public EntityRaiderTank(Vector2 _v)
	{
		super (_v);
		
		spr.setTexture(Assets.pyra_body[0]);
		pos=_v;
		
		id=this.getClass().getName();
		main_tex=Assets.raider_tank;
		
		uid="raider_tank";
		//Helper.log ("THIS ID="+id);
		icon=Assets.entity_pyra_icon;
		
		armored[0]=new WeaponRaiderTank();
		armored[0].generate();
		
		armored[1]=new WeaponRaiderTank();
		armored[1].generate();
		
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
		
		spr.setOrigin(75, 0);
		offset.y=10;
		can_rotate=false;
		
		friction=0.05f;
		speed/=0.2f;
	}
	
	@Override
	public void bottom_draw(float _d)
	{
		GScreen.batch.setColor(1,1,1,.25f);
		GScreen.batch.draw(Assets.shadow, pos.x-75, pos.y-40,150,100);
	}
	
	@Override
	public void draw_action(float _d) {

		

		
		if (!is_enemy)
		{
			spr.setColor(Color.GREEN);
		}
		
		if (rotate_cooldown<=0)
		{
			rotate_cooldown=0.1f;
			
			if ((is_see)&&(target!=null))
			{
				float dst=pos.dst(target.pos);
				
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
			else
			{
				float c=(float) Math.toDegrees(Math.atan2(impulse.x, impulse.y));
				if (c<0){c=360+c;}
		    	
		    	if (c>360){c=c-360;}
		    	//pl.spr.setRotation(360-c);
		    	if (c>347)
				{bottom_draw=0;}
		    	else
		    	{bottom_draw=(int) Math.round(c/22.5);}
			}
			
			
		}
		else
		{
			rotate_cooldown-=_d;
		}
		
		if (body_rotate_cooldown<=0)
		{
			draw_sprite+=1;
			if (draw_sprite>15) {draw_sprite=0;}
			
			body_rotate_cooldown=0.2f;
		}
		else
		{
			body_rotate_cooldown-=_d;
		}
		
		float cold_rating=1.0f-buff_cold/(buff_cold+100.0f);
		
		GScreen.batch.setColor(color_total_R*cold_rating,color_total_G*cold_rating,color_total_B,1f);
		
		int wi=(int)(bottom_draw/6f);
		int he=bottom_draw-wi*6;
		//{GScreen.batch.draw	(main_tex, pos.x-45, pos.y,	   0,	   90,     90,90);}
		
		GScreen.batch.draw	(main_tex, pos.x-75, pos.y-10f, he*150, wi*150, 150, 150);
		
		draw_hp();
		
		

	}
	

	
	
	
	
}
