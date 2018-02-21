package com.midfag.game.script.actions;

import java.util.List;

import com.midfag.entity.Entity;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionEntityTarget extends ScriptAction {


	
	public ScriptActionEntityTarget(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		List<Entity> l=ScriptSystem.find_entity(data[1]);
		List<Entity> t=ScriptSystem.find_entity(data[2]);
		
		if (t.size()>0)
		for (Entity ent:l)
		{
			ent.target=t.get(0);
		}
	}

}
