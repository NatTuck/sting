package com.ironbeard.sting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class StingView {
	public static String windowTitle = "Sting Game";
	public static int startWidth  = 1024;
	public static int startHeight = 768;
	public static int baseTileSize = 64;
	
	OrthographicCamera camera;

	// Current window size in pixels.
	int width;
	int height;
	
	// Current view center, in tiles.
	float centerX;
	float centerY;
	float zoom;
	
	public StingView() {
		width  = startWidth;
		height = startHeight;
		
		centerX = 0.0f;
		centerY = 0.0f;
		zoom = 1.0f;
	
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 768);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public float getCenterX() {
		return centerX;
	}
	
	public float getCenterY() {
		return centerY;
	}
	
	public Camera getCam() {
		return camera;
	}
	
	public float tileSize() {
		return baseTileSize * zoom;
	}
	
	public float baseX() {
		return centerX - width / (2.0f * tileSize());
	}
	
	public float baseY() {
		return centerY - height / (2.0f * tileSize());
	}
	
	public float screenToTileX(float sx) {
		return sx / tileSize() + baseX();
	}
	
	public float screenToTileY(float sy) {
		return sy / tileSize() + baseY();
	}
	
	public float tileToScreenX(float tx) {
		return tileSize() * (tx - baseX());
	}
	
	public float tileToScreenY(float ty) {
		return tileSize() * (ty - baseY());
	}
	
	public Posn mouseToTile(int mx, int my) {
		Vector3 mv = new Vector3(mx, my, 0);
		Vector3 sv = camera.unproject(mv, 0.0f, 0.0f, width, height);
		int tx = (int)Math.floor(screenToTileX(sv.x));
		int ty = (int)Math.floor(screenToTileY(sv.y));
		return new Posn(tx, ty);
	}
	
	public void	draw(GameState state) {
		TerrainView terrain = new TerrainView();
		terrain.draw(this, state);
	
		PropsView props = new PropsView();
		props.draw(this, state);
	
		InfoView info = state.getInfo();
		info.draw(this, state);
	}
	
	public void render(GameState state) {
		draw(state);
	}
	
	public void resize(int ww, int hh) {
		width = ww;
		height = hh;
		camera.setToOrtho(false, ww, hh);
	}
	
	public void setCenter(int x, int y) {
		centerX = x;
		centerY = y;
	}
	
	public void panView(int dx, int dy) {
		centerX += (float)(dx) / tileSize();
		centerY += (float)(dy) / tileSize();
	}
	
	public void zoomView(int amount) {
		if (amount < 0 && zoom < 10) {
			// Zoom in
			zoom *= 1.6;
		}
		if (amount > 0 && zoom > 0.1) {
			// Zoom out
			zoom *= 0.625;
		}
	}
}
