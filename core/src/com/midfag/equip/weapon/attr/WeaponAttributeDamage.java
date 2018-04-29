package com.midfag.equip.weapon.attr;

import com.midfag.equip.weapon.Weapon;

public class WeaponAttributeDamage extends WeaponAttribute {

	public WeaponAttributeDamage()
	{
		max_level=100;
		cost=5;
		
		name="damage";
		uid="attr_damage";
	}
	
	public void calculate(Weapon _w)
	{
		_w.total_damage+=(_w.base_damage*_w.level)*(0.1f*level);
		_w.total_dispersion+=0.3f*level;
		//_w.total_damage=_w.base_damage;
	}
}
