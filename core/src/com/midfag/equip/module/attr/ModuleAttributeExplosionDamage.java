package com.midfag.equip.module.attr;

import com.midfag.equip.module.LegendaryModuleUnitEXPLOSIONS;
import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.module.ModuleUnitPush;

public class ModuleAttributeExplosionDamage extends ModuleAttribute {


	//public
	
	public ModuleAttributeExplosionDamage()
	{
		cost=1;
		
		max_level=100;
		uid="maexdm";
		name="Урон от взрыва";
	}
	
	@Override
	public void calculate(ModuleUnit _m)
	{
		System.out.println("CALCULATED");
		((LegendaryModuleUnitEXPLOSIONS)_m).total_damage+=((LegendaryModuleUnitEXPLOSIONS)_m).base_damage*(level/20.0f);
	}
	
	@Override
	public String get_descr()
	{
		return 	"Улучшает урон от взрыва на "+5*level+"%";
	}
}
