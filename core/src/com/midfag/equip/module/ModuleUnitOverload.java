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

public class ModuleUnitOverload extends ModuleUnit {

	/*
	public float base_push_damage=50;
	public float total_push_damage=50;
	*/
	public float base_shoot_speed;
	public float total_shoot_speed;

	public float base_reload_speed;
	public float total_reload_speed;
	
	public float base_self_damage;
	public float total_self_damage;
	public float self_defence;

	public ModuleUnitOverload()
	{
		name="Модуль <предельная нагрузка>";
		uid="modoverload";
		
		base_duration=0.0f;
		base_cooldown=0.2f;
		
		base_shoot_speed=0.30f;
		base_reload_speed=0.30f;
		
		base_self_damage=50;
		/*
		Available_attribute_list.add(new ModuleAttributePushDamage());
		Available_attribute_list.add(new ModuleAttributeExplosionFire());
		Available_attribute_list.add(new ModuleAttributeExplosionIce());
		*/
		model=Assets.load("module_push_model");

		
		tex=Assets.load("icon_module_overload");
		indicate_tex=Assets.load("icon_indicate_overload");
		rarity=Rarity.COMMON;
		
		level=1;
		
		generate();
		update_stats();
	}
	
	@Override
	public void  get_available_attribute()
	{
		Available_attribute_list.clear();
		
		Available_attribute_list.add(new ModuleAttributeMoreBonusAttackSpeed());
		Available_attribute_list.add(new ModuleAttributeMoreBonusReloadSpeed());
		Available_attribute_list.add(new ModuleAttributeSelfDamageProtect());
	}
	
	@Override
	public String get_description()
	{
		return "Скорость атаки +"+Math.round(total_shoot_speed*1000.0f)/10.0f+"%, скорость перезарядки +"+Math.round(total_reload_speed*1000.0f)/10.0f+"%"+"\n"+"Наносит "+total_self_damage*Math.round((1f-self_defence)*10.0f)/10.0f+"(+"+Math.round((total_self_damage/10f*(1f-self_defence))*10.0f)/10.0f+"% от запаса) урона владельцу";
	}
	
	@Override
	public void use(Entity _e)
	{
		active=!active;
		
		
		if (active)
		{
			_e.bonus_attack_speed+=total_shoot_speed;
			_e.bonus_reload_speed+=total_reload_speed;
		}
		else
		{
			cooldown=total_cooldown;
			
			_e.bonus_attack_speed-=total_shoot_speed;
			_e.bonus_reload_speed-=total_reload_speed;
		}
		
		
		
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
		total_shoot_speed=base_shoot_speed;
		total_reload_speed=base_reload_speed;
		
		total_self_damage=base_self_damage;
		self_defence=0;
	}
	
	@Override
	public void update(Entity _e, float _d)
	{
			cooldown-=_d;
			if (cooldown<=0)
			{cooldown=0;}
			
			if (active)
			{
				_e.armored_shield.value-=(total_self_damage+total_self_damage/2000f*_e.armored_shield.value)*(1f-self_defence)*_d;
			}
			
	}
	
	
}
