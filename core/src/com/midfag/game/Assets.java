package com.midfag.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.SoundLoader.SoundParameter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Assets {
	

	
	
	public static Texture panel;
	public static Texture diod;
	public static Texture particle;
	
	public static Texture tube;
	public static Texture tube_carcas;
	
	public static Texture explosion;
	
	public static float trololo_volume=3;
	
	public static Sound fall_hit=Gdx.audio.newSound(Gdx.files.internal("data/fall_hit.wav"));
	
	public static Sound trololo=Gdx.audio.newSound(Gdx.files.internal("data/trololo.wav"));
	public static Sound smiler_saw=Gdx.audio.newSound(Gdx.files.internal("data/smiler_saw.wav"));
	
	public static Sound shoot00;
	public static Sound shoot01;
	public static Sound shoot02;
	public static Sound shoot04=Gdx.audio.newSound(Gdx.files.internal("data/shoot04.wav"));
	public static Sound shoot_fire=Gdx.audio.newSound(Gdx.files.internal("data/fire.wav"));
	
	public static Music battle_music_00=Gdx.audio.newMusic(Gdx.files.internal("data/battle_00.wav"));
	public static Sound expl=Gdx.audio.newSound(Gdx.files.internal("data/expl.wav"));
	
	public static Sound metal_sound;

	public static Sound plastic;
	
	public static Sound metal_destroy;
	public static Sound saw;
	//public static Sound music;
	
	public static long music_id;
	
	public static Music music = Gdx.audio.newMusic(Gdx.files.internal("data/music01.mp3"));

	public static Sound flash;

	public static Sound minigun;

	public static Sound shoot03;
	public static Sound shoot75523235;

	public static Sound chaos;
	public static Sound shoot_laser;
	
	public static Sound engine=Gdx.audio.newSound(Gdx.files.internal("data/engine3.wav"));;
	
	public static Sound crash=Gdx.audio.newSound(Gdx.files.internal("data/crash.wav"));;
	public static Sound time_slow=Gdx.audio.newSound(Gdx.files.internal("data/time_slow.wav"));;
	public static Sound jet=Gdx.audio.newSound(Gdx.files.internal("data/jet.wav"));;
	public static long time_slow_id;
	
	public static Sound freeze=Gdx.audio.newSound(Gdx.files.internal("data/freeze.wav"));;
	
	public static Sound gate=Gdx.audio.newSound(Gdx.files.internal("data/gate.wav"));
	public static Sound knock=Gdx.audio.newSound(Gdx.files.internal("data/knock.wav"));
	public static Sound door=Gdx.audio.newSound(Gdx.files.internal("data/door.wav"));
	public static Sound engine_start=Gdx.audio.newSound(Gdx.files.internal("data/engine_start.wav"));
	
	public static Texture raider_tank=load ("raider_tank");
	public static Texture smiler=load ("smiler");
	public static Sprite skill_wheel=new Sprite(load ("eye"));
	public static Texture select_texture[]=new Texture[5];
	public static Sprite select_sprite=new Sprite(load ("selected_skill0"));

	public static Texture missile;
	
	public static Texture gui_module_bg=load ("gui_module_bg");
	public static Texture gui_module=load ("gui_module");
	
	public static Texture rect=load ("rect");
	public static Texture rect_white=load ("rect_white");
	public static Texture round=load ("round_bg");
	
	public static Texture stone_wall_01=load ("stone_wall_01");
	public static Texture stone_pilon_01=load ("stone_pilon_01");
	public static Texture stone_barak=load ("decor_stone_barak");
	
	public static Texture star=load ("star");
	
	public static Texture fall=load("fall");
	public static Texture mine=load("mine");
	
	public static Texture planet0=load("planet_00");
	public static Texture planet1=load("planet_01");
	public static Texture planet2=load("planet_02");
	public static Texture planet3=load("planet_03");
	public static Texture planet4=load("planet_04");
	
	public static Texture planet_good0=load("planet_good0");
	public static Texture planet_good1=load("planet_good1");
	public static Texture planet_good2=load("planet_good2");
	public static Texture planet_good3=load("planet_good3");
	public static Texture planet_good4=load("planet_good4");
	public static Texture planet_good5=load("planet_good5");
	
	public static Texture human=load ("human");
	public static Texture dialog_texture=load("dialog_gui");
	
	public static Texture barrel=load("barrel");
	public static Texture barrel_icon=load("barrel_icon");
	
	public static Texture barrel_crash=load("barrel_crash");
	public static Texture barrel_crash_icon=load("barrel_crash_icon");
	
	public static Texture helper=load("helper");
	public static Texture helper_icon=load("helper_icon");
	
	public static Texture light=load("light");
	
	public static Texture rabitz_01=load("rabitz_01");
	public static Texture rabitz_01_icon=load("rabitz_01_icon");
	
	public static Texture rabitz_02=load("rabitz_02");
	public static Texture rabitz_02_icon=load("rabitz_02_icon");
	
	
	public static Texture gui_interface=load("interface");
	public static Texture indicate_null=load("icon_indicate_null");
	
	
	public static Texture[] human_head=new Texture[4];
	
	public static Texture[] human_body=new Texture[12];
	public static Texture[] human_pants=new Texture[12];
	
	public static Texture decor_building_00=load ("decoration_building_00");
	public static Texture decor_tube_cystern=load ("decoration_tube_cystern");
	public static Texture decor_tube_big=load ("decoration_tube_big");
	public static Texture decoration_train=load ("decoration_train");
	public static Texture decoration_train_vagon_rect=load ("decoration_train_vagon_rect");
	public static Texture decoration_train_vagon_open=load ("decoration_train_vagon_open");
	
	public static Texture decoration_tree=load ("decoration_tree");
	public static Texture decoration_tree_icon=load ("decoration_tree_icon");
	
	public static Texture decoration_power_line=load ("decoration_power_line");
	public static Texture decoration_power_line_icon=load ("decoration_power_line_icon");
	
	public static Texture decoration_steel_box=load ("decoration_steel_box");
	public static Texture decoration_steel_box_icon=load ("decoration_steel_box_icon");
	
	public static Texture decoration_steel_box_cap=load ("decoration_steel_box_cap");
	public static Texture decoration_steel_box_cap_icon=load ("decoration_steel_box_cap_icon");
	
	public static Texture decoration_steel_box_door=load ("decoration_steel_box_door");
	public static Texture decoration_steel_box_door_icon=load ("decoration_steel_box_door_icon");
	
	public static Texture cystern=load ("decoration_cystern");
	
	public static Texture stone_wall_02=load ("stone_wall_02");
	
	public static Texture beton_wall=load ("beton_wall");
	
	public static Texture graffiti_01=load ("graffiti_01");
	public static Texture graffiti_01_icon=load ("graffiti_01_icon");
	
	public static Texture beton_wall_icon=load ("beton_wall_icon");
	
	public static Texture beton_wall_window=load ("beton_wall_window");
	public static Texture beton_wall_window_icon=load ("beton_wall_window_icon");
	
	public static Texture building_wall_in=load ("building_02");
	
	public static Texture wall_angle_A=load ("wall_angle_A");
	public static Texture wall_angle_B=load ("wall_angle_B");
	
	public static Texture building_floor=load ("building_floor");
	public static Texture building_floor_icon=load ("building_floor_icon");
	
	public static Texture building_roof=load ("roof");
	public static Texture building_roof_icon=load ("roof_icon");
	
	public static Texture building_roof_long=load ("roof_long");
	public static Texture building_roof_long_icon=load ("roof_long_icon");
	
	public static Texture indicate_bg=load ("indicate_bg");
	public static Texture highlight=load ("highlight");
	public static Texture icon_cooldown=load ("icon_cooldown");
	public static Texture icon_duration=load ("icon_duration");
	
	public static Texture decoration_cystern_icon=load ("decoration_cystern_icon");
	public static Texture decoration_tube_big_icon=load ("decoration_tube_big_icon");
	public static Texture decoration_stone_barak_icon=load ("decoration_stone_barak_icon");
	public static Texture decoration_stone_pilon_icon=load ("decoration_stone_pilon_icon");
	public static Texture decoration_stone_wall_01_icon=load ("decoration_stone_wall_01_icon");
	public static Texture decoration_stone_wall_02_icon=load ("decoration_stone_wall_02_icon");
	public static Texture decoration_tube_cystern_icon=load ("decoration_tube_cystern_icon");
	public static Texture entity_pyra_icon=load ("entity_pyra_icon");
	public static Texture entity_wheel_icon=load ("entity_wheel_icon");
	public static Texture entity_elite_wheel_icon=load ("entity_elite_wheel_icon");
	
	
	public static Texture decoration_train_icon=load ("decoration_train_icon");
	public static Texture decoration_train_vagon_rect_icon=load ("decoration_train_vagon_rect_icon");
	public static Texture decoration_train_vagon_open_icon=load ("decoration_train_vagon_open_icon");
	/*
	public static Texture mech_down=load ("mech01"));;
	public static Texture mech_right=load ("mech02"));;
	public static Texture mech_left=load ("mech03"));;*/
	
	public static Texture mech_leg=load ("leg");
	public static Texture mech_foot=load ("foot");
	public static Texture point_start=load ("point_start");
	public static Texture rama=load ("rama");
	public static Texture text_bg=load ("text_bg");
	
	public static Texture text_bg_blue=load ("text_bg_blue");//24.04.2018 03.19 Abyss, Pt. 1 | Dark Ambient Orchestra
	
	//public static Texture selected_skill=load ("selected_skill"));
	
	public static Texture mech_foot_shadow=load ("foot_shadow");

	//public static Texture 
	
	public static Texture shadow=load ("shadow");
	
	public static Texture noise=load ("noise");
	public static Texture normal_map=load ("normal_map");
	public static Texture dissolve=load("dissolve");
	
	public static Texture transport_drone=load("big_drone");
	public static Texture transport_drone_shadow=load("big_drone_shadow");
	public static Texture transport_drone_icon=load("big_drone_icon");
	public static Texture dron_container=load("dron_container");
	
	public static Texture button_e=load("button_e");
	public static Texture quest=load("quest");
	
	public static Texture[] pyra_body=new Texture[16];
	public static Texture[] pyra_head=new Texture[16];
	
	public static Texture[] wheel_body=new Texture[16];
	
	public static Texture[] wheel_elite_body=new Texture[16];
	public static Texture[] effect_shield=new Texture[13];
	public static Texture[] effect_explosion=new Texture[6];
	public static Texture[] effect_freeze=new Texture[11];
	
	public static Texture[] turret_body=new Texture[16];
	public static Texture decoration_building_00=load ("decoration_building_00_icon");
	public static Texture decor_building_00_part_00=load ("decoration_building_00_part_00");
	public static Texture decor_building_00_part_00_icon=load ("decoration_building_00_part_00_icon");
	public static float battle_music_multiplier=0.05f;
	
	public static Texture spawn_tower_light=load("spawn_tower_light");
	public static Texture spawn_tower=load("spawn_tower");
	public static Texture spawn_tower_bottom=load("spawn_tower_bottom");
	private static long smiler_saw_id;
	public static boolean smiler_saw_play;
	public static long trololo_id;
	
	public Assets()
	{
		
	}
	
	public static Texture load(String _s) {
		// TODO Auto-generated method stub
		Texture tex;
		
	
		
			if (Gdx.files.internal("data/"+_s+".png").exists())
			{
				tex = new Texture(Gdx.files.internal("data/"+_s+".png"));
			
			tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			return tex;
			}
			else
			{
			// TODO Auto-generated catch block
				

			Helper.log("texture ["+"data/"+_s+".png"+"] not found");
			
			//e.printStackTrace();
			tex = new Texture(Gdx.files.internal("data/null.png"));
			}
		
		return tex;

	}

	public static void load_assets()
	{
		stone_wall_01.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		stone_wall_02.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		stone_pilon_01.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		star.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		/*
		planet0.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		planet1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		planet2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		*/
		
		/*
		mech_right.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		mech_down.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		mech_left.setFilter(TextureFilter.Linear, TextureFilter.Linear);*/
		
		
		skill_wheel.setTexture(load ("eye"));
		skill_wheel.setSize(2048, 2048);
		skill_wheel.setOrigin(1024, 1024);
		
		skill_wheel.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		
		panel=load ("panel");
		diod=load ("diod");
		particle=load ("particle");
		explosion=load ("explosion0");
		missile=load ("missile");
		
		
		
		
		shoot00 = Gdx.audio.newSound(Gdx.files.internal("data/shoot00.wav"));
		shoot01 = Gdx.audio.newSound(Gdx.files.internal("data/shoot01.wav"));
		shoot02 = Gdx.audio.newSound(Gdx.files.internal("data/shoot02.wav"));
		shoot03 = Gdx.audio.newSound(Gdx.files.internal("data/shoot03.wav"));
		shoot75523235 = Gdx.audio.newSound(Gdx.files.internal("data/shoot75523235.wav"));
		shoot_laser = Gdx.audio.newSound(Gdx.files.internal("data/shoot_laser.wav"));
		
		metal_sound = Gdx.audio.newSound(Gdx.files.internal("data/metal_sound.wav"));
		plastic = Gdx.audio.newSound(Gdx.files.internal("data/plastic.wav"));
		
		metal_destroy = Gdx.audio.newSound(Gdx.files.internal("data/metal_destroy.wav"));
		
		saw = Gdx.audio.newSound(Gdx.files.internal("data/saw.wav"));
		
		minigun = Gdx.audio.newSound(Gdx.files.internal("data/minigun.wav"));
		
		flash = Gdx.audio.newSound(Gdx.files.internal("data/flash.wav"));
		//music = Gdx.audio.newSound(Gdx.files.internal("data/music.wav"));
		chaos = Gdx.audio.newSound(Gdx.files.internal("data/chaos_chaos_chaos.wav"));
		
		chaos = Gdx.audio.newSound(Gdx.files.internal("data/chaos_chaos_chaos.wav"));
		//flash = Gdx.audio.newSound(Gdx.files.internal("data/flash.wav"));		

		
		stone_barak.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		cystern.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		icon_cooldown.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		icon_duration.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		noise.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		
		smiler_saw_id=smiler_saw.play(0.0f);
		smiler_saw.setLooping(smiler_saw_id, true);
		smiler_saw.pause();
		
		trololo_id=trololo.play(0.0f);
		trololo.setLooping(trololo_id, true);
		trololo.pause();
		
		music.setLooping(true);
		music.setVolume(0.25f);
		music.play();
	
		battle_music_00.setLooping(true);
		battle_music_00.setVolume(0.25f);
		battle_music_00.play();
		battle_music_00.pause();
		
		/*time_slow_id=time_slow.play();
		time_slow.setLooping(time_slow_id, true);
		time_slow.setVolume(time_slow_id, 0.01f);
		*/
		
		//select_sprite.setOrigin(37f, 37f);
		select_sprite.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		select_sprite.setAlpha(1.0f);
		
	}
	
	public static void post_load_assets()
	{
		for (int i=0; i<5; i++)
		{select_texture[i]=load ("selected_skill"+i);}
		
		for (int i=0; i<=22; i++)
		{
			if (i<10)
			{GScreen.tile[i]=load ("tile/tile0"+i);}
			else
			{GScreen.tile[i]=load ("tile/tile"+i);}
			
			
			GScreen.tile[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		
		
		for (int i=0; i<4; i++)
		{
			human_head[i]=load ("human/head"+i);
			

		}
		
		for (int i=0; i<3; i++)
		{
			human_body[0+i*4]=load ("human/body_up_"+i);
			human_body[1+i*4]=load ("human/body_right_"+i);
			human_body[2+i*4]=load ("human/body_bottom_"+i);
			human_body[3+i*4]=load ("human/body_left_"+i);
		}
		
		for (int i=0; i<3; i++)
		{
			human_pants[0+i*4]=load ("human/pants_up_"+i);
			human_pants[1+i*4]=load ("human/pants_right_"+i);
			human_pants[2+i*4]=load ("human/pants_bottom_"+i);
			human_pants[3+i*4]=load ("human/pants_left_"+i);
		}
		
		for (int i=0; i<16; i++)
		{
			if (i<10)
			{pyra_body[i]=load ("pyra/body00"+i);}
			else
			{pyra_body[i]=load ("pyra/body0"+i);}
			
			pyra_body[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		for (int i=0; i<16; i++)
		{
			if (i<10)
			{turret_body[i]=load ("turret/body00"+i);}
			else
			{turret_body[i]=load ("turret/body0"+i);}
			
			turret_body[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		for (int i=0; i<16; i++)
		{
			if (i<10)
			{pyra_head[i]=load ("pyra/head00"+i);}
			else
			{pyra_head[i]=load ("pyra/head0"+i);}
			
			pyra_head[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		for (int i=0; i<16; i++)
		{
			if (i<10)
			{wheel_body[i]=load ("wheel/body00"+i);}
			else
			{wheel_body[i]=load ("wheel/body0"+i);}
			
			wheel_body[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		for (int i=0; i<16; i++)
		{
			if (i<10)
			{wheel_elite_body[i]=load ("elite_wheel/body00"+i);}
			else
			{wheel_elite_body[i]=load ("elite_wheel/body0"+i);}
			
			wheel_elite_body[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		
		for (int i=0; i<13; i++)
		{
			if (i<10)
			{effect_shield[i]=load ("effect_shield/shield0"+i);}
			else
			{effect_shield[i]=load ("effect_shield/shield"+i);}
			
			effect_shield[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		for (int i=0; i<6; i++)
		{
			if (i<10)
			{effect_explosion[i]=load ("effect_explosion/explosion0"+i);}
			else
			{effect_explosion[i]=load ("effect_explosion/explosion"+i);}
			
			effect_explosion[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		for (int i=0; i<11; i++)
		{
			if (i<10)
			{effect_freeze[i]=load ("effect_freeze/freeze0"+i);}
			else
			{effect_freeze[i]=load ("effect_freeze/freeze"+i);}
			
			effect_freeze[i].setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}
}
