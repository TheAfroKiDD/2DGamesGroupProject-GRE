package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.OurGame;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.touchDown;

public class MainMenuScreen implements Screen {
    private final int PbuttHeight = 200;
    private final int PbuttWidth = 400;
    private final int EbuttHeight = 200;
    private final int EbuttWidth = 400;
    private final int EbuttY = 100;
    private final int EbuttX = OurGame.Width /2 - EbuttWidth/2;
    private final int PbuttY = 300;
    private final int PbuttX = OurGame.Width /2 - EbuttWidth/2;

    OurGame game;

    Texture Back,Title, PlayButt, ExitButt;


    public MainMenuScreen(final OurGame game){
        this.game = game;
        Title = new Texture("gfx/Title.png");
        Back = new Texture("gfx/Backgrounds/MenuBackground.png");
        PlayButt = new Texture("gfx/PlayButton.png");
        ExitButt = new Texture("gfx/ExitButton.png");

        final MainMenuScreen mainMenuScreen = this;

        Gdx.input.setInputProcessor((new InputAdapter(){

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer,int button){

              int x = OurGame.Width /2 - PbuttWidth/2 ;
              if(game.cam.getInputInGameWorld().x< x + PbuttWidth  && game.cam.getInputInGameWorld().x>x && OurGame.Height - game.cam.getInputInGameWorld().y < PbuttY + PbuttHeight && OurGame.Height - game.cam.getInputInGameWorld().y> PbuttY ){
                      mainMenuScreen.dispose();
                 game.setScreen(new GameScreen(game));
              }
                x = OurGame.Width /2 - EbuttWidth/2;
                if(game.cam.getInputInGameWorld().x< x + EbuttWidth && game.cam.getInputInGameWorld().x>x && OurGame.Height - game.cam.getInputInGameWorld().y < EbuttY + EbuttHeight && OurGame.Height - game.cam.getInputInGameWorld().y> EbuttY ){
                    mainMenuScreen.dispose();
                    Gdx.app.exit();
                }
                return super.touchUp(screenX,screenY,pointer,button);
            }
        }));
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(Back,0,0);
        game.batch.draw(Title,200,530);
        game.batch.draw(PlayButt,PbuttX,PbuttY);
        game.batch.draw(ExitButt,EbuttX,EbuttY);
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
        ExitButt.dispose();
        Gdx.input.setInputProcessor(null);
    }
}
