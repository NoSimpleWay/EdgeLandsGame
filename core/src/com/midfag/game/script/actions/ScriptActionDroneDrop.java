package com.midfag.game.script.actions;

import java.util.List;

import com.midfag.entity.Entity;
import com.midfag.entity.enemies.EntityTransportDrone;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionDroneDrop extends ScriptAction {


	
	public ScriptActionDroneDrop(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		List<Entity> l=ScriptSystem.find_entity(data[1]);
		
		for (Entity ent:l)
		{
			((EntityTransportDrone) ent).drop_container(Integer.parseInt(data[2]));
		}
	}

}
