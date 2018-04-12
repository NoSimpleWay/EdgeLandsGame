package com.midfag.game.GUI.edit;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.InputHandler;

import com.midfag.game.GUI.buttons.Button;

public class ButtonLoadMap extends Button {
	
	public Texture tex;
	
	public String[] ss=new String[100];
	public GUIEdit gui;
	
	public ButtonLoadMap(float _x, float _y)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
		
		tex=new Texture(Gdx.files.internal("button_load_map.png"));
		//gui=_gui;
	}
	
	@Override
	public void second_draw()
	{
		
	}
	
	@Override
	public void after_draw()
	{
		GScreen.batch_static.setColor(Color.WHITE);
		GScreen.batch_static.draw(tex, pos.x-22, pos.y-22);
	}
	
	
	@Override
	public void some_update(float _d)
	{
		if (!GScreen.show_edit)
		{
			need_remove=true;
			//GScreen.Button_list.remove(this);
		}
		
		if ((InputHandler.but==0)&&(is_overlap()))
		{
			Helper.LoadMap();
			
			//System.out.println("LOLWHUT "+"WTF".substring(1, 3));
			
			InputHandler.but=-1;
			
			
		}
	}
}
