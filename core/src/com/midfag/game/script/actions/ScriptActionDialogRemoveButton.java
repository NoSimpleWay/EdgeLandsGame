package com.midfag.game.script.actions;

import com.midfag.game.GScreen;
import com.midfag.game.GUI.ButtonDialogNext;
import com.midfag.game.GUI.ButtonDialogSelect;
import com.midfag.game.script.ScriptSystem;

public class ScriptActionDialogRemoveButton extends ScriptAction {


	
	public ScriptActionDialogRemoveButton(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
			if (data[1].equals("standart"))
			for (int i=0; i<GScreen.Button_list.size(); i++)
			{
				if (GScreen.Button_list.get(i) instanceof ButtonDialogNext)
				{
					GScreen.Button_list.get(i).need_remove=true;
				}
			}
			
			if (data[1].equals("select"))
			for (int i=0; i<ScriptSystem.last_dialog_gui.Button_list.size(); i++)
			{
				if (ScriptSystem.last_dialog_gui.Button_list.get(i) instanceof ButtonDialogSelect)
				{
					ScriptSystem.last_dialog_gui.Button_list.remove(i);
					i--;
				}
			}
		

		
	}

}
