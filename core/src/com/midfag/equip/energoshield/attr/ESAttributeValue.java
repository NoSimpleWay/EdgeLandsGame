package com.midfag.equip.energoshield.attr;

import com.midfag.equip.energoshield.Energoshield;

public class ESAttributeValue extends ESAttribute {
	

	
	public ESAttributeValue()
	{
		name="shield";
		cost=2;
		max_level=100;
	}
	
	@Override
	public void calculate(Energoshield _e)
	{
		_e.total_value+=_e.base_value*(level*0.02f);
		_e.total_value+=(level*5f);
	}
	
	@Override
	public String get_descr() {
		// TODO Auto-generated method stub
		return "+"+level*2+"% ёмкость щита/+"+(level*5f);
	}
}
