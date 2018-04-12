package com.midfag.equip.weapon.attr;

import com.midfag.equip.weapon.Weapon;

public class WeaponAttributeColdDamage extends WeaponAttribute {

	public WeaponAttributeColdDamage()
	{
		max_level=100;
		cost=1;
		
		name="fire damage";
	}
	
	public void calculate(Weapon _w)
	{
		//_w.total_damage=_w.base_damage*(1f+0.2f*level);
		_w.total_cold_damage=+_w.base_damage*level*0.015f;
		//_w.total_damage=_w.base_damage;
	}
}
