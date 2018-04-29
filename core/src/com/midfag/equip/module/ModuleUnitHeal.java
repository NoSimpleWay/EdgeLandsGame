package com.midfag.equip.module;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.Shd;
import com.midfag.entity.ShdMove;
import com.midfag.equip.module.attr.*;

import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Phys;
import com.midfag.game.Enums.Rarity;

public class ModuleUnitHeal extends ModuleUnit {

	/*
	public float base_push_damage=50;
	public float total_push_damage=50;
	*/
	public float base_immediate_heal;
	public float total_immediate_heal;
	
	public float base_gradual_heal;
	public float total_gradual_heal;



	public ModuleUnitHeal()
	{
		name="Модуль <полевой ремонт>";
		uid="modheal";
		
		base_duration=10.0f;
		base_cooldown=15;
		
		base_immediate_heal=30;
		base_gradual_heal=7;
		
		/*
		Available_attribute_list.add(new ModuleAttributePushDamage());
		Available_attribute_list.add(new ModuleAttributeExplosionFire());
		Available_attribute_list.add(new ModuleAttributeExplosionIce());
		*/
		model=Assets.load("module_push_model");

		
		tex=Assets.load("icon_module_heal");
		indicate_tex=Assets.load("icon_indicate_heal");
		rarity=Rarity.COMMON;
		
		level=1;
		
		generate();
		update_stats();
	}
	
	@Override
	public void  get_available_attribute()
	{
		Available_attribute_list.clear();
		
		Available_attribute_list.add(new ModuleAttributeDuration());
		Available_attribute_list.add(new ModuleAttributeFastCooldown());
		Available_attribute_list.add(new ModuleAttributeMoreHeal());
		
	}
	
	@Override
	public String get_description()
	{
		return "Восстанавливает "+total_immediate_heal+" щита моментально"+"\n"+"Восстанавливает "+total_gradual_heal+" щита/сек в течении "+total_duration+" сек.";
	}
	
	@Override
	public void use(Entity _e)
	{
		duration=total_duration;
		
		_e.armored_shield.value+=total_immediate_heal;
		
		//Assets.jet.play();
	}
	
	@Override
	public boolean can_use() {
		// TODO Auto-generated method stub
		return can_use_default();
	}
	


	
	@Override
	public void additional_update_stats()
	{
		total_immediate_heal=base_immediate_heal;
		total_gradual_heal=base_gradual_heal;
	}
	
	@Override
	public void update(Entity _e, float _d)
	{
			cooldown-=_d;
			if (cooldown<=0)
			{cooldown=0;}
			
			if (duration>0)
			{
				_e.armored_shield.value+=total_gradual_heal*_d;
				if (_e.armored_shield.value>_e.armored_shield.total_value)
				{_e.armored_shield.value=_e.armored_shield.total_value;}
			}
			
			if (duration>0)
			{
				duration-=_d;
				//_e.rotate_block=true;
				
				if (duration<=0)
					{duration=0;
					cooldown=total_cooldown;
					use_end_skill(_e, _d);}
			}
	}
	
	
}
