package com.midfag.game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.EntityHuman;
import com.midfag.entity.EntityPlayer;
import com.midfag.entity.LightSource;
import com.midfag.entity.decorations.DecorBarrel;
import com.midfag.equip.weapon.Weapon;
import com.midfag.game.GUI.ButtonLayout;
import com.midfag.game.GUI.buttons.Button;
import com.midfag.game.script.ScriptSystem;

public class Helper {
	
	//private static final String BUILDER_CLASS = "builder.class";
	public static String alphabet[]=new String [16];
	public static String hex[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"};
	
	public Helper()
	{
		
	}
	
	
	
	public static void LoadMap()
	{

		//for (int i=0; i<16; i++)
		/*for (int j=0; j<16; j++)
		{
			alphabet[j]=hex[j];
			//alphabet[j]=hex[j];
		}*/
		
		for (int i=0; i<300; i++)
		for (int j=0; j<300; j++)
		{
			GScreen.path[j][i]=300;
		}
			
		
		
		for (int i=0; i<30; i++)
		for (int j=0; j<30; j++)
		{
			
			if (GScreen.cluster[j][i].Entity_list!=null){GScreen.cluster[j][i].Entity_list.clear();}
			if (GScreen.cluster[j][i].Phys_list!=null){GScreen.cluster[j][i].Phys_list.clear();}
		}
		
		FileHandle file = Gdx.files.local("level_data/z.txt");
		
		String s=file.readString();
		

		String[] ss = s.split("\n");
		
		    // if file doesnt exists, then create it
		//System.out.println(ss);
		//System.out.println(ss[0]);
		Entity e=null;
		LightSource li=null;
		
		for (int i=0; i<ss.length; i++)
		{
			if (ss[i].equals("###ENTITY"))
			{
				i++;
				
				String id=ss[i];
				

				
				log ("UID="+id+" | ID="+SysConfig.get_package_path_by_uid(id));
				
				if (SysConfig.get_package_path_by_uid(id).equals("")) {e=null;}
				else
				{e=get_object_from_id(SysConfig.get_package_path_by_uid(id));}
				//System.out.println("ID="+id);	
			}
			
			if (e!=null)
			{
				if (ss[i].equals("pos.x"))
				{
					i++;
					e.pos.x=Integer.parseInt(ss[i]);
				}
				
				if (ss[i].equals("pos.y"))
				{
					i++;
					e.pos.y=Integer.parseInt(ss[i]);
				}
				
				if (ss[i].equals("y"))
				{
					i++;
					e.z=Integer.parseInt(ss[i]);
				}
				
				if (ss[i].equals("script_id"))
				{
					
					i++;
					e.id_for_script=ss[i];
					
					ScriptSystem.Entity_with_id_list.add(e);
				}
				
				if (ss[i].equals("interact_entry_point"))
				{
					
					i++;
					e.interact_entry_point=ss[i];
					e.is_interact=true;
					
				}
				
								
				if (ss[i].equals("LightSource"))
				{
					//i++;
					//e.z=Integer.parseInt(ss[i]);
					li=new LightSource();
				}
				
				
				
				if (ss[i].equals("LiR")) {i++; li.R=Float.parseFloat(ss[i]);}
				if (ss[i].equals("LiG")) {i++; li.G=Float.parseFloat(ss[i]);}
				if (ss[i].equals("LiB")) {i++; li.B=Float.parseFloat(ss[i]);}
				if (ss[i].equals("LiP")) {i++; li.light_power=Float.parseFloat(ss[i]);}
				if (ss[i].equals("LightReady")) {e.light_source=li;}
				
				
				if (ss[i].equals("PUT"))
				{
					
					GScreen.add_entity_to_map(e);
					
					
					if (e instanceof EntityHuman){log("!!!!!!!!!!!!!!!!!!!!!!!!   HUMAN"); GScreen.pl_human=e; GScreen.pl=e;}
					if (e.getClass().equals(EntityPlayer.class)){log("!!!!!!!!!!!!!!!!!!!!!!!!   MECH"); GScreen.pl_mech=e;}
					
					GScreen.camera_target=GScreen.pl;
					
					e.fill_path();
				}

					
			}
		}
		
	
		if (GScreen.pl_human == null)
		{
			log(get_error_text("PLAYER HUMAN NOT FOUND ON MAP DATA, AUTO SPAWN"));
			GScreen.pl_human=GScreen.add_entity_to_map(new EntityHuman(new Vector2(4400,4400)));
			
			GScreen.pl=GScreen.pl_human;
			GScreen.camera_target=GScreen.pl;
		}
		
		if (GScreen.pl_mech == null)
		{
			log(get_error_text("PLAYER MECH NOT FOUND ON MAP DATA, AUTO SPAWN"));
			GScreen.pl_mech=GScreen.add_entity_to_map(new EntityPlayer(new Vector2(4400,4400)));
		}
		
		file = Gdx.files.local("level_data/z_tile.txt");
		
		s=file.readString();
		
		
		

					String compress =s;
					String temp=compress;
					String dict="";
					
					/*
					for (int i=0; i<alphabet.length; i++)
					{
						//dict+=temp.substring(0,4);
						
						String di=temp.substring(0,4);
						
						boolean free_space=true;
						for (int z=0; z<16; z++)
						{
							
							if (alphabet[i].equals(""))
							{
								alphabet[i]=di;
							}
						}
						
					}*/
					

					
				
					file = Gdx.files.local("z_tile_compress.txt");
					file.writeString(compress, false);
					
					file = Gdx.files.local("z_tile_dict.txt");
					file.writeString(dict, false);
					
					file = Gdx.files.local("z_tile_compress.txt");
					compress=file.readString();
					
					file = Gdx.files.local("z_tile_dict.txt");
					dict=file.readString();

					String result=compress;
					
					/*
					for (int i=15; i>=0; i--)
					{
						result=result.replace(alphabet[i], dict.substring(i*2,i*2+1));
					}*/
					
					

					
					file = Gdx.files.local("z_tile_decode.txt");
					file.writeString(result, false);
		
		
		ss=s.split("\n");
		
		//System.out.println("FIRST DATA="+ss[0]);
		
		for (int i=0; i<100; i++)
		for (int j=0; j<100; j++)
		{
			String sub_s=ss[i].substring(j*2, j*2+2);
			
			//if ((i==0)&(j==0)){System.out.println("TWO LETTER="+sub_s);}
			
			if (!sub_s.equals("no"))
				{
					//GScreen.tile_map[j][i]=Integer.parseInt(sub_s);
					GScreen.tile_map[j][i]=(int) (Math.random()*4);
					int ty=(int)GScreen.tile_map[j][i]/5;
					int tx=GScreen.tile_map[j][i]-ty*5;
					
					GScreen.tile_map_x[j][i]=tx*100+1;
					GScreen.tile_map_y[j][i]=ty*100+1;
				}
		}
		
		file = Gdx.files.local("level_data/z_tile_overlay.txt");
		
		s=file.readString();
		ss=s.split("\n");
		
		//System.out.println("FIRST DATA="+ss[0]);
		
		for (int i=0; i<100; i++)
		for (int j=0; j<100; j++)
		{
			String sub_s=ss[i].substring(j*2, j*2+2);
			
			//if ((i==0)&(j==0)){System.out.println("TWO LETTER (overlay)="+sub_s);}
			
			if (!sub_s.equals("no"))
				{
					GScreen.tile_map_overlay[j][i]=Integer.parseInt(sub_s);
				}
			else
			{
				GScreen.tile_map_overlay[j][i]=-1;
			}
		}
		
		
		for (int i=0; i<GScreen.cluster.length; i++)
		for (int j=0; i<GScreen.cluster.length; i++)
		{
			for (int k=0; k<GScreen.cluster[j][i].Entity_list.size();k++)
	    	{
	    		Entity ee=GScreen.cluster[j][i].Entity_list.get(i);
	    		
	    		if (ee.light_source!=null) {ee.light_source.update_light_position(ee.pos.x,ee.pos.y);}
	    	}
		}
		
		
	}
	
	public static String get_error_text(String _t)
	{
		return "***************** - ERROR: "+_t+" - *****************";
	}
	
	public static Entity get_object_from_id(String _id)
    {
		try {
			Class<?> c = Class.forName(_id);
			//log("INVOKED ID="+_id);
			Constructor<?>[] constructor=c.getConstructors(); 
			
			
			//System.out.println("CONSTRUCTOR[0]: "+constructor[0]);
			//System.out.println("CONSTRUCTOR[1]: "+constructor[1]);
			
			Entity enn=(Entity) constructor[0].newInstance(new Vector2());
			return enn;


			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
    	return null;
    }
	
	public static Weapon get_weapon_from_id(String _id)
    {
		try {
			Class<?> c = Class.forName(_id);
			//log("INVOKED ID="+_id);
			Constructor<?>[] constructor=c.getConstructors(); 
			
			
			//System.out.println("CONSTRUCTOR[0]: "+constructor[0]);
			//System.out.println("CONSTRUCTOR[1]: "+constructor[1]);
			
			Weapon enn=(Weapon) constructor[0].newInstance();
			return enn;


			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
    	return null;
    }
	
	public static void add_button_with_layout(Button _b, ButtonLayout _l)
	{
		GScreen.Button_list.add(_b);
		_l.buttons.add(_b);
	}

	public static void log(String _s) {
		// TODO Auto-generated method stub
		System.out.println(_s);
		
	}
}
