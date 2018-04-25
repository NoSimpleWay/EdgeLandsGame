package com.midfag.game.skills.weapon_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.entity.Entity;
import com.midfag.entity.missiles.Missile;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.skills.Skill;

public class SkillFire_A_ExplodingFire extends Skill {
	
	

	public SkillFire_A_ExplodingFire()
	{
		super();
		
		pos.x=-100;
		pos.y=40;
		
		spr.setTexture(Assets.load("icon_exploding_fire"));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="Взрывающийся огонь!";
		info=	"При атаке горящего противника есть 10% шанс"+"\n"+"потушить его, но нанести урон, зависящий от "+"\n"+"силы горения противника";
		
		//skill_a=new SkillShield_A_MoreValue();
		
	}
	
	@Override
	public void missile_hit_action(Entity _master, Entity _target, Missile _mis)
	{
		//if (Math.random()<0.1f) {Assets.expl.play(); _target.hit_action(_target.buff_burn*10f,false); _target.buff_burn=0;}
	}
	
	@Override
	public void weapon_gen_action(Weapon _w)
	{
		//_w.total_fire_damage+=_w.fire_multiplier*2f;
	}
	
	@Override
	public void learn_action()
	{
		((SkillFire)parent).is_exploding=true;
		
		/*
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
		*/
	}
	
}
