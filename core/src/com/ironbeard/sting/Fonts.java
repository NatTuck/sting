package com.ironbeard.sting;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Fonts {
	private static Map<String, BitmapFont> cache;

	static BitmapFont get(String name, int size) {
		return get(name, size, "Regular");
	}
	
	static BitmapFont get(String name, int size, String var) {
		String key = name + size;
		
		if (cache == null) {
			cache = new HashMap<String, BitmapFont>();
		}
		
		if (!cache.containsKey(key)) {
			cache.put(key, generate(name, size, var));
			if (cache.size() > 100) {
				Gdx.app.log("fonts", "Too many fonts in cache: " + cache.size());
			}
		}
		
		return cache.get(key);
	}
	
	static BitmapFont generate(String name, int size, String var) {
		FreeTypeFontParameter cfg = new FreeTypeFontParameter();
		cfg.size = size;
		cfg.borderWidth = 2;
	
		FileHandle file = Gdx.files.internal("fonts/" + name + "/" + name + "-" + var + ".ttf");
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(file);
		BitmapFont font = gen.generateFont(cfg);
		gen.dispose();
		return font;
	}
}
