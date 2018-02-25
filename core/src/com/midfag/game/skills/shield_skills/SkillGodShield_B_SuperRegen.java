package com.midfag.game.skills.shield_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.game.GScreen;
import com.midfag.game.skills.Skill;

public class SkillGodShield_B_SuperRegen extends Skill {
	
	

	public SkillGodShield_B_SuperRegen()
	{
		super();
		
		pos.x=-70;
		pos.y=-70;
		
		spr.setTexture(new Texture(Gdx.files.internal("skill_shield_of_god.png")));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="Божий реген";
		info=	"+250% реген ";
		
		//skill_a=new SkillShield_A_MoreValue();
		
	}
	
	@Override
	public void shield_gen_action(Energoshield _e)
	{
		_e.total_regen_speed+=+_e.base_regen_speed*2.5f;
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
