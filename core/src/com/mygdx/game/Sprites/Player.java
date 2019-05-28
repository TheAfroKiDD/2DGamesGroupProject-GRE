package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Logger;

public class Player extends Sprite{
    public static float wid;
    public static float hei;



    public static float MaxVel = 5f*50.0f;
    public static float JumpVel = 4*50f;
    public static float Damp = 0.87f ;
    public int Health = 5;
    public float stateTime = 0;
    public boolean facesRight = true;
    public boolean grounded =false;
    public State state = State.Still;

   public enum State
    {
        Still,Moving,Jumping
    }

    public final Vector2 pos = new Vector2();
    public final Vector2 vel = new Vector2();
    private static final float Grav = -0.5f * 50.0f;


    private TiledMapTileLayer collisionLayer;

    public Player (Sprite sprite,TiledMapTileLayer collisionLayer){
        super(sprite);
        this.collisionLayer = collisionLayer;
    }


    public void draw(SpriteBatch spriteBatch){
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    public void update(float delta){
        //Gravity change
        vel.y -= (Grav * delta);

        //Clamping velocity
        if(vel.y>MaxVel){vel.y = MaxVel;}
        else if(vel.y<MaxVel){vel.y = -MaxVel;}

        //
        float oldX = getX(), oldY = getY() , tileWid = collisionLayer.getTileWidth(),tileHeig = collisionLayer.getTileHeight();
        boolean  colX =false, colY = false;

        setX(getX() + vel.x * delta);
        setY(getY() + vel.y * delta);

        if(vel.y<0){
            //Down Collisions

            colY = collisionLayer.getCell((int) Math.floor((getX() / tileWid)) , (int) Math.floor((getY() / tileHeig))  ).getTile().getProperties().containsKey("Blocked");

            if(!colY){colY = collisionLayer.getCell((int) Math.floor(((getX() + getWidth() /2) / tileWid)),(int) Math.floor((getY() / tileHeig))).getTile().getProperties().containsKey("Blocked");}
            if(!colY){colY = collisionLayer.getCell((int) Math.floor((((getX() + getWidth()) / tileWid))),(int) Math.floor((getY() / tileHeig))).getTile().getProperties().containsKey("Blocked");}

            if(colY){grounded = true;}
        }
        else if(vel.y>0){
            //Up Collisions
            colY = collisionLayer.getCell((int) Math.floor(((getX() / tileWid))), (int) Math.floor((getY() + getHeight()) / tileHeig)).getTile().getProperties().containsKey("Blocked");
            if(!colY){colY = collisionLayer.getCell((int) Math.floor((((getX() + getWidth() /2) / tileWid))), (int) Math.floor(((getY() + getHeight()) / tileHeig))).getTile().getProperties().containsKey("Blocked");}
            if(!colY){colY = collisionLayer.getCell((int) Math.floor((((getX() + getWidth()) / tileWid))), (int) Math.floor(((getY() + getHeight()) / tileHeig))).getTile().getProperties().containsKey("Blocked");}
        }

        if(colY){setY(oldY); vel.y = 0;}

    }

    public TiledMapTileLayer getColLayer() {
        return collisionLayer;
    }

    public static float getDamp() {
        return Damp;
    }

    public static void setDamp(float damp) {
        Damp = damp;
    }
    public static float getWid() {
        return wid;
    }

    public static void setWid(float wid) {
        Player.wid = wid;
    }

    public static float getHei() {
        return hei;
    }

    public static void setHei(float hei) {
        Player.hei = hei;
    }

    public static float getMaxVel() {
        return MaxVel;
    }

    public static void setMaxVel(float maxVel) {
        MaxVel = maxVel;
    }

    public static float getJumpVel() {
        return JumpVel;
    }

    public static void setJumpVel(float jumpVel) {
        JumpVel = jumpVel;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public boolean isFacesRight() {
        return facesRight;
    }

    public void setFacesRight(boolean facesRight) {
        this.facesRight = facesRight;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Vector2 getPos() {
        return pos;
    }

    public Vector2 getVel() {
        return vel;
    }

    public static float getGrav() {
        return Grav;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

}
