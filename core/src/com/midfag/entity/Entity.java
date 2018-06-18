package com.midfag.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.midfag.entity.missiles.Missile;
import com.midfag.entity.missiles.MissileExplosion;
import com.midfag.entity.missiles.MissileParticlePiece;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.energoshield.EnergoshieldRobo;
import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.weapon.Weapon;
import com.midfag.equip.weapon.WeaponRobofirle;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;
import com.midfag.game.Phys;
import com.midfag.game.GUI.buttons.Button;
import com.midfag.game.GUI.edit.ButtonSlider;
import com.midfag.game.Enums.EntityType;
import com.midfag.game.script.ScriptSystem;
import com.midfag.game.skills.Skill;

public class Entity {
	
	public float random_mul_x;
	public float random_mul_y;
	//Sprite spr=new Sprite(new Texture(Gdx.files.internal("barrel.png")));
	public float bonus_attack_speed=1f;
	public float bonus_reload_speed=1f;
	
	public Entity near_object=null;
	public Texture main_tex=null;
	
	public boolean have_collision=true;
	public int update_calls=0;
	public String[] update_data=new String[10];
	
	public int cx;
	public int cy;
	public int ncx;
	public int ncy;
	
	public boolean need_change_cluster=false;
	public EntityType type=EntityType.ENTITY;
	public float mass=100;
	
	public Sprite spr=new Sprite(Assets.load("eye"));
	
	public float time_slow_resist=0;
	
	public float path_offset_x=0;
	public float path_offset_y=0;
	
	public Vector2 pos=new Vector2();
	public Vector2 offset=new Vector2();
	
	public float light_update_cooldown=0.2f;
	
	public float color_multiplier_R;
	public float color_multiplier_G;
	public float color_multiplier_B;
	
	public float dynamic_multiplier_R;
	public float dynamic_multiplier_G;
	public float dynamic_multiplier_B;
	
	public float color_total_R=1f;
	public float color_total_G=1f;
	public float color_total_B=1f;
	
	List<List<String>> list = new ArrayList<List<String>>();
	//public List<Entity> entity_list = new ArrayList<Entity>();

	
	//public List<Phys> Phys_list_local = new ArrayList<Phys>();
	public List<Skill> Skills_list = new ArrayList<Skill>();
	public List<AnimationEffect> Effect = new ArrayList<AnimationEffect>();

	public boolean move_vert;
	
	public int direction;
	
	public float stun=0;
	
	public float phys_timer=(float) Math.random();
	
	public boolean is_AI=true;
	public boolean is_enemy=true;
	
	public boolean is_player=false;

	public float hurt_sound_cooldown;
	
	public float dead_time;
	
	public boolean burrow=false;
	
	public float speed=255f;
	
	public float slow;
	
	public boolean can_rotate=true;
	
	public Weapon[] armored=new Weapon[2];
	public Energoshield armored_shield=new EnergoshieldRobo();
	
	public Vector2 impulse=new Vector2();
	
	public long miso=0;
	
	public Object[] inventory=new Object[100];
	
	public boolean custom_phys=false;
	public boolean is_decor=false;
	
	public boolean have_ability=false;
	
	public String id;
	public String uid="entity";
	
	public int order=0;
	
	public boolean diagonal=false;

	public boolean standart_draw=true;
	
	public Texture[] tex=new Texture[16];
	public int draw_sprite=0;
	
	public Texture[] bottom_tex=new Texture[16];
	public int bottom_draw=0;

	public float rot;

	public float friction=0.5f;
	
	public boolean path=false;
	
	public float buff_burn;
	public float buff_cold;
	public float buff_timer=1;
	
	public float look_cooldown=0.5f;
	public boolean is_see=false;

	public int multiply_missile_count=1;
	public ModuleUnit[] armored_module=new ModuleUnit[5];
	public boolean rotate_block;
	public Entity target=null;
	public Texture icon;
	
	public boolean active=true;
	
	public boolean hidden=false;
	
	
	
	public float constant_move_x=0;
	public float constant_move_y=0;
	public float constant_move_z=0;
	
	public float constant_speed_x=0;
	public float constant_speed_y=0;
	public float constant_speed_z=0;

	public String id_for_script="";

	public boolean selected=false;
	public float z;
	
	public boolean is_interact=false;
	public String interact_entry_point="";
	
	public int path_x=-1;
	public int path_y=-1;

	public boolean updatable=true;
	
	public LightSource light_source=null;

	public float collision_size_y=20;

	public float collision_size_x=20;

	public  float size=20;

	public float stuck=0f;
	public boolean need_remove=false;
	public boolean have_module=false;
	
	public void use_module(int _id)
	{
		if ((armored_module[_id]!=null)&&(armored_module[_id].can_use()))
		{
			armored_module[_id].use(this);
		}
	}
	
	public void generate_path()
	{
		int px=(int)((pos.x+path_offset_x)/GScreen.path_cell);
		int py=(int)((pos.y+path_offset_y)/GScreen.path_cell);
		
		if ((z<=30)&&(path_x>=0))
			for (int i=px-path_x; i<=px+path_x; i++)
			for (int j=py-path_y; j<=py+path_y; j++)
			{
				GScreen.path[i][j][0]=-900;
				GScreen.path[i][j][1]=-900;
				/*
				if (GScreen.path[i+1][j]<900){GScreen.path[i+1][j]=700;}
				if (GScreen.path[i-1][j]<900){GScreen.path[i-1][j]=700;}
				
				if (GScreen.path[i][j+1]<900){GScreen.path[i][j+1]=700;}
				if (GScreen.path[i][j-1]<900){GScreen.path[i][j-1]=700;}*/
			}
	}
	
	public void init(String _point)
	{
    	
			sound_init();
			if (light_source!=null) {light_source.update_light_position(pos.x,pos.y);}
			
			
			
			
			

			generate_path();
			
			
			
		
	}
	
	public Entity(Vector2 _v)
	{
		pos=_v;

		if (_v != null)
		{
			//armored[0]=new WeaponRobofirle();
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
		}
	}
	
	
	
	public float iso(float _f)
	{
		//if (!diagonal)
		//{return pos.y;}
		
		//if (!diagonal)
		return (pos.y-pos.x);
		
		//return pos.y+pos.x;
		//return pos.y-pos.x;
	}
	
	
	
	public void effect_draw(float _d)
	{
		for (int i=0; i<Effect.size(); i++)
		{
			Effect.get(i).do_animation(_d);
			
			if (Effect.get(i).frame>=Effect.get(i).max_frame)
			{
				Effect.remove(i);
				i--;
			}
		}
		
		if (is_interact)
		{
			GScreen.batch.setColor(1,1,1,(float) ((Math.sin(TimeUtils.millis()/100))+1)/2f);
			
			
			GScreen.batch.draw(Assets.quest, pos.x-4, pos.y+55);
			
			Main.font_big.draw(GScreen.batch, "!"+z, pos.x, pos.y);
			
			if ((Math.abs(pos.x-GScreen.pl.pos.x)+Math.abs(pos.y-GScreen.pl.pos.y)<120))
			{GScreen.batch.draw(Assets.button_e, GScreen.pl.pos.x-7+20, GScreen.pl.pos.y-7+85,40,40);}

		}
	}
	
	
	public void do_custom_phys()
	{

		
	}

	public void take_damage(float _damage)
	{
		
	}
	
	public void freeze_it(float _value)
	{
		buff_cold+=_value*(1.0f-(buff_cold/(buff_cold+100.0f)));

	}
	
	public void burn_it(float _value) 
	{
		buff_burn+=_value;
	}
	
	public void hit_action(float _damage, boolean _sound)
	{
		
		//stun+=1;
		
		
		
		if (have_ability)
		{
			for (int i=0; i<Skills_list.size(); i++)
			{
				if (Skills_list.get(i).learned)
				{_damage=Skills_list.get(i).damage_action(this,_damage);}
			}
		}
		
		armored_shield.value-=_damage;
		//armored_shield.value-=_damage/2+_damage*1.5f*GScreen.rnd(1);
		
		float warm_protect=1.0f;
		for (int i=0; i<Skills_list.size(); i++)
		{
				if (Skills_list.get(i).learned)
				{warm_protect+=Skills_list.get(i).warm_damage_action(this);}
		}
		
		armored_shield.warm+=(_damage/(armored_shield.total_reflect*0.5f));
		
		armored_shield.warm=Math.min(5, armored_shield.warm);
		
		if ((hurt_sound_cooldown<=0)&(_sound))
		{
			/*
			if (is_AI)
			{Assets.metal_sound.play(0.05f, (float) (Math.random()*0.2f+1.9f), 0);}
			else
			{Assets.plastic.play(0.25f, (float) (Math.random()*0.1f+0.55f), 0);}
			*/
			
			if (!is_AI) {Assets.plastic.play(0.25f, (float) (Math.random()*0.1f+0.55f), 0);}
			hurt_sound_cooldown=0.25f;
		}
		
		if (armored_shield.value<=0)
		{
			pre_death_action(true);
			dead_action(true);
		}
		
		if (!is_decor)
		Effect.add(new AnimationEffectShield(pos,-spr.getOriginX(),-spr.getOriginY()));
		
		
	}
	
	public void pre_death_action(boolean need_dead_anim) {
		// TODO Auto-generated method stub
		
	}





	public void some_draw()
	{

	}
	public void draw()
	{
		//GScreen.batch.begin();
		
		some_draw();
		if ((!burrow)&&(standart_draw))
		{
		
			
				spr.setPosition(pos.x-spr.getOriginX(),pos.y-spr.getOriginY()+z);
				GScreen.Draw_list.add(this);
				//spr.draw(GScreen.batch);
				//Main.font.draw(GScreen.batch, "!"+iso(0), pos.x, pos.y+100);
				//Main.font.draw(GScreen.batch, ""+(int)(armored_shield.value), pos.x, pos.y);
			//GScreen.batch.end();
			/*
			Main.shapeRenderer.begin(ShapeType.Filled);
				Main.shapeRenderer.setColor(1, 1, 1, 0.2f);
				
				if (armored_weapon!=null)
				{
					Main.shapeRenderer.rectLine(pos.x, pos.y,pos.x+GScreen.sinR(360-spr.getRotation()-add_dispersion/2f-armored_weapon.total_dispersion/2f)*200,pos.y+GScreen.cosR(360-spr.getRotation()-add_dispersion/2f-armored_weapon.total_dispersion/2f)*200,0.2f);
					Main.shapeRenderer.rectLine(pos.x, pos.y,pos.x+GScreen.sinR(360-spr.getRotation()+add_dispersion/2+armored_weapon.total_dispersion/2)*200,pos.y+GScreen.cosR(360-spr.getRotation()+add_dispersion/2f+armored_weapon.total_dispersion/2f)*200,0.2f);
				}
				
				
			Main.shapeRenderer.end();*/
			
			
		}
		//else
		//{GScreen.batch.end();}
	}
	
	public void dead_action( boolean need_dead_anim)
	{
		
		//Helper.log("DESTROY <"+id+">");
		need_remove=true;
		/*
		if ((need_dead_anim))
		{
			for (int v=0; v<3; v++)
			{
				GScreen.Missile_list.add(new MissileParticlePiece(new Vector2(pos.x,pos.y),(float) (Math.random()*360),(float)Math.pow(GScreen.rnd(1000),0.5)+100.0f,is_AI));
			}
			
			for (int v=0; v<3; v++)
			{
				GScreen.Missile_list.add(new MissileExplosion(new Vector2(pos.x,pos.y),(float) (Math.random()*360),(float) (10f+Math.random()*80f),is_AI));
			}
			
			need_dead_anim=false;
		}
		*/
		
		//if (!is_player)
		{

			
			//GScreen.cluster[(int)(pos.x/300)][(int)(pos.y/300)].Entity_list.remove(this);
			
			
				//GScreen.Entity_list.remove(this);
				//GScreen.cluster[(int)(pos.x/300f)][(int)(pos.y/300f)].Entity_list.remove(this);
			
		}
		
		//m
		Assets.metal_destroy.play(0.25f, (float) (Math.random()*0.1f+0.95f), 0);
		
		ScriptSystem.Entity_with_id_list.remove(this);

	}
	
	public void hard_move(float _x, float _y, float _d, String _caller)
	{
		do_move(_x,_y,_d,false, _caller);
	}
	
	public void move (float _x, float _y, float _d, String _caller)
	{
		do_move(_x,_y,_d,true, _caller);
	}
	
	public void do_move (float _x, float _y, float _d,boolean _need, String _caller)
	{
		

		//Helper.log("CALLER IS "+_caller);
		cx=(int)(pos.x/300f);
		cy=(int)(pos.y/300f);
		
		int pcx=(int)(pos.x/30f);
		int pcy=(int)(pos.y/30f);
		
		//light_update_cooldown-=_d;
		
		
		
		/*if (light_update_cooldown<=0)
		{
			
			
		}*/
		
		if (stun<=0)
		{
			if (_need)
			{
				_x*=(1-slow)*_d;
				_y*=(1-slow)*_d;

			}
			else
			{
				_x*=_d;
				_y*=_d;

			}
			
				pos.x+=_x;
				pos.y+=_y;
				
				if (pos.x>8900){pos.x=8900;}
				if (pos.x<100){pos.x=100;}
				
				if (pos.y>8900){pos.y=8900;}
				if (pos.y<100){pos.y=100;}
				//spr.setColor((float)Math.random()*0.2f+0.8f,(float)Math.random()*0.2f+0.8f, (float)Math.random()*0.2f+0.8f, 1.0f);
				
				ncx=(int)(pos.x/300f);
				ncy=(int)(pos.y/300f);
				
				int npcx=(int)(pos.x/30f);
				int npcy=(int)(pos.y/30f);
				
				if ((cx!=ncx)||(cy!=ncy))
				{
						Helper.log("PRE CHANGE CLUSTER cx="+cx+" cy="+cy+" ncx="+ncx+" ncy="+ncy);
						need_change_cluster=true;
						
						GScreen.cluster[cx][cy].Entity_list.remove(this);
						GScreen.cluster[ncx][ncy].Entity_list.add(this);
				}
				
				if (
						(pcx!=npcx)||(pcy!=npcy)
						||
						(
							(
									(z+constant_speed_z*_d>=30)
									&&
									(z<31)
							)
						)
						||
						(
							(
									(z+constant_speed_z*_d<=30)
									&&
									(z>29)
							)
						)
					)
				{
					{
						
						if (light_source!=null)
						{
							light_source.update_light_position(pos.x,pos.y);
						}
						else
						{
							update_dynamic_color_state();
							update_color_state();
						}
						
					    if (path_x>=0)
					    {
					    	Helper.log("UPDATE POSITION!");
					    	int cluster_x=(int)(pos.x/300f);
						    int cluster_y=(int)(pos.y/300f);
						    
						    int psx=(int)(pos.x/30.0f);
						    int psy=(int)(pos.y/30.0f);
						    
							for (int i=psy-30; i<psy+30; i++)
							for (int j=psx-30; j<psx+30; j++)
							if ((j>=0)&&(j<300)&&(i>=0)&&(i<300))
							{
								if (GScreen.path[j][i][0]<0){GScreen.path[j][i][0]=300;}
								if (GScreen.path[j][i][1]<0){GScreen.path[j][i][1]=300;}
							}
							
							
							for (int x=cluster_x-4; x<=cluster_x+4; x++)
						    for (int y=cluster_y-4; y<=cluster_y+4; y++)
						    if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
						    for (int i=0; i<GScreen.cluster[x][y].Entity_list.size();i++)
						    {
						        	Entity e=GScreen.cluster[x][y].Entity_list.get(i);
						        	
							    	if ((!e.hidden))
							    	{
							    		e.generate_path();
							    	}
						    }
							
							GScreen.need_shadow_update=true;
							
							GScreen.need_light_update=true;
							GScreen.need_static_light_update=true;
							GScreen.need_pixmap_update=true;
							

							//if (light_source!=null){GScreen.need_light_update=true; GScreen.need_dynamic_light_update=true;}
					    }
					    
						if (light_source!=null)
						{
							GScreen.need_light_update=true;
							GScreen.need_static_light_update=true;
							GScreen.need_pixmap_update=true;
						}

					    
					   
					    //if ((light_source!=null)&&(!light_source.is_static)){GScreen.need_dynamic_light_update=true;}
					    

					    
						
						
					}
				}
				

		}
		
		//if (need_change_cluster)
		//{Helper.log("PRE PHANGE CLUSTER[2] cx="+cx+" cy="+cy+" ncx="+ncx+" ncy="+ncy);}
	}
	
	public void reposition(float _x, float _y)
	{
		int tcx=(int)(pos.x/300f);
		int tcy=(int)(pos.y/300f);
		
		int tncx=(int)(_x/300f);
		int tncy=(int)(_y/300f);
		
		if (
				((tcx!=tncx)||(tcy!=tncy))
				&&
				(tncx>=0)
				&&
				(tncx<30)
				&&
				(tncy>=0)
				&&
				(tncy<30)
				&&
				(tcx>=0)
				&&
				(tcx<30)
				&&
				(tcy>=0)
				&&
				(tcy<30)
			)
		{
				{GScreen.cluster[tcx][tcy].Entity_list.remove(this);
				GScreen.cluster[tncx][tncy].Entity_list.add(this);}	
		}
		
		pos.x=_x;
		pos.y=_y;
		
		{
			if (light_source!=null)
			{
				light_source.update_light_position(pos.x,pos.y);
			}
			else
			{
				//update_dynamic_color_state();
				update_color_state();
			}
			
		    //if (path_x>=0)
		    //{
		    	int cluster_x=(int)(_x/300f);
			    int cluster_y=(int)(_y/300f);
			    
			   
				
				
				if (path_x>=0)
				{
					
						int psx=(int)(_x/30.0f);
					    int psy=(int)(_y/30.0f);
					    
						for (int i=psy-30; i<psy+30; i++)
						for (int j=psx-30; j<psx+30; j++)
						if ((j>=0)&&(j<300)&&(i>=0)&&(i<300))
						{
							if (GScreen.path[j][i][0]<0){GScreen.path[j][i][0]=100;}
							if (GScreen.path[j][i][1]<0){GScreen.path[j][i][1]=100;}
						}
						
				for (int x=cluster_x-4; x<=cluster_x+4; x++)
			    for (int y=cluster_y-4; y<=cluster_y+4; y++)
			    if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
			    for (int i=0; i<GScreen.cluster[x][y].Entity_list.size();i++)
			    {
			        	Entity e=GScreen.cluster[x][y].Entity_list.get(i);
			        	
				    	if ((!e.hidden))
				    	{
				    		e.generate_path();
				    	}
			    }
				GScreen.need_shadow_update=true;
				}
				
				GScreen.need_light_update=true;
				
				if ((light_source!=null)&&(light_source.is_static)){ GScreen.need_static_light_update=true; GScreen.need_pixmap_update=true; }
				if (light_source!=null){GScreen.need_light_update=true; GScreen.need_dynamic_light_update=true;}
		    //}
		    //if ((light_source!=null)&&(!light_source.is_static)){GScreen.need_dynamic_light_update=true;}
		}
	}
	
	public void change_cluster()
	{
		GScreen.cluster[cx][cy].Entity_list.remove(this);
		GScreen.cluster[ncx][ncy].Entity_list.add(this);
		
		need_change_cluster=false;
		Helper.log("CLUSTER CHANGED! "+cx+" "+cy+"| "+ncx+" "+ncy);
	}
	
	public void standart_slider()
	{

		
		
	}
	
	public void shoot(float _d, int _i)
	{
		
		//For
		multiply_missile_count=1;
		
		for (int i=0; i<Skills_list.size(); i++)
		{
			if (Skills_list.get(i).learned)
			{Skills_list.get(i).prefire_action(this);}
		}
		
		if (armored[_i].reload_timer<=0)
		{
			if ((armored[_i].need_warm>0)&&(armored[_i].warm==0)&&(armored[_i].reload_timer<=0))
			{
				if (miso==0)
				 {
					miso = Assets.minigun.play(0.1f,0.1f,0);
					Assets.minigun.setLooping(miso, true);
				 }
			}
		
			armored[_i].warm+=_d;
			armored[_i].warm=Math.min(armored[_i].warm, armored[_i].need_warm);
		}
		else
		{
			armored[_i].warm-=_d;
			if (armored[_i].warm<0){armored[_i].warm=0;}
		}
		
		
		if (
				(armored[_i]!=null)
				&&
				(armored[_i].cd<=0)
				&&
				(
					(
						(target!=null)
						&&
						(pos.dst(target.pos)<9000)
					)
					||
					(is_player)
				)
				&&
				(armored[_i].reload_timer<=0)
				&&
				(
						!(
								(armored[_i].need_warm!=0)
								&&
								(armored[_i].warm/armored[_i].need_warm<0.2f))
						)
						
				)
		{
			//System.out.println("try shoot");
			//assert armored_weapon!=null;
			if (armored[_i].need_warm<=0)
			{armored[_i].cd=armored[_i].total_shoot_cooldown;}
			else
			{
				{armored[_i].cd=armored[_i].total_shoot_cooldown/(armored[_i].warm/armored[_i].need_warm*1.0f);}
			}
			
			
			
			//System.out.println("TotMisCou: "+armored_weapon.total_missile_count);
			for (int zz=0; zz<armored[_i].total_missile_count*multiply_missile_count; zz++)
			{
				Missile mis=armored[_i].get_missile(this);
				
				GScreen.Missile_list.add(mis);
				
				mis.damage=armored[_i].total_damage;
				mis.fire_damage=armored[_i].total_fire_damage;
				mis.cold_damage=armored[_i].total_cold_damage;
				mis.master=this;
				mis.master_weapon=armored[_i];
				
				//mis.sp
				
			}
			
			armored[_i].add_disp+=armored[_i].total_dispersion_additional;
			
			if (is_AI)
			{
				if (pos.dst(GScreen.pl.pos)<800)
				{armored[_i].get_shoot_sound().play((1f-pos.dst(GScreen.pl.pos)/800.0f)*0.15f);}
			}
			else
			{
				{armored[_i].get_shoot_sound().play(0.15f);}
			}
		
			
		
			
			armored[_i].ammo--;
			if (armored[_i].ammo<=0)
			{
				armored[_i].reload_timer=armored[_i].total_reload_time;
				
				for (int i=0; i<Skills_list.size(); i++)
				{
						if (Skills_list.get(i).learned)
						{Skills_list.get(i).weapon_start_reload_action(this,_i);}
				}

			}
			/*
			if (is_player)
			{Assets.shoot01.play(0.75f, (float) (0.5f), 0);}
			
			if (!is_player)
			{
				if (pos.dst(GScreen.pl.pos)<300)
				{Assets.shoot00.play((1f-pos.dst(GScreen.pl.pos)/300.0f)*0.1f);}
				//Assets.shoot00.play(0.05f, (float) (Math.random()*0.2f+1.5f), 0);
			}
			*/
			
			
			
			
		}
	}
	public void some_update(float _d)
	{
		
	}
	
	public boolean can_see(Entity _e)
	{

		float dx=_e.pos.x-pos.x;
		float dy=_e.pos.y-pos.y;
		float spd=(float) Math.sqrt(dx*dx+dy*dy);
		
		Entity e=GScreen.get_collision(pos.x,pos.y,_e.pos.x,_e.pos.y,(pos.x-_e.pos.x)/(pos.y-_e.pos.y),(pos.y-_e.pos.y)/(pos.x-_e.pos.x),1);
		
		if ((e!=null)&&(e.is_enemy!=is_enemy))
		{GScreen.enemy_see_player_timer=3f; return true;}
		
		return false;
	}
	
	public void update(float _d)
	{
			
			update_calls++;

			if (stuck>0)
			{stuck-=_d;}
		
		rotate_block=false;
		
		float cold_rating=1-buff_cold/(buff_cold+100);
		
		if(have_module)
		for (int i=0; i<5; i++)
		{
			
			if (armored_module[i]!=null)
				{armored_module[i].update(this, _d);}
		}
		
		for (int i=0; i<2; i++)
		{
			
			if (armored[i]!=null)
				{armored[i].update(this, _d);}
		}
		if (armored_shield!=null)
		{
			armored_shield.update(_d);
		}
		some_update(_d);
		
		/*
		if (
				((is_see)&&(!is_player))
			)
		{
			float a=GScreen.pl.pos.x-pos.x;
	    	float b=GScreen.pl.pos.y-pos.y;
	    	//float c=(float) Math.sqrt((a*a)+(b*b));
	    	float c=(float) Math.toDegrees(Math.atan2(a, b));
	    	rot=180-c+180;
		}*/
    	//spr.setRotation(180-c);
    	
		if (armored_shield!=null)
    	for (int i=0; i<=armored_shield.Attribute_list.size()-1; i++)
		{
			armored_shield.Attribute_list.get(i).update(_d, this);
		}
		
		for (int j=0; j<2; j++)
		{

			
			if (armored[j]!=null)
			for (int i=0; i<=armored[j].Attribute_list.size()-1; i++)
			{
				armored[j].Attribute_list.get(i).update(_d, this,armored[j]);
			}
		}
		
		
		
		for (int i=0; i<2; i++)
		{
			//if (armored[i]!=null)
			//if (miso>0)
			//{Assets.minigun.setPitch(miso, armored[i].warm/armored[i].need_warm);}
			


			
			if (armored[i]!=null)
			if (armored[i].reload_timer>0)
			{
				armored[i].reload_timer-=_d*cold_rating*bonus_reload_speed;
				
				if (armored[i].reload_timer<=0)
				{
					armored[i].ammo=(int) armored[i].total_ammo_size;
				}
			}
		}
		
		slow=Math.min(0.5f, slow);
		slow*=0.99f;
		
		
		for (int i=0; i<2; i++)
		{
			if (armored[i]!=null)
			{
				armored[i].add_disp*=Math.pow(0.05f,_d);
			
				
				armored[i].cd-=_d*cold_rating*bonus_attack_speed;
			}
		}
		
		if ((armored_shield!=null)&&(armored_shield.value>0))
		{
			if (armored_shield.warm<=0)
			{armored_shield.value+=armored_shield.total_regen_speed*_d;}
			//{armored_shield.value+=armored_shield.total_regen_speed*_d*(1f-armored_shield.warm/50f);}
			
			// if (this instanceof EntityHuman) {Helper.log("WARM!");}
			armored_shield.warm-=_d;
			armored_shield.warm=Math.max(0, armored_shield.warm);
			armored_shield.value=Math.min(armored_shield.total_value, armored_shield.value);
		}
		//удыу
		else
		{
			dead_time+=_d;
		}
		
		hurt_sound_cooldown-=_d;
		
		
		
		/*if (Math.abs(impulse.x)<2){impulse.x=0;}
		if (Math.abs(impulse.y)<2){impulse.y=0;}*/
		
		//if (Math.abs(impulse.x)<0.5f) {impulse.x=0;}
		//if (Math.abs(impulse.y)<0.5f) {impulse.y=0;}
		
		float mx=impulse.x*cold_rating;
		float my=impulse.y*cold_rating;
		
		/*
		if (mx>0){mx+=20;} else if (mx<0){mx-=20;}
		if (my>0){my+=20;} else if(my<0){my-=20;}*/
		
		near_object=null;
		//float spd=(float) (Math.sqrt(mx*mx+my*my));
		
		
		if ((mx!=0)||(my!=0))
		for (int i=0; i<GScreen.Missile_list.size(); i++)
		{
			Missile mis=GScreen.Missile_list.get(i);
			if (is_enemy!=mis.is_enemy)
			{
				float dx=0;
				float dy=0;
				if (Math.abs(my)<0.1f) {dx=99999;}else{dx=mx/my;}
				if (Math.abs(mx)<0.1f) {dy=99999;}else{dy=my/mx;}
				
				///GScreen.temp_vector_collision_result.set(99999,99999);
				
				GScreen.temp_vector_collision_result.set(GScreen.collision_missile(mis,pos.x,pos.y,pos.x+mx*_d,pos.y+my*_d,dx,dy,size));
				
				if (GScreen.temp_vector_collision_result.x<9999)
				{
					
					GScreen.missile_collision_action(mis, this);
				}
			}
		}
		
		if (((is_player)&&(GScreen.show_edit))||(z>50))
		{}
		else
		{
			float dx=0;
			float dy=0;
			if (Math.abs(my)<0.1f) {dx=99999;}else{dx=mx/my;}
			if (Math.abs(mx)<0.1f) {dy=99999;}else{dy=my/mx;}
			
			near_object=GScreen.get_collision(pos.x,pos.y,pos.x+mx*_d,pos.y+my*_d,dx,dy,size);
		}
		

		if (constant_move_z>0)
		{
			constant_move_z-=Math.abs(constant_speed_z*_d);
			z+=constant_speed_z*_d;
		}
		else
		{
			constant_speed_z=0;
		}
		
		if (near_object==null)
		{
			float cmx=0;
			float cmy=0;
			
			if (constant_move_x>0)
			{
				constant_move_x-=Math.abs(constant_speed_x*_d);
				cmx=constant_speed_x;
			}
			else
			{
				constant_speed_x=0;
			}
			
			
			if (constant_move_y>0)
			{
				constant_move_y-=Math.abs(constant_speed_y*_d);
				cmy=constant_speed_y;
			}
			else
			{
				constant_speed_y=0;
			}
			
			
			
			move (mx+cmx,my+cmy,_d,"ENTITY UPDATE");
			
			//hit_action(99999);
			
		}
		else
		{

			//impulse.scl(Math.max(1f/(near_object.mass/mass+1f),0.9f));
			stuck=0.1f;
			random_mul_x=Math.round((float) (Math.random()*2f-1f));
			random_mul_y=Math.round((float) (Math.random()*2f-1f));
			
			//near_object.add_impulse(impulse.x, impulse.y, 0.5f/(near_object.mass/mass+1f));
			float additive_impulse_x=(impulse.x-near_object.impulse.x);
			float additive_impulse_y=(impulse.y-near_object.impulse.y);
			//-20-0=-20
			
			if (Math.abs(impulse.x)>Math.abs(near_object.impulse.x))
			{
				near_object.impulse.x+=additive_impulse_x*mass/(mass+near_object.mass);
				impulse.x-=additive_impulse_x*(1f-mass/(mass+near_object.mass));
			}	
			
			if (Math.abs(impulse.y)>Math.abs(near_object.impulse.y))
			{
				near_object.impulse.y+=additive_impulse_y*mass/(mass+near_object.mass);
				impulse.y-=additive_impulse_y*(1f-mass/(mass+near_object.mass));
			}
		}

		look_cooldown-=_d;
		
		impulse.scl((float) Math.pow(friction, _d));
		
		if (is_AI)
		{
			
			
			if (look_cooldown<=0)
			{

				
				look_cooldown=1.5f;
				
				is_see=false;
				if ((is_enemy)){target=GScreen.pl;}
				if (target!=null){is_see=can_see(target);}
				
				if (
						(target!=null)
						&&
						(
								(
										(target.armored_shield!=null)&&(target.armored_shield.value<=0)
								)
								||
								(!is_see)
						)
					)
				{target=null;}
				//if {}
				
				if (target==null)

				{
					List<Entity> l=GScreen.get_entity_list(pos);
					
					for (int zzz=0; zzz<l.size(); zzz++)
						{
						//System.out.println("check "+i);
							if ((l.get(zzz).is_enemy!=is_enemy)&&(!l.get(zzz).is_decor)&&(can_see(l.get(zzz))))
							{target=l.get(zzz); System.out.println("IS_ENEMY "+(l.get(zzz).is_enemy)); zzz=999; is_see=true;   break;}
						}
				}
			}
			
	    	boolean go_shoot=true;
	    	if ((armored==null)||(!is_see))
	    	{go_shoot=false;}

	    	if ((target!=null)&&(target.armored_shield.value>0))
	    	{
		    	if (go_shoot)
		    	{
		    		if (armored[0]!=null)
		    		{shoot(_d,0);}
		    		
		    		if (armored[1]!=null)
		    		{shoot(_d,1);}
		    	}
	    	}
		}
		
		if (is_player)
		{
			if ((InputHandler.but==0)&&(armored_shield!=null)&&(armored_shield.value>0)&&(GScreen.main_control))
			{
	    		if (armored[0]!=null)
	    		{shoot(_d,0);}
	    		
	    		if (armored[1]!=null)
	    		{shoot(_d,1);}
			}
			else
			{
				for (int i=0; i<2; i++)
				{
					if (armored[i]!=null)
					{armored[i].warm-=_d;
					if (armored[i].warm<0){armored[i].warm=0;}}
				}
			}
		}
		
		buff_timer-=_d;
		
		if ((buff_timer<=0))
		{
			buff_timer+=1;
			
			if(buff_burn>0.1f)
			{hit_action(buff_burn,false);}
			
			buff_burn*=0.9f;
			buff_cold*=0.9f;

		}
		
		if ((armored_shield!=null)&&(armored_shield.value<=0))
		{
			need_remove=true;
		}
		
		post_update(_d);
	}

	public void add_impulse(float _x, float _y, float _d) {
		// TODO Auto-generated method stub
		impulse.x+=_x*_d;//\
		impulse.y+=_y*_d;
	}



	public void draw_hp()
	{
		GScreen.batch.setColor(Color.DARK_GRAY);
		GScreen.batch.draw(GScreen.rect_white, pos.x-15, pos.y-40, 30,10);
		
		GScreen.batch.setColor(Color.GREEN);
		GScreen.batch.draw(GScreen.rect_white, pos.x-15, pos.y-40, 30f*armored_shield.value/armored_shield.total_value,10);
		GScreen.batch.setColor(Color.WHITE);
	}
	
	public void update_color_state()
	{
		color_multiplier_R=0;
    	color_multiplier_G=0;
    	color_multiplier_B=0;
    	
    	int summ=0;
    	long pxm=0;
    	
		
    	if (GScreen.pixmap!=null)
    	{
	    	for (int a=1; a<=2; a++)
	    	for (int b=-2; b<=2; b++)
	    	{
	    		//pxm=GScreen.illumination_fbo.getColorBufferTexture().pix
	    		pxm=GScreen.pixmap.getPixel((int)(pos.x/30)+b, (int)(pos.y/30)-a);
	    		
	    		color_multiplier_R+=((pxm >> 24) & 0xFF)/2550f;
	    		color_multiplier_G+=((pxm >> 16) & 0xFF)/2550f;
	    		color_multiplier_B+=((pxm >> 8) & 0xFF)/2550f;
	    		
		    	
	    		/*color_multiplier_R+=GScreen.light_mask_R[(int)(pos.x/30)-b][(int)(pos.y/30f)-a]/15f;
	    		color_multiplier_G+=GScreen.light_mask_G[(int)(pos.x/30)-b][(int)(pos.y/30f)-a]/15f;
	    		color_multiplier_B+=GScreen.light_mask_B[(int)(pos.x/30)-b][(int)(pos.y/30f)-a]/15f;
	    		*/
	    		//color_multiplier_R+=GScreen.illumination_fbo.getColorBufferTexture().;
	    		
	    	}
	    	
	    	/*dynamic_multiplier_R=1f/(1f+GScreen.path[(int)(pos.x/30)][(int)(pos.y/30-1)]/5f);
			dynamic_multiplier_G=dynamic_multiplier_R;
			dynamic_multiplier_B=dynamic_multiplier_R;*/
			
	    	color_total_R=color_multiplier_R+dynamic_multiplier_R; if (color_total_R>1) {color_total_R=1;}
	    	color_total_G=color_multiplier_G+dynamic_multiplier_G; if (color_total_G>1) {color_total_G=1;}
	    	color_total_B=color_multiplier_B+dynamic_multiplier_B; if (color_total_B>1) {color_total_B=1;}	
	    		/*
	    		float red = ((pxm >> 24) & 0xFF)/255f;
	    		float green = ((pxm >>16 ) & 0xFF)/255f;
	    		float blue = ((pxm>>8) & 0xFF)/255f;
						Helper.log(">> "+red+" >> "+green+" >> "+blue);
	    	
	    		*/
						/*
	    	color_multiplier_R=Math.min(1, color_multiplier_R);
	    	color_multiplier_G=Math.min(1, color_multiplier_G);
	    	color_multiplier_B=Math.min(1, color_multiplier_B);*/
    	}
    	
    	
	}

	public void draw_action(float _d)
	{

		
		draw_action(_d, 1f);
		//Main.font.draw(GScreen.batch, ""+constant_move_y, pos.x, pos.y);
	}
	
	public float get_cold_rating()
	{
		return 1-buff_cold/(buff_cold+100);
	}
	
	public void draw_action(float _d, float _siz) {

		
		if (!is_decor)
		{draw_hp();}

		Color temp_color=spr.getColor();

		if (light_source==null)
		{spr.setColor(color_total_R,color_total_G,color_total_B,1f);}
		else
		{spr.setColor(1,1,1,1f);}
		
		//if (selected) {spr.setColor(0f, 1f, 0f, 0.9f);}
		
		spr.setScale(_siz);
		spr.draw(GScreen.batch);

		GScreen.batch.setColor(Color.WHITE);
	}
	
	
	public void draw_offset_mask(float _d) {
		

		float mask_multiplier=(pos.y-GScreen.camera.position.y+350f)/700f;
		
		//
		
		/*
		 * small	=95/512=0.18
		 * big		=249/512=0.48
		 * 
		 */
		
		spr.setColor(mask_multiplier,1,1,1);
		spr.draw(GScreen.batch);
		//spr.setColor(1,1,1,1);

		GScreen.batch.setColor(Color.WHITE);
	}
	
	public void fill_path()
	{
		/*
		if (path)
		for (int i=-0; i<=5; i++)
		for (int j=-5; j<=4; j++)
		{
			GScreen.path[Math.round(pos.x/30f)+j][Math.round(pos.y/30f)+i]=900;
		}*/
	}

	public void sound_init() {
		// TODO Auto-generated method stub
		
	}

	public void update_dynamic_color_state() {
		// TODO Auto-generated method stub
		/*dynamic_multiplier_R=1f/(1f+GScreen.path[(int)(pos.x/30)][(int)(pos.y/30-1)]/5f);
		dynamic_multiplier_G=dynamic_multiplier_R;
		dynamic_multiplier_B=dynamic_multiplier_R;*/
		
		
    	color_total_R=color_multiplier_R+dynamic_multiplier_R; if (color_total_R>1) {color_total_R=1;} 
    	color_total_G=color_multiplier_G+dynamic_multiplier_G; if (color_total_G>1) {color_total_G=1;}
    	color_total_B=color_multiplier_B+dynamic_multiplier_B; if (color_total_B>1) {color_total_B=1;}	
		
	}

	public void default_interact_action(float delta) {
		// TODO Auto-generated method stub
		
	}

	public void bottom_draw(float _d) {
		// TODO Auto-generated method stub
		
	}

	public void post_update(float _d) {
		// TODO Auto-generated method stub
		
	}
	



	
}
