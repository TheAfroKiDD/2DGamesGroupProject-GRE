package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.tools.GameCamera;


public class OurGame extends Game {
	public	SpriteBatch batch;
	public GameCamera cam;

	public static int Height = 800;
	public static int Width = 1200;

	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new GameCamera(1200,800);

		this.setScreen(new MainMenuScreen(this));
}
	@Override
	public void render () {
		batch.setProjectionMatrix(cam.combined());
		super.render();
	}
	@Override
	public void resize(int width, int height){
		cam.update(width,height);
		super.resize(width,height);
	}
}
