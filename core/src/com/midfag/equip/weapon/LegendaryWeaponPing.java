package com.midfag.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.missiles.Missile;
import com.midfag.entity.missiles.MissilePing;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Enums.Rarity;

public class LegendaryWeaponPing extends Weapon {
	
	
	
		public LegendaryWeaponPing()
		{
			super ();
			uid="weapon_ping";
			
			base_damage=5;
			base_missile_count=1;
			base_shoot_cooldown=0.03f;
			base_dispersion=8;
			base_dispersion_additional=3;
			base_ammo_size=120;
			base_reload_time=10/10;
			
			fire_multiplier=base_shoot_cooldown/base_missile_count;
			
			need_warm=7;
			
			rarity=Rarity.LEGENDARY;
			
			generate();
			update_attributes_bonus();
			
			name="-Ping 454.739.223.565";
			
			spr.setTexture(Assets.load("icon_firle"));
			spr.setSize(75, 40);
			
			model.setTexture(Assets.load("model_minigun_ping"));
			model.setSize(200, 90);
			
			 red_text="ѕревышен интервал ожидани€ дл€ запроса.";
			
			//System.out.println("Child class");
		}
		

		@Override
		public Missile get_missile(Entity pl)
		{
			return new MissilePing(
					new Vector2(pl.pos.x,pl.pos.y),
					(float) Math.toRadians(360-pl.rot+get_dispersion()+GScreen.rnd(add_disp)-add_disp/2),
					(GScreen.rnd(200)+400.0f),
					pl.is_AI);
		}
		
		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot02;
		}
		
		//public void
}
