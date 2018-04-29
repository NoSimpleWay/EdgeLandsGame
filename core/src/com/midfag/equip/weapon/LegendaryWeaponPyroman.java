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

public class LegendaryWeaponPyroman extends Weapon {
	
	
		public float fire_summ=0;
	
		public LegendaryWeaponPyroman()
		{
			super ();
			uid="weapon_pyro";
			
			base_fire_damage=2.1f;
			base_damage=0;
			base_missile_count=1;
			base_shoot_cooldown=0.15f;
			base_dispersion=8;
			base_dispersion_additional=3;
			base_ammo_size=80;
			base_reload_time=3;
			
			fire_multiplier=base_shoot_cooldown/base_missile_count*1.3f;
			
			need_warm=0;
			
			rarity=Rarity.LEGENDARY;
			
			get_available_attribute();
			


			
			name="Гефест";
			
			spr.setTexture(Assets.load("icon_pyro"));
			spr.setSize(75, 40);
			
			model.setTexture(Assets.load("icon_pyro"));
			model.setSize(200, 90);
			
			 red_text="Питу это бы понравилось!";
			
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
		}

		@Override
		public Missile get_missile(Entity pl)
		{
			return new MissileSimple(
					new Vector2(pl.pos.x,pl.pos.y),
					(float) Math.toRadians(360-pl.rot+get_dispersion()+GScreen.rnd(add_disp)-add_disp/2),
					(GScreen.rnd(200)+400.0f),
					pl.is_AI);
		}
		
		@Override
		public Sound get_shoot_sound()
		{
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
			fire_summ+=_m.fire_damage;
			
			if (fire_summ>100*_m.master_weapon.level)
			{
				fire_summ=0;
				
				Entity pyro=new EntityPyro(new Vector2(_m.master.pos));
				pyro.is_enemy=_m.master.is_enemy;
				
				pyro.armored[0].base_fire_damage=1;
				pyro.armored[0].level=_m.master_weapon.level;
				pyro.armored[0].generate();
				
				pyro.armored_shield.base_value=700;
				pyro.armored_shield.level=_m.master_weapon.level;
				pyro.armored_shield.generate();
				
				GScreen.add_entity_to_map(pyro);
			}
		}
		//public void
}
