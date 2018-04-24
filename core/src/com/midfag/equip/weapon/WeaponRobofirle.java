package com.midfag.equip.weapon;

import com.badlogic.gdx.audio.Sound;
import com.midfag.game.Assets;

public class WeaponRobofirle extends Weapon {
	
	
	
		public WeaponRobofirle()
		{
			base_damage=15;
			base_missile_count=1;
			base_shoot_cooldown=0.2f;
			base_dispersion=1;
			base_dispersion_additional=0.5f;
			base_ammo_size=15;
			base_reload_time=1.0f;
			
			fire_multiplier=base_shoot_cooldown/base_missile_count;
			
			update_stats();
		}
		
		public void update_stats()
		{

			
			//system.
			/*
			total_damage=base_damage;
			total_missile_count=base_missile_count;
			total_shoot_cooldown=base_shoot_cooldown;
			total_dispersion=base_dispersion;
			total_dispersion_additional=base_dispersion_additional;
			total_ammo_size=base_ammo_size;
			total_reload_time=base_reload_time;*/
			generate();
			
		}
		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot00;
		}
		
		//public void
}
