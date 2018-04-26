package com.midfag.equip.module.attr;

import com.midfag.equip.module.LegendaryModuleUnitEXPLOSIONS;
import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.module.ModuleUnitPush;

public class ModuleAttributeBurnDamage extends ModuleAttribute {


	//public
	
	public ModuleAttributeBurnDamage()
	{
		cost=1;
		
		max_level=1000;
		uid="mabd";
		name="Урон от горения";
	}
	
	@Override
	public void calculate(ModuleUnit _m)
	{
		System.out.println("CALCULATED");
		((LegendaryModuleUnitEXPLOSIONS)_m).total_burn+=((LegendaryModuleUnitEXPLOSIONS)_m).base_burn*(level/20.0f);
	}
	
	@Override
	public String get_descr()
	{
		return 	"Улучшает урон от горения на "+5*level+"%";
	}
}
