package com.midfag.game.GUI;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.entity.Entity;
import com.midfag.game.Assets;
import com.midfag.game.GScreen;
import com.midfag.game.Main;
import com.midfag.game.GUI.buttons.Button;

public class GUIDialog extends GUI {
	
	public Texture dialog_texture=Assets.dialog_texture;
	
	public List<DialogPool> dialog_pool=new ArrayList<DialogPool>();
	public List<DialogPool> fly_dialog_pool=new ArrayList<DialogPool>();
	
	public int current_pool=0;
	public String exit_point;
	//public Sprite spr=new Sprite(new Texture(Gdx.files.internal("dialog_arrow.png")));
	public boolean remove_if_end;
	
	public List<Button> button_pool=new ArrayList<Button>();

	public boolean black_noise=false;
	
	//public List<>
	
	public GUIDialog()
	{
		//spr.setOrigin(10, 5);
	}
	
	public void draw_dialog(Entity _e, String _text)
	{
		float px=(_e.pos.x-GScreen.camera.position.x)/GScreen.camera.zoom+GScreen.scr_w/2f;
		
		px=Math.max(400, px);
		px=Math.min(GScreen.scr_w-300, px);
		
		float py=(_e.pos.y-GScreen.camera.position.y)/GScreen.camera.zoom+GScreen.scr_h/2f;

		
		if (_e.pos.x-GScreen.camera.position.x>0)
		{px+=0;}else{px-=350;}
		
		if (_e.pos.y-GScreen.camera.position.y>0)
		{py+=30;}else{py-=130;}
		py=Math.max(200,py);
		py=Math.min(GScreen.scr_h-150, py);
		
		
		
		
		GScreen.batch_static.draw(dialog_texture,px,py,320,130);
		
		Main.font.draw(GScreen.batch_static, _text,px+10,py+117,290,-1,true);
		
	}
	
	public void sub_update(float _d) {
		
		/*
		for (DialogPool pool:dialog_pool)
		{
			
		}
		*/
		if (!GScreen.show_dialog)
		{
			GScreen.GUI_list.remove(this);
		}
		
		
		Main.font.setColor(Color.BLACK);
		
		if (current_pool<dialog_pool.size())
		{
			
			if (dialog_pool.get(current_pool).black_noise)
			{
				GScreen.batch_static.setColor(Color.BLACK);
				Main.font.setColor(Color.WHITE);
				GScreen.batch_static.setShader(Main.shader);
				
				Main.shader.setUniformf("x", 0);
				Main.shader.setUniformf("y", 0);
				
				Main.shader.setUniformf("uTime", GScreen.wave_time*77);
		    	Main.shader.setUniformf("zoom",1);
			}
			else
			{
				GScreen.batch_static.setColor(Color.WHITE);
				Main.font.setColor(Color.BLACK);
				GScreen.batch_static.setShader(Main.shader_default);
			}
			
			if (dialog_pool.get(current_pool).entity==null)
			{
				GScreen.batch_static.draw(dialog_texture, 17, 50,GScreen.scr_w-34,93);
				
				Main.font.draw
							(
								GScreen.batch_static,
								dialog_pool.get(current_pool).text,
								37,
								125,
								GScreen.scr_w-74,
								1,
								true
							);
			}
			else
			{
				
				
				draw_dialog(dialog_pool.get(current_pool).entity,dialog_pool.get(current_pool).text);
				
			}
		}
		

		
		for (Button but:button_pool)
		{
			but.update(_d);
			but.draw();
			
		}
		
		for (DialogPool pool:fly_dialog_pool)
		{
			pool.indicate_timer-=_d;
			if ((pool.timer>0)&&(pool.indicate_timer<=0))
			{
				draw_dialog(pool.entity,pool.text);
				pool.timer-=_d;
			}
			
		}
		
		for (int i=0; i<fly_dialog_pool.size(); i++)
		{
			if (fly_dialog_pool.get(i).timer<=0)
			{
				fly_dialog_pool.remove(i);
				i--;
			}
		}
		
	}
	
	public void add_variant_button(Button _b)
	{
		Button_list.add(_b);
		float pos=Button_list.size()*40;
		
		for (Button _but:Button_list)
		{
			_but.pos.y=pos;
			pos-=40;
		}
	}
}
