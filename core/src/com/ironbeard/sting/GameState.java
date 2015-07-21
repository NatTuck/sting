package com.ironbeard.sting;

import java.util.Map;

import com.ironbeard.simplex.OpenSimplexNoise;

public class GameState {
	// Start with a fixed size, 1024 * 1024 world.
	
	// World Map autogen
	// - Automatically generated from several sets of simplex noise.
	// - Tile Types
	// - Wandering Monster Types
	// - Events / Resources
	int seed;
	OpenSimplexNoise terrainNoise;
	OpenSimplexNoise encounterNoise;
	
	Map<Posn, TileState> tileStates;
	// Mobs (people and monsters)
	// Buildings (objects that don't move)
	
	public GameState(int gameSeed, Map<Posn, TileState> prevStates) {
		seed = gameSeed;
		terrainNoise = new OpenSimplexNoise(3 * seed + seed);
		encounterNoise = new OpenSimplexNoise(5 * seed + seed);
	}
	
	public String terrainAt(int xx, int yy) {
		double nval = 10 * (1.0 + terrainNoise.eval(xx / 16.0, yy / 16.0));
		if (nval < 5)  return "water";
		if (nval < 8)  return "sand";
		if (nval < 11) return "grass";
		if (nval < 14) return "dirt";
		if (nval < 16) return "swamp";
		return "bog";
	}
	
	public int varAt(int xx, int yy) {
		return (Math.abs(seed) + xx * xx * 5 + yy * yy * 7) % 2;
	}
	
	public String monsterAt(int xx, int yy) {
		return "goblin";
	}
}
