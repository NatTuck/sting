package com.ironbeard.sting;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Textures {
	static Map<String, Texture> cache;
	
	static Texture get(String group, String name) {
		String key = group + "/" + name;
		
		if (cache == null) {
			cache = new HashMap<String, Texture>();
		}
		
		if (!cache.containsKey(key)) {
			cache.put(key, load(group, name));
		}
		
		return cache.get(key);
	}
	
	static Texture load(String group, String name) {
		String tp = group + "/" + name + ".png";
		return new Texture(Gdx.files.internal(tp));
	}
}
