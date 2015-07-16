package com.ironbeard.sting;

import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {
	int x0;
	int y0;
	StingGame game;

	public InputHandler(StingGame gg) {
		game = gg;
	}
	
	@Override
	public boolean touchDown (int x, int y, int pointer, int button) {
		x0 = x;
		y0 = y;
		return true;
	}
	
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		game.panView(x0 - x, y - y0);
		x0 = x;
		y0 = y;
		return true;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// Do nothing
		return true;
	}
	
	@Override
	public boolean scrolled(int amount) {
		game.zoomView(amount);
		return true;
	}
}
