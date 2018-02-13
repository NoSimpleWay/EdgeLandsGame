package com.midfag.equip.energoshield.attr;

import com.midfag.equip.energoshield.Energoshield;


public class ESAttributeReflect extends ESAttribute {
	

	
	public ESAttributeReflect()
	{
		name="regeneration";
		cost=1;
		max_level=100;
	}
	
	@Override
	public void calculate(Energoshield _e)
	{
		_e.total_reflect=_e.base_reflect+(level*1f);
	}
	
	@Override
	public String get_descr() {
		// TODO Auto-generated method stub
		return "+"+level+" ������� ���������";
	}
}
