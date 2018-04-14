package com.midfag.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.InputProcessor;


import com.midfag.entity.Entity;
import com.midfag.entity.enemies.EntityEliteWheel;
import com.midfag.entity.enemies.EntityPyra;
import com.midfag.entity.enemies.EntityWheel;
import com.midfag.entity.friends.EntityTurret;
import com.midfag.game.Enums.ButtonVerticalFunction;
import com.midfag.game.Enums.EditMode;
import com.midfag.game.Enums.EquipGenerationType;
import com.midfag.game.Enums.Rarity;
import com.midfag.game.Enums.WorldConfigMode;
import com.midfag.game.GUI.GUI;
import com.midfag.game.GUI.GUIInventory;
import com.midfag.game.GUI.GUISkillsWheel;
import com.midfag.game.GUI.buttons.ButtonChangeQuality;
import com.midfag.game.GUI.buttons.ButtonEquip;
import com.midfag.game.GUI.buttons.ButtonRandomGenerator;
import com.midfag.game.GUI.buttons.ButtonVertical;

import com.midfag.game.GUI.buttons.ButtonSkill;
import com.midfag.game.GUI.edit.ButtonChangeMode;
import com.midfag.game.GUI.edit.ButtonLoadMap;
import com.midfag.game.GUI.edit.ButtonPathVisualize;
import com.midfag.game.GUI.edit.ButtonPhysVisualize;
import com.midfag.game.GUI.edit.ButtonRandomizeTile;
import com.midfag.game.GUI.edit.ButtonSaveMap;
import com.midfag.game.GUI.edit.GUIEdit;
import com.midfag.game.skills.Skill;


public class InputHandler implements InputProcessor {


	public static int initial_x;

	public static int initial_y;
    
    public static int posx;
    public static int posy;
    
    public static int sposx;
    public static int sposy;
    
    public static int realx;
    public static int realy;
    
    public static boolean MB=false;
    
    public static int but;
    
    public static int key;
    
    public static float MB_timer;
    
    public static float prevx;
    public static float prevy;
    
    public static float dx;
    public static float dy;
    
    public static boolean press=false;

	public static boolean keyF_release=false;
	public static boolean keyE_release=false;
	public static int scroll_amount;
	
	public static WorldConfigMode wcm=WorldConfigMode.ILLUMINATION_BLUR_POWER;
    // Ask for a reference to the Bird when InputHandler is created.
    public InputHandler() {
        // myBird now represents the gameWorld's bird.

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	MB=true;
        but=button;
        
        return true; // Return true to say we handled the touch.
    }

    @Override
    public boolean keyDown(int keycode) {
    	key=keycode;
    	

    	if (key==Keys.LEFT_BRACKET)
    	{
    		GScreen.WD_active--;
    		
    		if (GScreen.WD_active<0) {GScreen.WD_active=GScreen.WD.size()-1;}
    	}
    	
    	if (key==Keys.RIGHT_BRACKET)
    	{
    		GScreen.WD_active++;
    		
    		if (GScreen.WD_active>=GScreen.WD.size()) {GScreen.WD_active=0;}
    	}
    	
    	if (key==Keys.Q)
    	{
    		
    		Entity en;
    		
    		for (int i=0; i<25; i++)
    		{
	    		if (Math.random()>0.25f)
	    		{
	    			if (Math.random()>0.1f)
	    			{en=GScreen.add_entity_to_map(new EntityPyra(new Vector2()));}
	    			else
	    			{en=GScreen.add_entity_to_map(new EntityTurret(new Vector2())); en.is_enemy=true;}
	    		}
	    		else
	    		{
	    			if (Math.random()<0.9f)
	    			{en=GScreen.add_entity_to_map(new EntityWheel(new Vector2()));}
	    			else
	    			{en=GScreen.add_entity_to_map(new EntityEliteWheel(new Vector2()));}
	    		}
	    		
	    		
	    		
	    		en.pos.x=(float) (GScreen.pl.pos.x+Math.random()*3000-1500);
	    		en.pos.y=(float) (GScreen.pl.pos.y+Math.random()*3000-1500);
	    		
	    		GScreen.add_entity_to_map(en);
    		}
    	}
    	
    	
    	
    	//if (key!=Keys.F){keyF_release=true;}
    	
    	if (key==Keys.Z)
    	{
    		GScreen.main_control=GScreen.show_edit;
    		GScreen.show_edit=!GScreen.show_edit;
    		
    		GUIEdit gui=new GUIEdit();
    		
    		//GScreen.Button_list.add(new ButtonPutter(150,50,new DecorPilon2(new Vector2(),false),gui));
    		//GScreen.Button_list.add(new ButtonPutter(250,50,new DecorPilon3(new Vector2(),false),gui));
    		//GScreen.Button_list.add(new ButtonPutter(250,50,new DecorTube(new Vector2(),true),gui));
    		//GScreen.Button_list.add(new ButtonPutter(150,50,GScreen.Object_list.get(1)));
    		
    		GScreen.Button_list.add(new ButtonSaveMap(50,GScreen.scr_h-50));
    		GScreen.Button_list.add(new ButtonLoadMap(150,GScreen.scr_h-50));
    		
    		GScreen.Button_list.add(new ButtonRandomizeTile(650,GScreen.scr_h-50));
    		GScreen.Button_list.add(new ButtonPathVisualize(710,GScreen.scr_h-50));
    		GScreen.Button_list.add(new ButtonPhysVisualize(770,GScreen.scr_h-50));
    		
    		GScreen.Button_list.add(new ButtonChangeMode(300,GScreen.scr_h-50,EditMode.ENTITY,gui));
    		GScreen.Button_list.add(new ButtonChangeMode(400,GScreen.scr_h-50,EditMode.TILE,gui));
    		GScreen.Button_list.add(new ButtonChangeMode(500,GScreen.scr_h-50,EditMode.PATTERN,gui));
    		
			GScreen.Button_list.add(new ButtonVertical(650,40,gui,false,ButtonVerticalFunction.ENTITY_SELECTOR_OFFSET));
    		GScreen.Button_list.add(new ButtonVertical(650,65,gui,true,ButtonVerticalFunction.ENTITY_SELECTOR_OFFSET));
    		
    		GScreen.GUI_list.add(gui);
    	}
    	
    	if (key==Keys.O)
    	{
    		
    		
    		GScreen.show_skills_wheel=!GScreen.show_skills_wheel;
    		
    		GUI gui=new GUISkillsWheel();
    		
    		((GUISkillsWheel)gui).main_skill_picked=false;
    		
			GScreen.skills_camera.position.x=0;
			GScreen.skills_camera.position.y=0;
    		
    		if (GScreen.show_skills_wheel)
    		{
    			GScreen.show_equip=false;
    			
    			GScreen.skills_camera.zoom=1;

    			GScreen.skills_camera.update();
    			
    			Assets.skill_wheel.setTexture(new Texture(Gdx.files.internal("skills_wheel.png")));
    			Assets.skill_wheel.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear); 
    			GScreen.main_control=false;
    			
    			for (int i=0; i<GScreen.pl.Skills_list.size(); i++)
    			{
    				Skill sk=GScreen.pl.Skills_list.get(i);
    				
    				gui.Button_list.add(new ButtonSkill(sk.pos.x,sk.pos.y,sk,gui));
    			}
    			
    			GScreen.GUI_list.add(gui);
    			
    			
    		}
    		else
    		{
    			Assets.skill_wheel.getTexture().dispose();
    			GScreen.main_control=true;
    			
    			
    			GScreen.skills_camera.zoom=1;
    			GScreen.skills_camera.position.x=500;
    			GScreen.skills_camera.position.y=350;
    			GScreen.skills_camera.update();

    		}
    		

    	}
    	if (key==Keys.I)
    	{
			GScreen.skills_camera.zoom=1;

			GScreen.skills_camera.position.x=GScreen.scr_w/2.0f;
			GScreen.skills_camera.position.y=GScreen.scr_h/2.0f;
			
			GScreen.skills_camera.update();
    		
    		GScreen.show_equip=!GScreen.show_equip;
    		
    		GUI gui=new GUIInventory();
    		
    		if (GScreen.show_equip)
    		{
    			GScreen.show_skills_wheel=false;
    			
    			
    			GScreen.main_control=false;
    			
    			gui.Button_list.add(new ButtonEquip(150,250,-1));
    			gui.Button_list.add(new ButtonEquip(250,250,-2));
    			
    			gui.Button_list.add(new ButtonEquip(350,250,-5));
    			
    			for (int i=0; i<5; i++)
    			{gui.Button_list.add(new ButtonEquip(450+i*100,250,-10-i));}
    			
    			gui.Button_list.add(new ButtonRandomGenerator(150,45,EquipGenerationType.WEAPON));
    			gui.Button_list.add(new ButtonRandomGenerator(250,45,EquipGenerationType.SHIELD));
    			gui.Button_list.add(new ButtonRandomGenerator(350,45,EquipGenerationType.MODULE));
    			
    			gui.Button_list.add(new ButtonChangeQuality(150,310,Rarity.COMMON));
    			gui.Button_list.add(new ButtonChangeQuality(250,310,Rarity.UNCOMMON));
    			gui.Button_list.add(new ButtonChangeQuality(350,310,Rarity.RARE));
    			gui.Button_list.add(new ButtonChangeQuality(450,310,Rarity.ELITE));
    			gui.Button_list.add(new ButtonChangeQuality(550,310,Rarity.LEGENDARY));
    			gui.Button_list.add(new ButtonChangeQuality(650,310,Rarity.ANOMALY));
    			
    			gui.Button_list.add(new ButtonVertical(150,360,null,true,ButtonVerticalFunction.INVENTORY_LEVEL));
    			gui.Button_list.add(new ButtonVertical(150,330,null,false,ButtonVerticalFunction.INVENTORY_LEVEL));
    			
    			//Assets.shoot00.
    			for (int j=0; j<3; j++)
    			for (int i=0; i<10; i++)
    			{
    				//if (GScreen.pl.inventory[i] instanceof Weapon)
    				{gui.Button_list.add(new ButtonEquip(150+i*85,200-j*45,i+j*10));}
    			}
    			
    			gui.Button_list.add(new ButtonEquip(200,250,99));
    			//gui.Button_list.add(new ButtonSkill(30,30,GScreen.pl.Skills_list.get(0),gui));
    			

    			
    			GScreen.GUI_list.add(gui);
    			
    		}
    		else
    		{
    			//GScreen.GUI_list.remove(arg0)
    			GScreen.main_control=true;
    		}
    		
    	}
    	
    	System.out.println(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
    	key=-777;
    	keyF_release=true;
    	
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    
    
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    	MB=false;
    	but=-1;
    	
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
    	/*posx=(int) (screenX+GScreen.camera.position.x-500);
    	posy=(int)(700-screenY-350+GScreen.camera.position.y);
    	MB_timer++;*/

        return false;
    }
    
    public static void update()
    {
    	
    	dx=Gdx.input.getX()-prevx;
    	dy=-(Gdx.input.getY()-prevy);
    	

    	
    	
    	posx=(int) ((Gdx.input.getX()+GScreen.camera.position.x/GScreen.camera.zoom-GScreen.scr_w/2f)*GScreen.camera.zoom);
    	posy=(int)((GScreen.scr_h-Gdx.input.getY()-GScreen.scr_h/2f+GScreen.camera.position.y/GScreen.camera.zoom)*GScreen.camera.zoom);
    	
    	sposx=(int) ((Gdx.input.getX()+GScreen.skills_camera.position.x/GScreen.skills_camera.zoom-GScreen.scr_w/2f)*GScreen.skills_camera.zoom);
    	sposy=(int)((GScreen.scr_h-Gdx.input.getY()-GScreen.scr_h/2+GScreen.skills_camera.position.y/GScreen.skills_camera.zoom)*GScreen.skills_camera.zoom);
    	
    	
    	realx=Gdx.input.getX();
    	realy=GScreen.scr_h-Gdx.input.getY();
    	
    	if ((GScreen.show_skills_wheel)&&(but==0))
    	{
    		GScreen.skills_camera.position.add((-(realx-GScreen.curx))*GScreen.skills_camera.zoom, -(realy-GScreen.cury)*GScreen.skills_camera.zoom, 0);
    		GScreen.skills_camera.update();
    	}
    	
    	
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
    	/*posx=(int) (screenX+GScreen.camera.position.x-500);
    	posy=(int)(700-screenY-350+GScreen.camera.position.y);
    	*/

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
    	scroll_amount=amount;
    	if (((GScreen.main_control)||(GScreen.show_edit))&&(!Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)))
    	{
    		if(amount>0)
    		{
    			GScreen.camera.zoom+=0.05f;
    			GScreen.camera.zoom*=1.05f;
    		}
    		
    		if(amount<0)
    		{
    			
    			GScreen.camera.zoom*=0.95f;
    		}
    		
    		
    	}
    	
    	if (GScreen.show_skills_wheel)
    	{
    		GScreen.skills_camera.zoom+=amount/100.0f;
    		if (GScreen.skills_camera.zoom<0.5f){GScreen.skills_camera.zoom=0.5f;}
    		GScreen.skills_camera.update();
    		//A//ssets.skill_wheel.scale(-amount/50f);
    	}
    	
    	
        return false;
    }

}
