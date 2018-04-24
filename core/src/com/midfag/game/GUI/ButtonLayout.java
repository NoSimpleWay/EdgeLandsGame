package com.midfag.game.GUI;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.midfag.entity.Entity;
import com.midfag.game.Enums.LayoutOffsetXType;
import com.midfag.game.Enums.LayoutOffsetYType;
import com.midfag.game.Enums.LayoutPositionXType;
import com.midfag.game.Enums.LayoutPositionYType;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.GUI.buttons.Button;
import com.midfag.game.GUI.world_debug.WorldDebug;
import com.midfag.game.script.ScriptSystem;

public class ButtonLayout {
	
	public float pos_x;
	public float pos_y;
	
	public float real_pos_x;
	public float real_pos_y;
	
	public float offset_x;
	public float offset_y;
	
	public List<Button> buttons = new ArrayList<Button>();
	
	public LayoutPositionXType lpx;
	public LayoutPositionYType lpy;
	
	public LayoutOffsetXType lox;
	public LayoutOffsetYType loy;
	
	public boolean need_remove=false;
	
	float container_width;
	float container_height;
	
	public void update(float _d)
	{
		if (!GScreen.show_edit)
		{
			need_remove=true;
		}
		
		container_width=buttons.size()*offset_x;
		container_height=buttons.size()*offset_y+buttons.get(0).size_y;
		
		for (int i=0; i<buttons.size(); i++)
		{
			Button b=buttons.get(i);
			
			if (lpx==LayoutPositionXType.FREE)
			{
				real_pos_x=pos_x;
			}
			else
			if (lpx==LayoutPositionXType.RIGHT_CORNER)
			{
				real_pos_x=GScreen.scr_w-pos_x;
			}
			else
			if (lpx==LayoutPositionXType.LEFT_CORNER)
			{
				real_pos_x=pos_x;
			}
			
			if (lox==LayoutOffsetXType.LEFT)
			{
				b.pos.x=real_pos_x+i*offset_x+buttons.get(0).size_x/2f;
			}
			else
			if (lox==LayoutOffsetXType.CENTER)
			{
				b.pos.x=real_pos_x+i*offset_x+buttons.get(0).size_x/2f-container_width/2f;
			}
			else
			if (lox==LayoutOffsetXType.RIGHT)
			{
				b.pos.x=real_pos_x+i*offset_x+buttons.get(0).size_x/2f-container_width;
			}
			
			
			

			if (lpy==LayoutPositionYType.FREE)
			{
				real_pos_y=pos_y;
			}
			else
			if (lpy==LayoutPositionYType.TOP_CORNER)
			{
				real_pos_y=pos_y;
			}
			
			
			if (lpy==LayoutPositionYType.FREE)
			{
				b.pos.y=real_pos_y+i*offset_y;
			}
			else
			if (lpy==LayoutPositionYType.TOP_CORNER)
			{
				b.pos.y=GScreen.scr_h-real_pos_y+i*offset_y-buttons.get(0).size_y/2f;
			}
		}
		
		
	}
	
	public void pre_draw(float _d)
	{
		
		
		float x = 0;
		float y = 0;
		
		if (lox==LayoutOffsetXType.LEFT)
		{
			x=real_pos_x;
		}
		
		if (lox==LayoutOffsetXType.CENTER)
		{
			x=real_pos_x-container_width/2f;
		}
		
		if (lox==LayoutOffsetXType.RIGHT)
		{
			x=real_pos_x-container_width;
		}
		
		if (lpy==LayoutPositionYType.FREE) {y=real_pos_y-container_height/2f;}
		if (lpy==LayoutPositionYType.TOP_CORNER) {y=GScreen.scr_h-container_height-real_pos_y;}
		
		GScreen.batch_static.setColor(0,0,0,0.5f);
		GScreen.batch_static.draw(Assets.rect_white, x-4, y-4,container_width+7,container_height+7);
		
		GScreen.batch_static.setColor(1,1,1,0.5f);
		GScreen.batch_static.draw(Assets.rect_white, x-2, y-2,container_width+4,container_height+4);
		
		GScreen.batch_static.setColor(Color.WHITE);
	}
	
	public void post_draw(float _d)
	{
		
			GScreen.batch_static.setColor(Color.RED);
			GScreen.batch_static.draw(Assets.rect_white, pos_x, pos_y,3,3);
			GScreen.batch_static.setColor(Color.WHITE);
		
	}
	

}
