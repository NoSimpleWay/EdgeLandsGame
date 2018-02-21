package com.midfag.game.script.actions;

import com.midfag.game.script.ScriptSystem;
import com.midfag.game.script.ScriptTimer;

public class ScriptActionTimerAction extends ScriptAction {


	
	public ScriptActionTimerAction(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
		boolean bool=false;
		
		for (ScriptTimer st:ScriptSystem.Timer_list)
		if(st.name.equals(data[1]))
		{
			if ((data.length>=4)&&(data[3].equals("on"))){bool=true;}
			
			if (data[2].equals("remove")){st.need_remove=true;}
		}
		
		
	}

}
