package com.midfag.game.skills.shield_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.skills.Skill;

public class SkillGodShield extends Skill {
	
	

	public SkillGodShield()
	{
		super();
		
		pos.x=170;
		pos.y=215;
		
		spr.setTexture(Assets.load("skill_shield_of_god"));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="Божья силушка";
		info=	"+250% ёмкость "+"\n"+
				"+250% регенерация"+"\n"+
				"+250% отражение";
		
		//skill_a=new SkillShield_A_MoreValue();
		
	}
	
	@Override
	public void shield_gen_action(Energoshield _e)
	{
		_e.total_value+=_e.base_value*2.5f;
		_e.total_regen_speed+=_e.base_regen_speed*2.5f;
		_e.total_reflect+=_e.base_reflect*2.5f;
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
				((Energoshield)GScreen.pl.inventory[i]).update_attributes_bonus();
			}
		}
	}
	
}
