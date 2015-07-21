package com.ironbeard.sting;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {
	int pan;
	int x0;
	int y0;
	StingGame game;

	public InputHandler(StingGame gg) {
		pan = -1;
		game = gg;
	}
	
	@Override
	public boolean touchDown (int x, int y, int pointer, int button) {
		if (button == Input.Buttons.RIGHT) {
			pan = pointer;
		    x0 = x;	
		    y0 = y;
		}
		return true;
	}
	
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (pointer == pan) {
			game.panView(x0 - x, y - y0);
			x0 = x;
			y0 = y;
		}
		return true;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (button == Input.Buttons.RIGHT) {
			pan = -1;
		}
		if (button == Input.Buttons.LEFT) {
			game.gotClick(x, y);
		}
		
		// Do nothing
		return true;
	}
	
	@Override
	public boolean scrolled(int amount) {
		game.zoomView(amount);
		
		return true;
	}
}
