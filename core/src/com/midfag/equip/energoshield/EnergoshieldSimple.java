package com.midfag.equip.energoshield;



public class EnergoshieldSimple extends Energoshield {
	

	
	public EnergoshieldSimple()
	{
		base_value=500;
		base_regen_speed=50;
		base_reflect=10;
		
		name="Sample shield";
		
		level=1;
		
		generate();
		update_attributes_bonus();
		
	}
	

}
