package com.midfag.game.script.actions;

import java.util.List;

import com.midfag.entity.Entity;
import com.midfag.game.GScreen;
import com.midfag.game.script.ScriptSystem;


public class ScriptActionCameraTarget extends ScriptAction {
	public ScriptActionCameraTarget(String[] _data) {
		data=_data;
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
			List<Entity> l=ScriptSystem.find_entity(data[1]);
			
			if (l.size()>0)
			{GScreen.camera_target=l.get(0);}
			
	}
}
