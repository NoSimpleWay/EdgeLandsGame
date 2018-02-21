package com.midfag.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.midfag.game.Main;


public class DesktopLauncherWithoutScriptSystem {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width=1000;
		config.height=700;
		config.samples=2;
		//config.fullscreen=false;
		//config.foregroundFPS = 90;
		config.vSyncEnabled = false; // Setting to false disables vertical sync
		config.foregroundFPS = 60; // Setting to 0 disables foreground fps throttling
		config.backgroundFPS = 30; // Setting to 0 disables background fps throttling
		
		new LwjglApplication(new Main(false), config);
	}
}
