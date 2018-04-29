package com.midfag.equip.module;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.entity.Entity;
import com.midfag.equip.module.attr.ModuleAttribute;
import com.midfag.equip.module.attr.ModuleAttributeDuration;
import com.midfag.equip.module.attr.ModuleAttributeFastCooldown;

import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Enums.Rarity;

public class ModuleUnit {

	public boolean active=false;
	public float prepare_time=-777;
	public float base_cooldown;
	public float total_cooldown;
	public float cooldown;
	
	public float base_duration;
	public float total_duration;
	public float duration;
	
	public Texture tex=Assets.load("icon_firle");
	public Texture indicate_tex=Assets.load("icon_firle");
	public Texture lock_tex=Assets.load("icon_firle");
	public Texture model;
	public Rarity rarity;
	public float level=1.0f;
	
	public boolean lock=false;
	public boolean can_be_locked=false;
	
	public String name;
	public String description;
	private int highlight_value;
	private boolean gennable=true;
	private float attr_point;
	private int attr_count;
	public List<ModuleAttribute> Available_attribute_list = new ArrayList<ModuleAttribute>();
	public List<ModuleAttribute> Attribute_list = new ArrayList<ModuleAttribute>();
	public Color color;
	public String uid;
	
	
	public ModuleUnit()
	{
		get_available_attribute();
	}
	
	public void  get_available_attribute()
	{
		Available_attribute_list.clear();
		Available_attribute_list.add(new ModuleAttributeDuration());
		Available_attribute_list.add(new ModuleAttributeFastCooldown());
	}
	
	public void update(Entity _e, float _d)
	{
		
	}
	
	public void use(Entity _e)
	{
		
	}
	
	public String get_description()
	{
		return "";
	}
	
	public void update_attributes_bonus(Entity _e)
	{
		update_stats();
		
		for (int i=0; i<_e.Skills_list.size();i++)
		{
			if (_e.Skills_list.get(i).learned)
			{_e.Skills_list.get(i).module_gen_action(this);}
		}
	}
	
	public void use_end_skill(Entity _e, float _d)
	{
		for (int i=0; i<Attribute_list.size(); i++)
		{Attribute_list.get(i).end_action(_e,_d);}	
	}
	
	
	public void generate()
	{
		if (gennable)
		{

			get_available_attribute();
			Attribute_list.clear();
			
			/*
			int r=0;
			if (rarity.ordinal()==0)
			{
				for (int i=0; i<6; i++)
				{
					r=i;
					
					if (Math.random()>0.5f){break;}
				}
				
				if (r==0) {rarity=Rarity.COMMON;}
				if (r==1) {rarity=Rarity.UNCOMMON;}
				if (r==2) {rarity=Rarity.RARE;}
				if (r==3) {rarity=Rarity.ELITE;}
				if (r==4) {rarity=Rarity.LEGENDARY;}
				if (r==5) {rarity=Rarity.ANOMALY;}
			}
			*/
			
			attr_point=(float) (level*10f*(Math.pow(1.26f,rarity.ordinal())));
			
		
			attr_count=(int) (GScreen.rnd(3))+1;
		
			for (int i=0; i<(Available_attribute_list.size()-attr_count); i++)
			{
				Available_attribute_list.remove((int)(Math.random()*Available_attribute_list.size()));
				i--;
			}
			
			
			for (int i=0; i<500; i++)
			{
				if (!Available_attribute_list.isEmpty())
				{
					
					for (int j=0; j<Available_attribute_list.size(); j++)
					{
						ModuleAttribute aal=Available_attribute_list.get(j);
						
						if 	(
								(aal.cost>attr_point)//если очков на новый атрибут не хватает
								||
								(aal.level>=aal.max_level)//или бонус достиг максимального уровня
							)
								
						{
								System.out.println("Available_attribute_list_remove="+aal.name);
								Attribute_list.add(aal);//добавляем максимально возможно апнутый атрибут в список имеющихся бонусов
								
								Available_attribute_list.remove(j);//убираем атрибут из списка апа
								j--;
						}
					}
					
					
					if (!Available_attribute_list.isEmpty())
					{
						ModuleAttribute wa=Available_attribute_list.get((int)(Math.random()*Available_attribute_list.size()));//выбираем случайный атрибут из имеющихся
						
						wa.level++;//апаем его уровень
						attr_point-=wa.cost;//уменьшаем очки улучшения
					}
				}
				else
				{break;}
			}	
		}
	}
	
	public void recalculate_base() {
		// TODO Auto-generated method stub
		
		
	}

	public void indicate(float _x, float _y, float _d)
	{
		
		GScreen.batch_static.setColor(1, 1, 1, 0.5f);
		GScreen.batch_static.draw(indicate_tex, _x-22, _y-22);
		GScreen.batch_static.setColor(Color.WHITE);
		
		if (total_cooldown>0)
		{
			//GScreen.batch_static.setColor(Color.RED);
			GScreen.batch_static.draw(Assets.icon_cooldown, _x-27, _y-27+25*(1-cooldown/total_cooldown),50f,50f*cooldown/total_cooldown);
		}
		
		if (total_duration>0)
		{
			//GScreen.batch_static.setColor(Color.RED);
			GScreen.batch_static.draw(Assets.icon_duration, _x-27, _y-27+25*(1-duration/total_duration),50f,50f*duration/total_duration);
		}
		
		if (active)
		{
			//GScreen.batch_static.setColor(Color.RED);
			GScreen.batch_static.draw(Assets.icon_duration, _x-27, _y-27+25,50f,50f);
		}
		
		if (highlight_value>0)
		{
			float col=1f-Math.abs(0.25f-highlight_value)*4f;
					
			GScreen.batch_static.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
			
			GScreen.batch_static.setColor(col, col, col, 1);
			highlight_value-=_d;
			GScreen.batch_static.draw(Assets.highlight, _x-50, _y-50);
			GScreen.batch_static.setColor(Color.WHITE);
					
			GScreen.batch_static.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		}
		
		if (lock)
		{
			GScreen.batch_static.setColor(Color.WHITE);
			GScreen.batch_static.draw(lock_tex, _x-22, _y-22);
		}
		
		
	}
	
	

	
	
	public void update_stats()
	{
		total_duration=base_duration;
		total_cooldown=base_cooldown;
		

		
		Helper.log("MODULE IS CRAZY");
		
		additional_update_stats();
		
		for (int i=0; i<Attribute_list.size(); i++)
		{
			Attribute_list.get(i).calculate(this);
		}
		
		color=Color.WHITE;
		/*
		if (rarity==Rarity.COMMON){color=Color.WHITE;}
		if (rarity==Rarity.UNCOMMON){color=Color.GREEN;}
		if (rarity==Rarity.RARE){color=Color.ROYAL;}
		if (rarity==Rarity.ELITE){color=Color.MAGENTA;}
		if (rarity==Rarity.LEGENDARY){color=Color.ORANGE;}
		if (rarity==Rarity.ANOMALY){color=Color.CYAN;}*/

	}
	

	public void additional_update_stats() {
		// TODO Auto-generated method stub
		
	}

	public String get_name() {
		// TODO Auto-generated method stub
		return name;
	}

	public boolean can_use() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean can_use_default()
	{
		if ((cooldown<=0)&&(duration<=0)&&(!lock))
		{return true;}
		
		return false;
	}
	
	public void preparing_complete()
	{
		
	}
	
	
}
