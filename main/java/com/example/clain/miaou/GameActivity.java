package com.example.clain.miaou;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private static final Random random = new Random();

    private int Res_score;
    private int nextScore;

    // Screen Size
    private int screenWidth, screenHeight;


    // Composant graphique

    ImageView back;
    ImageView pause;
    ImageView restart;
    TextView score;

    private ImageView meteor, cat, coin;

    // Position
    private  float meteorX;
    private  float meteorY;

    private float coinX;
    private float coinY;

    private float catX;
    private  float catY;

    // Initialize  class
    private Handler handler = new Handler();
    private Timer timer = new Timer();



    // Donnnées


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene);


        back = (ImageView) findViewById(R.id.iv_back);
        pause = (ImageView) findViewById(R.id.iv_pause);
        restart = (ImageView) findViewById(R.id.iv_reset);
        score = (TextView) findViewById(R.id.tv_res);

        meteor = (ImageView) findViewById(R.id.iv_meteor);
        cat = (ImageView) findViewById(R.id.iv_cat);
        coin =(ImageView) findViewById(R.id.iv_coin);

        // Redirige le clic sur la méthode onClick

        back.setOnClickListener(this);
        pause.setOnClickListener(this);
        restart.setOnClickListener(this);
        score.setOnClickListener(this);


        // Get Screen Size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        /**
         *   init position
         */



        //Meteor
        meteorX = random.nextInt(screenWidth)+1;
        meteor.setX(meteorX);
        meteor.setY(200);

        //Cat
        cat.setX((screenWidth/2)-50);
        cat.setY(0);

        // Coins
        coinX = random.nextInt(screenWidth)+1;
        coin.setX(coinX);
        coin.setY(0);

        //debug
        System.out.println("ScreenHeight :  " + screenHeight);



        /**
         * Start the timer
         */

       GameTimer();





    }

    public void GameTimer(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {


                        //System.out.println("Appelle changePos");
                        changePos();

                        nextScore += 1;

                        if(nextScore >= 50){
                            Res_score += 1;
                            score.setText("" + Res_score);
                            nextScore = 0;
                        }

                    }
                });
            }
        }, 0, 10);
    }

    public void changePos(){
        //System.out.println("Update Position");
        meteorY += 2;
        coinY += 1;


        if (meteor.getY() > screenHeight){
            meteorX = random.nextInt(screenWidth)+1;
            meteorY = 200;


        }
        meteor.setX(meteorX);
        meteor.setY(meteorY);

        if (coin.getY() > screenHeight){
            coinX = random.nextInt(screenWidth)+1;
            coinY = 100;


        }
        coin.setX(coinX);
        coin.setY(coinY);
    }






    @Override
    public void onClick(View v) {

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);

        switch (v.getId()){
            case R.id.iv_back:

                back.startAnimation(animation);
                System.out.println("Retour");
                Intent mainMenu  = new Intent(GameActivity.this, MainActivity.class);

                startActivity(mainMenu);
                break;

            case R.id.iv_pause:
                pause.startAnimation(animation);
                System.out.println("Pause");
                timer.cancel();

                break;


            case R.id.iv_reset:
                restart.startAnimation(animation);
                System.out.println("reset");
                Intent game  = new Intent(GameActivity.this, GameActivity.class);
                startActivity(game);
                break;


             default:
                 break;

        }
    }



}
