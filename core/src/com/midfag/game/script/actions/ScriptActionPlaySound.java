package com.midfag.game.script.actions;

import com.badlogic.gdx.audio.Sound;
import com.midfag.game.Assets;

public class ScriptActionPlaySound extends ScriptAction {


	
	public ScriptActionPlaySound(String[] _data) {
		// TODO Auto-generated constructor stub
		data=_data;
	}

	public void action()
	{
		//Helper.log("SCRIPT SAY <"+say+">");
		try {
			Sound sou=(Sound) Assets.class.getField(data[1]).get(null);
			sou.play();
			//Assets.gate.play();
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
