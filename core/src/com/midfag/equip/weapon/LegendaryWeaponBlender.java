package com.midfag.equip.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.missiles.Missile;

import com.midfag.game.Assets;
import com.midfag.game.GScreen;

public class LegendaryWeaponBlender extends Weapon {
	
	
	
		public LegendaryWeaponBlender()
		{
			uid="blender";
			
			base_damage=10;
			base_missile_count=15;
			base_shoot_cooldown=0.025f;
			base_dispersion=8;
			base_dispersion_additional=3;
			base_ammo_size=120;
			base_reload_time=1;
			
			fire_multiplier=base_shoot_cooldown/base_missile_count;
			
			need_warm=3;
			
			generate();
			update_attributes_bonus();
			
			name="Áëåíäåğ";
			
			missile_speed=1500;
			
			spr.setTexture(Assets.load("icon_legendary_ping_minigun"));
			
			red_text="İÒÎ ÂÎÎÁÙÅ ÇÀÊÎÍÍÎ?";
		}
		

		@Override
		public Missile get_missile(Entity pl)
		{
			return new Missile(
					new Vector2(pl.pos.x,pl.pos.y),
					(float) Math.toRadians(360-pl.rot+get_dispersion()+GScreen.rnd(add_disp)-add_disp/2),
					(GScreen.rnd(200)+missile_speed),
					pl.is_AI);
		}
		
		@Override
		public Sound get_shoot_sound()
		{
			return Assets.shoot02;
		}
		
		//public void
}
