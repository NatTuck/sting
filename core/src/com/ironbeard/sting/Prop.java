package com.ironbeard.sting;

public class Prop {
	public String name;

	public Prop(String nn) {
		name = nn;
	}
	
	@Override
	public String toString() {
		return "[prop: " + name + "]";
	}
}
