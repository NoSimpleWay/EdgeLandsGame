package com.midfag.game;

import java.util.ArrayList;
import java.util.List;

import com.midfag.entity.Entity;
import com.midfag.entity.EntityHuman;
import com.midfag.entity.EntityPlayer;
import com.midfag.entity.decorations.*;
import com.midfag.entity.enemies.*;
import com.midfag.equip.weapon.*;
import com.midfag.equip.weapon.WeaponSimpleShotgun;
import com.midfag.game.GUI.buttons.Button;

public class SysConfig {

	public static String PackagePathDecorations="com.midfag.entity.decorations";
	
	public static List<Entity> EntityRegisterer = new ArrayList<Entity>();
	public static List<Weapon> WeaponRegisterer = new ArrayList<Weapon>();
	
	
	public  SysConfig()
	{

	}
	
	public static void RegisterWeapon()
	{
		WeaponRegisterer.clear();
		
		WeaponRegisterer.add(new WeaponSimpleShotgun());
		WeaponRegisterer.add(new WeaponSimpleMinigun());
		WeaponRegisterer.add(new WeaponSimpleFirle());
		WeaponRegisterer.add(new WeaponSimpleLaser());
		WeaponRegisterer.add(new WeaponRobofirle());
		WeaponRegisterer.add(new LegendaryWeaponPing());
		WeaponRegisterer.add(new LegendaryWeaponChaos());
		WeaponRegisterer.add(new LegendaryWeaponBlender());
		WeaponRegisterer.add(new LegendaryWeaponMirrorLaser());
	}
	
	public static void RegisterEntity()
	{
		EntityRegisterer.clear();
		EntityRegisterer.add(new DecorBarrel(null));
		EntityRegisterer.add(new DecorBarrelCrash(null));
		EntityRegisterer.add(new DecorBuilding(null));
		EntityRegisterer.add(new DecorBuildingFloor(null));
		EntityRegisterer.add(new DecorBuildingRoof(null));
		EntityRegisterer.add(new DecorBuildingRoofLong(null));
		EntityRegisterer.add(new DecorBuildingWall(null));
		EntityRegisterer.add(new DecorBuildingWallWindow(null));
		EntityRegisterer.add(new DecorCystern(null));
		EntityRegisterer.add(new DecorGraffiti(null));
		EntityRegisterer.add(new DecorPowerLine(null));
		EntityRegisterer.add(new DecorRabitz(null));
		EntityRegisterer.add(new DecorRabitz2(null));
		EntityRegisterer.add(new DecorSteelBox(null));
		EntityRegisterer.add(new DecorSteelBoxCap(null));
		EntityRegisterer.add(new DecorSteelBoxDoor(null));
		EntityRegisterer.add(new DecorSteelWall(null));
		EntityRegisterer.add(new DecorStoneBarak(null));
		EntityRegisterer.add(new DecorStonePilon(null));
		EntityRegisterer.add(new DecorStoneWall(null));
		EntityRegisterer.add(new DecorStoneWall2(null));
		EntityRegisterer.add(new DecorTrain(null));
		EntityRegisterer.add(new DecorTrainVagonOpen(null));
		EntityRegisterer.add(new DecorTrainVagonRect(null));
		EntityRegisterer.add(new DecorTree(null));
		EntityRegisterer.add(new DecorTube(null));
		EntityRegisterer.add(new DecorTubeBig(null));
		//EntityRegisterer.add(new DecorTubeCarcas(null));
		EntityRegisterer.add(new DecorTubeCystern(null));
		EntityRegisterer.add(new DecorWallAngleA(null));
		EntityRegisterer.add(new DecorWallAngleB(null));
		EntityRegisterer.add(new SystemHelper(null));
		
		EntityRegisterer.add(new EntityEliteWheel(null));
		EntityRegisterer.add(new EntityPyra(null));
		//EntityRegisterer.add(new EntityPyraFriend(null));
		EntityRegisterer.add(new EntityTransportDrone(null));
		EntityRegisterer.add(new EntityTransportDroneContainer(null));
		EntityRegisterer.add(new EntityVizjun(null));
		EntityRegisterer.add(new EntityWheel(null));
		
		EntityRegisterer.add(new EntityPlayer(null));
		EntityRegisterer.add(new EntityHuman(null));
		
		EntityRegisterer.add(new DecorBuilding00(null));
		EntityRegisterer.add(new DecorBuilding00_Part00(null));
		
		for (int i=0; i<EntityRegisterer.size(); i++)
		{
			for (int j=i+1; j<EntityRegisterer.size(); j++)
			{
				if (EntityRegisterer.get(i).uid.equals(EntityRegisterer.get(j).uid))
				{
					Helper.log("***************** - ERROR: replicate UID ["+EntityRegisterer.get(i).uid+" | "+EntityRegisterer.get(i).getClass().getName()+" | "+EntityRegisterer.get(j).getClass().getName()+"]");
				}
			}
		}
	}
	
	public static String get_package_path_by_uid(String _uid)
	{
		for (int i=0; i<EntityRegisterer.size(); i++)
		{
			if (_uid.equals(EntityRegisterer.get(i).uid))
			{
				return EntityRegisterer.get(i).getClass().getName();
			}
		}
		return "";
	}
	
	
	
}
