package com.midfag.game.GUI.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.energoshield.EnergoshieldFast;
import com.midfag.equip.energoshield.EnergoshieldMirror;
import com.midfag.equip.energoshield.EnergoshieldSimple;
import com.midfag.equip.energoshield.EnergoshieldStone;
import com.midfag.equip.module.ModuleUnitPush;
import com.midfag.equip.module.ModuleUnitTimeSlow;
import com.midfag.equip.module.LegendaryModuleUnitEXPLOSIONS;
import com.midfag.equip.module.LegendaryModuleUnitTimeStop;
import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.module.ModuleUnitTurret;
import com.midfag.equip.weapon.LegendaryWeaponMirrorLaser;
import com.midfag.equip.weapon.LegendaryWeaponBlender;
import com.midfag.equip.weapon.LegendaryWeaponChaos;
import com.midfag.equip.weapon.LegendaryWeaponPing;
import com.midfag.equip.weapon.WeaponSimpleFirle;
import com.midfag.equip.weapon.WeaponSimpleLaser;
import com.midfag.equip.weapon.WeaponSimpleMinigun;
import com.midfag.equip.weapon.WeaponSimpleShotgun;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.InputHandler;
import com.midfag.game.SysConfig;
import com.midfag.game.Assets;
import com.midfag.game.Enums.EquipGenerationType;

public class ButtonRandomGenerator extends Button {

	public EquipGenerationType egt;
	public Texture tex;
	
	public ButtonRandomGenerator(float _x, float _y, EquipGenerationType _egt) {
		super(_x, _y);
		
		size_x=50;
		size_y=50;
		spr.setSize(50,50);
		
		egt=_egt;
		if (!GScreen.show_equip){need_remove=true;}
		
		if (egt==EquipGenerationType.WEAPON)	{tex=Assets.load("skill_weapon");}
		if (egt==EquipGenerationType.SHIELD)	{tex=Assets.load("skill_shield");}
		if (egt==EquipGenerationType.MODULE)	{tex=Assets.load("icon_time_control");}
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void after_draw()
	{
		GScreen.batch_static.setColor(Color.WHITE);
		GScreen.batch_static.draw(tex, pos.x-22, pos.y-22);
	}
	
	public void some_update(float _d)
	{
		if ((is_overlap())&&(InputHandler.but==0))
		{
				InputHandler.but=-1;
				
				if (egt==EquipGenerationType.MODULE)
				{
					for (int i=0; i<30; i++)//;
			        {
						switch ((int)GScreen.rnd(5))
			        	{
			        		case 0: GScreen.pl.inventory[i]=new ModuleUnitPush();		break;
			        		case 1: GScreen.pl.inventory[i]=new ModuleUnitTimeSlow();	break;
			        		case 2: GScreen.pl.inventory[i]=new ModuleUnitTurret();		break;
			        		case 3: GScreen.pl.inventory[i]=new LegendaryModuleUnitTimeStop();		break;
			        		case 4: GScreen.pl.inventory[i]=new LegendaryModuleUnitEXPLOSIONS();		break;
			        	}
						
						
			        }
				}
				
								
				if (egt==EquipGenerationType.SHIELD)
				{
					for (int i=0; i<30; i++)//;
			        {					
						switch ((int)GScreen.rnd(4))
			        	{
			        		case 0: GScreen.pl.inventory[i]=new EnergoshieldSimple();		break;
			        		case 1: GScreen.pl.inventory[i]=new EnergoshieldStone();		break;
			        		case 2: GScreen.pl.inventory[i]=new EnergoshieldFast();			break;
			        		case 3: GScreen.pl.inventory[i]=new EnergoshieldMirror();		break;
			        	}
						((Energoshield) GScreen.pl.inventory[i]).update_attributes_bonus(GScreen.pl);
					}
					
				}
				
				if (egt==EquipGenerationType.WEAPON)
				{  
					/*for (int i=0; i<30; i++)//;
			        {
						if (Math.random()>0.05)
			        	{
			        		switch ((int)GScreen.rnd(4))
				        	{
				        		case 0: GScreen.pl.inventory[i]=new WeaponSimpleFirle();		break;
				        		case 1: GScreen.pl.inventory[i]=new WeaponSimpleMinigun();		break;
				        		case 2: GScreen.pl.inventory[i]=new WeaponSimpleShotgun();		break;
				        		case 3: GScreen.pl.inventory[i]=new WeaponSimpleLaser(); 		break;
				        	}
			        	}
			        	else
			        	switch ((int)GScreen.rnd(4))
				        {
				       		case 0: GScreen.pl.inventory[i]=new LegendaryWeaponBlender();	break;
				       		case 1: GScreen.pl.inventory[i]=new LegendaryWeaponChaos();		break;
				       		case 2: GScreen.pl.inventory[i]=new LegendaryWeaponPing();		break;
				       		case 3: GScreen.pl.inventory[i]=new LegendaryWeaponMirrorLaser();		break;
				        }
			        }*/
					
					for (int i=0; i<SysConfig.WeaponRegisterer.size(); i++)
					{
						GScreen.pl.inventory[i]=Helper.get_weapon_from_id(SysConfig.WeaponRegisterer.get(i).getClass().getName());
					}
				}
			
		}
	}

}
