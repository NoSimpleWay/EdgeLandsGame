package com.midfag.equip.module.attr;

import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.module.ModuleUnitHeal;
import com.midfag.equip.module.ModuleUnitOverload;

public class ModuleAttributeMoreBonusReloadSpeed extends ModuleAttribute {


	//public
	
	public ModuleAttributeMoreBonusReloadSpeed()
	{
		cost=1;
		
		max_level=300;
		uid="mabrs";
		name="Усиленное ускорение перезарядки оружия";
	}
	
	@Override
	public void calculate(ModuleUnit _m)
	{
		//System.out.println("CALCULATED");
		((ModuleUnitOverload)_m).total_reload_speed+=((ModuleUnitOverload)_m).base_reload_speed*((_m.level-1f)+0.02f*level);
		((ModuleUnitOverload)_m).total_self_damage+=((ModuleUnitOverload)_m).base_self_damage*((_m.level-1f)+0.02f*level);
	}
	
	@Override
	public String get_descr()
	{
		return 	"+"+2f*level+"% ускорение перезарядки оружия, "+2f*level+"% усиление получаемого урона от модуля";
	}
}
