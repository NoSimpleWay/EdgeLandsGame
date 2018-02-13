package com.midfag.game.script.actions;

import com.midfag.game.GScreen;

public class ScriptActionCameraAutoZoom extends ScriptAction {
	
	public ScriptActionCameraAutoZoom (String[] _data) {
		data=_data;
		// TODO Auto-generated constructor stub
		
//		name=_name;
		

	}
	
	@Override
	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
			if (data[1].equals("on"))
			{GScreen.camera_auto_zoom=true;}
			else
			{GScreen.camera_auto_zoom=false;}
	}
}
