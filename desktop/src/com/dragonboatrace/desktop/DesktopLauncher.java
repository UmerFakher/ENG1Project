
package com.dragonboatrace.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dragonboatrace.DragonBoatRace;
import com.dragonboatrace.tools.Settings;

import java.awt.*;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        //Settings.setResolution(size.width, size.height);
        config.width = 1920;
        config.height = 1080;
        config.fullscreen = true;
        config.resizable = false;
        config.vSyncEnabled = false;
        config.foregroundFPS = 60;
        new LwjglApplication(new DragonBoatRace(), config);
    }
}