package com.ironbeard.sting;

public class MoreMath {
	public static int floorMod(int x, int y) {
		return ((x % y) + y) % y;
	}
	
	public static int floorMod(float x, float y) {
		return floorMod((int)(x), (int)(y));
	}
}
