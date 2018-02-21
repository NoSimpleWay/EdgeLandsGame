package com.midfag.game.script.actions;

import java.util.List;

import com.midfag.entity.Entity;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.script.ScriptSystem;

public class ScriptConditionDistantionToEntity extends ScriptAction {


	
	public ScriptConditionDistantionToEntity(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		
		List<Entity> l=ScriptSystem.find_entity(data[1]);
		Float dist=l.get(0).pos.dst(GScreen.pl.pos);
		
		if (
				(dist>=Integer.parseInt(data[2]))
				&&
				(dist<=Integer.parseInt(data[3]))
			)
		{
			Helper.log("SCRIPT EXECUTION <"+data[4]+">");
			
			ScriptSystem.execute_line=ScriptSystem.get_execute_line(data[4]);

		
		}
		else
		if (data.length>=6)
		{
			ScriptSystem.execute_line=ScriptSystem.get_execute_line(data[5]);
		}
		
		
	}

}
