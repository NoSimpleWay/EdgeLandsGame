package com.midfag.game.script;

public class ScriptTimer {
	
	public boolean need_remove=false;
	public String name;
	public float timer;
	public float base_timer;
	public String entry_point;
	public boolean recycle=false;
	
	public ScriptTimer(String _name, String _entry, float _time, boolean _rec) {
		// TODO Auto-generated constructor stub
		name=_name;
		entry_point=_entry;
		timer=_time;
		base_timer=timer;
		recycle=_rec;
	}
	

	//timer shuimer
}
