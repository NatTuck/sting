package com.ironbeard.sting;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StingGame extends ApplicationAdapter {
	Music music;
	InputHandler input;
	
	StingView view;
	
	@Override
	public void create () {
		input = new InputHandler(this);
		Gdx.input.setInputProcessor(input);
		
		music = Gdx.audio.newMusic(Gdx.files.internal("ghost-hunter.mp3"));
		music.setLooping(true);
		//music.play();
	
		view = new StingView();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		view.render();
	}

	@Override
	public void resize(int ww, int hh) {
		view.resize(ww, hh);
	}
	
	public void panView(int dx, int dy) {
		view.panView(dx, dy);
	}
	
	public void zoomView(int amount) {
		view.zoomView(amount);
	}
}
