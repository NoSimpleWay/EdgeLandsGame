package com.midfag.equip.module.attr;

import com.midfag.equip.module.LegendaryModuleUnitEXPLOSIONS;
import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.module.ModuleUnitPush;

public class ModuleAttributeSelfDamageProtect extends ModuleAttribute {


	//public
	
	public ModuleAttributeSelfDamageProtect()
	{
		cost=1;
		
		max_level=500;
		name="Уменьшение ущерба";
	}
	
	@Override
	public void calculate(ModuleUnit _m)
	{
		System.out.println("CALCULATED");
		((LegendaryModuleUnitEXPLOSIONS)_m).self_defence+=(level*5)/(level*5+100f);
	}
	
	@Override
	public String get_descr()
	{
		return 	"Уменьшает вред от использования на "+((level*5)/(level*5+100f)*100f)+"%";
	}
}
