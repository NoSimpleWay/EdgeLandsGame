package com.midfag.equip.energoshield.attr;

import com.midfag.equip.energoshield.Energoshield;


public class ESAttributeReflect extends ESAttribute {
	

	
	public ESAttributeReflect()
	{
		name="regeneration";
		uid="refl";
		cost=1;
		max_level=100;
	}
	
	@Override
	public void calculate(Energoshield _e)
	{
		_e.total_reflect+=(level*0.2f);
		_e.total_reflect+=_e.base_reflect*level*0.02f;
		_e.total_reflect+=_e.base_reflect*(_e.level-1f);
	}
	
	@Override
	public String get_descr() {
		// TODO Auto-generated method stub
		return "+"+level*2f+"%/+"+level*0.2+" уровень отражения";
	}
}
