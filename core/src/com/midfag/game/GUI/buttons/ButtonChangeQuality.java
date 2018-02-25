package com.midfag.game.GUI.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.midfag.equip.energoshield.Energoshield;
import com.midfag.equip.energoshield.EnergoshieldFast;
import com.midfag.equip.energoshield.EnergoshieldMirror;
import com.midfag.equip.energoshield.EnergoshieldSimple;
import com.midfag.equip.energoshield.EnergoshieldStone;
import com.midfag.equip.module.ModuleUnit;
import com.midfag.equip.module.ModuleUnitPush;
import com.midfag.equip.module.ModuleUnitTimeSlow;
import com.midfag.equip.module.ModuleUnitTurret;
import com.midfag.equip.weapon.Weapon;
import com.midfag.equip.weapon.WeaponLegendaryBlender;
import com.midfag.equip.weapon.WeaponLegendaryChaos;
import com.midfag.equip.weapon.WeaponLegendaryPing;
import com.midfag.equip.weapon.WeaponSimpleFirle;
import com.midfag.equip.weapon.WeaponSimpleLaser;
import com.midfag.equip.weapon.WeaponSimpleMinigun;
import com.midfag.equip.weapon.WeaponSimpleShotgun;
import com.midfag.game.GScreen;
import com.midfag.game.InputHandler;
import com.midfag.game.Enums.EquipGenerationType;
import com.midfag.game.Enums.Rarity;

public class ButtonChangeQuality extends Button {

	public Rarity rar;
	public Texture tex;
	
	public ButtonChangeQuality(float _x, float _y, Rarity _rar) {
		super(_x, _y);
		
		rar=_rar;
		if (!GScreen.show_equip){need_remove=true;}
		
		if (rar==Rarity.COMMON)	{tex=new Texture(Gdx.files.internal("rarity_common.png"));}
		if (rar==Rarity.UNCOMMON)	{tex=new Texture(Gdx.files.internal("rarity_uncommon.png"));}
		if (rar==Rarity.RARE)	{tex=new Texture(Gdx.files.internal("rarity_rare.png"));}
		if (rar==Rarity.ELITE)	{tex=new Texture(Gdx.files.internal("rarity_elite.png"));}
		if (rar==Rarity.LEGENDARY)	{tex=new Texture(Gdx.files.internal("rarity_legendary.png"));}
		if (rar==Rarity.ANOMALY)	{tex=new Texture(Gdx.files.internal("rarity_anomaly.png"));}
		
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
				
					for (int i=0; i<30; i++)//;
			        {
						
						if (GScreen.pl.inventory[i] instanceof Energoshield)
						{
							((Energoshield)GScreen.pl.inventory[i]).rarity=rar;
							((Energoshield)GScreen.pl.inventory[i]).generate();
							((Energoshield)GScreen.pl.inventory[i]).update_attributes_bonus(GScreen.pl);
						}
						
						if (GScreen.pl.inventory[i] instanceof Weapon)
						{
							((Weapon)GScreen.pl.inventory[i]).rarity=rar;
							((Weapon)GScreen.pl.inventory[i]).generate();
							((Weapon)GScreen.pl.inventory[i]).update_attributes_bonus(GScreen.pl);
						}
						
						if (GScreen.pl.inventory[i] instanceof ModuleUnit)
						{
							((ModuleUnit)GScreen.pl.inventory[i]).rarity=rar;
							((ModuleUnit)GScreen.pl.inventory[i]).generate();
							((ModuleUnit)GScreen.pl.inventory[i]).update_attributes_bonus(GScreen.pl);
						}
			        }
		}
			
		}
	}


