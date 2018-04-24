package com.midfag.game.GUI.edit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.entity.Entity;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Main;
import com.midfag.game.GUI.buttons.Button;

public class ButtonSlider extends Button {

	public Texture[] tex=new Texture[5];
	public float anim=0;
	public Color col=new Color(Color.WHITE);
	public float min;
	public float max;
	public float step;
	public float value;
	public Entity e;
	
	public boolean change=false;
	
	public boolean hold=false;
	
	
	//public Texture tex;
	
	
	public ButtonSlider(float _x, float _y, float _min, float _max, float _step)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
		
		min=_min;
		max=_max;
		step=_step;
		//e=_e;
		
		size_x=200;
	
		
		for (int i=0; i<5; i++)
		{tex[i]=new Texture(Gdx.files.internal("gui/slider/slider"+i+".png"));}
	}
	
	@Override
	public void second_draw()
	{
		
	}
	
	@Override
	public void after_draw()
	{
		GScreen.batch_static.setColor(col);
		if (hold) {GScreen.batch_static.setColor(0.2f,0.2f,0.2f,1f);}
		
		float shake=0;
		if (is_overlap()&&(InputHandler.but==0)) {shake=InputHandler.dx/10f;}
		GScreen.batch_static.draw(tex[(int)(anim)], pos.x-100+shake, pos.y-12);
		
		Main.font.setColor(col);
		Main.font.draw(GScreen.batch_static, ""+value, pos.x, pos.y);
		GScreen.batch_static.setColor(Color.WHITE);
	}
	
	//@SuppressWarnings("static-access")
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
			//InputHandler.but=-1;
			
			hold=true;
			
			
			
			//Gdx.input.setCursorPosition((int)(pos.x), (int)(GScreen.scr_h-pos.y));
		}
		else
		{
			change=false;
		}
		
		if ((InputHandler.but!=0))
		{
			hold=false;
		}
		
		if (hold)
		{
			if ((value+InputHandler.dx*step>min)&&(value+InputHandler.dx*step<max)) {anim+=InputHandler.dx*0.5f;}
			if (anim<0) {anim=4;}
			if (anim>4) {anim=0;}
			
			value+=InputHandler.dx*step;
			
			if (value>max) {value=max;}
			if (value<min) {value=min;}
			
			change=true;
		}
		
	}
	
}
