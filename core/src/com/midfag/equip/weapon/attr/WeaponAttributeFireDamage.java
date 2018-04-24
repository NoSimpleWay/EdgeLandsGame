package com.midfag.equip.weapon.attr;

import com.midfag.equip.weapon.Weapon;

public class WeaponAttributeFireDamage extends WeaponAttribute {

	public WeaponAttributeFireDamage()
	{
		max_level=100;
		cost=1;
		
		name="fire damage";
	}
	
	public void calculate(Weapon _w)
	{
		//_w.total_damage=_w.base_damage*(1f+0.2f*level);
		_w.total_fire_damage=+_w.fire_multiplier*level*3f;
		//_w.total_damage=_w.base_damage;
	}
}
