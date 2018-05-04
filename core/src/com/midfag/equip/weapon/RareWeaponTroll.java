package com.midfag.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.enemies.EntityPyro;
import com.midfag.entity.missiles.Missile;
import com.midfag.entity.missiles.MissilePing;
import com.midfag.entity.missiles.MissileSimple;
import com.midfag.entity.missiles.MissileTroll;
import com.midfag.equip.weapon.attr.WeaponAttributeAccuracy;
import com.midfag.equip.weapon.attr.WeaponAttributeAttackSpeed;
import com.midfag.equip.weapon.attr.WeaponAttributeClipSize;
import com.midfag.equip.weapon.attr.WeaponAttributeColdDamage;
import com.midfag.equip.weapon.attr.WeaponAttributeDamage;
import com.midfag.equip.weapon.attr.WeaponAttributeFireDamage;
import com.midfag.equip.weapon.attr.WeaponAttributeReloadSpeed;
import com.midfag.equip.weapon.attr.WeaponAttributeStability;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Enums.Rarity;

public class RareWeaponTroll extends Weapon {
	
	
		public float fire_summ=0;
		public float trololo_volume=0;
	
		public RareWeaponTroll()
		{
			super ();
			uid="weapon_troll";
			
			base_fire_damage=0f;
			base_damage=20;
			base_missile_count=10;
			base_shoot_cooldown=0.1f;
			base_dispersion=18;
			base_dispersion_additional=5;
			base_ammo_size=20;
			base_reload_time=3;
			
			fire_multiplier=base_shoot_cooldown/base_missile_count*13f;
			
			need_warm=0;
			
			rarity=Rarity.RARE;
			
			get_available_attribute();
			
			missile_speed=1550;

			
			name="TR-011";
			
			spr.setTexture(Assets.load("icon_trollgun"));
			spr.setSize(75, 40);
			
			model.setTexture(Assets.load("icon_trollgun"));
			model.setSize(200, 90);
			
			 red_text="Проблемы?";
			
			//System.out.println("Child class");
		}
		
		
		@Override
		public void get_available_attribute()
		{
			Available_attribute_list.clear();
			Available_attribute_list.add(new WeaponAttributeAttackSpeed());
			Available_attribute_list.add(new WeaponAttributeClipSize());
			Available_attribute_list.add(new WeaponAttributeReloadSpeed());
			Available_attribute_list.add(new WeaponAttributeFireDamage());
			Available_attribute_list.add(new WeaponAttributeColdDamage());
			Available_attribute_list.add(new WeaponAttributeDamage());
		}

		@Override
		public Missile get_missile(Entity pl)
		{
			return new MissileTroll(
					new Vector2(pl.pos.x,pl.pos.y),
					(float) Math.toRadians(360-pl.rot+get_dispersion()+GScreen.rnd(add_disp)-add_disp/2),
					(GScreen.rnd(100)+1500.0f),
					pl.is_AI);
		}
		
		@Override
		public Sound get_shoot_sound()
		{
			Assets.trololo_volume=3;
			Assets.trololo.setVolume(Assets.trololo_id, 1f);
			Assets.trololo.resume();
			return Assets.shoot_fire;
		}
		
		@Override
		public void equip()
		{
			Helper.log("EQ");
		}
		
		@Override
		public void unequip()
		{
			Helper.log("UNEQ");
		}
		
		@Override
		public void ability_hit(Missile _m, Entity _target)
		{
			if (_target.is_enemy!=_m.is_enemy)
			{
				_m.damage/=30f;
				_m.fire_damage/=30f;
				_m.cold_damage/=30f;
			}
			
		}
		
		@Override
		public void update( Entity _target, float _d)
		{
			//Helper.log("@@@");
			if (Assets.trololo_volume>0)
			{Assets.trololo_volume-=_d;}
			
			//if (trololo_volume<Assets.trololo_volume)
			//{
			//	Assets.trololo_volume=trololo_volume;
			//}
			

			if (Assets.trololo_volume<1)
			{
				Assets.trololo.setVolume(Assets.trololo_id, Assets.trololo_volume);
			}
			
			if (Assets.trololo_volume<0.1f)
			{Assets.trololo.pause();}
			
		}
		
		
		//public void
}
