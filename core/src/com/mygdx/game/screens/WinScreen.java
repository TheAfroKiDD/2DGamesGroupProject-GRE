package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.OurGame;

public class WinScreen implements Screen {

    private Texture Title, PlayButt, MenuButt, Back;
    private int ButtonWid = 200;
    private int ButtonHei = 100;
    private int MenuX = 100;
    private int MenuY = 50;
    private int PlayX = 900;
    private int PlayY = 50;

    OurGame game;

    final WinScreen WinScreen = this;


    public WinScreen(final OurGame game) {
        this.game = game;
        Title = new Texture("gfx/Win/Win.png");
        PlayButt = new Texture("gfx/Win/PlayButt.png");
        MenuButt = new Texture("gfx/Win/MenuButt.png");
        Back = new Texture("gfx/Win/Back.png");


        Gdx.input.setInputProcessor(new InputAdapter() {

            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                if (screenX < PlayX + ButtonWid && screenX > PlayX && OurGame.Height - screenY < PlayY + ButtonHei && OurGame.Height - screenY > PlayY) {
                    WinScreen.dispose();
                    game.setScreen(new GameScreen(game));
                }
                if (screenX < MenuX + ButtonWid && screenX > MenuX && OurGame.Height - screenY < MenuY + ButtonHei && OurGame.Height - screenY > MenuY) {
                    WinScreen.dispose();
                    game.setScreen(new MainMenuScreen(game));
                }

                return super.touchUp(screenX, screenY, pointer, button);
            }

        });
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(Back, 0, 0);
        game.batch.draw(Title, 200, 530);
        game.batch.draw(PlayButt, PlayX, PlayY);
        game.batch.draw(MenuButt, MenuX, MenuY);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Back.dispose();
        Title.dispose();
        PlayButt.dispose();
        MenuButt.dispose();
        Gdx.input.setInputProcessor(null);
    }
}
