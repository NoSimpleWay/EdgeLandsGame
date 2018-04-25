package com.midfag.equip.weapon.attr;

import com.midfag.equip.weapon.Weapon;

public class WeaponAttributeAttackSpeed extends WeaponAttribute {

	public WeaponAttributeAttackSpeed()
	{
		max_level=20;
		cost=3;
		
		name="attack_speed";
		uid="attr_as";
	}
	
	@Override
	public void calculate(Weapon _w)
	{
		//float bonus=1-level/(level+10);
		_w.total_shoot_cooldown=_w.base_shoot_cooldown*(1f-level/(level+10f));
		_w.total_reload_time+=0.05f*level+_w.base_reload_time*0.05f*level;
	}
}
