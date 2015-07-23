package com.ironbeard.sting;

public class TerrainView extends GridView {
	public TerrainView() {
		super();
	}
	
	@Override
	public void drawSquare(StingView view, GameState state, int tx, int ty, float ts) {
		String tile = state.terrainAt(tx, ty) + state.varAt(tx, ty);
		batch.draw(Textures.get("tiles", tile), view.tileToScreenX(tx), view.tileToScreenY(ty), ts, ts);
	}
}
