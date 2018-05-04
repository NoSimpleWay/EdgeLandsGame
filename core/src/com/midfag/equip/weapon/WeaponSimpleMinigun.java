package com.midfag.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.game.Assets;

public class WeaponSimpleMinigun extends Weapon {
	
	
	
		public WeaponSimpleMinigun()
		{
			uid="weapon_simple_minigun";
			
			base_damage=10;
			base_missile_count=1;
			base_shoot_cooldown=0.07f;
			base_dispersion=10;
			base_dispersion_additional=1;
			base_ammo_size=100;
			base_reload_time=7;
			
			fire_multiplier=base_shoot_cooldown/base_missile_count;
			
			need_warm=3;
			
			missile_speed=2018;
			
			name="Стандартный пулемет";
			
			spr.setTexture(Assets.load("icon_minigun"));
			spr.setSize(75, 40);
			
			model.setTexture(Assets.load("model_minigun"));
			model.setSize(200, 90);
		}
		

		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot03;
		}
		
		//public void
}
