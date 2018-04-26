package com.midfag.equip.energoshield;



public class EnergoshieldRobo extends Energoshield {
	

	
	public EnergoshieldRobo()
	{
		super();
		base_value=400;
		value=400;
		base_regen_speed=30;
		base_reflect=3;
		
		name="Roboshield";
		uid="shrobo";
		gennable=false;
		
		generate();
		update_attributes_bonus();

	}
	

}
