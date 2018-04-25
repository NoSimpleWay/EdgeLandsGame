package com.midfag.game.GUI.edit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.GUI.buttons.Button;

public class ButtonFreezeTime extends Button {

	public Texture tex;
	public Texture tex2;
	public ButtonFreezeTime(float _x, float _y) {
		super(_x, _y);
		
		if (!GScreen.show_edit){need_remove=true;}
		size_x=55;
		size_y=55;
		
		spr.setSize(55, 55);
		
		tex=Assets.load("button_time_freeze_off");
		tex2=Assets.load("button_time_freeze_on");
		
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void second_draw()
	{
		if (!GScreen.time_freeze)
		{
			GScreen.batch_static.draw(tex, pos.x-25, pos.y-25);
		}
		else
		{
			GScreen.batch_static.draw(tex2, pos.x-25, pos.y-25);
		}
	}
	
	public void some_update(float _d)
	{
		if (!GScreen.show_edit){need_remove=true;}
		
		if ((is_overlap())&&(InputHandler.but==0))
		{
				InputHandler.but=-1;
				
				GScreen.time_freeze=!GScreen.time_freeze;
				
				if (GScreen.time_freeze)
				{GScreen.pl_human.time_slow_resist=0.999f; GScreen.pl_mech.time_slow_resist=0.999f;}
				else
				{GScreen.pl_human.time_slow_resist=0; GScreen.pl_mech.time_slow_resist=0;}
			
		}
	}

}
