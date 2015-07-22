package com.ironbeard.sting;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
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
			
			String tp = "props/fort.png";
			Texture tt = new Texture(Gdx.files.internal(tp));
			tiles.put("castle", tt);
		}
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
		int rows = (int)(height / tileSize() + 1);
		int cols = (int)(width  / tileSize() + 1);
		
		int row0 = (int)(centerY - rows / 2);
		int col0 = (int)(centerX - cols / 2);
	
		batch.begin();
		
		for (int ii = -2; ii < rows + 2; ++ii) {
			for (int jj = -2; jj < cols + 2; ++jj) {
				int ty = row0 + ii;
				int tx = col0 + jj;
				String tile = state.terrainAt(tx, ty) + state.varAt(tx, ty);
				batch.draw(tiles.get(tile), tileToScreenX(tx), tileToScreenY(ty), tileSize(), tileSize());
				
				if (tx == 0 && ty == 0) {
					batch.draw(tiles.get("castle"), tileToScreenX(tx), tileToScreenY(tx), tileSize(), tileSize());
				}
			}
		}
		
		batch.end();
		
		BitmapFont font = Fonts.get("Lobster", 20);
		batch.begin();
		font.draw(batch, "Hello", 100, 100);
		batch.end();
	}
	
	public void render(GameState state) {
		batch.setProjectionMatrix(camera.combined);
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
