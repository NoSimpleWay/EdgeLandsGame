package com.midfag.game.GUI.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.Assets;
import com.midfag.game.Enums.Rarity;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;

public class ButtonEquip extends Button {

	public Object obj;
	public float mov;
	
	public float info_x=90;
	public float info_y=670;
	
	public int inventory_id;
	
	public ButtonEquip(float _x, float _y, int _id)
	{
		super (_x,_y);
		mov=0;
		
		spr.setTexture(Assets.load("button_bg_equip"));
		
		
		inventory_id=_id;
		
		update_object();
		
		
	}
	
	public void update_object()
	{
		
		//info_y=GScreen.scr_h-25;
		
		if (inventory_id>=0)
		{
			obj=null;
			if (GScreen.pl.inventory[inventory_id] instanceof Weapon){obj=(Weapon)GScreen.pl.inventory[inventory_id];}
			if (GScreen.pl.inventory[inventory_id] instanceof Energoshield){obj=(Energoshield)GScreen.pl.inventory[inventory_id];}
			if (GScreen.pl.inventory[inventory_id] instanceof ModuleUnit){obj=(ModuleUnit)GScreen.pl.inventory[inventory_id];}
		}
		
		if (inventory_id==-1)
		{
			obj=(Weapon)GScreen.pl.armored[0];
			
		}
		
		if (inventory_id==-2)
		{
			obj=(Weapon)GScreen.pl.armored[1];
			
		}
				
		if (inventory_id==-5)
		{
			obj=(Energoshield)GScreen.pl.armored_shield;
		}
		
		if ((inventory_id<=-10)&&(inventory_id>-15))
		{
			obj=(ModuleUnit)GScreen.pl.armored_module[Math.abs(inventory_id)-10];
		}
		
		
		
		if (inventory_id==99)
		{
			pos.x=InputHandler.realx+20;
			pos.y=InputHandler.realy+20;
			
			off_bg=true;
			
		}
		
	}
	
	@Override
	public void entry()
	{
		update_object();
	}
	
	public void color_it(float _a, float _b)
	{
		if (Math.abs(_a-_b)>0.01)
		{
		if (_a==_b)
		{Main.font.setColor(0.5f, 0.6f, 0.7f, 1);}
		else
		if (_a>_b)	
		{Main.font.setColor(0.45f, 1.0f, 0.55f, 1);}
		else	
		{Main.font.setColor(1.0f, 0.75f, 0.65f, 1);}
		}
		else
		{Main.font.setColor(0.5f, 0.6f, 0.7f, 1);}	
	}
	
	
	public void get_color_by_rarity(Rarity _rar)
	{
		if (_rar.equals(Rarity.COMMON)) {standart_color=Color.WHITE;}
		if (_rar.equals(Rarity.UNCOMMON)) {standart_color=Color.GREEN;}
		if (_rar.equals(Rarity.RARE)) {standart_color=Color.BLUE;}
		if (_rar.equals(Rarity.ELITE)) {standart_color=Color.MAGENTA;}
		if (_rar.equals(Rarity.LEGENDARY)) {standart_color=Color.YELLOW;}
		if (_rar.equals(Rarity.ANOMALY)) {standart_color=Color.CYAN;}
		
	}
	
	@Override
	public void some_update(float _d)
	{
		if (!GScreen.show_equip){need_remove=true;}
		update_object();
		
		if (obj instanceof ModuleUnit){get_color_by_rarity(((ModuleUnit)obj).rarity);}
		if (obj instanceof Weapon){get_color_by_rarity(((Weapon)obj).rarity);}
		if (obj instanceof Energoshield){get_color_by_rarity(((Energoshield)obj).rarity);}
		
		if (is_overlap())
		{
			mov=0;
			//need_remove=true;
			//float tx=150;
			
			//float ty=700-250;
			/*Main.shapeRenderer_static.setColor(0.1f, 0.12f, 0.15f,0.5f);
			Main.shapeRenderer_static.rect(info_x-10, info_y-10-200, 300, 220);*/
			
			Main.font.setColor(Color.WHITE);
			//GScreen.batch_static.setColor(1.0f,1.0f,1.0f,1.0f);
		
			GScreen.batch_static.setColor(Color.WHITE);
			
			if (obj instanceof ModuleUnit)
			{
				
				ModuleUnit m=((ModuleUnit)obj);
				
				draw_info(""+((ModuleUnit)obj).get_name(),"");
				mov+=25;
				//draw_info("Bonuses: ",""+((Weapon)obj).attr_count);
				draw_info(m.get_description(),"");
				mov+=25;

				color_it (m.base_cooldown,m.total_cooldown); draw_info("Перезарядка: ",""+Math.round(m.total_cooldown*100.0f)/100.0f);
				color_it (m.total_duration,m.base_duration); draw_info("Длительность: ",""+Math.round(m.total_duration*100.0f)/100.0f);
				mov+=25;
				for (int i=0; i<m.Attribute_list.size(); i++)
				{
					//Helper.log("ATTR LIST SIZE="+m.Attribute_list.size());
					Main.font.setColor(Color.WHITE);
					draw_info(m.Attribute_list.get(i).get_descr(),"");
				}
				
				//model.
				

			}
			
			if (obj instanceof Weapon)
			{
				
				Weapon w=((Weapon)obj);
				
				float mx=0;
				
				draw_info(""+((Weapon)obj).get_name()+" ("+w.level+" level) "+w.attr_point_indicate,"");
				//mov+=25;
				//draw_info("Bonuses: ",""+((Weapon)obj).attr_count);
				mov+=15;
				color_it (w.total_damage,w.base_damage); draw_info("Урон: ",""+w.total_damage,1);
				if (w.total_fire_damage>0) {Main.font.setColor(Color.YELLOW); mx+=280; draw_info("Поджог: ",""+w.total_fire_damage*10f,mx); }
				if (w.total_cold_damage>0) {Main.font.setColor(Color.CYAN); mx+=280; draw_info("Заморозка: ",""+w.total_cold_damage*10f,mx); }
				mov+=28;
				color_it (w.base_shoot_cooldown,w.total_shoot_cooldown/GScreen.pl.bonus_attack_speed); draw_info("Скорострельность: ",""+Math.round(1.0f/w.total_shoot_cooldown*GScreen.pl.bonus_attack_speed*10.0f)/10.0f);
				color_it (w.base_dispersion,w.total_dispersion);draw_info("Dispersion: ",""+Math.round(w.total_dispersion),1);
				color_it (w.base_dispersion_additional,w.total_dispersion_additional);draw_info("Dispersion add: ",""+Math.round(w.total_dispersion_additional),280);
				mov+=28;
				color_it (w.total_ammo_size,w.base_ammo_size);draw_info("Ammo size: ",""+Math.round(w.total_ammo_size),1);
				color_it (w.base_reload_time,w.total_reload_time/GScreen.pl.bonus_attack_speed);draw_info("Reload time: ",""+Math.round(w.total_reload_time/GScreen.pl.bonus_reload_speed*10.0f)/10f,280);
				
				mov=200;
				Main.font.setColor(1.0f, 0.2f, 0.1f, 1f);
				draw_info("'"+w.red_text+"'","");
				
				/*
				for (int i=0; i<w.Attribute_list.size(); i++)
				{
					draw_info (w.Attribute_list.get(i).name,""+w.Attribute_list.get(i).level);
				}
				*/
				//if (Math.random()<0.01){((Weapon)obj).model.setPosition((float)(Math.random()*100),(float)(Math.random()*100));}
				
				((Weapon)obj).model.setPosition(info_x,info_y-300);
				((Weapon)obj).model.draw(GScreen.batch_static);
				//model.
				

			}
			
				
			if (obj instanceof Energoshield)
			{

				
				Energoshield e=((Energoshield)obj);
				draw_info(""+e.name,"("+e.attr_point_indicate+","+e.level+")");
				
				mov+=15;
				
				//draw_info("Bonuses: ",""+((Energoshield)obj).attr_count);
				
				
				color_it (e.total_value,e.base_value); draw_info("Value: ",""+((Energoshield)obj).total_value);
				color_it (e.total_regen_speed,e.base_regen_speed); draw_info("Regen speed: ",""+((Energoshield)obj).total_regen_speed);
				color_it (e.total_reflect,e.base_reflect); draw_info("Reflect chance: ",""+((Energoshield)obj).total_reflect);
				
				Main.font.setColor(1.0f, 0.5f, 0.25f, 1);
				
				
				for (int i=0; i<e.Attribute_list.size(); i++)
				{
					if (!e.Attribute_list.get(i).base){draw_info(""+e.Attribute_list.get(i).name,""+e.Attribute_list.get(i).get_attr_value());}
				}
				
				mov+=25;
				for (int i=0; i<e.Attribute_list.size(); i++)
				{
					Main.font.setColor(Color.WHITE);
					draw_info(e.Attribute_list.get(i).get_descr(),"");
				}

			}
			
			
			//System.out.println("!!!");
			if (InputHandler.but==0)
			{
				InputHandler.but=-1;
				if (inventory_id>=0)
				{
					
					

					Object swap=GScreen.pl.inventory[inventory_id];
					GScreen.pl.inventory[inventory_id]=GScreen.pl.inventory[99];
					GScreen.pl.inventory[99]=swap;
					
					
					
					update_object();
					Main.font.setColor(1.0f, 1.0f, 1.0f, 1);
				}
				else
				{
					
					if ((inventory_id==-1)&&(GScreen.pl.inventory[99] instanceof Weapon))
					{
						GScreen.pl.armored[0].unequip();
						
						Object swap=(Weapon)GScreen.pl.armored[0];
						GScreen.pl.armored[0]=(Weapon)GScreen.pl.inventory[99];
						GScreen.pl.inventory[99]=swap;
						
						GScreen.pl.armored[0].equip();
					}
					
					if ((inventory_id==-2)&&(GScreen.pl.inventory[99] instanceof Weapon))
					{
						GScreen.pl.armored[1].unequip();
						
						Object swap=(Weapon)GScreen.pl.armored[1];
						GScreen.pl.armored[1]=(Weapon)GScreen.pl.inventory[99];
						GScreen.pl.inventory[99]=swap;
						
						GScreen.pl.armored[1].equip();
					}
					
					if ((inventory_id==-5)&&(GScreen.pl.inventory[99] instanceof Energoshield))
					{
						Object swap=(Energoshield)GScreen.pl.armored_shield;
						GScreen.pl.armored_shield=(Energoshield)GScreen.pl.inventory[99];
						GScreen.pl.inventory[99]=swap;
					}
					
					if ((inventory_id<=-10)&&(inventory_id>-15)&&(GScreen.pl.inventory[99] instanceof ModuleUnit))
					{
						Gdx.audio.newSound(Gdx.files.internal("data/module_put.wav")).play(0.2f);
						Object swap=(ModuleUnit)GScreen.pl.armored_module[Math.abs(inventory_id)-10];
						GScreen.pl.armored_module[Math.abs(inventory_id)-10]=(ModuleUnit)GScreen.pl.inventory[99];
						GScreen.pl.inventory[99]=swap;
					}
					
					update_object();
				}
				
	
				
			}
		}
		
		if (!need_remove)
		{
			if (obj instanceof Energoshield)
			{
				((Energoshield)obj).spr.setPosition(pos.x-spr.getWidth()/2+15,pos.y-spr.getHeight()/2);
				((Energoshield)obj).spr.draw(GScreen.batch_static);
				
				
			}
			
			if (obj instanceof Weapon)
			{
				((Weapon)obj).spr.setPosition(pos.x-spr.getWidth()/2,pos.y-spr.getHeight()/2);
				((Weapon)obj).spr.draw(GScreen.batch_static);
			}
			
			if (obj instanceof ModuleUnit)
			{
				GScreen.batch_static.setColor(((ModuleUnit)obj).color);
				GScreen.batch_static.draw(((ModuleUnit)obj).tex, pos.x-spr.getWidth()/2+15f,pos.y-spr.getHeight()/2f);
				
			}
		}
		
		
		
		
	}
	
	public void draw_info(String _s1, String _s2, float _x)
	{
		Main.font.draw(GScreen.batch_static, _s1, info_x+_x, info_y-mov);
		
		if (!_s2.equals(""))
		{
			Main.font.draw(GScreen.batch_static, _s2, info_x+190+_x, info_y-mov);
			GScreen.batch_static.setColor(0.25f,1,0.5f,0.1f);
				GScreen.batch_static.draw(Assets.rect_white, info_x+_x-2+188, info_y-mov-15,70,18);
			GScreen.batch_static.setColor(Color.WHITE);
		}
		
		if ((_s2.equals(""))&&(mov<180))
		{
			Helper.layout.setText(Main.font, _s1);
			
			
			GScreen.batch_static.setColor(1.0f,1,0.5f,0.1f);
			GScreen.batch_static.draw(Assets.rect_white, info_x+_x-2, info_y-3-mov-Helper.layout.height,Helper.layout.width+5,Helper.layout.height+5);
			GScreen.batch_static.setColor(Color.WHITE);
		}
		
		if ((!_s2.equals("")))
		{
				GScreen.batch_static.setColor(1,1,1,0.1f);
					GScreen.batch_static.draw(Assets.rect_white, info_x+_x-2, info_y-mov-15,170,18);
				GScreen.batch_static.setColor(Color.WHITE);	
		}
		
		if (mov>=180)
		{
			GScreen.batch_static.setColor(1,0.5f,0.25f,0.1f);
				GScreen.batch_static.draw(Assets.rect_white, info_x+_x-2, info_y-mov-15,770,18);
			GScreen.batch_static.setColor(Color.WHITE);
		}
		if (_x==0) {mov+=28;} 
		

	}
	
	public void draw_info(String _s1, String _s2)
	{
		draw_info(_s1, _s2, 0);
	}
	
}
