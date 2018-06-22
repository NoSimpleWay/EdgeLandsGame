package com.midfag.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.Monitor;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.midfag.game.Main;


public class DesktopLauncherWithoutScriptSystem {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		//Monitor[] monitors = ;
		config.width=LwjglApplicationConfiguration.getDesktopDisplayMode().width/2;
		config.height=LwjglApplicationConfiguration.getDesktopDisplayMode().height/2;
		
		config.width=1000;
		config.height=700;
		
		//System.gc();
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		config.samples=4;
=======
		config.samples=3;
>>>>>>> parent of 362126d... eclipse project setting
=======
		config.samples=3;
>>>>>>> parent of 362126d... eclipse project setting
=======
		config.samples=3;
>>>>>>> parent of 362126d... eclipse project setting
		config.fullscreen=false;
		//config.fullscreen=false;
		//config.foregroundFPS = 90;
		config.vSyncEnabled = false; // Setting to false disables vertical sync
		config.foregroundFPS = 80; // Setting to 0 disables foreground fps throttling
		config.backgroundFPS = 80; // Setting to 0 disables background fps throttling
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 362126d... eclipse project setting
=======
>>>>>>> parent of 362126d... eclipse project setting
		config.vSyncEnabled=false;
>>>>>>> parent of 362126d... eclipse project setting
		
		new LwjglApplication(new Main(false), config);
	}
}
