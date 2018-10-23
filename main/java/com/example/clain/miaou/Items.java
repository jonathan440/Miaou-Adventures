package com.example.clain.miaou;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class Items {

    private static final Random random = new Random();
    private Bitmap Meteor;
    private Bitmap Coin;
    private Bitmap Explosion;
    private int ExplosionX, ExplosionY;
    private int  MeteorX, MeteorY;
    private int CoinX, CoinY;
    private int screenX, screenY;
    private Rect collisionM;
    private int score, counter;

    public Items(Context context, int screenWidth, int screenHeight){
        MeteorX =  random.nextInt(screenWidth-(screenWidth/10))+1;
        MeteorY = 0;
        this.screenX = screenWidth;
        this.screenY = screenHeight;


        Meteor = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor);
        Coin = BitmapFactory.decodeResource(context.getResources(), R.drawable.coins);
        Explosion = BitmapFactory.decodeResource(context.getResources(),R.drawable.explosion);


        collisionM = new Rect(MeteorX,MeteorY,Meteor.getWidth(),Meteor.getHeight());
    }

    public void update(){

        counter += 1;
        int  meteorVariation = random.nextInt(25 - 20 +1)+20;
        MeteorY += meteorVariation;
        if (MeteorY > screenY){
            MeteorX = random.nextInt(screenX-(screenX/10))+1;
            MeteorY = 0;
        }

        CoinY += 4;
        if (CoinY > screenY){
            CoinX = random.nextInt(screenX-(screenX/10))+1;
            CoinY = 0;
        }


    }

    public int getScore(){
        if(counter >= 50){
            score += 1;
            counter = 0;
        }

        return score;
    }

    public Bitmap getBitmap(){
        return Meteor;
    }
    public Bitmap getBitmap2(){
        return Coin;
    }
    public  Bitmap getBitmapExplosion(){return Explosion;}

    public int getMeteorX() {
        return MeteorX;
    }

    public int getMeteorY() {
        return MeteorY;
    }

    public int getCoinX() {
        return CoinX;
    }

    public int getCoinY() {
        return CoinY;
    }

    public int getExplosionX(){ return ExplosionX;
    }

    public int getExplosionY(){ return ExplosionY;
    }

    public Rect getCollisionM(){
        return collisionM;
    }
}

