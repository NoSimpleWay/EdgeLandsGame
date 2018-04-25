package com.midfag.game.skills.weapon_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.entity.AnimationEffectExpl;
import com.midfag.entity.Entity;
import com.midfag.entity.missiles.Missile;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.skills.Skill;

public class SkillFire extends Skill {
	
	public boolean is_exploding=false;
	private int explode_cooldown;

	public SkillFire()
	{
		super();
		
		pos.x=-60;
		pos.y=40;
		
		spr.setTexture(Assets.load("icon_fire"));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="Пироман";
		info=	"20 урона от горения к выстрелам";
		
		//skill_a=new SkillShield_A_MoreValue();
		
	}
	
	@Override
	public void missile_hit_action(Entity _master, Entity _target, Missile _mis)
	{
		//Helper.log("missile_hit_action");
		if ((is_exploding)&&(Math.random()<0.1f)&&(cooldown<=0))
		{
			Assets.expl.play(0.1f);
		_target.hit_action(_target.buff_burn*20f,false);
		_target.buff_burn=0;
		_target.Effect.add(new AnimationEffectExpl(_target.pos,-_target.spr.getOriginX(),-_target.spr.getOriginY()));
		
		cooldown=3.0f;
		}
		
		
	}
	
	@Override
	public void weapon_gen_action(Weapon _w)
	{
		_w.total_fire_damage+=_w.fire_multiplier*2f;
	}
	
	/*
	@Override
	public void update(float _d)
	{
		
	}
	*/
	
	@Override
	public void learn_action()
	{
		
		System.out.println("!@#$%^&");
		if (GScreen.pl.armored[0]!=null)GScreen.pl.armored[0].update_attributes_bonus(GScreen.pl);
		if (GScreen.pl.armored[1]!=null)GScreen.pl.armored[1].update_attributes_bonus(GScreen.pl);
		
		for (int i=0; i<GScreen.pl.inventory.length; i++)
		{
			if (GScreen.pl.inventory[i] instanceof Weapon)
			{
				((Weapon)GScreen.pl.inventory[i]).update_attributes_bonus(GScreen.pl);
			}
		}
	}
	
}
