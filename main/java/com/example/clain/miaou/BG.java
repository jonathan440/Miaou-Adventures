package com.example.clain.miaou;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BG {
    private Bitmap bitmap;
    private Bitmap  StarResized,  StarResized2;
    private int x, y, x2, y2;
    private int speed  = 10;
    private int screenX, screenY;

    public BG(Context context, int screenWidth, int screenHeight){

        this.screenX = screenWidth;
        this.screenY = screenHeight;

        x = 0;
        y = 0;
        x2 = 0;
        y2 = -screenHeight;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.stars);
        StarResized = Bitmap.createScaledBitmap(bitmap, screenWidth, screenHeight, true);
        StarResized2 = Bitmap.createScaledBitmap(bitmap, screenWidth, screenHeight, true);
    }

    public void update(){


        //  Fond 1
        if(y <= screenY){
            y += speed;

        }
        else{
            y = -screenY;
        }

        // Fond 2
        if(y2 <= screenY){
            y2 += speed;
        }
        else{
            y2 = -screenY;
        }
    }

    public Bitmap getBitmap(){
        return StarResized;

    }
    public Bitmap getBitmap2(){
        return StarResized2;

    }

    public int getX(){
        return  x;
    }
    public  int getY(){
        return  y;
    }
    public int getY2(){ return y2; }

    public int getSpeed(){
        return speed;
    }

}
