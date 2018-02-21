package com.midfag.game.script.actions;

import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.script.ScriptSystem;

public class ScriptConditionPlayerInMech extends ScriptAction {


	
	public ScriptConditionPlayerInMech(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		
		if (GScreen.pl.equals(GScreen.pl_mech))
		{
			Helper.log("SCRIPT EXECUTION <"+data[1]+">");
			
			ScriptSystem.execute_line=ScriptSystem.get_execute_line(data[1]);

		
		}
		else
		if (data.length>=3)
		{
			ScriptSystem.execute_line=ScriptSystem.get_execute_line(data[2]);
		}
		
		
	}

}
