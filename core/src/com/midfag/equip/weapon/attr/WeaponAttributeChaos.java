package com.midfag.equip.weapon.attr;

import com.midfag.entity.Entity;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.Assets;

public class WeaponAttributeChaos extends WeaponAttribute {

	public float chaos_time=3;
	
	public WeaponAttributeChaos()
	{
		super();
		
		max_level=1;
		cost=100;
		
		name="accuracy";
		uid="attr_chaos";
		
		
	}
	
	@Override
	public void calculate(Weapon _w)
	{
		//float bonus=1-level/(level+10);
	
	}
	
	
	
	@Override
	public void update(float _d, Entity pl, Weapon _w)
	{
		System.out.println("CHAOS! "+chaos_time);
		if (chaos_time>0)
		{
			chaos_time-=_d;
			
			if (chaos_time<=0)
			{
				chaos_time+=3;
				
				
				_w.Attribute_list.clear();
				_w.Available_attribute_list.clear();
				
				_w.Attribute_list.add(new WeaponAttributeChaos());
				
				_w.base_damage=(float)(Math.random()*30)+1;
				_w.base_dispersion=(float)(Math.random()*90);
				_w.base_dispersion_additional=(float)(Math.random()*90);
				
				_w.base_reload_time=(float)(Math.random()*3);
				_w.base_shoot_cooldown=(float)(Math.random()*2)+0.1f;
				
				
				_w.Available_attribute_list.add(new WeaponAttributeDamage());
				_w.Available_attribute_list.add(new WeaponAttributeAttackSpeed());
				_w.Available_attribute_list.add(new WeaponAttributeAccuracy());
				_w.Available_attribute_list.add(new WeaponAttributeStability());
				_w.Available_attribute_list.add(new WeaponAttributeClipSize());
				_w.Available_attribute_list.add(new WeaponAttributeReloadSpeed());
				
				_w.Available_attribute_list.add(new WeaponAttributeFireDamage());
				_w.Available_attribute_list.add(new WeaponAttributeColdDamage());
				
				_w.base_missile_count=(int) (Math.random()*10)+1;
				
				System.out.println("CHAOS!");
				
				Assets.chaos.play(0.2f);
				_w.generate();
				
				_w.update_attributes_bonus();
				
				
			}
		}
	}
	
}
