package com.midfag.game.script.actions;

import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Localisation;
import com.midfag.game.GUI.ButtonDialogNext;
import com.midfag.game.GUI.ButtonDialogSelect;
import com.midfag.game.GUI.GUIDialog;
import com.midfag.game.GUI.buttons.Button;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionAddDialogSelectButton extends ScriptAction {
	

	public ScriptActionAddDialogSelectButton(String[] _data) {
		data=_data;
		// TODO Auto-generated constructor stub
//		name=_name;
		

	}
	
	@Override
	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");

		Button dialog_button=new ButtonDialogSelect
									(
										500,
										57,
										ScriptSystem.last_dialog_gui,
										Localisation.get_value_from_id(data[1]),
										data[2]
									);
		
		ScriptSystem.last_dialog_gui.add_variant_button(dialog_button);
	}

}
