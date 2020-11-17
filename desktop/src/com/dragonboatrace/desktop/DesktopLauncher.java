package com.dragonboatrace.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dragonboatrace.DragonBoatRace;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 1080;
		config.width = 1920;
		config.fullscreen = false;
		config.vSyncEnabled = false;
		config.foregroundFPS = 60;
		new LwjglApplication(new DragonBoatRace(), config);
	}
}
