package com.example.clain.miaou;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.Color.WHITE;

public class GameView extends /*View*/ SurfaceView  implements Runnable, SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder = null;
    private BG bg;
    private Hero hero;
    private Items items;
    private boolean ItemToDraw = true;
    private boolean explosion = false, Explosion = false, clearExplosion = false;
    private int nextLevel = 0;
    public  boolean defaite = false;
    private int nextBonus = 0;
    private int lastUpdate = 0;



    //objet de dessin
    private Paint paint;
    private Canvas canvas;


    private volatile Thread gameThread = null;
    int fps = 60;


    // Taille de l'ecran
    private int screenWidth, screenHeight;



    /**
     * Constructeur
     */

    public GameView(Context context){
        super(context);
    }


     public GameView(Context context, AttributeSet attrs){
        super(context, attrs);
        getHolder().addCallback(this);
        this.setFocusable(true);


        if(surfaceHolder == null){
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
        }

        if(paint == null){
            paint = new Paint();
        }



        /**
         *   Get Screen Size
         *
         */
        // Permet de recuperer la taille de l'ecran
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();

        // Load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);

        // Taille de l'ecran
        screenWidth = size.x;
        screenHeight = size.y;


        /**
         * Composant graphique
         */

        bg = new BG(context, screenWidth, screenHeight);
        hero = new Hero(context, screenWidth, screenHeight);
        items = new Items(context,screenWidth,screenHeight);
        //meteor = new Items(context,screenWidth,screenHeight);


    }

    public void demarrer(){
        if(gameThread == null){
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void arreter(){
        if (gameThread != null){
            Thread Th = gameThread;
            gameThread = null;
            Th.interrupt();
        }
    }






    @Override
    public void run() {

        Thread ThreadRun = Thread.currentThread();

        // déclaration des temps de départ et de pause
        long startTime = System.currentTimeMillis();
        long sleepTime = 17; // 17 millisecondes

        while (gameThread == ThreadRun && !ThreadRun.isInterrupted()){

            sleepTime = 100/fps - (System.currentTimeMillis() - startTime);

            // pause
            if(sleepTime > 0){
                try {
                    Thread.sleep(17);
                } catch (InterruptedException e) {}
            }
            startTime = System.currentTimeMillis();

            update();
            draw();



        }

    }

    private void update(){

        bg.update();
        hero.activerDeplacement();
        items.updateMeteor();
        Bonus();

        // si double tap on supprime le bonus de l'ecran
        if(GameActivity.DoubleTap){
            items.initCoinsPos();
        }
        else items.update();

        //Détection des collisions
        collision();



    }

    private void collision(){

        if (items.getMeteorY() >=   hero.getY() && items.getMeteorX() >=  hero.getX() &&  items.getMeteorX() <= hero.getX()+hero.getBitmap().getWidth()) {
            actionIfCollision();
        }

    }





    private void actionIfCollision(){
        System.out.println("Collision !");

        // element à dessiner lorsque le jeu tourne
        ItemToDraw = false;
        explosion = true;
        Explosion = true;
        defaite = true;

        hero.desactiverDeplacement();

    }


    private void draw(){

        if(surfaceHolder.getSurface().isValid()){

            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.WHITE);

            canvas.drawBitmap(bg.getBitmap(),bg.getX(),bg.getY(),paint);
            canvas.drawBitmap(bg.getBitmap2(),bg.getX(),bg.getY2(),paint);

            if(ItemToDraw){
                canvas.drawBitmap(hero.getBitmap(),hero.getX(), hero.getY(), paint);
                canvas.drawBitmap(items.getBitmap(),items.getMeteorX(),items.getMeteorY(),paint);
                nextLevel += 1;


                if(!GameActivity.GetDoubleTap()){
                    canvas.drawBitmap(items.getBitmap2(),items.getCoinX(),items.getCoinY(),paint);
                }




            }




            if(Explosion){
                int x = hero.getX();
                int y = hero.getY();
                canvas.drawBitmap(items.getBitmapExplosion(),x,y,paint);
                Explosion = false;
                clearExplosion = true;
                arreter();



            }




            surfaceHolder.unlockCanvasAndPost(canvas);
        }
        else System.out.println("IS NOT VALIDE");
    }

    public void Bonus(){

        nextBonus +=1;
        if(nextBonus >= 500){
            GameActivity.setDoubleTap(false);
            nextBonus = 0;
        }



    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // sout
        System.out.println("Oui !!!!!!!!!!");
        //demarrer();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
