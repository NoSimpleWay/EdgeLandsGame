package com.midfag.entity.enemies;




import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;


import com.midfag.equip.weapon.*;


import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Main;


public class EntityMine extends Entity {
	
	//Sprite spr=new Sprite(new Texture(Gdx.files.internal("barrel.png")));
	

	private int fall_anim=0;
	private float fall_timer=0.05f;
	
	private float rotate_cooldown;
	public int explosion_anim=0;
	public float explosion_timer=0.1f;
	private boolean triggered=false;
	
	//private int body_rotate;
	
	public EntityMine(Vector2 _v)
	{
		super (_v);
		
		if (_v!=null) {Assets.fall_hit.play();}
		
		spr.setTexture(Assets.pyra_body[0]);
		pos=_v;
		
		have_collision=false;
		
		id=this.getClass().getName();
		main_tex=Assets.mine;
		
		uid="enemy_mine";
		//Helper.log ("THIS ID="+id);
		icon=Assets.entity_pyra_icon;
		
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
		
		spr.setOrigin(75, 0);
		offset.y=10;
		can_rotate=false;
		
		friction=0.05f;
		speed=0.0f;
	}
	
	@Override
	public void bottom_draw(float _d)
	{
		GScreen.batch.setColor(1,1,1,.25f);
		GScreen.batch.draw(Assets.shadow, pos.x-25.0f, pos.y-25.0f,50,50);
	}
	
	@Override
	public void draw_action(float _d) {


		
		float cold_rating=1.0f-buff_cold/(buff_cold+100.0f);
		
		if (fall_anim>3)
		{
		GScreen.batch.setColor(color_total_R*cold_rating,color_total_G*cold_rating,color_total_B,1f);
		GScreen.batch.draw	(main_tex, pos.x-40, pos.y-50, 80*explosion_anim, 0, 80, 100);
		}
		else
		{
			GScreen.batch.setColor(Color.WHITE);
			GScreen.batch.draw	(Assets.fall, pos.x-8, pos.y, 16*fall_anim, 0, 16, 512);
		}
		
		draw_hp();
		
		/*
		if((target!=null)&&(can_see(target))) 
		{
			
			Main.font_big.draw(GScreen.batch, ""+pos.dst(target.pos), pos.x, pos.y);
		}
		*/
		
	}
	
	@Override
	public void some_update(float _d)
	{
		
		if (fall_anim<=3)
		{
			fall_timer-=_d;
			
			if (fall_timer<=0)
			{
				fall_timer=0.05f;
				fall_anim++;
				
				
			}
		}
		if ((fall_anim>3)&&(target!=null)&&(can_see(target)))
		{
			float dst=pos.dst(target.pos);
			if ((dst<120)&&(!triggered))
			{
				Assets.expl.play();
				triggered=true;
			}
			
			if (triggered)
			{
				explosion_timer-=_d;
				
				if (explosion_timer<=0)
				{
					explosion_timer=0.1f;
					explosion_anim++;
					
					if (explosion_anim>2)
					{
						
						target.hit_action(100, true);
						target.burn_it(10);
						need_remove=true;
					}
				}
			}
		}
		
	}
	

	
	
	
	
}
