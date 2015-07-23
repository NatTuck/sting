package com.ironbeard.sting;

import java.util.ArrayList;
import java.util.List;

import com.ironbeard.sting.mobs.Mob;

public class TileState {
	private String type;
	private List<Mob> mobs;
	private List<Prop> props;
	
	public TileState(String tileType) {
		type = tileType;
		mobs = new ArrayList<Mob>();
		props = new ArrayList<Prop>();
	}
	
	public List<Mob> listMobs() {
		return mobs;
	}
	
	public List<Prop> listProps() {
		return props;
	}
	
	public void addProp(Prop pp) {
		props.add(pp);
	}
	
	public String toString() {
		return "[Tile] Mobs = " + mobs.size() + ", Props = " + props.size();
	}
	
	public List<String> infoLines() {
		List <String> ss = new ArrayList<String>();
		ss.add("Terrain: " + type);
		ss.add("");
		
		ss.add("Props:");
		for (Prop pp : props) {
			ss.add(pp.toString());
		}
		ss.add("");
		
		ss.add("Mobs:");
		for (Mob mm : mobs) {
			ss.add(mm.toString());
		}
		
		return ss;
	}
}
