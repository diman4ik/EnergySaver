package com.denis.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DenisGame extends Game {
	public SpriteBatch batcher;

	@Override
	public void create () {
		batcher = new SpriteBatch();
		Settings.load();
		Assets.load();
		Assets.roomMusic.setLooping(true);
		Assets.roomMusic.setVolume(0.4f);
		Assets.roomMusic.play();

		setScreen(new StartScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
