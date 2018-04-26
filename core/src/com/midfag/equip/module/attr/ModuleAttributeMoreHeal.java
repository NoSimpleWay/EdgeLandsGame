package com.midfag.equip.module.attr;

import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.module.ModuleUnitHeal;

public class ModuleAttributeMoreHeal extends ModuleAttribute {


	//public
	
	public ModuleAttributeMoreHeal()
	{
		cost=1;
		
		max_level=300;
		uid="maheal";
		name="”силенное восстановление";
	}
	
	@Override
	public void calculate(ModuleUnit _m)
	{
		System.out.println("CALCULATED");
		((ModuleUnitHeal)_m).total_immediate_heal+=((ModuleUnitHeal)_m).base_immediate_heal*((_m.level-1f)+0.03f*level);
		((ModuleUnitHeal)_m).total_gradual_heal+=((ModuleUnitHeal)_m).base_gradual_heal*((_m.level-1f)+0.03f*level);
	}
	
	@Override
	public String get_descr()
	{
		return 	"+"+3f*level+"% восстановление щита";
	}
}
