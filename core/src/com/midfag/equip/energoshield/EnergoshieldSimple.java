package com.midfag.equip.energoshield;



public class EnergoshieldSimple extends Energoshield {
	

	
	public EnergoshieldSimple()
	{
		base_value=500;
		base_regen_speed=15;
		base_reflect=4;
		
		name="Sample shield";
		uid="shsimple";
		
		generate();
		update_attributes_bonus();
		
	}
	

}
