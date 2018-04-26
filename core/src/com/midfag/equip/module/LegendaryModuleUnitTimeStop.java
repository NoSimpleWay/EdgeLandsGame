package com.midfag.equip.module;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.entity.Entity;
import com.midfag.equip.module.attr.ModuleAttributeDuration;
import com.midfag.equip.module.attr.ModuleAttributeExplosionIce;
import com.midfag.equip.module.attr.ModuleAttributeFastCooldown;
import com.midfag.equip.module.attr.ModuleAttributeMoreTimeSlowResist;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.screen_effect.ScreenEffectTimeStop;
import com.midfag.game.Enums.Rarity;

public class LegendaryModuleUnitTimeStop extends ModuleUnit {

	/*
	public float base_push_damage=50;
	public float total_push_damage=50;
	*/
	public float base_time_slow;
	public float total_time_slow;
	
	public float base_time_slow_resist;
	public float total_time_slow_resist;


	public LegendaryModuleUnitTimeStop()
	{
		name="Модуль 'Остановка времени'";
		uid="modtistop";
		
		base_duration=7.0f;
		base_cooldown=60;
		base_time_slow=0.5f;
		
		level=1;
		
		base_time_slow_resist=0.5f;
		total_time_slow_resist=0.5f;
		
		can_be_locked=true;
		
		tex=Assets.load("icon_time_stop");
		indicate_tex=Assets.load("icon_indicate_time_slow");
		
		rarity=Rarity.COMMON;
		
		//Available_attribute_list.add(new ModuleAttributeExplosionIce());
		
		generate();
		update_stats();
	}
	
	public void  get_available_attribute()
	{
		Available_attribute_list.clear();
		
		Available_attribute_list.add(new ModuleAttributeDuration());
		Available_attribute_list.add(new ModuleAttributeFastCooldown());
		Available_attribute_list.add(new ModuleAttributeMoreTimeSlowResist());
		
	}
	
	@Override
	public String get_description()
	{
		return "Полностью останавливает время на "+total_duration+" сек"+"\n"+ "Cопротивление эффекту ("+(total_time_slow_resist*100)+"%)";
	}
	
	@Override
	public void use(Entity _e)
	{
		duration=total_duration;
		GScreen.screen_effect=new ScreenEffectTimeStop();
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
		total_time_slow=base_time_slow;
		total_time_slow_resist=base_time_slow_resist;
	}
	
	@Override
	public void update(Entity _entity, float _delta)
	{
			cooldown-=GScreen.time_speed*_delta;
			if (cooldown<=0){cooldown=0;}
			
			if (duration>0)
			{
				duration-=GScreen.real_delta;
				if (duration<=0)
				{
					duration=0; cooldown=total_cooldown;
					use_end_skill(_entity, _delta);	
					
					GScreen.screen_effect.end_action();
					GScreen.screen_effect=null;
					
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
	
	
}
