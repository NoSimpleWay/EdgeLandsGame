package com.midfag.equip.weapon;

import com.badlogic.gdx.audio.Sound;
import com.midfag.game.Assets;

public class WeaponRaiderTank extends Weapon {
	
	
	
		public WeaponRaiderTank()
		{
			base_damage=5;
			base_missile_count=3;
			base_shoot_cooldown=0.2f;
			base_dispersion=0f;
			base_dispersion_additional=0.0f;
			base_ammo_size=30;
			base_reload_time=5.0f;
			missile_speed=1200;
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
