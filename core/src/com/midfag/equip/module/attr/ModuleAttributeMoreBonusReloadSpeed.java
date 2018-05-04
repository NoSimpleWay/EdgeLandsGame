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
		((ModuleUnitOverload)_m).total_reload_speed+=((ModuleUnitOverload)_m).base_reload_speed*(0.01f*level);
		((ModuleUnitOverload)_m).total_self_damage+=((ModuleUnitOverload)_m).base_self_damage*(0.01f*level);
	}
	
	@Override
	public String get_descr()
	{
		return 	"+"+1f*level+"% ускорение перезарядки оружия, "+1f*level+"% усиление получаемого урона от модуля";
	}
}
