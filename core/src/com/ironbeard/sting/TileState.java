package com.ironbeard.sting;

import java.util.ArrayList;
import java.util.List;

import com.ironbeard.sting.mobs.Mob;

public class TileState {
	private List<Mob> mobs;
	
	public TileState() {
		mobs = new ArrayList<Mob>();
	}
	
	public List<Mob> listMobs() {
		return mobs;
	}
}
