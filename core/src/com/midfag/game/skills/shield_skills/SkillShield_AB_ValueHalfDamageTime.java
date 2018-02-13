package com.midfag.game.skills.shield_skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.midfag.entity.Entity;
import com.midfag.game.skills.Skill;

public class SkillShield_AB_ValueHalfDamageTime extends Skill {
	public SkillShield_AB_ValueHalfDamageTime()
	{
		super();
		
		pos.x=75;
		pos.y=00;
		
		spr.setTexture(new Texture(Gdx.files.internal("icon_half_damage.png")));
		spr.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		name="������ �����������";
		info="������ 5 ������"+"\n"+"���������� ���� ���������� �����."+"\n"+"��������� 2 �������";
		
		need_to_indicate=true;
		indicate_tex=new Texture(Gdx.files.internal("icon_half_damage.png"));
		
		cooldown_base=5;
		cooldown=5;
		
		duration_base=5;
		
	}
	
	@Override
	public float damage_action(Object _o, float _damage)
	{
		if ((_o instanceof Entity)&&(duration>0)){return _damage/2f;}
		return _damage;
	}

}
