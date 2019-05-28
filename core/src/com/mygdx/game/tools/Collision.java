package com.mygdx.game.tools;

public class Collision {

    float x,y;
    int width,height;

    public Collision(float x,float y,int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move (float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean collidesWith(Collision coll){
        return x < coll.x+coll.width && y < coll.y +coll.height && x+width>coll.x && y+ height > coll.y;
    }
}
