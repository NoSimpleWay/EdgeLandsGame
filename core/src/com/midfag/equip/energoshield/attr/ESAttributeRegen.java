package com.midfag.equip.energoshield.attr;

import com.midfag.equip.energoshield.Energoshield;

public class ESAttributeRegen extends ESAttribute {
	

	
	public ESAttributeRegen()
	{
		name="regeneration";
		uid="reg";
		cost=2.0f;
		max_level=100;
	}
	
	@Override
	public void calculate(Energoshield _e)
	{
		_e.total_regen_speed+=_e.base_regen_speed*(level*0.02f)+level*0.5f+_e.base_regen_speed*(_e.level-1);
	}
	
	@Override
	public String get_descr() {
		// TODO Auto-generated method stub
		return "+"+level*2f+"% скорость регенерации +"+level*0.5f+"/сек скорость регенерации";
	}
}
