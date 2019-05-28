package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Pool;

import com.mygdx.game.OurGame;
import com.mygdx.game.Sprites.Coin;
import com.mygdx.game.Sprites.Player;
import com.mygdx.game.Sprites.TumbleWeed;
import com.mygdx.game.Sprites.Whiskey;

public class GameScreen implements Screen {
    private Texture Back,LArrow,RArrow,UArrow,AArrow,TumbleTexture,WhiskeyTexture,CoinTexture;
    private Sprite PlayerSprite;
    private int ButtonWid = 100;
    private int Buttonheg = 100;
    private int LArrowX = 50;
    private int LArrowY = 50;
    private int UArrowX = 160;
    private int UArrowY = 50;
    private int RArrowX = 900;
    private int RArrowY = 50;
    private int AArrowX = 1010;
    private int AArrowY = 50;
    private int Screencount =0;
    private OrthographicCamera cam;
    private OrthogonalTiledMapRenderer rend;
    private TumbleWeed Tumbleweed;
    private Whiskey whiskey;
    private Coin coin;
    private TiledMap map;
    private Player player;
    private Array tiles = new Array();
    private static final float Grav = -2.5f;
    public Animation Moving;
    public Animation Still;
    public Animation Jumping;

    private Pool rectPool = new Pool(){
        @Override
        protected Rectangle newObject(){
            return new Rectangle();
        }

    };
    private boolean firstrend = false;
    public float unitScale = 1/50f;
    private Logger logger = new Logger("Test",Logger.INFO);
    final  OurGame game;
    final GameScreen gameScreen = this;
    public GameScreen(final OurGame game){
        this.game = game;

        Back = new Texture("gfx/Backgrounds/GameBackground.png");
        map = new TmxMapLoader().load("gfx/Tiles/GameLevel.tmx");
        rend = new OrthogonalTiledMapRenderer(map,unitScale);
        cam = new OrthographicCamera();
        cam.setToOrtho(false,24f,16);
        cam.update();
        Texture PlayerTexture = new Texture("gfx/TexturePacks/Player.png");
        TextureRegion[] PlayerFrames = TextureRegion.split(PlayerTexture,48,64)[0];
        Still = new Animation(0,PlayerFrames[6]);
        Moving = new Animation(0.4f,PlayerFrames[4],PlayerFrames[5]);
        Moving.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        Jumping = new Animation(2.5f,PlayerFrames[1],PlayerFrames[3]);

        player = new Player(new Sprite(PlayerTexture),(TiledMapTileLayer) map.getLayers().get(0));
        player.setPosition(1* player.getColLayer().getTileWidth(),7* player.getColLayer().getTileHeight());

       // TumbleTexture = new Texture("");
        //WhiskeyTexture = new Texture("");
        //CoinTexture = new Texture("");
        RArrow = new Texture("gfx/RightArrow.png");
        LArrow = new Texture("gfx/LeftArrow.png");
        UArrow = new Texture("gfx/UpArrow.png");
        AArrow = new Texture("gfx/Shoot.png");


        Gdx.input.setInputProcessor((new InputAdapter(){

            public boolean touchDown(int screenX, int screenY, int pointer, int button){


                if(screenX < LArrowX + ButtonWid && screenX > LArrowX  && OurGame.Height - screenY < LArrowY + Buttonheg && OurGame.Height - screenY > LArrowY ){
                    player.vel.x -= Player.MaxVel;

                    if(player.grounded){player.state = player.state.Moving;}
                    player.facesRight = false;
                }

                //sets center of button Via coordinate
                 if(screenX < RArrowX + ButtonWid && screenX > RArrowX  && OurGame.Height - screenY < RArrowY + Buttonheg && OurGame.Height - screenY > RArrowY ){
                    player.vel.x += Player.MaxVel;
                    if(player.grounded){player.state = player.state.Moving;}
                    player.facesRight = true;
                }

                //sets center of button Via coordinate
                if(screenX < UArrowX + ButtonWid && screenX > UArrowX && OurGame.Height - screenY < UArrowY + Buttonheg && OurGame.Height - screenY > UArrowY ){
                    if(player.grounded){
                        player.vel.y = player.JumpVel ; player.state = player.state.Jumping;
                    }
                }

                //sets center of button Via coordinate
                if(screenX < AArrowX + ButtonWid && screenX > AArrowX  && OurGame.Height - screenY < AArrowY + Buttonheg && OurGame.Height - screenY > AArrowY ){

                }
                return super.touchUp(screenX,screenY, pointer,button);
            }
            public boolean touchUp(int screenX, int screenY, int pointer, int button){
                player.vel.x = 0;
                return false;
            }

        }));


    }


    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f,0.7f,1.0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
            game.batch.draw(Back, 0,0);
             player.draw(game.batch);
        game.batch.end();
        cam.update();
        rend.setView(cam);
        rend.render();

        RunScreen(1);
        if(player.getX()<5){
            player.setX(5.1f);
            if(Screencount>=1){
                player.setX(1100f);
                Screencount--;
            }
        }
        if(player.getX() > 0 && player.getX() <= 1200){cam.position.x = 12.0f; }
        if(player.getX() > 1200){ player.setX(5.1f); Screencount++;}
        if(Screencount == 1){cam.position.x = 36.0f; RunScreen(2); }
        if(Screencount == 2){cam.position.x = 60.0f; RunScreen(3);}
        if(Screencount == 3){gameScreen.dispose();game.setScreen(new WinScreen(game));}






        DrawUI();


        if(player.Health<=0){
            gameScreen.dispose();
            game.setScreen(new GameOverScreen(game));
        }

    }

    private void DrawUI() {
        game.batch.begin();
        game.batch.draw(RArrow,RArrowX,RArrowY);
        game.batch.draw(LArrow,LArrowX,LArrowY);
        game.batch.draw(UArrow,UArrowX,UArrowY);
        game.batch.draw(AArrow,AArrowX,AArrowY);
        game.batch.end();
    }


    private void getTiles(Vector2 start, Vector2 end, Array tiles) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        rectPool.freeAll(tiles);
        tiles.clear();
        for(int y = (int) start.y; y<=end.y; y++){
            for(int x = (int) start.x; x<= end.x; x++){
                TiledMapTileLayer.Cell cell = layer.getCell(x,y);
                if(cell != null){
                    Rectangle rect = (Rectangle) rectPool.obtain();
                    rect.set(x,y,1,1);
                    tiles.add(rect);
                }
            }
        }
    }

    private void RunScreen (int screen){

    }

    private Vector2 tmp = new Vector2();

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

    }
}
