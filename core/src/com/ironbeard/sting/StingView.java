package com.ironbeard.sting;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.ironbeard.simplex.OpenSimplexNoise;

public class StingView {
	public static String windowTitle = "Sting Game";
	public static int startWidth  = 1024;
	public static int startHeight = 768;
	public static int baseTileSize = 64;
	
	static Random rand = new Random();
	
	static String[] tileNames = {"bog", "swamp", "grass", "dirt", "ice", "sand", "water"};

	OrthographicCamera camera;
	SpriteBatch batch;

	// Current window size in pixels.
	int width;
	int height;
	
	// Current view center, in tiles.
	float centerX;
	float centerY;
	float zoom;
	
	OpenSimplexNoise noise;
	Map<String, Texture> tiles;
	
	public StingView() {
		width  = startWidth;
		height = startHeight;
		
		centerX = 0.0f;
		centerY = 0.0f;
		zoom = 1.0f;
	
		batch = new SpriteBatch();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 768);
		
		noise = new OpenSimplexNoise(rand.nextInt(1024));
		tiles = new HashMap<String, Texture>();
		
		for (String name : tileNames) {
			for (int ii = 0; ii < 3; ++ii) {
				String tp = "tiles/" + name + ii + ".png";
				Texture tt = new Texture(Gdx.files.internal(tp));
				tiles.put(name + ii, tt);
			}
		}
	}
	
	public String tileAt(int ii, int jj) {
		double nval = 10 * (1.0 + noise.eval(ii / 8.0, jj / 8.0));
		if (nval < 5)  return "water0";
		if (nval < 8)  return "sand0";
		if (nval < 11) return "grass0";
		if (nval < 14) return "dirt0";
		if (nval < 16) return "swamp0";
		return "bog0";
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
	
	public Posn screenToTile(Posn sp) {
		return new Posn((int)screenToTileX(sp.x), (int)screenToTileY(sp.y));
	}

	public float tileToScreenX(float tx) {
		return tileSize() * (tx - baseX());
	}
	
	public float tileToScreenY(float ty) {
		return tileSize() * (ty - baseY());
	}
	
	public Posn tileToScreen(Posn tp) {
		int sx = (int)(Math.round(tileSize() * (tp.x - baseX())));
		int sy = (int)(Math.round(tileSize() * (tp.y - baseY())));
		return new Posn(sx, sy);
	}
	
	public void	draw() {
		int rows = (int)(height / tileSize() + 1);
		int cols = (int)(width  / tileSize() + 1);
		
		int row0 = (int)(centerY - rows / 2);
		int col0 = (int)(centerX - cols / 2);
	
		batch.begin();
		
		for (int ii = -1; ii < rows + 1; ++ii) {
			for (int jj = -1; jj < cols + 1; ++jj) {
				int ty = row0 + ii;
				int tx = col0 + jj;
				//float sy = ii * tileSize() - toY;
				//float sx = jj * tileSize() - toX;
				batch.draw(tiles.get(tileAt(ty, tx)), tileToScreenX(tx), tileToScreenY(ty), tileSize(), tileSize());
			}
		}
		
		batch.end();
	}
	
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		draw();
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
		
		int rows = (int)(height / tileSize() + 1);
		int cols = (int)(width  / tileSize() + 1);
		
		int row0 = (int)(centerY - rows / 2);
		int col0 = (int)(centerX - cols / 2);
	
		//int toX = MoreMath.floorMod(centerX * tileSize(), tileSize());
		//int toY = MoreMath.floorMod(centerY * tileSize(), tileSize());
		int toX = (int)(centerX * tileSize() % tileSize());
		int toY = (int)(centerY * tileSize() % tileSize());
	
		Gdx.app.log("pan", "" + col0 + " " + row0 + " " + toX + " " + toY);
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
