package com.midfag.game.script.actions;

import com.midfag.game.script.ScriptSystem;
import com.midfag.game.script.ScriptTimer;

public class ScriptActionAddTimer extends ScriptAction {


	
	public ScriptActionAddTimer(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
		boolean rec=false;
		if ((data.length>=5)&&(data[4].equals("recycle"))){rec=true;}
		ScriptSystem.Timer_pool.add(new ScriptTimer(data[1], data[2],Float.parseFloat(data[3]),rec));
		
		
	}

}
