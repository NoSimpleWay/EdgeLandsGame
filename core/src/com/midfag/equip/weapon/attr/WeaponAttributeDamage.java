package com.midfag.equip.weapon.attr;

import com.midfag.equip.weapon.Weapon;

public class WeaponAttributeDamage extends WeaponAttribute {

	public WeaponAttributeDamage()
	{
		max_level=100;
		cost=5;
		
		name="damage";
	}
	
	public void calculate(Weapon _w)
	{
		_w.total_damage+=_w.base_damage*(1f+0.2f*level)+_w.base_damage*(_w.level-1);
		//_w.total_damage=_w.base_damage;
	}
}
