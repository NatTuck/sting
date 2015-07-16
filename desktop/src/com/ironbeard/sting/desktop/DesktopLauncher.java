package com.ironbeard.sting.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ironbeard.sting.StingGame;
import com.ironbeard.sting.StingView;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title =  StingView.windowTitle;
		config.width =  StingView.startWidth;
		config.height = StingView.startHeight;
		config.vSyncEnabled = true;
		new LwjglApplication(new StingGame(), config);
	}
}
