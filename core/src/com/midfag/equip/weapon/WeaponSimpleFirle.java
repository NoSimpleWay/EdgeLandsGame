package com.midfag.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.equip.weapon.attr.WeaponAttributeAccuracy;
import com.midfag.equip.weapon.attr.WeaponAttributeAttackSpeed;
import com.midfag.equip.weapon.attr.WeaponAttributeChaos;
import com.midfag.equip.weapon.attr.WeaponAttributeClipSize;
import com.midfag.equip.weapon.attr.WeaponAttributeColdDamage;
import com.midfag.equip.weapon.attr.WeaponAttributeDamage;
import com.midfag.equip.weapon.attr.WeaponAttributeFireDamage;
import com.midfag.equip.weapon.attr.WeaponAttributeReloadSpeed;
import com.midfag.equip.weapon.attr.WeaponAttributeStability;
import com.midfag.game.Assets;

public class WeaponSimpleFirle extends Weapon {
	
	
	
		public WeaponSimpleFirle()
		{
			uid="weapon_simple_firle";
			
			base_damage=10;
			base_missile_count=1;
			base_shoot_cooldown=0.10f;
			base_dispersion=5;
			base_dispersion_additional=10;
			base_ammo_size=17;
			base_reload_time=3;
			
			fire_multiplier=base_shoot_cooldown/base_missile_count;
			
			
			
			name="Винтовка";
			
			 spr.setTexture(Assets.load("icon_firle"));//()=)
		}
		

		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot04;
		}
		

		
		//public void
}
