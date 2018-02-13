package com.midfag.game.script.actions;

import java.util.List;

import com.midfag.entity.Entity;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Localisation;
import com.midfag.game.GUI.ButtonDialogNext;
import com.midfag.game.GUI.ButtonDialogSelect;
import com.midfag.game.GUI.DialogPool;
import com.midfag.game.GUI.GUIDialog;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionAddEntityToMap extends ScriptAction {


	
	public ScriptActionAddEntityToMap(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		Entity en=Helper.get_object_from_id(data[1]);
		en.pos.x=Integer.parseInt(data[2]);
		en.pos.y=Integer.parseInt(data[3]);
		en.init("script");
		GScreen.add_entity_to_map(en);
		
		if (data.length>=5)
		{
			en.id_for_script=data[4];
			ScriptSystem.Entity_with_id_list.add(en);
		}
	}

}
