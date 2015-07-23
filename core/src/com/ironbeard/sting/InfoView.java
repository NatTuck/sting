package com.ironbeard.sting;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class InfoView {
	String title;
	List<String> lines;

	BitmapFont tFont;
	BitmapFont bFont;
	
	ShapeRenderer shape;
	SpriteBatch batch;
	
	public InfoView(String tt) {
		title = tt;
		lines = new ArrayList<String>();
		shape = new ShapeRenderer();
		batch = new SpriteBatch();
		
		tFont = Fonts.get("Lobster", 24);
		bFont = Fonts.get("Roboto", 18);
	}
	
	public void draw(StingView view, GameState state) {
		int rectW = 300;
		int rectH = view.getHeight();
		int rectX = view.getWidth() - rectW;
		int rectY = 0;
		
		Gdx.gl.glEnable(GL20.GL_BLEND);
		shape.setProjectionMatrix(view.getCam().combined);
		shape.begin(ShapeType.Filled);
		shape.setColor(0.0f, 0.0f, 0.0f, 0.5f);
		shape.rect(rectX, rectY, rectW, rectH);
		shape.end();
		
		int tX = rectX + 20;
		int tY = rectH - 20;
		
		batch.begin();
		tFont.draw(batch, title, tX, tY);
		batch.end();
		
		int bX = rectX + 20;
		int bY = rectH - 60;
		
		batch.begin();
		for (int ii = 0; ii < lines.size(); ++ii) {
			bFont.draw(batch, lines.get(ii), bX, bY - ii * bFont.getLineHeight());
		}
		batch.end();
	}
	
	public void clearLines() {
		lines.clear();
	}
	
	public void addLine(String text) {
		lines.add(text);
	}
	
	public void setLines(List<String> xs) {
		lines = xs;
	}
	
	public static InfoView welcomeInfo() {
		InfoView info = new InfoView("Welcome");
		info.addLine("Welcome to the game.");
		info.addLine("Click a tile for tile info");
		return info;
	}
}
