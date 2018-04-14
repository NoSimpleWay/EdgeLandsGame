package com.midfag.equip.module;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.entity.Entity;
import com.midfag.equip.module.attr.ModuleAttributeBurnDamage;
import com.midfag.equip.module.attr.ModuleAttributeDuration;
import com.midfag.equip.module.attr.ModuleAttributeExplosionDamage;
import com.midfag.equip.module.attr.ModuleAttributeExplosionIce;
import com.midfag.equip.module.attr.ModuleAttributeFastCooldown;
import com.midfag.equip.module.attr.ModuleAttributeMoreTimeSlowResist;
import com.midfag.equip.module.attr.ModuleAttributeSelfDamageProtect;
import com.midfag.game.GScreen;
import com.midfag.game.screen_effect.ScreenEffectEXPLOSIONS;
import com.midfag.game.screen_effect.ScreenEffectTimeStop;
import com.midfag.game.Enums.Rarity;

public class LegendaryModuleUnitEXPLOSIONS extends ModuleUnit {

	/*
	public float base_push_damage=50;
	public float total_push_damage=50;
	*/
	public float base_time_slow;
	public float total_time_slow;
	
	public float base_time_slow_resist;
	public float total_time_slow_resist;
	private Entity master;
	
	public float base_damage=20;
	public float base_burn=2;
	
	public float total_damage=20;
	public float total_burn=2;
	
	public float self_defence=0;

	public LegendaryModuleUnitEXPLOSIONS()
	{
		name="Модуль 'Детонатор'";
		
		
		
		base_duration=1.5f;
		base_cooldown=60;
		
		level=5;
		
		
		can_be_locked=true;
		
		tex=new Texture(Gdx.files.internal("icon_explosion.png"));
		indicate_tex=new Texture(Gdx.files.internal("icon_explosion.png"));
		
		rarity=Rarity.COMMON;
		
		//Available_attribute_list.add(new ModuleAttributeExplosionIce());
		
		additional_update_stats();
		generate();
		update_stats();
		
		
		total_duration=2f;
	}
	
	public void  get_available_attribute()
	{
		Available_attribute_list.clear();
		Available_attribute_list.add(new ModuleAttributeExplosionDamage());
		Available_attribute_list.add(new ModuleAttributeBurnDamage());
		Available_attribute_list.add(new ModuleAttributeFastCooldown());
		Available_attribute_list.add(new ModuleAttributeSelfDamageProtect());
		
	}
	
	@Override
	public String get_description()
	{
		return "Подрывает всех противников, нанося им урон от взрыва и поджигет их"+"\n"+"Урон от взрыва: "+total_damage+" dddd"+"Урон от огня: "+total_burn;
	}
	
	@Override
	public void use(Entity _e)
	{
		prepare_time=1.5f;
		master=_e;
		duration=total_duration;
		GScreen.screen_effect=new ScreenEffectEXPLOSIONS();
		GScreen.screen_effect.MasterModule=this;
		GScreen.pl.time_slow_resist=total_time_slow_resist;
		
		for (int i=0; i<5; i++)
		{
			if (
					(GScreen.pl.armored_module[i]!=null)
					&&
					(GScreen.pl.armored_module[i].can_be_locked)
					&&
					(GScreen.pl.armored_module[i].duration<=0)
					&&
					(GScreen.pl.armored_module[i].cooldown<=0)
				)
			{
				GScreen.pl.armored_module[i].lock=true;
			}
		}
	}
	
	@Override
	public boolean can_use() {
		// TODO Auto-generated method stub
		return can_use_default();
	}
	

	public String get_descr()
	{
		return "";
	}
	
	@Override
	public void additional_update_stats()
	{
		base_damage*=level;
		base_burn*=level;
		
		total_damage=base_damage;
		total_burn=base_burn;
	}
	
	@Override
	public void update(Entity _entity, float _delta)
	{
			if (prepare_time>0) {prepare_time-=_delta;}
			
			
			if((prepare_time<=0)&&(prepare_time!=-777))
			{
				prepare_time=-777;
				preparing_complete();
			}
			
			cooldown-=GScreen.time_speed*_delta;
			if (cooldown<=0){cooldown=0;}
			
			if (duration>0)
			{
				duration-=GScreen.real_delta;
				if (duration<=0)
				{
					duration=0; cooldown=total_cooldown;
					use_end_skill(_entity, _delta);	
					
					//GScreen.screen_effect.end_action();
					//GScreen.screen_effect=null;
					
					for (int i=0; i<5; i++)
					{
						if (
								(GScreen.pl.armored_module[i]!=null)
							)
						{GScreen.pl.armored_module[i].lock=false;}
					}
				}
			}
	}
	
	@Override
	public void preparing_complete()
	{
		List<Entity> el=GScreen.get_entity_list(GScreen.temp_vector.set(master.pos.x,master.pos.y));
		
		for (int i=0; i<el.size(); i++)
		{
			if ((!el.get(i).is_decor)&&(el.get(i).active)&&(el.get(i).is_enemy!=master.is_enemy))
			{
				el.get(i).hit_action(total_damage,false);
				el.get(i).burn_it(total_burn);
				
			
			}
		}
		
		master.hit_action(total_damage*(1-self_defence), false);
		master.burn_it(total_burn*(1-self_defence));
		
		cooldown=total_cooldown;
	}
	
	
}
