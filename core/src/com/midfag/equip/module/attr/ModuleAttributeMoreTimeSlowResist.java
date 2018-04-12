package com.midfag.equip.module.attr;

import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.module.ModuleUnitPush;
import com.midfag.equip.module.ModuleUnitTimeStop;

public class ModuleAttributeMoreTimeSlowResist extends ModuleAttribute {


	//public
	
	public ModuleAttributeMoreTimeSlowResist()
	{
		cost=10;
		
		max_level=5;
		name="Сопротивление времени";
	}
	
	@Override
	public void calculate(ModuleUnit _m)
	{
		System.out.println("CALCULATED");
		((ModuleUnitTimeStop)_m).total_time_slow_resist+=(level*0.1f);
	}
	
	@Override
	public String get_descr()
	{
		return 	"+ сопротивление эффекту замедления времени "+10*level+"%";
	}
}
