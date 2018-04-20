package com.midfag.game.GUI.edit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.GUI.buttons.Button;

public class ButtonSaveMap extends Button {

	public int[][] tile_map;
	public int[][] tile_map_overlay;
	
	public Texture tex;
	
	public ButtonSaveMap(float _x, float _y)
	{
		super(_x,_y);
		pos.x=_x;
		pos.y=_y;
		
		tex=new Texture(Gdx.files.internal("button_save_map.png"));
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
			InputHandler.but=-1;
			
			System.out.println("SAVED");
			FileHandle file = Gdx.files.local("z.txt");
			
			String s="";
			    // if file doesnt exists, then create it
			
			for (int i=0; i<30; i++)
			for (int j=0; j<30; j++)
			{
				for (int k=0; k<GScreen.cluster[j][i].Entity_list.size(); k++)
				{	
					s+="###ENTITY"+"\n";
					s+=GScreen.cluster[j][i].Entity_list.get(k).uid+"\n";
					
					s+="pos.x"+"\n";
					s+=Math.round(GScreen.cluster[j][i].Entity_list.get(k).pos.x)+"\n";
					
					s+="pos.y"+"\n";
					s+=Math.round(GScreen.cluster[j][i].Entity_list.get(k).pos.y)+"\n";
					
					s+="y"+"\n";
					s+=Math.round(GScreen.cluster[j][i].Entity_list.get(k).z)+"\n";
					
					s+="angle"+"\n";
					s+=Math.round(GScreen.cluster[j][i].Entity_list.get(k).spr.getRotation())+"\n";
					
					if (!GScreen.cluster[j][i].Entity_list.get(k).id_for_script.equals(""))
					{s+="script_id"+"\n";
					s+=GScreen.cluster[j][i].Entity_list.get(k).id_for_script+"\n";}
					
					if (!GScreen.cluster[j][i].Entity_list.get(k).interact_entry_point.equals(""))
					{s+="interact_entry_point"+"\n";
					s+=GScreen.cluster[j][i].Entity_list.get(k).interact_entry_point+"\n";}
					
					s+="PUT"+"\n";
					
					s+="\n";
					
					
				}
			}
			
			file.writeString(s, false);
			
			tile_map=GScreen.tile_map;
			tile_map_overlay=GScreen.tile_map_overlay;
			
			
			
			s="";
			String ss="";
		
			file = Gdx.files.local("z_tile.txt");
			for (int i=0; i<100; i++)
			{
				//System.out.println ("READY: "+i/3f+"% "+_d*1000);
				ss="";
				
				for (int j=0; j<100; j++)
				{
					if (tile_map[j][i]<0) {ss+="no";}
					else
					{
						//if (tile_map[j][i]<100){ss+="0";}
						if (tile_map[j][i]<10){ss+="0";}
						ss+=tile_map[j][i];
						
						//ss+="|";
					}
				}
				s+=ss;
				
				s+="\n";
			}
			file.writeString(s, false);
			
			s="";
			file = Gdx.files.local("z_tile_overlay.txt");
			for (int i=0; i<100; i++)
			{
				//System.out.println ("READY: "+i/3f+"% "+_d*1000);
				ss="";
				
				for (int j=0; j<100; j++)
				{
					if (tile_map_overlay[j][i]<0) {ss+="no";}
					else
					{
						//if (tile_map[j][i]<100){ss+="0";}
						if (tile_map_overlay[j][i]<10){ss+="0";}
						ss+=tile_map_overlay[j][i];
						
						//ss+="|";
					}
				}
				s+=ss;
				
				s+="\n";
			}
			file.writeString(s, false);
			
		
		}
		
	}
}
