package com.midfag.game.GUI.edit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.GUI.buttons.Button;

public class ButtonChunkInfo extends Button {

	public Texture tex;
	
	public ButtonChunkInfo(float _x, float _y) {
		super(_x, _y);
		
		if (!GScreen.show_edit){need_remove=true;}
		size_x=55;
		size_y=55;
		
		spr.setSize(55, 55);
		
		tex=Assets.load("button_chunk_info");
		
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void second_draw()
	{
		GScreen.batch_static.draw(tex, pos.x-25, pos.y-25);
	}
	
	public void some_update(float _d)
	{
		if (!GScreen.show_edit){need_remove=true;}
		
		if ((is_overlap())&&(InputHandler.but==0))
		{
				InputHandler.but=-1;
				
				GScreen.chunk_info=!GScreen.chunk_info;
			
		}
	}

}
