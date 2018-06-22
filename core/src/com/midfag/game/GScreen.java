package com.midfag.game;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.CustomSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.midfag.entity.Entity;
//import com.midfag.entity.Entity.MySpriteComparator;
import com.midfag.entity.Shd;
import com.midfag.entity.enemies.EntityPyra;
import com.midfag.entity.enemies.EntityVizjun;
import com.midfag.entity.missiles.Missile;
import com.midfag.equip.energoshield.EnergoshieldSimple;
import com.midfag.equip.weapon.WeaponSimpleFirle;
import com.midfag.equip.weapon.WeaponSimpleMinigun;
import com.midfag.equip.weapon.WeaponSimpleShotgun;
import com.midfag.game.GUI.ButtonLayout;
import com.midfag.game.GUI.GUI;
import com.midfag.game.GUI.buttons.Button;
import com.midfag.game.GUI.world_debug.*;

import com.midfag.game.screen_effect.ScreenEffect;
import com.midfag.game.script.ScriptSystem;
import com.midfag.game.script.ScriptTimer;



public class GScreen implements Screen {
	
	private static final int lightmap_size = 300;
	public static int enemy_count=0;
	public static int plposx;
	public static int plposy;
	
	public static List<Entity> temp_entity_list=new ArrayList<Entity>();
	public static float battle_music_timer=0;
	public static boolean enemy_see_player=false;
	public static int WD_active=0;
	
	MySpriteComparator comparator = new MySpriteComparator();
	
	public static List<ButtonLayout> layouts = new ArrayList<ButtonLayout>();//_______________________nienie oece?aneeo eeiee
	public static List<WorldDebug> WD = new ArrayList<WorldDebug>();//_______________________nienie oece?aneeo eeiee
	
    public static float lightmap_spread_power = 0.35f;

	public static final int path_cell = 30;
    
    public static float white=Color.WHITE.toFloatBits();
    
    public static ScreenEffect screen_effect;

    public static Pixmap pixmap= new Pixmap(lightmap_size, lightmap_size, Format.RGB888);
    
    public float illum_cooldown=1f;
    
    long time=0;
    
    public static int bound_x_left; 
    public static int bound_x_right;
	
    public static int bound_y_down; 
    public static int bound_y_up;
    
    public static Boolean need_light_update=true;
    public static Boolean need_dynamic_light_update=true;
    public static Boolean need_shadow_update=true;
    public static Boolean need_pixmap_update=true;
    
    public static Color global_illumination=new Color(0.65f,0.65f,0.65f, 1f);

    public int draw_distance;
    
	public static Texture rect_white=Assets.load("rect_white");
    
    Color col1=new Color();
    Color col2=new Color();
    Color col3=new Color();
    Color col4=new Color();
    
    public static ShapeRenderer sr;
    
	private static final int enemy_gen_count = 0;

    public static CustomSpriteBatch batch;
    public static SpriteBatch batch_static;
    public static SpriteBatch batch_illum;
	
	public final Main game;
    
	public static int scr_h=700;
	public static int scr_w=1000;
	
	public static int background_path;
	public static int main_path;
    public static float[][][] path=new float[300][300][2];
    public static long[][] path_time=new long[300][300];
    
    public static float light_mask_R[][]=new float[300][300];
    public static float light_mask_G[][]=new float[300][300];
    public static float light_mask_B[][]=new float[300][300];
    
    public static float light_mask_total[][]=new float[300][300];
    
    
    public static int[][] tile_map=new int[300][300];
    
    public static int[][] tile_map_x=new int[300][300];
    public static int[][] tile_map_y=new int[300][300];
    
    public static int[][] tile_map_overlay=new int[300][300];
    
    public static Cluster[][] cluster=new Cluster[30][30];
    
    public static List<Phys> Phys_list = new ArrayList<Phys>();//nienie oece?aneeo eeiee
    public static List<Missile> Missile_list = new ArrayList<Missile>();//nienie nia?yaia
    public static List<Entity> Entity_list = new ArrayList<Entity>();//nienie nouanoa (IIN, aaei?aoee)
    public static List<Shd> Shd_list = new ArrayList<Shd>();//nienie neaaia, inoaaeyaiuo nia?yaaie
    public static List<GUI> GUI_list = new ArrayList<GUI>();//nienie iieuciaaoaeuneeo eioa?oaenia
    
    public static List<String> Timer = new ArrayList<String>();//nienie iieuciaaoaeuneeo eioa?oaenia
    public static List<Float> Timer_value = new ArrayList<Float>();//nienie iieuciaaoaeuneeo eioa?oaenia
    
    public static List<String> Timer_max_name = new ArrayList<String>();//nienie iieuciaaoaeuneeo eioa?oaenia
    public static List<Float> Timer_max = new ArrayList<Float>();//nienie iieuciaaoaeuneeo eioa?oaenia
    
    public static float Timer_max_reset_time=0.5f;
    
    public static long saved_timer;
    
    public static Entity pl;
    
    public static Entity pl_mech;
    public static Entity pl_human;
    
    public static float time_speed=1;
    public static float real_delta;
    
    public static OrthographicCamera camera;
    public static OrthographicCamera skills_camera;
    public static OrthographicCamera lightmap_camera;
    
    public static int cluster_x;
    public static int cluster_y;

   // public 

    public static List<Entity> Draw_list = new ArrayList<Entity>();//nienie iieuciaaoaeuneeo eioa?oaenia
    
    //public int[] test=new int[1000];
    
    public static Phys near_object;
    public float near_dist;
    
    public static Vector2 temp_vectorA=new Vector2(0.0f,0.0f);
    public static Vector2 temp_vectorB=new Vector2(0.0f,0.0f);
    
    public static Vector2 temp_vector_collision_result=new Vector2(0.0f,0.0f);
    public static Vector2 temp_vector=new Vector2(0.0f,0.0f);
    
    public Vector2 prev_pos=new Vector2();
    
    public static String id="";
    
    
   
    
    public float cooldown;
    public float overlay_cooldown;
    
    public static boolean show_debug=false;
    public static boolean show_equip=false;
    public static boolean show_skills_wheel=false;
    public static boolean show_edit=false;
    
    public static boolean main_control=true;
    
    public static float zoom=1;
    public static float need_zoom=1;
    
    public static float curx=1;
    public static float cury=1;
    
    public String active_id;
    
    public static List<Button> Button_list = new ArrayList<Button>();
    
    
    
    public boolean keypress=false;

	private byte path_calculate_mode;
	
	private int pcv_left=-80;
	private int pcv_right=-60;
	
	private float pixmap_update_cooldown;

	private Texture shadow_texture;

	private boolean first_draw=true;

	private boolean first_calc=true;

	public float path_cooldown=0.1f;

	private int fps;
	
	private int fps_max;
	private int fps_min;
	
	private float fps_reset_time=1f;
	
	private int cluster_draw_distance;

	public static float sho;


	public static boolean camera_auto_zoom=true;

	public static float wave_time;

	public static boolean path_visualisation=false;
	public static boolean phys_visualisation=false;

	public static Texture tile_texture=Assets.load("terrain");

	public static Texture[] tile= new Texture[50];

	public static float time_speed_value;
	
	public static FrameBuffer terrain_fbo= new FrameBuffer(Pixmap.Format.RGB888, 1000/1, 700/1, false);
	public static FrameBuffer entity_fbo= new FrameBuffer(Pixmap.Format.RGB888, 1000/1, 700/1, false);
	//public static FrameBuffer light_fbo= new FrameBuffer(Pixmap.Format.RGBA8888, 90, 90, false);
	
	public static final int lightmap_scale=1;
	
	//public static FrameBuffer lightmap_offset_mask= new FrameBuffer(Pixmap.Format.RGBA8888, 1000, 700, false);
	public static FrameBuffer lightmap_fbo= new FrameBuffer(Pixmap.Format.RGB888, lightmap_size, lightmap_size, false);
	public static FrameBuffer lightmap_fbo_main= new FrameBuffer(Pixmap.Format.RGB888, lightmap_size, lightmap_size, false);
	//public static FrameBuffer dynamic_lightmap_fbo= new FrameBuffer(Pixmap.Format.RGBA8888, lightmap_size*lightmap_scale, lightmap_size*lightmap_scale, false);
	
	public static FrameBuffer shadow_fbo= new FrameBuffer(Pixmap.Format.RGB888, lightmap_size*lightmap_scale, lightmap_size*lightmap_scale, false);

	
	public static Texture lightmap_texture;
	public static Texture dynamic_lightmap_texture;

	public static boolean show_dialog;
	
	public static Entity camera_target=pl;

	public static float debug_cooldown=0;

	public static boolean need_static_light_update=true;

	public static int lightmap_blur_pass=8;
	public static int lightmap_spread_pass=8;

	public static boolean chunk_info;

	public static boolean time_freeze=false;
	public static float enemy_see_player_timer;
	public static float light_blur_power=0.15f;
	
	public class MySpriteComparator implements Comparator<Entity> {
		@Override
		public int compare (Entity sprite1, Entity sprite2) {
			if (sprite2==null) {Helper.log("ERROR! ENTITY 2 IS NULL!");}
			if (sprite1==null) {Helper.log("ERROR! ENTITY 1s IS NULL!");}
			return (sprite2.pos.y - sprite1.pos.y) > 0 ? 1 : -1;
			//if (sprite2.pos.y = sprite1.pos.y)
			//return sprite1;
		}
	}
    
	public static void draw_shaded_text(String _s, float _x, float _y, Color _c, float _l)
	{
		batch_static.setColor(0.2f,0.2f,0.2f,0.8f);
		batch_static.draw(Assets.rect_white, _x-3, _y-15, _l+6, 20);
		
		Main.font.setColor(Color.BLACK);
		Main.font.draw(batch_static, _s, _x+1, _y-1);
		
		Main.font.setColor(_c);
		Main.font.draw(batch_static, _s, _x, _y);
		
		batch_static.setColor(Color.WHITE);
	}
	
	public static Entity get_collision(float _x1, float _y1, float _x2, float _y2, float _dx, float _dy, float _size)
	{
		
		Entity ne=null;
		int x_min=Math.min((int)(_x1/300)-1, (int)(_x2/300)-1);
    	int x_max=Math.max((int)(_x1/300)+1, (int)(_x2/300)+1);
    	
    	int y_min=Math.min((int)(_y1/300)-1, (int)(_y2/300)-1);
    	int y_max=Math.max((int)(_y1/300)+1, (int)(_y2/300)+1);
    	
    	x_min=Math.max(0, x_min);
    	y_min=Math.max(0, y_min);
    	
    	x_max=Math.min(29, x_max);
    	y_max=Math.min(29, y_max);
    	
		float min=9999;

		float dst=0;
    	
    	for (int x=x_min; x<=x_max; x++)
    	for (int y=y_min; y<=y_max; y++)
    	for (int j=0; j<cluster[x][y].Entity_list.size(); j++)
    	{
    		Entity e=cluster[x][y].Entity_list.get(j);
    	if (e.z<15)
    	{
    			

    			if (e.have_collision)
    			{	temp_vector_collision_result.set
    					(
	    					collision_vertical
			    			(
								e,															//target_entity
								_x1,_y1,											//start_point
								_x2,_y2,						//end_point
								_dx,_dy,	//dynamic
								_size															//size
				    		)
		    			);

    				
    				if (temp_vector_collision_result.x<9999)
    				{
    					
    					dst=(float) Math.sqrt((temp_vector_collision_result.x-_x1)*(temp_vector_collision_result.x-_x1)+(temp_vector_collision_result.y-_y1)*(temp_vector_collision_result.y-_y1));
    					
	    				if (dst<min){min=dst;ne=e;}
	    			}
    			}
    	}
    	}
    	
    				
    	
    	
    	return ne;
	}
	
	public static Vector2 collision_vertical(Entity _target, float _x1, float _y1, float _x2, float _y2, float _dx, float _dy, float _size)
	{
		float collision_size_x=_target.collision_size_x+_size;
		float collision_size_y=_target.collision_size_y+_size;
		
	
		///
		
		float dist=_target.pos.y+collision_size_y-_y1;
		if ((Math.abs(_dx)<999)&&(_target.pos.y+collision_size_y<_y1)&&(_target.pos.y+collision_size_y>_y2)&&(Math.abs(_target.pos.x-(_x1+dist*_dx))<collision_size_x))
		{return temp_vectorA.set(_x1+dist*_dx, _target.pos.y+collision_size_y);}
		
		////
		
		dist=_target.pos.y-collision_size_y-_y1;
		if ((Math.abs(_dx)<999)&&(_target.pos.y-collision_size_y>_y1)&&(_target.pos.y-collision_size_y<_y2)&&(Math.abs(_target.pos.x-(_x1+dist*_dx))<collision_size_x))
		{return temp_vectorA.set(_x1+dist*_dx, _target.pos.y-collision_size_y);}
		
		////
		
		dist=_target.pos.x+collision_size_x-_x1;
		if ((Math.abs(_dy)<999)&&(_target.pos.x+collision_size_x<_x1)&&(_target.pos.x+collision_size_x>_x2)&&(Math.abs(_target.pos.y-(_y1+dist*_dy))<collision_size_y))
		{return temp_vectorA.set(_target.pos.x+collision_size_x, _y1+dist*_dy);}
		
		////
		
		dist=_target.pos.x-collision_size_x-_x1;
		if ((Math.abs(_dy)<999)&&(_target.pos.x-collision_size_x>_x1)&&(_target.pos.x-collision_size_x<_x2)&&(Math.abs(_target.pos.y-(_y1+dist*_dy))<collision_size_y))
		{return temp_vectorA.set(_target.pos.x-collision_size_x, _y1+dist*_dy);}
		
		return temp_vectorA.set(999999, 999999);
	
	}
	
	public static Vector2 collision_missile(Missile _target, float _x1, float _y1, float _x2, float _y2, float _dx, float _dy, float _size)
	{
		float collision_size_x=_size;
		float collision_size_y=_size;
		
	
		///
		
		float dist=_target.pos.y+collision_size_y-_y1;
		if ((Math.abs(_dx)<999)&&(_target.pos.y+collision_size_y<_y1)&&(_target.pos.y+collision_size_y>_y2)&&(Math.abs(_target.pos.x-(_x1+dist*_dx))<collision_size_x))
		{return temp_vectorA.set(_x1+dist*_dx, _target.pos.y+collision_size_y);}
		
		////
		
		dist=_target.pos.y-collision_size_y-_y1;
		if ((Math.abs(_dx)<999)&&(_target.pos.y-collision_size_y>_y1)&&(_target.pos.y-collision_size_y<_y2)&&(Math.abs(_target.pos.x-(_x1+dist*_dx))<collision_size_x))
		{return temp_vectorA.set(_x1+dist*_dx, _target.pos.y-collision_size_y);}
		
		////
		
		dist=_target.pos.x+collision_size_x-_x1;
		if ((Math.abs(_dy)<999)&&(_target.pos.x+collision_size_x<_x1)&&(_target.pos.x+collision_size_x>_x2)&&(Math.abs(_target.pos.y-(_y1+dist*_dy))<collision_size_y))
		{return temp_vectorA.set(_target.pos.x+collision_size_x, _y1+dist*_dy);}
		
		////
		
		dist=_target.pos.x-collision_size_x-_x1;
		if ((Math.abs(_dy)<999)&&(_target.pos.x-collision_size_x>_x1)&&(_target.pos.x-collision_size_x<_x2)&&(Math.abs(_target.pos.y-(_y1+dist*_dy))<collision_size_y))
		{return temp_vectorA.set(_target.pos.x-collision_size_x, _y1+dist*_dy);}
		
		return temp_vectorA.set(999999, 999999);
	
	}
	
	
	public static void add_timer(String _s)
	{
		if (show_debug)
		if (debug_cooldown<=0)
		{
			Timer.add(_s);
			float tim=Math.round(TimeUtils.timeSinceNanos(saved_timer)/10000f)/100f;
			Timer_value.add(tim);
			
			boolean founded=false;
			
			for (int i=0; i<Timer_max_name.size(); i++)
			{
				if (Timer_max_name.get(i).equals(_s))
				{
					if (tim>Timer_max.get(i)){Timer_max.set(i, tim);}
					founded=true;
				}
			}
			
			if (!founded)
			{
				Timer_max.add(tim);
				Timer_max_name.add(_s);
			}
			
			//Timer_value.add(tim);
			//if ()
			saved_timer=TimeUtils.nanoTime();
		}
	}
	
	public static List<Entity> get_entity_list(Vector2 _v)
	{
		//List<Entity> l=new ArrayList<Entity>();
		temp_entity_list.clear();
		
		int temp_cluster_x=(int)(_v.x/300f);
		int temp_cluster_y=(int)(_v.y/300f);
	        
	  
	    for (int x=temp_cluster_x-2; x<=temp_cluster_x+2; x++)
	    for (int y=temp_cluster_y-2; y<=temp_cluster_y+2; y++)
	    if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
	    for (int i=0; i<cluster[x][y].Entity_list.size();i++)
	    {
	    	temp_entity_list.add(cluster[x][y].Entity_list.get(i));
	    }
	    
	    return temp_entity_list;
	}
	
    public GScreen get_this()
    {
    	return this;
    }
    
    public static boolean collision (float _xs, float _ys, float _xe, float _ye, float _px, float _py, float _r)//;
    {
    	float xx=_xs-_xe;
    	float yy=_ys-_ye;
    	
    	float dy=yy/xx;
    	float dix=_xs-_px;
    	
    	float dx=xx/yy;
    	float diy=_ys-_py;
    	
    	float point_A=dix*dy;
    	float point_B=diy*dx;
    	
    	
    	
    	float iff=15.0f/(Math.abs(diy-point_A)-15.0f);

    	//float xx=_xs-_xe;
    	Main.shapeRenderer.setColor(Color.RED);
    	Main.shapeRenderer.circle(_px, _py-Math.abs(diy-point_A), 3);
    	
    	Main.shapeRenderer.setColor(Color.GREEN);
    	Main.shapeRenderer.circle(_px+Math.abs(dix-point_B), _py, 3);
    	
    	if (((Math.abs(dix-point_B))<15+15*iff)&&(iff>0))
    	{Main.shapeRenderer.setColor(Color.BLUE);}
    	else
    	{Main.shapeRenderer.setColor(Color.ORANGE);}
    	Main.shapeRenderer.circle(_px+15+15*iff, _py, 3);
    	
    	return false;
    }
    
    public static Entity add_entity_to_map(Entity _e)
    {
    	int x=(int)(_e.pos.x/300f);
    	int y=(int)(_e.pos.y/300f);
    
    	//System.out.println("Object="+_e);
    	
    	if ((x>0)&&(x<30)&&(y>0)&&(y<30)&&(_e!=null))
    	{cluster[x][y].Entity_list.add(_e);}
    	

    	if ((_e.is_enemy)&&(!_e.is_decor)&&(_e!=null))
    	{
    		Helper.log("ENEMY "+_e.getClass().getName()+" x:"+_e.pos.x+" y:"+_e.pos.y);
    		enemy_count++;
    	}
    	_e.init("putter");
    	
    	return _e;
    }
    
    
    public static Phys get_contact(float _x,float _y,float _x2,float _y2,float _dx,float _dy,float _d,boolean _break, boolean _global, boolean _walk)
    {
    	float near_dist=9999;
    	near_object=null;
    	
    	int x_min=Math.min((int)(_x/300)-1, (int)(_x2/300)-1);
    	int x_max=Math.max((int)(_x/300)+1, (int)(_x2/300)+1);
    	
    	int y_min=Math.min((int)(_y/300)-1, (int)(_y2/300)-1);
    	int y_max=Math.max((int)(_y/300)+1, (int)(_y2/300)+1);
    	
    	x_min=Math.max(0, x_min);
    	y_min=Math.max(0, y_min);
    	
    	x_max=Math.min(29, x_max);
    	y_max=Math.min(29, y_max);
    	
    	for (int x=x_min; x<=x_max; x++)
    	for (int y=y_min; y<=y_max; y++)
    	for (int i=0; i<cluster[x][y].Phys_list.size(); i++)
    	{
    		
    		
    			Phys po=cluster[x][y].Phys_list.get(i).is_contact(_x,_y,_x2,_y2,_dx,_dy,_d);
    			
    			//cluster[x][y].Phys_list.get(i).draw();
    			
		    	if (po!=null)
		    	if (po.vector_mul<near_dist)
		    	if ((po.move_block)||(!_walk))
		    	{
		    		near_object=po;
		    		near_dist=po.vector_mul;
		    		
		    		if (_break){ x=999; y=999; break;}
		    	}
    		
    	}
    	
    	if (_global)
    	{
    		if (Phys_list.size()>0)
	    	for (int i=0; i<Phys_list.size(); i++)
	    	{
	    			Phys po=Phys_list.get(i).is_contact(_x,_y,_x2,_y2,_dx,_dy,_d);
	    			//Phys_list.get(i).draw();
	    			
			    	if (po!=null)
			    	if (po.vector_mul<near_dist)
			    	{
			    		near_object=po;
			    		near_dist=po.vector_mul;
			    	}
	    		}
    	}
    	
    	return near_object;
    }
    
    public static float rnd(float _r)
    {
    	return (float)(Math.random()*_r);
    	//return 0;
    }
    
    public static float sinR(float _a)
    {
    	return (float) Math.sin(Math.toRadians(_a));
    }
    
    public static float cosR(float _a)
    {
    	return (float) Math.cos(Math.toRadians(_a));
    }
    
    public static float sin(float _a)
    {
    	return (float) Math.sin((_a));
    }
    
    public static float cos(float _a)
    {
    	return (float) Math.cos((_a));
    }
    
    
    
    public GScreen(final Main gam) {
    	
    	
    	
    	WD.add(new WorldDebugIlluminationPower());
    	WD.add(new WorldDebugIlluminationBlurPass());
    	WD.add(new WorldDebugIlluminationSpreadPass());
    	
    	WD.add(new WorldDebugIlluminationColorR());
    	WD.add(new WorldDebugIlluminationColorG());
    	WD.add(new WorldDebugIlluminationColorB());
    	
    	WD.add(new WorldDebugShadowMapOpacity());
    	WD.add(new WorldDebugLightBlurOpacity());
    	
    	Localisation.locad_local();

    	sr=gam.shapeRenderer;	
    	
    	for (int i=0; i<300; i++)
    	for (int j=0; j<300; j++)
    	{
    		light_mask_R[j][i]=global_illumination.r;
    		light_mask_G[j][i]=global_illumination.g;
    		light_mask_B[j][i]=global_illumination.b;
    	}
        
       if (Main.script_activate)
       {
    	ScriptSystem.add_script("test");
    	//ScriptSystem.add_script("test");
    	//ScriptSystem.Timer_list.add(new ScriptTimer("open_door_timer",			"open_door",			1.0f/100f));
    	ScriptSystem.Timer_list.add(new ScriptTimer("test_timer",			"test",			1.0f/100f,	false));
       }
    	
    	//ScriptSystem.add_script("test");
    	
    	//ScriptSystem.execute("test");
    	//find_and_push_entity SteelBoxDoor 0 50 0 50
    	
    	
    	

    	

    	
    	//System.out.println(listener.);
    	
    	tile_texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	terrain_fbo.getColorBufferTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	
        batch = new CustomSpriteBatch();
        //batch.setShader(shader);
        
        batch_static = new SpriteBatch();
      
        //batch_wheel = new SpriteBatch();
        //batch_static.setShader(batch.getShader());
      
        
        batch_illum = new SpriteBatch();
        
        
        /*
        for (int i=0; i<1000; i++)
        {
     	   test[i]=(int) (Math.random()*1000);
        }*/
        

    	
        for (int i=0; i<30; i++)//;
        for (int j=0; j<30; j++)//;
        {
        	cluster[j][i]=new Cluster();
        }
    	
        for (int i=0; i<300; i++)//;
        for (int j=0; j<300; j++)//;
        {
        	path[j][i][main_path]=100;
        }
        
        temp_vectorA=new Vector2(0,0);
        temp_vectorB=new Vector2(0,0);
        
        this.game = gam;
        Random rn=new Random();
        
        InputHandler.but=-1;
        

       // Helper.LoadMap();
        

        
        
    	

        
       // float tubes_count
        //DecorTube o=null;
        
        //add_entity_to_map(new DecorStoneWall(new Vector2(200,200)));
        
        /*for (int k=0; k<0; k++)
        {
        	o=null;
        	
	        for (int j=0; j<3; j++)
	        {
	        	
	        	Entity tub=add_entity_to_map(new DecorTubeCarcas(new Vector2(200+j*440,300+k*55)));
	        	//Entity_list.add();
	        	
	        	if (o!=null){((DecorTube)tub).left=o;}
	        	if (o!=null){((DecorTube)o).right=((DecorTube)tub);}
	        	
	        	o=(DecorTube) tub;
	        	
	        	
		        for (int i=0; i<10; i++)
		        {
		        	if ((Math.random()<1.9)||(i==0)||(i==9))
		        	{
		        		tub=add_entity_to_map(new DecorTube(new Vector2(200+j*440+40+i*40,300+k*55)));
		        		
		        		((DecorTube)tub).left=o;
		        		((DecorTube)o).right=((DecorTube)tub);
		        		
		        		o=(DecorTube) tub;
		        	}
		        }
	        }
        }*/

        
        for (int i=0; i<300; i++)
        for (int j=0; j<300; j++)
        {
        	tile_map[j][i]=(int) rnd(3);
        	if (rnd(100)<20)
        	{tile_map[j][i]=(int) rnd(9);}
        	
        	tile_map_overlay[j][i]=-1;
        	
        }
        
        
        Gdx.input.setInputProcessor(new InputHandler());

        for (int i=0; i<0; i++)
        {
        	float randx=rnd(900);
        	float randy=rnd(900);
        	
        	Phys_list.add(new Phys(new Vector2(randx,randy), new Vector2(randx+rnd(200)-100,randy+rnd(200)-100),true,null,true));
        }
        

        /*
        Phys_list.add(new Phys(new Vector2(115,100), new Vector2(115f,200),true,null,true));
        Phys_list.add(new Phys(new Vector2(101,200), new Vector2(302,201),true,null,true));
        Phys_list.add(new Phys(new Vector2(301,200), new Vector2(250,100),true,null,true));
        Phys_list.add(new Phys(new Vector2(250,100), new Vector2(100,101),true,null,true));*/
        
        
        Phys_list.add(new Phys(new Vector2(80,9000), new Vector2(80,80),true,null,true));
        Phys_list.add(new Phys(new Vector2(9005,9000), new Vector2(9000,80),true,null,true));
        
        Phys_list.add(new Phys(new Vector2(80,80), new Vector2(9000,80),true,null,true));
        Phys_list.add(new Phys(new Vector2(80,9000), new Vector2(9000,9000),true,null,true));
        
			/*
			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
			 * /=/=/=/			*PHYS* GENERATOR		/=/=/=/
			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
			 * 
			 */
        camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 700);
		camera.position.set(new Vector3(4500,4500,0));
		
		//camera.zoom=0.001f;
		
		skills_camera = new OrthographicCamera();
		skills_camera.setToOrtho(false, 1000/1, 700/1);
		skills_camera.position.set(new Vector3(500,350,0));
		skills_camera.zoom=1f;
		skills_camera.update();
		
		lightmap_camera = new OrthographicCamera();
		lightmap_camera.setToOrtho(false, lightmap_size, lightmap_size);
		lightmap_camera.position.set(new Vector3(lightmap_size/2.0f,lightmap_size/2.0f,0));
		lightmap_camera.update();
		 batch_illum.setProjectionMatrix(lightmap_camera.combined);
		
		
			
        for (int i=0; i<200*0; i++)
        {
	        Vector2 pc=new Vector2(rnd(10000),rnd(10000));
	        float vecangle=rnd(360);
	        Vector2 p1=new Vector2(pc.x+sinR(vecangle)*rnd(400),pc.y+cosR(vecangle)*rnd(400));
	        Vector2 p2=new Vector2(pc.x+sinR(vecangle+90)*rnd(400),pc.y+cosR(vecangle+90)*rnd(400));
	        Vector2 p3=new Vector2(pc.x+sinR(vecangle+180)*rnd(400),pc.y+cosR(vecangle+180)*rnd(400));
	        Vector2 p4=new Vector2(pc.x+sinR(vecangle+270)*rnd(400),pc.y+cosR(vecangle+270)*rnd(400));
	        
	        Phys_list.add(new Phys(p1, p2,true,null,true));
	        Phys_list.add(new Phys(p2, p3,true,null,true));
	        Phys_list.add(new Phys(p3, p4,true,null,true));
	        Phys_list.add(new Phys(p4, p1,true,null,true));
	        // create the camera and the SpriteBatch
        }

		camera.zoom=50.0f;
		

		


        for (int i=0; i<enemy_gen_count; i++)
        {
        	if (Math.random()>0.75f)
        	add_entity_to_map(new EntityVizjun(new Vector2(350+rnd(3000),300+rnd(3000))));
        	else
        	add_entity_to_map(new Entity(new Vector2(350+rnd(3000),300+rnd(3000))));
        }
        
        //Entity_list.add(new DecorStoneBarak(new Vector2(100,200)));

        /*
        for (int i=0; i<100; i++)
        for (int j=0; j<100; j++)
        {
        	cels[i][j]=rnd(1);
        	cels[i][j]=cels[i][j]*cels[i][j]*cels[i][j];
        	
        	if (rnd(100)<=1){cels[i][j]=10;}
        }*/

        Helper.LoadMap();
        
      
        
        //resize(scr_w, scr_h);

	   	
	   
        
		
        
        Assets.post_load_assets();
        
	   
	   	/*
        for (int i=0; i<30; i++)//;
        {
        	switch (rn.nextInt(4))
        	{
        		case 0: pl.inventory[i]=new WeaponSimpleFirle();	break;
        		case 1: pl.inventory[i]=new WeaponSimpleMinigun();	break;
        		case 2: pl.inventory[i]=new WeaponSimpleShotgun();	break;
        		case 3: pl.inventory[i]=new EnergoshieldSimple();	break;
        	}
        }*/
        

       // pl.spr.setSize(51,21);
      
        
        need_light_update=true;
        need_shadow_update=true;
        need_pixmap_update=true;
        need_pixmap_update=true;

    }



    //@SuppressWarnings("static-access")
	@Override
    public void render(float delta) {
		Timer.clear();
		Timer_value.clear();
		add_timer("start_point");
		
		/*
		for (int i=0; i<1000; i++)
		{int x=Math.round(i/7f);}
		add_timer("test");
		*/
        /*for (int i=0; i<100; i++)
        {
        	timer_list[i]=0;
        }*/
		
		real_delta=delta;
    	if (delta>0.1f) {delta=0.1f;}
    	
    	if (time_freeze) {delta*=0.10f;}
    	
    	
    	if ((InputHandler.key==Keys.Q)&&(InputHandler.keyF_release))
    	{
    		InputHandler.keyF_release=false;
    		
    		Entity en;
    		
    		for (int i=0; i<100; i++)
    		{
	    		en=new EntityPyra(new Vector2());
	    		
	    		en.pos.x=(float) (GScreen.pl.pos.x+Math.random()*1500-750);
	    		en.pos.y=(float) (GScreen.pl.pos.y+Math.random()*1500-750);
	    		
	    		GScreen.add_entity_to_map(en);
    		}
    	}
    	
    	if ((InputHandler.key==Keys.F)&&(InputHandler.keyF_release))
    	{
    		//Helper.log("_-_-_-_--_-_-__--__--");
    		InputHandler.keyF_release=false;
    		
    		if (pl.equals(pl_human))
    		{
    			pl=pl_mech;
    			pl_human.hidden=true;
    			
    			camera_target=pl;
    		}
    		else
    		{
    			pl=pl_human;
    			pl_human.hidden=false;
    			pl_human.reposition(pl_mech.pos.x, pl_mech.pos.y-30);
    			
    			camera_target=pl;
    		}	
    	}
    	
    	ScriptSystem.update(delta);
    	
    	debug_cooldown-=real_delta;
    	
    	
    
    	
    	float world_debug_change=0;
    	
    	/*
    	if (Gdx.input.isKeyPressed(Keys.DOWN))
    	{
    		world_debug_change=-real_delta;
    		//Helper.log("@@@@@@@@@@@@@@@@@@@@@@@@@@");
    		
    		
    	}
    	
    	if (Gdx.input.isKeyPressed(Keys.UP))
    	{
    		world_debug_change=real_delta;
    		//Helper.log("@@@@@@@@@@@@@@@@@@@@@@@@@@");
    		
    		
    	}
    	
    	if (world_debug_change!=0)
    	{
    		lightmap_spread_power+=world_debug_change/10f;
    		GScreen.need_light_update=true;
    		
    		if (lightmap_spread_power<0) {lightmap_spread_power=0;}
    		if (lightmap_spread_power>1) {lightmap_spread_power=1;}
    		
    		need_static_light_update=true;
    		need_pixmap_update=true;
    	}
    	*/
    	
    	//Draw_list.clear();
    	
    	//delta/=1f;
    	delta*=Math.min(1, time_speed);
    	delta*=time_speed;
    	
    	/*
    	if (!main_control)
    	{delta/=10f;}*/
    	
    	if (delta>0.1f){delta=0.1f;}

    	
       //Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
    	//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        /*batch.begin();
        	batch.draw(Assets.planet0, 5000-256000, 5000-256000, 512000,512000);
        batch.end();*/
        
    	cooldown--;
    	overlay_cooldown--;
    	
    	curx=InputHandler.realx;
    	cury=InputHandler.realy;
    	
    	InputHandler.update();
    	InputHandler.prevx=Gdx.input.getX();
    	InputHandler.prevy=Gdx.input.getY();
    	
    	int camplposx=(int)(camera.position.x/30f);
    	int camplposy=(int)(camera.position.y/30f);
    	
    	
    	if (first_draw) {draw_distance=150; first_draw=false;}
    	else
    	{draw_distance=Math.round(camera.zoom*(scr_w/30));}
    	
    	cluster_draw_distance=Math.round(camera.zoom*(scr_w/500f));
    	if (cluster_draw_distance>12) {cluster_draw_distance=12;}
    	
    	if (Gdx.input.isKeyPressed(Keys.K)) draw_distance=5; //{iter=10;}
    	//draw_distance=Math.min(draw_distance, 300);
    	
    	
    	//if (plposx>2000) {Helper.log("WTF");plposx=1000;}
    	
    	bound_x_left=camplposx-draw_distance; if (bound_x_left<0) {bound_x_left=0;}
    	bound_x_right=camplposx+draw_distance; if (bound_x_right>299) {bound_x_right=299;}
    	
    	bound_y_down=camplposy-draw_distance; if (bound_y_down<0) {bound_y_down=0;}
    	bound_y_up=camplposy+draw_distance; if (bound_y_up>299) {bound_y_up=299;}
    	
    	//TextureAtlas atlas= new TextureAtlas();
    	

    	//asaved_timer=TimeUtils.nanoTime();
    	
		/*======================================*/
    	
    	/*======================================*/
    	
		
		 cluster_x=(int)(camera.position.x/300f);
	     cluster_y=(int)(camera.position.y/300f);
	     
	    	
    	
	    if (need_dynamic_light_update)
	    {
	    	int cbound_x_left=(int) (cluster_x/300f-cluster_draw_distance); if (cbound_x_left<0) {cbound_x_left=0;}
	    	int cbound_x_right=(int) (cluster_x/300f+cluster_draw_distance); if (cbound_x_right>29) {cbound_x_right=29;}
	    	
	    	int cbound_y_down=(int)(cluster_y/300f-cluster_draw_distance); if (cbound_y_down<0) {cbound_y_down=0;}
	    	int cbound_y_up=(int)(cluster_y/300f+cluster_draw_distance); if (cbound_y_up>29) {cbound_y_up=29;}
	    	
			for (int x=cbound_x_left; x<=cbound_x_right; x++)
			for (int y=cbound_y_down; y<=cbound_y_up; y++)
			{
				for (int i=0; i<cluster[x][y].Entity_list.size();i++)
				 {
		        	Entity e=cluster[x][y].Entity_list.get(i);
		        	e.update_dynamic_color_state();
				 }	
			}
	    }
	    
	   // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
	    
	    add_timer("need_shadow_update_begin");
	    if (need_shadow_update)
		{
			need_shadow_update=false;
			
			shadow_fbo.begin();
				batch_illum.begin();
					batch_illum.enableBlending();
					batch_illum.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
					
					batch_illum.setColor(1.0f,1.0f,1.0f,1f);
					batch_illum.draw(rect_white, 0, 0, lightmap_size*lightmap_scale, lightmap_size*lightmap_scale);
					
					batch_illum.setColor(sho,sho,sho,1f);
					//for (int k=0; k<10; k++)
					for (int i=bound_y_down; i<bound_y_up; i++)
					for (int j=bound_x_left; j<bound_x_right; j++)
			        {
			        	if (path[j][i][main_path]<-250)
			        	{
			        		batch_illum.draw(rect_white,j*lightmap_scale,i*lightmap_scale,1*lightmap_scale,1*lightmap_scale);
			        	}
			        }
				batch_illum.end();
			shadow_fbo.end();
			
			shadow_texture=shadow_fbo.getColorBufferTexture();
		}
	    add_timer("need_shadow_update_end");
	    
	    add_timer("need_light_update_begin");
	    //LightSystem.static_light_update();
	   
	    

	    
    	if (need_light_update)
		{
    		
    		need_light_update=false;
			//add_timer("shadow_update");
			
			batch_illum.enableBlending();
			batch_illum.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			
	    		lightmap_fbo.begin();
		    		batch_illum.begin();
		    			batch_illum.enableBlending();
		    			batch_illum.setColor(0.0f,0.0f,0.0f,1f);
			    		batch_illum.draw(rect_white,0,0,lightmap_size*lightmap_scale,lightmap_size*lightmap_scale);
			    		
			    		
			        	for (int x=cluster_x-cluster_draw_distance; x<=cluster_x+cluster_draw_distance; x++)
			        	for (int y=cluster_y-cluster_draw_distance; y<=cluster_y+cluster_draw_distance; y++)
			        	if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
			        	for (int i=0; i<cluster[x][y].Entity_list.size();i++)
			        	{
			        		Entity e=cluster[x][y].Entity_list.get(i);
			        		
			        		batch_illum.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);

			        		if ((!e.hidden)&&(e.light_source!=null))
			        		{
			        			batch_illum.setColor(e.light_source.R,e.light_source.G,e.light_source.B,1f);
			        			float po=e.light_source.light_power;
			        			batch_illum.draw(rect_white, (int)(e.pos.x/30f-(po-1f)/2f), (int)(e.pos.y/30f-(po/2.0f-0.5f)/2f),po, po/2.0f); 
			        		}
			        		
			        		batch_illum.setColor(1,1,1,1f);
			        		
			        		batch_illum.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);
			        		batch_illum.draw(shadow_texture, 0, lightmap_size*lightmap_scale,lightmap_size*lightmap_scale, -lightmap_size*lightmap_scale); 
			        	}
			    		 
		    		batch_illum.end();
	    		lightmap_fbo.end();
	    		
	    		lightmap_texture=lightmap_fbo.getColorBufferTexture();
	    		
	    		
	    					add_timer("light_source_light_generate");
	    		 
	    		

	    				batch_illum.enableBlending();
	    			
	    				//batch_illum.setColor(0.1f,0.1f,0.1f,1.0f);
	    				
	    				for (int k=0; k<lightmap_spread_pass*lightmap_scale*1; k++)
	    				{
	    					lightmap_fbo_main.begin();
	    	    			batch_illum.begin();
	    	    			//////
	    	    				if (k==0) {batch_illum.setColor(Color.BLACK); batch_illum.draw(Assets.rect_white, 0, 0, 999, 999); batch_illum.setColor(Color.WHITE);}
		    					batch_illum.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
					        	batch_illum.setColor(lightmap_spread_power,lightmap_spread_power,lightmap_spread_power,1.0f);				
		    				
			    				batch_illum.draw(lightmap_texture, 1, lightmap_size, lightmap_size, -lightmap_size);
			    				batch_illum.draw(lightmap_texture, -1, lightmap_size, lightmap_size, -lightmap_size);
			    				
			    				batch_illum.draw(lightmap_texture, 0, lightmap_size-1, lightmap_size, -lightmap_size);
			    				batch_illum.draw(lightmap_texture, 0, lightmap_size+1, lightmap_size, -lightmap_size);
		    				
			    				batch_illum.setColor(1,1,1,1f);
				        		
				        		batch_illum.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);
				        		batch_illum.draw(shadow_texture, 0, lightmap_size,lightmap_size, -lightmap_size); 
				        		
				        	//////
				    		batch_illum.end();
					    	lightmap_fbo_main.end();
					    	
					    	lightmap_fbo.begin();
	    	    			batch_illum.begin();
	    	    				batch_illum.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	    	    				batch_illum.draw(lightmap_fbo_main.getColorBufferTexture(), 0, lightmap_size,lightmap_size,-lightmap_size);
	    	    			batch_illum.end();
					    	lightmap_fbo.end();
					    	
					    	lightmap_texture=lightmap_fbo.getColorBufferTexture();
					    		//lightmap_texture=lightmap_fbo_main.getColorBufferTexture();
					    	
	    				}
	    				
	    				
	    				
	    									add_timer("spread");
	    				
	    				//batch_illum.setColor(0.0f,1.0f,0.0f,1f);
			    		//batch_illum.draw(rect_white,250,250,50,50);

	    		
	    		
	    		
	    		
    				batch_illum.enableBlending();
	    		
    				
    				for (int i=0; i<lightmap_blur_pass; i++)
    				{
    				lightmap_fbo_main.begin();	
    	    			batch_illum.begin();
	    					batch_illum.setColor(1f,1f,1f,light_blur_power);
	    					batch_illum.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	    					
	    					//batch_illum.draw(lightmap_fbo.getColorBufferTexture(), 0, lightmap_size, lightmap_size, lightmap_size);
		    				
		    				batch_illum.draw(lightmap_texture, -1.0f, lightmap_size, lightmap_size, -lightmap_size);
		    				batch_illum.draw(lightmap_texture, 1.0f, lightmap_size, lightmap_size, -lightmap_size);
		    				//batch_illum.draw(lightmap_fbo.getColorBufferTexture(), 0-1, 300*light_map_size, 300*light_map_size, -300*light_map_size);
		    				
		    				batch_illum.draw(lightmap_texture, 0, lightmap_size+1.0f, lightmap_size, -lightmap_size);
		    				batch_illum.draw(lightmap_texture, 0, lightmap_size-1.0f, lightmap_size, -lightmap_size);
		    				
		    				batch_illum.setColor(1,1,1,1f);
		    				
			        		batch_illum.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);
			        		batch_illum.draw(shadow_texture, 0, lightmap_size,lightmap_size, -lightmap_size); 
			        		
			        		
			        		
			    		batch_illum.end();
			    	lightmap_fbo_main.end();
			    	
			    	lightmap_fbo.begin();
	    			batch_illum.begin();
	    				batch_illum.setColor(Color.WHITE);
	    				batch_illum.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	    				batch_illum.draw(lightmap_fbo_main.getColorBufferTexture(), 0, lightmap_size,lightmap_size,-lightmap_size);
	    			batch_illum.end();
			    	lightmap_fbo.end();
			    	
			    	lightmap_texture=lightmap_fbo.getColorBufferTexture();
    				}
    				
    				
    				lightmap_fbo_main.begin();	
	    			batch_illum.begin();
	    				batch_illum.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
	    				batch_illum.setColor(global_illumination);
	    				batch_illum.draw(rect_white, 0, 0, lightmap_size, lightmap_size);
	    				
	    				
		    		batch_illum.end();
		    		
			    		if ((need_pixmap_update))			
	    				{
								
							//Helper.log("#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+lightmap_fbo.getColorBufferTexture().getTextureData().);
							pixmap_update_cooldown=0.1f;
							need_pixmap_update=false;
							Gdx.gl.glReadPixels(0, 0, lightmap_size, lightmap_size, GL20.GL_RGB, GL20.GL_UNSIGNED_BYTE,  pixmap.getPixels());
							
							//Helper.log("PIXMAP UPDATED "+need_pixmap_update);
						}
						
						add_timer("pixmap");
		    		lightmap_fbo_main.end();
		    		lightmap_texture=lightmap_fbo_main.getColorBufferTexture();
    				 
    			
									add_timer("blur");
						
    								
									
									
    				
								
    		
	    			//add_timer("get_pixmap");
    		//lightmap_texture=lightmap_fbo_main.getColorBufferTexture();
    		
	    	

		}
    	
    	//pixmap_update_cooldown-=real_delta;
    	
    	
	    if (need_static_light_update)
	    {
	    	need_static_light_update=false;
	    	
			for (int x=0; x<30; x++)
			for (int y=0; y<30; y++)
			{
				for (int i=0; i<cluster[x][y].Entity_list.size();i++)
				 {
		        	Entity e=cluster[x][y].Entity_list.get(i);
		        	e.update_color_state();
				 }	
			}
			
			add_timer("entity_receive_light");
	    }
    	
		/*======================================*/
    	add_timer("need_light_update_end");
    	/*======================================*/
    	


		
    	// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
		
    	
    	 if ((Gdx.input.isButtonPressed(1))&&(!show_edit))
    			//if (InputHandler.but==1)
    			{

    				float camlen=(float) Math.sqrt((camera_target.pos.x-InputHandler.posx)*(camera_target.pos.x-InputHandler.posx)+(camera_target.pos.y-camera_target.z-InputHandler.posy)*(camera_target.pos.y-camera_target.z-InputHandler.posy));
    				camlen/=4;
    				need_zoom=camlen*0.0002f+1;
    				//camera.zoom=camlen*0.001f+1;
    			    camera.position.add(-(camera.position.x-camera_target.pos.x+sinR(180-pl.rot)*camlen)/5, -(camera.position.y-camera_target.pos.y+cosR(180-pl.rot)*camlen)/5, 0.0f);
    			    camera.update();
    				
    			}
    			else
    			{
    				float camspeed=2;
    				if (!main_control){camspeed=5;}
    				
    				camera.position.add(-(camera.position.x-camera_target.pos.x)/camspeed, -(camera.position.y-camera_target.z-camera_target.pos.y)/camspeed, 0.0f);
    				camera.update();
    			}
    	
    	terrain_fbo.begin();
    	
		batch.begin();
	    //Gdx.gl.glBlendEquation(GL20.GL_FUNC_ADD);
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	      

    	
    
    	int terx=(int)(camera.position.x/90f);
    	int tery=(int)(camera.position.y/90f);
    	
    	int terrain_draw_distance=Math.round(camera.zoom*(scr_w/160f));
    	
    	int terrain_x_left=terx-terrain_draw_distance; if (terrain_x_left<0) {terrain_x_left=0;}
    	int terrain_x_right=terx+terrain_draw_distance; if (terrain_x_right>99) {terrain_x_right=99;}
    	
    	int terrain_y_down=tery-terrain_draw_distance; if (terrain_y_down<0) {terrain_y_down=0;}
    	int terrain_y_up=tery+terrain_draw_distance; if (terrain_y_up>99) {terrain_y_up=99;}

    	//batch.setColor(global_illumination);
    	
    	
    	
    	
    	
    

    	batch.setColor(global_illumination);
    	
		
    	batch.draw(Assets.planet_good0, 5000-18000*243, 5000-18000*243, 36000*243,36000*243);
    	batch.draw(Assets.planet_good1, 5000-18000*81, 5000-18000*81, 36000*81,36000*81);
    	batch.draw(Assets.planet_good2, 5000-18000*27, 5000-18000*27, 36000*27,36000*27);
    	batch.draw(Assets.planet_good3, 5000-18000*9, 5000-18000*9, 36000*9,36000*9);
    	batch.draw(Assets.planet_good4, 5000-18000*3, 5000-18000*3, 36000*3,36000*3);
    	batch.draw(Assets.planet_good5, 5000-18000, 5000-18000, 36000,36000);
    	
    	batch.setColor(Color.WHITE);
    	
    	wave_time+=real_delta;
    	for (int i=terrain_y_down; i<=terrain_y_up; i++)
		for (int j=terrain_x_left; j<=terrain_x_right; j++)
	 	{

			batch.draw(tile_texture, j*90-45, i*90-45, tile_map_x[j][i], tile_map_y[j][i], 180, 180);
	 	}
    	//batch.flush();
    	
		add_timer("       draw_tile");
		
			

		
		
			batch.setColor(Color.WHITE);
		
			//Helper.log("COLOR "+Color.WHITE.toFloatBits());
			batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);
			//for (int i=0; i<25; i++)
				{batch.draw(lightmap_texture,0,30*lightmap_size,30*lightmap_size,-30*lightmap_size);}
				//{batch.draw(shadow_texture,0,9000,9000,-9000);}
		
			batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			
			
			
		
		
		
		add_timer("missile_trail");
		
		


        
		

	    
	   // @SuppressWarnings("unused")
		Phys po=null;
    	Missile mis=null;
        for (int i=0; i<Missile_list.size();i++)
        {
        	mis=Missile_list.get(i);
        	
        	if (mis.lifetime>0)
        	{

	        	near_dist=99999;
	        	near_object=null;//w
	        	Entity near_entity=null;

	        	near_entity=get_collision
    			(
																				//target_entity
					mis.pos.x,mis.pos.y,											//start_point
					mis.pos.x+mis.sx*mis.speed*delta,mis.pos.y+mis.sy*mis.speed*delta,						//end_point
					(mis.sx)/(mis.sy),(mis.sy)/(mis.sx),	//dynamic
					0															//size
	    		);
		    	
		    	if ((near_entity!=null)&&(!near_entity.need_remove))
		    	{missile_collision_action(mis,near_entity);}
		    	
    	    	
        	}
        	mis.update(delta);	

        	if (mis.lifetime>0) {Missile_list.get(i).draw();}
        }
        
        add_timer("missile_collision_detect");
        
            for (int i=0; i<Missile_list.size();i++)
            {
            	if (Missile_list.get(i).lifetime<=-1)
            	{
            		Missile_list.remove(i);
            		i--;
            	}

            
            }
	    
            

		
        add_timer("entity");
        
        	batch.setColor(Color.WHITE);
        
        	if (chunk_info)
        	{
	  			for (int x=cluster_x-cluster_draw_distance; x<=cluster_x+cluster_draw_distance; x++)
	          	for (int y=cluster_y-cluster_draw_distance; y<=cluster_y+cluster_draw_distance; y++)
	          	if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
	          	{
	          		Main.font_big.setColor(Color.ORANGE);
	          		Main.font_big.draw(batch, ""+x+" "+y, x*300+17, y*300+17);
		            for (int i=0; i<cluster[x][y].Entity_list.size();i++)
		            {
		            	Entity e=cluster[x][y].Entity_list.get(i);
		            	
		            	Main.font.setColor(Color.WHITE);
		            	{
		            		Main.font.draw(batch, e.id.replace("com.midfag.entity", "")+" === "+e.cx+" "+e.cy, x*300, y*300+i*17+30);
		            		
		            		
		            	}
		            }
	          	}
        	}
        	
        	int fx=0;
            int fy=0;


            Entity near_interact=null;
            float interact_dist=99999f;
            
            //Helper.log("UPDATE BEGIN!");

            //batch.begin();
          	for (int x=cluster_x-8; x<=cluster_x+8; x++)
          	for (int y=cluster_y-8; y<=cluster_y+8; y++)
          	if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
            for (int i=0; i<cluster[x][y].Entity_list.size();i++)
            {
            	Entity e=cluster[x][y].Entity_list.get(i);
            	
            	if (e.update_calls==0)
    	        {
            		
    		    	if ((!e.hidden)&&((!e.is_decor)||(e.is_interact)))
    		    	{
    		    		//e.update_data[e.update_calls]="UPDATE DATA X="+x+" Y="+y+" I="+i;
    		    		e.update(delta*(1-e.time_slow_resist)+real_delta*e.time_slow_resist);
    		    		e.bottom_draw(delta);
    		    	}
    		    	
    		    	if (e.need_change_cluster) {i--; e.need_change_cluster=false;}
    		    	
    	        	 if ((e.is_interact)&&(Gdx.input.isKeyPressed(Keys.E))&&(Math.abs(pl.pos.x-GScreen.pl.pos.x)+Math.abs(pl.pos.y-GScreen.pl.pos.y)<80)&&(InputHandler.keyF_release))
    	        	 {
    	        		 //InputHandler.keyF_release=false;
    	        		 if (e.pos.dst(pl.pos)<interact_dist)
    	        		 {
    	        			 interact_dist=e.pos.dst(pl.pos);
    	        			 near_interact=e;
    	        		 } 
    	        	}
    	        	 
    	        	if (e.is_AI)
    	        	{	
    	        		if (!e.is_decor)
    	        		{
    		        		fx=(int)(e.pos.x/path_cell);
    			            fy=(int)(e.pos.y/path_cell);
    			            
    			        	if ((fx>1)&&(fy>1)&&(fx<298)&&(fy<298))
    			        	{
    			        			if (path[fx][fy][main_path]>0) {path[fx][fy][main_path]=-100;}
    			        			
    			        				float dirx=0;
    				        			float diry=0;
    				        			float dir=999.0f;
    				        			
    			        			
    				        			//int mov_dir=1;
    				        			//if ((e.target!=null)&&(e.target.pos.dst(e.pos)<528))
    				        			//{mov_dir=-0;}
    				        			
    				        			if (e.stuck<=0)
    				        			{
    				        				if ((Math.abs(path[fx][fy-1][main_path])<Math.abs(path[fx][fy+1][main_path]))&&(Math.abs(path[fx][fy-2][main_path])<200)) {diry=-1;}
	    				        			if ((Math.abs(path[fx][fy-1][main_path])>Math.abs(path[fx][fy+1][main_path]))&&(Math.abs(path[fx][fy+2][main_path])<200)) {diry=1;}
	    				        			
	    				        			if ((Math.abs(path[fx-1][fy][main_path])<Math.abs(path[fx+1][fy][main_path]))&&(Math.abs(path[fx-2][fy][main_path])<200)) {dirx=-1;}
	    				        			if ((Math.abs(path[fx-1][fy][main_path])>Math.abs(path[fx+1][fy][main_path]))&&(Math.abs(path[fx+2][fy][main_path])<200)) {dirx=1;}
    				        			}
    				        			else
    				        			{
    				        				dirx=e.random_mul_x;
    				        				diry=e.random_mul_y;
    				        			}
    				        			if (dirx*diry!=0) {dirx/=1.41f; diry/=1.41f;}
    			        			
    			        			

    				        		
    					        	e.add_impulse(e.speed*dirx, e.speed*diry,	delta*(1-e.time_slow_resist)+real_delta*e.time_slow_resist);
    			        	}
    		        	}
    	        	}
            	}

            }
          	
          	for (int draw_y=cluster_y+cluster_draw_distance; draw_y>=cluster_y-cluster_draw_distance; draw_y--)
			{
				
				Draw_list.clear();
				
		    	for (int draw_x=cluster_x-cluster_draw_distance; draw_x<=cluster_x+cluster_draw_distance; draw_x++)
		    	if ((draw_x>=0)&&(draw_y>=0)&&(draw_x<30)&&(draw_y<30))
		    	for (int i=0; i<cluster[draw_x][draw_y].Entity_list.size();i++)
		    	{
		    		Entity e=cluster[draw_x][draw_y].Entity_list.get(i);
		    		
		    		if (!e.hidden)
		    		{
		    			e.draw();
		    		}
		    		///
		    	}
		    	
		    	//Helper.log("Y="+draw_y+" size="+Draw_list.size());
		    	try {Draw_list.sort(comparator);} catch (Exception e) {e.printStackTrace();}
	
		         for (int i=0; i<Draw_list.size(); i++)
		         {
		        	Entity e=Draw_list.get(i);
		         	e.draw_action(delta);
		         	e.effect_draw(delta);
		         } 
			}
          
          	
          	if (enemy_see_player_timer>0)
          	{
          		enemy_see_player_timer-=real_delta;
          		
          		enemy_see_player=true;
          		if (enemy_see_player_timer<0) {enemy_see_player=false;}
          	}
          	
          	if (enemy_see_player)
          	{
          		//{Assets.battle_music_00.play();}
          		if ((battle_music_timer<0.2f)&&(battle_music_timer+real_delta>=0.2f)) {Assets.battle_music_00.play(); Assets.battle_music_00.setVolume(0.01f); Helper.log("TRY PLAY BATTLE MUSIC");}
          		if ((battle_music_timer>0.2)&&(battle_music_timer<1.2f)){Assets.battle_music_00.setVolume((battle_music_timer-0.2f)*Assets.battle_music_multiplier);}
          		if (battle_music_timer<5) {battle_music_timer+=real_delta;}
          		
          	}
          	else
          	{
          		if (battle_music_timer>0)
          		{
          			battle_music_timer-=real_delta;
          			if (battle_music_timer<=2) {Assets.battle_music_00.setVolume((battle_music_timer-1f)*Assets.battle_music_multiplier);}
          			if (battle_music_timer<=0) {Assets.battle_music_00.pause();}
          			
          		}
          	}
          	
          	if (near_interact!=null)
          	{
       		 if (near_interact.interact_entry_point.equals("#D"))
       		 {near_interact.default_interact_action(delta); }
       		 else
       		 {ScriptSystem.execute(near_interact.interact_entry_point);}
       		 
       		 InputHandler.keyF_release=false;
          	}

    	      		for (int x=cluster_x-8; x<=cluster_x+8; x++)
    	          	for (int y=cluster_y-8; y<=cluster_y+8; y++)
    	          	if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
    	            for (int i=0; i<cluster[x][y].Entity_list.size();i++)
    	            {
    	            	Entity e=cluster[x][y].Entity_list.get(i);
    	            	e.update_calls=0;
    	            	
    	            	if ((e.need_remove)) {cluster[x][y].Entity_list.remove(i); e.pre_death_action(false);  i--; if (e.is_enemy) {enemy_count--;}}

    	            }
    	      		
    	      		

    	      		
          	add_timer("entity_update");
          	
          	
          	
          	
		batch.end();
		
	    	sr.begin(ShapeType.Filled);
	    	Gdx.gl.glEnable(GL20.GL_BLEND);
	 	   for (int i=0; i<Missile_list.size();i++)
	        {
	 		   mis=Missile_list.get(i);
	 		   
	 		   mis.draw_shd(delta);
	        }
	 	   sr.end();

    	terrain_fbo.end();
    	
    	/*
    	entity_fbo.begin();
    	Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
    	batch.begin();
    	batch.setBlendFunction(-1, -1);
    	Gdx.gl20.glBlendFuncSeparate(Gdx.gl.GL_SRC_ALPHA,Gdx.gl.GL_ONE_MINUS_SRC_ALPHA, Gdx.gl.GL_ONE,Gdx.gl.GL_ONE);
	    	for (int draw_y=cluster_y+cluster_draw_distance; draw_y>=cluster_y-cluster_draw_distance; draw_y--)
			{
				
				Draw_list.clear();
				
		    	for (int draw_x=cluster_x-cluster_draw_distance; draw_x<=cluster_x+cluster_draw_distance; draw_x++)
		    	if ((draw_x>=0)&&(draw_y>=0)&&(draw_x<30)&&(draw_y<30))
		    	for (int i=0; i<cluster[draw_x][draw_y].Entity_list.size();i++)
		    	{
		    		Entity e=cluster[draw_x][draw_y].Entity_list.get(i);
		    		
		    		if (!e.hidden)
		    		{
		    			e.draw();
		    		}
		    		///
		    	}
		    	
		    	//Helper.log("Y="+draw_y+" size="+Draw_list.size());
		    	try {Draw_list.sort(comparator);} catch (Exception e) {e.printStackTrace();}
	
		         for (int i=0; i<Draw_list.size(); i++)
		         {
		        	Entity e=Draw_list.get(i);
		         	e.draw_action(delta);
		         	e.effect_draw(delta);
		         } 
			}
    	batch.end();
    	entity_fbo.end();
    	*/
    	
    	/*
    	lightmap_offset_mask.begin();
	    	batch.begin();
	    	Gdx.gl.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
		      	batch.setShader(Main.shader_lightmap_offset);
		      	for (int draw_y=cluster_y+cluster_draw_distance; draw_y>=cluster_y-cluster_draw_distance; draw_y--)
				{
					
					Draw_list.clear();
					
			    	for (int draw_x=cluster_x-cluster_draw_distance; draw_x<=cluster_x+cluster_draw_distance; draw_x++)
			    	if ((draw_x>=0)&&(draw_y>=0)&&(draw_x<30)&&(draw_y<30))
			    	for (int i=0; i<cluster[draw_x][draw_y].Entity_list.size();i++)
			    	{
			    		Entity e=cluster[draw_x][draw_y].Entity_list.get(i);
			    		
			    		if (!e.hidden)
			    		{
			    			e.draw();
			    		}
			    		///
			    	}
			    	
			    	//Helper.log("Y="+draw_y+" size="+Draw_list.size());
			    	try {Draw_list.sort(comparator);} catch (Exception e) {e.printStackTrace();}
		
			         for (int i=0; i<Draw_list.size(); i++)
			         {
			        	Entity e=Draw_list.get(i);
			         	e.draw_offset_mask(delta);
			         } 
				}
		      	batch.setShader(Main.shader_default);
	      	batch.end();
      	lightmap_offset_mask.end();
    	*/
		

    	
		add_timer("terrain_fbo_end");
		
		batch_static.begin();
		
		
		if (Gdx.input.isKeyPressed(Keys.H))
		{batch_static.setShader(Main.shader_bloom);}
		else
		{batch_static.setShader(Main.shader_default);}
		
		
		//terrain_fbo.begin();
		
			
		
			
				if (screen_effect!=null)
				{
					screen_effect.update(delta, real_delta);
				}
			
				batch_static.setColor(Color.WHITE);
				batch_static.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
				//for (int i=0; i<3; i++)
				{batch_static.draw(terrain_fbo.getColorBufferTexture(),0,scr_h,scr_w,-scr_h);}
				
				
				
				/*batch_static.setShader(Main.shader_lightmap);
				
				
				
					Main.shader_lightmap.setUniformf("zoom",camera.zoom);
					Main.shader_lightmap.setUniformf("x",camera.position.x/9000f);
					Main.shader_lightmap.setUniformf("y",camera.position.y/9000f);
					
					lightmap_offset_mask.getColorBufferTexture().bind(2);
					Main.shader_lightmap.setUniformi("u_texture3", 2); //passing first texture!!!
					
					lightmap_texture.bind(1);
					Main.shader_lightmap.setUniformi("u_texture2", 1); //passing first texture!!!
					
					entity_fbo.getColorBufferTexture().bind(0);
					
					
					Main.shader_lightmap.setUniformi("u_texture", 0); //passing first texture!!!
					
					batch_static.setBlendFunction(-1, -1);
					Gdx.gl20.glBlendFuncSeparate(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA,GL20.GL_ONE, GL20.GL_DST_ALPHA);
				{batch_static.draw(entity_fbo.getColorBufferTexture(),0,scr_h,scr_w,-scr_h);}*/
				
				//{batch_static.draw(terrain_fbo.getColorBufferTexture(),150,scr_h-15,scr_w,-scr_h);}
				batch_static.setShader(Main.shader_default);
				batch_static.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
				//{batch_static.draw(lightmap_offset_mask.getColorBufferTexture(),0,scr_h,scr_w,-scr_h);}
				//batch_static.draw(terrain_fbo.getColorBufferTexture(),0,scr_h,scr_w,-scr_h);
				
				
		//terrain_fbo.end();
		batch_static.end();
		
		
		
		add_timer("draw_FBO");


	   /*
	    * plposx=(int)(pl.pos.x/path_cell);
	    plposy=(int)(pl.pos.y/path_cell);
	    *
	    */
	    time++;
		

		
		

		
	   
 		
 		if ((show_edit))
 		{
			sr.begin(ShapeType.Line);
				Gdx.gl.glEnable(GL20.GL_BLEND);
		       
		        
		        
				sr.setColor(0.15f, 0.16f, 0.17f, 0.25f);
				for (int i=0; i<30; i++)
				for (int j=0; j<30; j++)
				{		
					sr.rect(0, 0, j*30*10,i*30*10);
				}
				
				int px=(int)(camera.position.x/30f);
				int py=(int)(camera.position.y/30f);
				
				for (int i=-10; i<10; i++)
				for (int j=-10; j<10; j++)
				{		
					sr.rect((px+j)*30-30, (py+i)*30-30, 30,30);
				}
				
				
				sr.setColor(Color.GREEN);
				
				int x_min=Math.min((int)(pl.pos.x/300)-1, (int)(InputHandler.posx/300)-1);
		    	int x_max=Math.max((int)(pl.pos.x)+1, (int)(InputHandler.posx/300)+1);
		    	
		    	int y_min=Math.min((int)(pl.pos.y/300)-1, (int)(InputHandler.posy/300)-1);
		    	int y_max=Math.max((int)(pl.pos.y/300)+1, (int)(InputHandler.posy/300)+1);
		    	
		    	x_min=Math.max(0, x_min);
		    	y_min=Math.max(0, y_min);
		    	
		    	x_max=Math.min(29, x_max);
		    	y_max=Math.min(29, y_max);
		    	
		    	sr.line(pl.pos.x, pl.pos.y,InputHandler.posx, InputHandler.posy);
		    	
    			float min=99999999;
    			//float xx;
    			//float yy;
    			float dst=9999;
    			//Helper.log("======= ");
    			temp_vector.x=99999;
    			temp_vector.y=99999;
    			
    			int counter=0;
    			
    			//for (int k=0; k<1000; k++)
		    	for (int x=x_min; x<=x_max; x++)
		    	for (int y=y_min; y<=y_max; y++)
		    	for (int i=0; i<cluster[x][y].Entity_list.size(); i++)
		    	{
		    			Entity e=cluster[x][y].Entity_list.get(i);
		    			
		    			if (pl.pos.y!=InputHandler.posy)
		    			{
		    				temp_vector_collision_result.set
		    				(collision_vertical
				    			(
									e,															//target_entity
									pl.pos.x,pl.pos.y,											//start_point
									InputHandler.posx,InputHandler.posy,						//end_point
									(pl.pos.x-InputHandler.posx)/(pl.pos.y-InputHandler.posy),(pl.pos.y-InputHandler.posy)/(pl.pos.x-InputHandler.posx),	//dynamic
									0															//size
					    		)
				    		);

		    				
		    				if (temp_vector_collision_result.x<99999)
		    				{
		    					
		    				
		    						dst=Math.abs(temp_vector_collision_result.x-pl.pos.x)+Math.abs(temp_vector_collision_result.y-pl.pos.y);
		    						sr.circle(temp_vector_collision_result.x, temp_vector_collision_result.y, 3);
			    					
			    					/*batch.begin();
			    						Main.font_big.draw(batch, ""+dst+ "("+min+")["+counter+"]", temp_vector_collision_result.x+15, temp_vector_collision_result.y);
			    					batch.end();*/
			    					
			    				if (dst<min)
			    				{

			    					
			    					min=dst;
			    					
			    					//Helper.log("TARGET CHANGED "+dst+"|"+counter);
			    					
			    					temp_vector.x=temp_vector_collision_result.x;
			    					temp_vector.y=temp_vector_collision_result.y;	
			    					
			    					counter++;
			    				}
		    				}
		    				
		    			}
		    	}
		    	
		    	
		    	{sr.circle(temp_vector.x,temp_vector.y, 15);}
		    	
		 		sr.end();
			
 		}
 		

 		
 		
 		 add_timer("path_calculate_additional");
		
		
 					if (cooldown<=0)
 					{cooldown=1;}
 					
 					if (overlay_cooldown<=0)
 					{overlay_cooldown=30;}
 		
 		

       
        
 					
 		
        
 		if (!pl.rotate_block)
	    {
 			
 		
 			float a=InputHandler.posx-pl.pos.x;
	    	float b=(InputHandler.posy)-pl.pos.y;
	    	
	    	float c=(float) Math.toDegrees(Math.atan2(a, b));
	    	float difference=pl.rot;
	    	pl.rot=180-c+180;
	    	difference=Math.abs(difference-pl.rot);
	    	
	    	if ((pl.armored[0]!=null)&&(pl.armored[0].is_rotate_reset_charge)) 
	    	{
	    		pl.armored[0].cd+=difference/50f;
	    		if (pl.armored[0].cd>pl.armored[0].total_shoot_cooldown) {pl.armored[0].cd=pl.armored[0].total_shoot_cooldown;}
	    		
	    		//sr.begin(ShapeType.Filled);
	    		//	sr.circle(pl.pos.x, pl.pos.y, pl.armored[0].cd/pl.armored[0].total_shoot_cooldown*30f);
	    		//sr.end();
	    	}
	    	
	    	if (c<0){c=360+c;}
	    	
	    	
	    	if (c>360){c=c-360;}
	    	//pl.spr.setRotation(360-c);
	    	if (c>347)
	    	{pl.draw_sprite=0;}
	    	else
	    	{pl.draw_sprite=Math.min(15, Math.round((c)/22.5f));}
    	}
    	

    	

            near_object=null;
        	near_dist=99999;
        
    		
       	 prev_pos.x=pl.pos.x+0.0001f;
       	 prev_pos.y=pl.pos.y+0.0001f;
       	 
      
       	 boolean is_press=false;
       	
       	
       	 float sp=1;
       	 if (show_edit){sp=5;}
       	 
       	 if ((((pl.armored_shield!=null)&&(pl.armored_shield.value>0))||(pl.armored_shield==null))&&(pl.active))
       	 {
	       	 if (Gdx.input.isKeyPressed(Keys.W)){pl.add_impulse(0, pl.speed,delta*(1-pl.time_slow_resist)+real_delta*pl.time_slow_resist*sp);  is_press=true; pl.move_vert=true; pl.direction=0;}
	       	 if (Gdx.input.isKeyPressed(Keys.S)){pl.add_impulse(0, -pl.speed,delta*(1-pl.time_slow_resist)+real_delta*pl.time_slow_resist*sp); is_press=true; pl.move_vert=true; pl.direction=2;}
	       	 
	       	 if (Gdx.input.isKeyPressed(Keys.A)){pl.add_impulse(-pl.speed, 0,delta*(1-pl.time_slow_resist)+real_delta*pl.time_slow_resist*sp); is_press=true; pl.move_vert=false; pl.direction=3;}
	       	 if (Gdx.input.isKeyPressed(Keys.D)){pl.add_impulse( pl.speed, 0,delta*(1-pl.time_slow_resist)+real_delta*pl.time_slow_resist*sp); is_press=true; pl.move_vert=false; pl.direction=1;}
       	 }
       	 
       	 if (!is_press)
	     {

	       	 //if (time_speed<0.025f){time_speed=0.025f;}
       	 }

            
        
        
        
       
		
		
	   
	    
	    
      	
      	if ((path_visualisation))
		{
			sr.begin(ShapeType.Filled);
			Gdx.gl.glEnable(GL20.GL_BLEND);
		    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
	   //for (int k=0; k<3; k++)
		
		 for (int i=plposy-81; i<plposy+81; i++)
		 for (int j=plposx-81; j<plposx+81; j++)
		 		if ((i>0)&&(i<299)&&(j>0)&&(j<299))
		 		{
		 		

		 					sr.setColor(0,1,0,0.1f);
			 					
			 					if (path[j][i][main_path]>0)
			 		 			{
			 						float path_cell_color=(1-path[j][i][main_path]/150f)*1.0f;
			 						
			 						if (path[j][i][main_path]<50) {sr.setColor(0,path_cell_color,0,0.5f);}
			 						else
			 						if (path[j][i][main_path]<100) {sr.setColor(path_cell_color,path_cell_color,0,0.5f);}
			 						else
			 						{sr.setColor(path_cell_color,0,0,0.5f);}
			 		 			}
			 		 			
			 					
			 					if (path[j][i][main_path]==0)
			 		 			{
			 		 	 			sr.setColor(1,1,1,0.5f);
			 		 			}
			 		 			
			 					
			 					if (path[j][i][main_path]<0)
			 					{
			 						sr.setColor(1,0,0,0.25f);
			 					}
			 				
			 					game.shapeRenderer.rect(j*30,i*30, 30,30);

		 			/*
		 			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
		 			 * /=/=/=/		*PATH* VISUALISATION		/=/=/=/
		 			 * /=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/=/
		 			 * 
		 			 */
		 				
		 		}

			sr.end();
			
			
			
			batch.begin();
				batch.setColor(Color.WHITE);
				Main.font.setColor(Color.WHITE);
				Main.font.getData().setScale(0.5f);
				 	for (int i=plposy-51; i<plposy+51; i++)
					 for (int j=plposx-51; j<plposx+51; j++)
					 if ((i>0)&&(i<299)&&(j>0)&&(j<299)&&(path[j][i][main_path]>=0)&&(path[j][i][main_path]<1000))
					{Main.font.draw(batch, ""+Math.round(path[j][i][main_path]), j*30+10f, i*30+15f);}
				 Main.font.getData().setScale(1.0f);
			batch.end();
			
			
		}
      	
      		//for (int k=0; k<10; k++)
      	
      	
      		path_cooldown-=delta;	
      		if (path_cooldown<=0) 
      		//for (int k=0; k<10; k++)
      		{

      			//boolean log=true;


      					
      				
				if (path_calculate_mode==0)
				{
					for (int i=plposy-81; i<plposy+81; i++)
					for (int j=plposx+pcv_left; j<plposx+pcv_right; j++)
					if ((i>0)&&(i<299)&&(j>0)&&(j<299))
					if (path[j][i][background_path]>-800)	
					{
						//if (log) {log=false; Helper.log("WTF clear");}
						if ((path[j][i][background_path]>0)) {path[j][i][background_path]+=25;}else {path[j][i][background_path]+=100;}
						if ((path[j][i][background_path]>300)) {path[j][i][background_path]=300;}
						if (path[j][i][background_path]==0) {path[j][i][background_path]=800;}
						//if (path[j][i]<=0) {path[j][i]+=80;}
					}
				}
				
			
				
				if (path_calculate_mode==1)
					for (int i=plposy-80; i<plposy+80; i++)
					for (int j=plposx+pcv_left; j<plposx+pcv_right; j++)
					if ((i>0)&&(i<299)&&(j>0)&&(j<299))
					if (path[j][i][background_path]>0)
					{
						float curpa=path[j][i][background_path];
						float plus=curpa+1;
						
						if ((path[j][i+1][background_path]>curpa)) {path[j][i+1][background_path]=plus;}
						if ((path[j+1][i][background_path]>curpa)) {path[j+1][i][background_path]=plus;}
						
						if ((path[j][i-1][background_path]>curpa)) {path[j][i-1][background_path]=plus;}
						if ((path[j-1][i][background_path]>curpa)) {path[j-1][i][background_path]=plus;}

						
					}
				
				
				if (path_calculate_mode==2)
				{
					for (int i=plposy+80; i>plposy-80; i--)
					for (int j=plposx+pcv_left; j<plposx+pcv_right; j++)
					if ((i>0)&&(i<299)&&(j>0)&&(j<299))
					if (path[j][i][background_path]>0)
					{
						float curpa=path[j][i][background_path];
						float plus=curpa+1;
						
						if ((path[j][i+1][background_path]>curpa)) {path[j][i+1][background_path]=plus;}
						if ((path[j+1][i][background_path]>curpa)) {path[j+1][i][background_path]=plus;}
						
						if ((path[j][i-1][background_path]>curpa)) {path[j][i-1][background_path]=plus;}
						if ((path[j-1][i][background_path]>curpa)) {path[j-1][i][background_path]=plus;}

						
					}
				}
				
				
					if (path_calculate_mode==3)
					{
						for (int i=plposy+80; i>plposy-80; i--)
						for (int j=plposx-pcv_left; j>plposx-pcv_right; j--)
						if ((i>0)&&(i<299)&&(j>0)&&(j<299))
						if (path[j][i][background_path]>0)	
						{
							float curpa=path[j][i][background_path];
							float plus=curpa+1;
							
							if ((path[j][i+1][background_path]>curpa)) {path[j][i+1][background_path]=plus;}
							if ((path[j+1][i][background_path]>curpa)) {path[j+1][i][background_path]=plus;}
							
							if ((path[j][i-1][background_path]>curpa)) {path[j][i-1][background_path]=plus;}
							if ((path[j-1][i][background_path]>curpa)) {path[j-1][i][background_path]=plus;}

							
						}
					}
				
				if (path_calculate_mode==4)
				{
						for (int i=plposy-80; i<plposy+80; i++)
						for (int j=plposx-pcv_left; j>plposx-pcv_right; j--)
						if ((i>0)&&(i<299)&&(j>0)&&(j<299))
						if (path[j][i][background_path]>0)	
						{
							float curpa=path[j][i][background_path];
							float plus=curpa+1;
							
							if ((path[j][i+1][background_path]>curpa)) {path[j][i+1][background_path]=plus;}
							if ((path[j+1][i][background_path]>curpa)) {path[j+1][i][background_path]=plus;}
							
							if ((path[j][i-1][background_path]>curpa)) {path[j][i-1][background_path]=plus;}
							if ((path[j-1][i][background_path]>curpa)) {path[j-1][i][background_path]=plus;}
							
						
						
							
						}

				}

				pcv_left+=20;
				pcv_right+=20;
				
				if ((path_calculate_mode==0)&&(pcv_left>=70)){pcv_right=81;}
				
				if (pcv_left>=80)
				{
					pcv_left=-80;
					pcv_right=-60;
					
					path_calculate_mode++;
					if (path_calculate_mode==1)
					{
						plposx=(int)(pl.pos.x/path_cell);
					    plposy=(int)(pl.pos.y/path_cell);
					    
						if (path[plposx][plposy][background_path]>=0)
						{path[plposx][plposy][background_path]=5;}
					}

					if (path_calculate_mode>4)
					{
						path_calculate_mode=0;
						
						if (main_path==0)
						{main_path=1;
						background_path=0;}
						else
						{main_path=0;
						background_path=1;}
						
						pcv_left=-81;
						pcv_right=-61;
					}
				}
				
				path_cooldown=0.00f;
      		}
      	
		
      	//first_calc=false;
      
      	


    		add_timer("path_calculate");
    	
        
      
        //0													o?a	o?e	iea	iei	oun										
        	//float test_float=	99	999	999	999	999	999	999	999	999	999	999	999	999f;
        	if (show_edit)
        	{
        		//for (int i=0; i<Phys_list.size(); i++)
        		//{Phys_list.get(i).draw();}
        	}
        	
        	
        	if(phys_visualisation)
	        {
        		sr.begin(ShapeType.Line);
	        	sr.setColor(Color.CYAN);
		      	for (int x=cluster_x-cluster_draw_distance; x<cluster_x+cluster_draw_distance; x++)
		      	for (int y=cluster_y-cluster_draw_distance; y<cluster_y+cluster_draw_distance; y++)
		      	if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
		      	{
		      		/*
		      		if(phys_visualisation)
		      		for (int i=0; i<cluster[x][y].Phys_list.size(); i++)
		      		{cluster[x][y].Phys_list.get(i).draw();}
		      		*/
		      		
		      		
		      		
		      		
	
		      			
				      	for (int i=0; i<cluster[x][y].Entity_list.size(); i++)
				      	{
				      		Entity e=cluster[x][y].Entity_list.get(i);
				      		float sx=e.collision_size_x;
				      		float sy=e.collision_size_y;
				      		
				      		sr.rect(e.pos.x-sx, e.pos.y-sy,sx*2,sy*2);
				      	}
		      		
		      		
		      		
		      		if (show_edit)
		      		for (int i=0; i<cluster[x][y].Entity_list.size(); i++)
		      		{
		      			if (cluster[x][y].Entity_list.get(i).selected)
		      			{sr.setColor(Color.GREEN);
		      			sr.circle(cluster[x][y].Entity_list.get(i).pos.x, cluster[x][y].Entity_list.get(i).pos.y, 1);
		      			
		      			sr.setColor(Color.RED);
		      			sr.circle(cluster[x][y].Entity_list.get(i).pos.x, cluster[x][y].Entity_list.get(i).pos.y+cluster[x][y].Entity_list.get(i).z, 1);}
		      		}
		      	}
		        sr.end();
        	}
        	
        	/*if (pl.dead_time>0)
        	{   
        		Gdx.gl.glEnable(GL20.GL_BLEND);
            	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        		sr.setColor(1, 0.25f, 0.125f, Math.min(1, (pl.dead_time/10f)*0.8f));
        		sr.rect(-10000, -10000, 20000, 20000);
        	}*/
        	
      
        	
    	
       
		Main.shapeRenderer.begin(ShapeType.Filled);
			//Main.shapeRenderer.setColor(0.5f, 1, 0.6f, 0.5f);
		
		Main.shapeRenderer.rect(pl.pos.x-camera.zoom/2f, pl.pos.y-camera.zoom/2f, camera.zoom,camera.zoom);
		Main.shapeRenderer.end();
		Main.shapeRenderer.setColor(0.9f, 1, 0.95f, 1.0f);
		
		
        
        
		
		
		if (true)
		{
		batch_static.begin();
		batch_static.setColor(Color.WHITE);
		Main.font.setColor(Color.WHITE);
			
				
			
			
			Main.font.draw(batch_static, "WARM: "+pl.armored_shield.warm, 17, 170);
			Main.font.draw(batch_static, "dx: "+InputHandler.dx, 17, 240);
			
			float warm_indicate=pl.armored_shield.warm/5f*Assets.panel.getWidth();
			
			batch_static.draw(Assets.panel, 400, 77);
			batch_static.setColor(Color.CYAN);
			batch_static.draw(Assets.rect_white, 405, 72, warm_indicate,30);
			
			
			for (int i=0; i<=(int)(50*pl.armored_shield.value/pl.armored_shield.total_value); i++)
			{
				if (i==(int)(50*pl.armored_shield.value/pl.armored_shield.total_value))
				{
					int hp1=(int)(50*pl.armored_shield.value/pl.armored_shield.total_value)*2;
					float hp2=pl.armored_shield.value/pl.armored_shield.total_value*100f;
					float hp3=(hp2-hp1)/2f;
					
					//Main.font.draw(batch_static, "hp1 "+hp1, 160, 29);
					//Main.font.draw(batch_static, "hp2 "+hp2, 160, 59);
					
					batch_static.setColor(hp3,hp3,hp3,hp3);
				}
				else
				{ batch_static.setColor(Color.WHITE);}
				
				batch_static.draw(Assets.diod, 400+7f*i+15, 77+3);
			}
			
		
		batch_static.setColor(Color.WHITE);
		
		{fps=Math.round(1.0f/real_delta);}
		
		if (fps_reset_time<=0)
		{
			fps_reset_time=1;
			
			fps_max=-1;
			fps_min=99999;
		}
		else
		{
			fps_reset_time-=real_delta;
		}
		
		if (fps>fps_max) {fps_max=fps;}
		if (fps<fps_min) {fps_min=fps;}
		
		
		draw_shaded_text("FPS: "+fps,0,30,Color.WHITE,100);
		draw_shaded_text("FPS (min): "+fps_min,110,30,Color.RED,100);
		draw_shaded_text("FPS (max): "+fps_max,220,30,Color.GREEN,100);
		
		Main.font_big.setColor(Color.BLACK);
		Main.font_big.draw(batch_static, Localisation.cache_enemy_count+": "+enemy_count, 16, scr_h-50);
		
		Main.font_big.setColor(Color.WHITE);
		Main.font_big.draw(batch_static, Localisation.cache_enemy_count+": "+enemy_count, 17, scr_h-49);
		
		
		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT))
		{
			WD.get(WD_active).update(real_delta);
			WD.get(WD_active).draw(real_delta);
		}
		
		Main.font.setColor(Color.WHITE);
		Main.font.draw(batch_static, "layouts count: "+layouts.size(), 0, 60);
		
		Main.font.setColor(Color.WHITE);
		Main.font.draw(batch_static, "battle music: "+battle_music_timer, 0, 90);
		
		Main.font.setColor(Color.WHITE);
		Main.font.draw(batch_static, "enemy see us: "+enemy_see_player, 0, 120);
		
		Main.font.setColor(Color.WHITE);
		//Main.font.draw(batch_static, "draw distance: "+draw_distance, 16, 60);
		//Main.font.draw(batch_static, "zoom: "+camera.zoom, 16, 160);
		

		
		if ((Gdx.input.isButtonPressed(1))&&(!show_edit))
		//if (InputHandler.but==1)
		{

			float camlen=(float) Math.sqrt((camera_target.pos.x-InputHandler.posx)*(camera_target.pos.x-InputHandler.posx)+(camera_target.pos.y-camera_target.z-InputHandler.posy)*(camera_target.pos.y-camera_target.z-InputHandler.posy));
			camlen/=4;
			need_zoom=camlen*0.0002f+1;
			//camera.zoom=camlen*0.001f+1;
		    camera.position.add(-(camera.position.x-camera_target.pos.x+sinR(180-pl.rot)*camlen)/5, -(camera.position.y-camera_target.pos.y+cosR(180-pl.rot)*camlen)/5, 0.0f);
		    camera.update();
			
		}
		else
		{
			float camspeed=2;
			if (!main_control){camspeed=5;}
			
			camera.position.add(-(camera.position.x-camera_target.pos.x)/camspeed, -(camera.position.y-camera_target.z-camera_target.pos.y)/camspeed, 0.0f);
			camera.update();
		}
			
			int pos=0;
			
			
			
			if ((pl.equals(pl_mech)))
			{
				
				for (int i=0; i<pl.Skills_list.size(); i++)
				{
					if (pl.Skills_list.get(i).learned)
					{
						
						pl.Skills_list.get(i).time_action(delta);
						
						if (pl.Skills_list.get(i).need_to_indicate)
		    			{pl.Skills_list.get(i).indicate(425+pos,100,real_delta);
		    			pos+=55;}
					}
				}
				
				pos=-125;
				
				for (int i=0; i<5; i++)
				{
					if (pl.armored_module[i]!=null)
					{
						pl.armored_module[i].indicate(scr_w/2+pos, 50, real_delta);
						pos+=61.2f/*=*/;
					}
					else
					{
						batch_static.setColor(1, 1, 1, 0.5f);
						batch_static.draw(Assets.indicate_null, scr_w/2+pos-22, 50-22);
						batch_static.setColor(Color.WHITE);
						pos+=61.2f/*=*/;
					}
				}
			}

		//game.shapeRenderer_static.begin(ShapeType.Filled);
			
			batch_static.setColor(Color.WHITE);
		
			//draw order
			//layout (pre draw)
			//button (pre draw)
			//button (post draw)
			//layout (post draw)
			
			if (show_equip)
			{
				batch_static.draw(Assets.gui_module, 73, 107);
				
				GScreen.batch_static.draw(Assets.text_bg_blue, 75, 440,850,250);
				
				for (int yy=0; yy<2; yy++)
				for (int xx=0; xx<2; xx++)
				{
					batch_static.draw(Assets.gui_module_bg, 95+220*xx, 150+150*yy);
					
					if ((pl.armored_module[xx+yy*2]!=null)&&(pl.armored_module[xx+yy*2].model!=null))
					{
						batch_static.draw(pl.armored_module[xx+yy*2].model, 90+220*xx, 145+150*yy);
					}
				}
				
				
			}
			 add_timer("01");
			for (int i=0; i<layouts.size(); i++)
			{
				if (!layouts.get(i).need_remove)
				{
					layouts.get(i).update(delta);
					layouts.get(i).pre_draw(delta);
				}
			}
			
			add_timer("02");
			
			for (int i=0; i<Button_list.size(); i++)
			{
				if (!Button_list.get(i).need_remove)
				{Button_list.get(i).draw();}
			}
			
			 add_timer("03");
			 
			for (int i=0; i<Button_list.size(); i++)
			{
				if (!Button_list.get(i).need_remove)
				{
					Button_list.get(i).second_draw();
					Button_list.get(i).update(delta);
					Button_list.get(i).second_update(delta);
				}
				else
				{Button_list.remove(i); i--;}
			}
			
			for (int i=0; i<layouts.size(); i++)
			{
				if (!layouts.get(i).need_remove)
				{
					
					layouts.get(i).post_draw(delta);
				}
			}
			
			for (int i=0; i<layouts.size(); i++)
			{
				if (layouts.get(i).need_remove) {layouts.remove(i); i--;}
			}
		
		
			 
			batch_static.end();
		}
			add_timer("04 "+batch_static.renderCalls);
			
			 batch_static.begin();
				for (int i=0; i<GUI_list.size(); i++)
					{GUI_list.get(i).update(real_delta);}
				
				for (int i=0; i<GUI_list.size(); i++)
					{GUI_list.get(i).update2(real_delta);}
				
			batch_static.end();
			
			
			 
			
		//game.shapeRenderer_static.end();
			

		
			
		if ((pl.armored_shield!=null)&&(pl.armored_shield.value<=0))
		{
			camera.rotate(1);
			camera.zoom+=0.001f;
			
			 if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			 {
				 pl.armored_shield.value=10000;
				 pl.dead_time=0;
				 
				 camera.direction.set(0, 0, -1);
				 camera.up.set(0, 1, 0);
				 camera.zoom=1;
				 camera.update();
				 
				 
			            
			     //Entity_list.add(new DecorStoneBarak(new Vector2(100,200)));
			     

			     
			    
			     
			     pl.init("ressurect");
			     
				
			}
		}
		add_timer("05");
		

		/*camera.position.x=Math.round(camera.position.x);
		camera.position.y=Math.round(camera.position.y);*/
		
		if ((InputHandler.keyF_release)&&(Gdx.input.isKeyPressed(Keys.SPACE)))
		{
			InputHandler.keyF_release=false;
			camera_auto_zoom=!camera_auto_zoom;
		}
		
		if (camera_auto_zoom)
		{
			camera.zoom+=(need_zoom-camera.zoom)*0.02f;
		}
		
        batch.setProjectionMatrix(camera.combined);
        add_timer("06");
        //batch_static.setProjectionMatrix(camera.combined);
      
        
        sr.setProjectionMatrix(camera.combined);
        
        game.shapeRenderer_static.setProjectionMatrix(skills_camera.combined);
        
        //batch_illum.setProjectionMatrix(lightmap_camera.combined);
        batch_static.setProjectionMatrix(skills_camera.combined);
        add_timer("07");
        
        InputHandler.scroll_amount=0;
        
   	 
	 
		
   		//game.shapeRenderer_static.end();
   			
        	Timer_max_reset_time-=real_delta;
        	
        	if (Timer_max_reset_time<=0)
        	{
        		Timer_max_reset_time=3.0f;
        		
        		for (int i=0; i<Timer_max.size(); i++)
        		{
        			Timer_max.set(i,0f);
        		}
        	}
        	
   			if (show_debug)
   			{
   				batch_static.begin();
   					batch_static.setColor(0.1f,0.2f,0.3f, 0.9f);
					batch_static.draw(Assets.text_bg_blue, scr_w-510,0,520,scr_h);
   				//batch_static.draw(Assets.star,0,0,900,900);
   				for (int i=0; i<Timer.size(); i++)
   				{
   					
   				
   					
   					
   					batch_static.setColor(Color.DARK_GRAY);
   						batch_static.draw(Assets.rect_white, scr_w-500, scr_h-i*25-90.5f,500,2);
   					batch_static.setColor(Color.WHITE);
   					
   					float timer_default=Timer_value.get(i);
   					if (timer_default<=1) {Main.font.setColor(Color.WHITE);}
   					else
   					if (timer_default<=1.5) {Main.font.setColor(Color.GREEN);}
   					else
   	   				if (timer_default<=3.0) {Main.font.setColor(Color.YELLOW);}
   	   				else
   	   				if (timer_default<=4.5) {Main.font.setColor(Color.ORANGE);}
   	   				else
   	   				if (timer_default<=6.0) {Main.font.setColor(Color.RED);}
   	   				else
   	   				if (timer_default>6.0) {Main.font.setColor(Color.CYAN);}
   					
   					Main.font.draw(batch_static, "draw delay: "+Timer.get(i), scr_w-500, scr_h-i*25-75.5f);
   					Main.font.draw(batch_static, ""+Timer_value.get(i), scr_w-100, scr_h-i*25-75.5f);
   					
   					for (int k=0; k<Timer_max_name.size(); k++)
   					{
   						if (Timer_max_name.get(k).equals(Timer.get(i)))
   						{
   							float timer_maximum=Timer_max.get(k);
   		   					if (timer_maximum<=1) {Main.font.setColor(Color.WHITE);}
   		   					else
   		   					if (timer_maximum<=1.5) {Main.font.setColor(Color.GREEN);}
   		   					else
   		   	   				if (timer_maximum<=3.0) {Main.font.setColor(Color.YELLOW);}
   		   	   				else
   		   	   				if (timer_maximum<=4.5) {Main.font.setColor(Color.ORANGE);}
   		   	   				else
   		   	   				if (timer_maximum<=6.0) {Main.font.setColor(Color.RED);}
   		   	   				else
   		   	   				if (timer_maximum>6.0) {Main.font.setColor(Color.CYAN);}
   							Main.font.draw(batch_static, ""+Math.round(Timer_max.get(k)*100f)/100f, scr_w-50, scr_h-i*25-75.5f);
   							
   							//float max=Timer_max.get(i)-(Timer_max.get(k)-Timer_value.get(i))/100.0f;
   							//Timer_max.set(k, max);
   						}
   					}
   					
   					/*
   					Main.font.setColor(Color.WHITE);
   					Main.font.draw(batch_static, "draw delay: "+Timer.get(i), scr_w-500.5f, scr_h-i*25-76);
   					*/
   					
   					
   				}
   				batch_static.end();
   			}
   			
   			add_timer("07");
   			
   			if (debug_cooldown<=0) {debug_cooldown=0.00f;}
        //InputHandler.but=-1;
    }

    public static void missile_collision_action(Missile mis, Entity near_entity) {
		// TODO Auto-generated method stub
    	if (!near_entity.is_decor)
		{
			float reflect_value=near_entity.armored_shield.total_reflect;
		
			float reflect_chance=Math.max(0.65f, 1.0f-reflect_value/mis.damage);//=1-0=1
		
			reflect_chance*=1.0f-(reflect_value/(reflect_value+100.0f));//=1*(1-0/100)=1*1=1
		
			if ((Math.random()<reflect_chance))
			{
				mis.hit_action(near_entity);
				mis.another_hit_action(near_entity);
				
				near_entity.hit_action(mis.damage,true);
				near_entity.burn_it(mis.fire_damage);
				near_entity.freeze_it(mis.cold_damage);
			}
		else
		{
			for (int k=0; k<near_entity.Skills_list.size(); k++)
			{
				
				if (near_entity.Skills_list.get(k).learned)
				{
					near_entity.Skills_list.get(k).prereflect_action(mis,near_entity);
				}
				
			}
			mis.lifetime=10;
			mis.angle+=Math.toRadians(180+(Math.random()*10-5));
			
			
			mis.update_vectors_state();
			
			mis.is_enemy=near_entity.is_enemy;
			mis.col=Color.GREEN;
			
			for (int k=0; k<near_entity.Skills_list.size(); k++)
			{
				
				if (near_entity.Skills_list.get(k).learned)
				{
					near_entity.Skills_list.get(k).reflect_action(mis,near_entity);
				}
				
			}
		}
		}
		else
		{
			mis.hit_action(near_entity);
			mis.another_hit_action(near_entity);
		}
	}

	@Override
    public void resize(int width, int height) {

		
		Helper.log("RESIZED "+width+"|"+height);
    	scr_w=(int) (width/1.0f);
    	scr_h=(int) (height/1.0f);

    	camera.setToOrtho(false, scr_w*1.0f, scr_h*1.0f);
    	skills_camera.setToOrtho(false, scr_w, scr_h);
    	
    	skills_camera.position.x=width/2f;
    	skills_camera.position.y=height/2f;
    	
    	camera.position.x=pl.pos.x;
    	camera.position.y=pl.pos.y;
    	
    	terrain_fbo.dispose();
    	terrain_fbo = new FrameBuffer(Pixmap.Format.RGB888, scr_w, scr_h, false);
    	terrain_fbo.getColorBufferTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	terrain_fbo.getColorBufferTexture().setWrap(TextureWrap.MirroredRepeat, TextureWrap.MirroredRepeat);
    	
    	//lightmap_fbo.dispose();
    	lightmap_fbo = new FrameBuffer(Pixmap.Format.RGB888, lightmap_size, lightmap_size, false);
    	lightmap_fbo.getColorBufferTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    	lightmap_fbo.getColorBufferTexture().setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
    	
    	/*
    	lightmap_offset_mask.dispose();
    	lightmap_offset_mask = new FrameBuffer(Pixmap.Format.RGB888, scr_w, scr_h, false);
    	lightmap_offset_mask.getColorBufferTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	lightmap_offset_mask.getColorBufferTexture().setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
    	//light_fbo = new FrameBuffer(Pixmap.Format.RGB888, (int)(scr_w/1f), (int)(scr_h/1f), false);*/
    	
    	//light_fbo.getColorBufferTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	
    	//illumination_fbo.getColorBufferTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	
    	Helper.log("width "+terrain_fbo.getWidth());
    	Helper.log("height "+terrain_fbo.getHeight());
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        //rainMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

      
    }



}