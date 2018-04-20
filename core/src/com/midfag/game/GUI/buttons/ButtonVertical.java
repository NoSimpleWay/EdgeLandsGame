package com.midfag.game.GUI.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.Enums.ButtonVerticalFunction;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.InputHandler;
import com.midfag.game.GUI.edit.GUIEdit;

public class ButtonVertical extends Button {
	public GUIEdit gui;
	public boolean up;
	public ButtonVerticalFunction function;

	public ButtonVertical(float _x, float _y, GUIEdit _gui, boolean _up, ButtonVerticalFunction _f) {
		super(_x, _y);
		
		if (_up)
		{spr.setTexture(new Texture(Gdx.files.internal("button_up.png")));}
		else
		{spr.setTexture(new Texture(Gdx.files.internal("button_down.png")));}	
		
		spr.setSize(50, 20);
		
		size_x=50;
		size_y=20;
		
		gui=_gui;
		up=_up;
		
		function=_f;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void some_update(float _d)
	{
		
		if ((!GScreen.show_edit)&&(function==ButtonVerticalFunction.ENTITY_SELECTOR_OFFSET))
		{
					need_remove=true;
					//GScreen.Button_list.remove(this);
		}
		
		if ((InputHandler.but==0)&&(is_overlap()))
		{
			InputHandler.but=-1;
			
			
			if (function==ButtonVerticalFunction.ENTITY_SELECTOR_OFFSET)
			{
				if (up) {gui.id_offset-=10;} else {gui.id_offset+=10;}
				
				if (gui.id_offset>100){gui.id_offset=100;}
				if (gui.id_offset<0){gui.id_offset=0;}
				
				
				
				Helper.log("OFFSET");
			}
			
			if (function==ButtonVerticalFunction.INVENTORY_LEVEL)
			{
				float offset=0.5f;
				if (!up) {offset=-0.5f;} 
				
				for (int i=0; i<30; i++)
				{
					if (GScreen.pl.inventory[i] instanceof Energoshield) {((Energoshield)GScreen.pl.inventory[i]).level+=offset;}
					if (GScreen.pl.inventory[i] instanceof Weapon) {((Weapon)GScreen.pl.inventory[i]).level+=offset;}
					if (GScreen.pl.inventory[i] instanceof ModuleUnit) {((ModuleUnit)GScreen.pl.inventory[i]).level+=offset;}
				}
			}
		}
	}
	

}
