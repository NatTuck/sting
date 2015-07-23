package com.ironbeard.sting;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GridView {
	public abstract void drawSquare(StingView view, GameState state, int tx, int ty, float ts);
	
	protected SpriteBatch batch;
	
	public GridView() {
		batch = new SpriteBatch();
	}
	
	public void draw(StingView view, GameState state) {
		int ww = view.getWidth();
		int hh = view.getHeight();
		float ts = view.tileSize();
		
		int rows = (int)(hh / ts + 1);
		int cols = (int)(ww  / ts + 1);
		
		int row0 = (int)(view.getCenterY() - rows / 2);
		int col0 = (int)(view.getCenterX() - cols / 2);
	
		batch.setProjectionMatrix(view.getCam().combined);
		batch.begin();
		
		for (int ii = -2; ii < rows + 2; ++ii) {
			for (int jj = -2; jj < cols + 2; ++jj) {
				int ty = row0 + ii;
				int tx = col0 + jj;
				drawSquare(view, state, tx, ty, ts);
			}
		}	
		
		batch.end();
	}
}
