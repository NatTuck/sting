package com.ironbeard.sting;

public class Posn {
	public final int x;
	public final int y;
	
	public Posn(int xx, int yy) {
		x = xx;
		y = yy;
	}
	
	public boolean equals(Posn p1) {
		return (x == p1.x && y == p1.y);
	}
	
	public int hashCode() {
		return (x << 16) + x + y;
	}
}
