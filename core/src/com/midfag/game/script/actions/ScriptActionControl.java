package com.midfag.game.script.actions;

import com.midfag.game.GScreen;

public class ScriptActionControl extends ScriptAction {
	

	public ScriptActionControl(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
//		name=_name;
		

	}
	
	@Override
	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
		/*GScreen.main_control=false;
		GScreen.pl.active=false;*/

		
		boolean control=true;
		if (data[2].equals("off")){control=false;}
		
		if (data[1].equals("main")){GScreen.main_control=control;}
		if (data[1].equals("player")){GScreen.pl.active=control;}
		
	}

}
