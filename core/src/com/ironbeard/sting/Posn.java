package com.ironbeard.sting;

public class Posn {
	public final int x;
	public final int y;
	
	public Posn(int xx, int yy) {
		x = xx;
		y = yy;
	}

	@Override
	public boolean equals(Object oo) {
		if (oo == null) {
			return false;
		}
		if (getClass() != oo.getClass()) {
			return false;
		}
		
		Posn p1 = (Posn)oo;
		return (x == p1.x && y == p1.y);
	}

	@Override
	public int hashCode() {
		return (x << 16) + x + y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
