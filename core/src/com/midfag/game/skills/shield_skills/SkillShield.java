package com.midfag.game.skills.shield_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.skills.Skill;

public class SkillShield extends Skill {
	
	

	public SkillShield()
	{
		super();
		
		maxlevel=5;
		pos.x=100;
		pos.y=100;
		
		spr.setTexture(Assets.load("skill_shield"));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="Апгрейд щитов";
		info=	"+"+50+" ёмкость "+"\n"+
				"+"+10+" регенерация"+"\n"+
				"+"+5+" отражение";
		
		//skill_a=new SkillShield_A_MoreValue();
		
	}
	
	@Override
	public void update_info()
	{
		name="Апгрейд щитов";
		info=	"+"+50*level+" ёмкость "+"\n"+
				"+"+10*level+" регенерация"+"\n"+
				"+"+5*level+" отражение";
	}
	
	@Override
	public void shield_gen_action(Energoshield _e)
	{
		_e.total_value+=50*level;
		_e.total_regen_speed+=10*level;
		_e.total_reflect+=5*level;
	}
	
	@Override
	public void learn_action()
	{
		
		System.out.println("!@#$%^&");
		GScreen.pl.armored_shield.update_attributes_bonus(GScreen.pl);
		
		for (int i=0; i<GScreen.pl.inventory.length; i++)
		{
			if (GScreen.pl.inventory[i] instanceof Energoshield)
			{
				((Energoshield)GScreen.pl.inventory[i]).update_attributes_bonus(GScreen.pl);
			}
		}
	}
	
}
