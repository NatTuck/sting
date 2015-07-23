package com.ironbeard.sting;

import com.badlogic.gdx.Gdx;

public class PropsView extends GridView {
	public PropsView() {
		super();
	}
	
	@Override
	public void drawSquare(StingView view, GameState state, int tx, int ty, float ts) {
		TileState tile = state.getTile(tx, ty);
	
		for (Prop prop : tile.listProps()) {
			batch.draw(Textures.get("props", prop.name), view.tileToScreenX(tx), view.tileToScreenY(tx), ts, ts);
		}
	}
}
