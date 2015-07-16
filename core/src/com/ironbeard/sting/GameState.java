package com.ironbeard.sting;

import java.util.Map;

import com.ironbeard.simplex.OpenSimplexNoise;

public class GameState {
	// Start with a fixed size, 1024 * 1024 world.
	// 
	
	
	
	
	// World Map autogen
	// - Automatically generated from several sets of simplex noise.
	// - Tile Types
	// - Wandering Monster Types
	// - Events / Resources
	// - 
	OpenSimplexNoise terrainNoise;
	OpenSimplexNoise encounterNoise;
	
	
	Map<Posn, TileState> encounterStatus;
	// Mobs (people and monsters)
	// Buildings (objects that don't move)
}
