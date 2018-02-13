package com.midfag.game.script.actions;

import java.util.List;

import com.midfag.entity.Entity;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Localisation;
import com.midfag.game.GUI.ButtonDialogNext;
import com.midfag.game.GUI.DialogPool;
import com.midfag.game.GUI.GUIDialog;
import com.midfag.game.script.ScriptSystem;
import com.midfag.game.script.ScriptTimer;

public class ScriptConditionEntityCount extends ScriptAction {


	
	public ScriptConditionEntityCount(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		
		List<Entity> l=ScriptSystem.find_entity(data[1]);
		Helper.log("L SIZE <"+l.size()+">");
		if (
				(l.size()>=Integer.parseInt(data[2]))
				&&
				(l.size()<=Integer.parseInt(data[3]))
			)
		{
			
			
			ScriptSystem.execute_line=ScriptSystem.get_execute_line(data[4]);

		
		}
		else
		if (data.length>=6)
		{
			ScriptSystem.execute_line=ScriptSystem.get_execute_line(data[5]);
		}
		
		
	}

}
