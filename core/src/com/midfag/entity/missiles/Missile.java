package com.midfag.entity.missiles;


import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.Shd;

import com.midfag.entity.ShdSmoke;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;



public class Missile {
	public Vector2 pos=new Vector2();
	public float angle;
	public float speed;
	Sprite spr=new Sprite(Assets.missile);
	
	Sprite spr_anim[]=new Sprite[10];
	
	public float lifetime;
	
	
	public GScreen gs;
	
	public Vector2 vector_len=new Vector2();
	
	
	public float next_x;
	public float next_y;
	
	public float start_x;
	public float start_y;
	
	public float sx;
	public float sy;
	
	public float px;
	public float py;
	
	public Color col=Color.WHITE;
	public boolean is_decor=false;
	public float damage;
	
	public int random_anim;
	
	public boolean is_enemy;
	public boolean have_shd=true;
	public float shd_lifetime=1f;
	public float fire_damage;
	public float cold_damage;
	
	public Entity master;
	
	
	
	public Missile(Vector2 _v,float _a, float _s, boolean _b)
	{

		lifetime=(float)(Math.random()*0.2f)+1.5f;
		pos=_v;
		angle=_a;
		speed=_s;
		
		is_enemy=_b;
		
		update_vectors_state();
		
		
	}
	public Missile()
	{
		
	}
	
	
	public void update(float _d)
	{
		preupdate(_d);
		
		if (lifetime>0)
		{
			px=pos.x;
			py=pos.y;
			pos.add(sx*speed*_d,sy*speed*_d);
		}
		/*
		next_x=pos.x+speed_x*speed*_d;
		next_y=pos.y+speed_y*speed*_d;
		*/
		lifetime-=_d;
		
		shd_lifetime-=_d;
		
		

	}
	
	public void preupdate(float _d)
	{
		
	}
	
	public void update_vectors_state()
	{
		sx=(float)Math.sin(angle);
		sy=(float)Math.cos(angle);
		
		start_x=pos.x;
		start_y=pos.y;
	}
	
	public void check()
	{
		if (this.lifetime<0)
		{
			//destr();
			//lifetime=0;
		}
	}
	
	public void draw()
	{
		
	
			//GScreen.batch.setColor(col);
			//for (float i=0; i<1; i+=0.1f)
			//{
			//spr.setScale(0.1f, 0.1f);
			
			if (random_anim<=0)
			{
				spr.setColor(col);
				spr.setPosition(pos.x-spr.getWidth()/2,pos.y-spr.getHeight()/2);
				spr.setRotation(360-angle/3.14f*180f);
				spr.draw(GScreen.batch);
			}
			else
			{
				int r=(int)(Math.random()*3);
				spr_anim[r].setColor(col);
				spr_anim[r].setPosition(pos.x-spr.getWidth()/2,pos.y-spr.getHeight()/2);
				spr_anim[r].setRotation(360-angle/3.14f*180f);
				spr_anim[r].draw(GScreen.batch);
			}
			
			/*
			spr.setPosition(pos.x-spr.getWidth()/2,pos.y-spr.getHeight()/2);
			spr.setColor(Color.BROWN);
			
			spr.setPosition(pos.x-spr.getWidth()/2+dynx*speed*0.05f,pos.y-spr.getHeight()/2+dyny*speed*0.05f);
			
			spr.draw(GScreen.batch);
			*/
			//GScreen.batch.draw(spr, pos.x, pos.y);
			//}
			//GScreen.batch.draw(tex,pos.x,pos.y);
			//Main.font.draw(GScreen.batch, ""+lifetime, pos.x, pos.y);
			//GScreen.batch.setColor(Color.GREEN);
			//GScreen.batch.draw(tex,pos.x+(float)Math.sin((angle))*10,pos.y+(float)Math.cos((angle))*10);
	}
	
	@SuppressWarnings("static-access")
	public void destr()
	{
		//System.out.println("REMOVED"+lifetime);
		gs.Missile_list.remove(this);
	}
	

	public Shd get_shd(Vector2 _s,Vector2 _e)
	{
			
			Shd s=new ShdSmoke(_s,_e);
			s.lifetime=0.35f;
			
			return s;
		
	}
	public void draw_shd(float delta) {
		// TODO Auto-generated method stub
		GScreen.sr.setColor(0.2f,0.1f,0.02f,shd_lifetime/2f);
		
		GScreen.sr.rectLine(start_x, start_y, pos.x, pos.y,2);
		
	}
	
	public void hit_action(Entity near_entity) {
		// TODO Auto-generated method stub
		if ((master!=null)&&(master.have_ability))
		for (int i=0; i<master.Skills_list.size(); i++)
		{
			if (master.Skills_list.get(i).learned)
			{master.Skills_list.get(i).missile_hit_action(master, near_entity, this);}
		}
		lifetime=-0.1f;
	}
	
	public void another_hit_action(Entity near_entity) {
		// TODO Auto-generated method stub
		
	}
}
