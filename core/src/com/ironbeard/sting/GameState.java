package com.ironbeard.sting;

import java.util.HashMap;
import java.util.Map;

import com.ironbeard.simplex.OpenSimplexNoise;

public class GameState {
	int seed;
	OpenSimplexNoise terrainNoise;
	OpenSimplexNoise encounterNoise;
	
	Map<Posn, TileState> tileStates;
	
	InfoView info;
	
	public GameState(int gameSeed, Map<Posn, TileState> prevStates) {
		seed = gameSeed;
		terrainNoise = new OpenSimplexNoise(3 * seed + seed);
		encounterNoise = new OpenSimplexNoise(5 * seed + seed);

		if (prevStates == null) {
			tileStates = new HashMap<Posn, TileState>();
		}
		else {
			tileStates = prevStates;
		}
		
		info = InfoView.welcomeInfo();
	}
	
	public void initSetup() {
		Map<Posn, TileState> ss = new HashMap<Posn, TileState>();
		TileState t00 = new TileState(terrainAt(0, 0));
		t00.addProp(new Prop("fort"));
		ss.put(new Posn(0, 0), t00);
		tileStates = ss;
		
		if (!ss.get(new Posn(0, 0)).equals(t00)) {
			throw new Error("Huh?");
		}
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
	
	public TileState getTile(int xx, int yy) {
		Posn pp = new Posn(xx, yy);
		
		if (tileStates.containsKey(pp)) {
			return tileStates.get(pp);
		}
		else {
			return new TileState(terrainAt(xx, yy));
		}
	}
	
	public TileState getTile(Posn tp) {
		return getTile(tp.x, tp.y);
	}
	
	public int varAt(int xx, int yy) {
		return (Math.abs(seed) + xx * xx * 5 + yy * yy * 7) % 2;
	}
	
	public String monsterAt(int xx, int yy) {
		return "goblin";
	}
	
	public InfoView getInfo() {
		return info;
	}
	
	public void showInfo(InfoView ifo) {
		info = ifo;
	}
	
	public InfoView tileInfo(Posn tp) {
		TileState tile = getTile(tp);
		InfoView info = new InfoView("Tile: " + tp.toString());
		info.setLines(tile.infoLines());
		return info;
	}
	
	@Override
	public String toString() {
		StringBuilder ss = new StringBuilder();
		ss.append("[GameState]\n");
		ss.append("Seed = " + seed + "\n");
		ss.append("Tiles:\n");
		for (Posn pp : tileStates.keySet()) {
			ss.append(pp.toString() + ": " + tileStates.get(pp).toString() + "\n");
		}
		return ss.toString();		
	}
}
