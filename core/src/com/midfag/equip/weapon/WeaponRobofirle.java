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
			
			/*generate();
			update			generate();
			update_attributes_bonus();*/
			
			generate();
			update_attributes_bonus();
			//update_stats();
		}
		

		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot00;
		}
		
		//public void
}
