package com.example.clain.miaou;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnClickListener,
        GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {


    private static final String DEBUG_TAG = "Gestures";
    GestureDetectorCompat mDetector;

    private Handler handler = new Handler();
    private  Timer timer = new Timer();
    private TimerTask timerTask;

    GameView gameView = null;



    private int SCORE = 0;
    private int counter = 0;
    public static boolean DoubleTap = false;

    int highScore = 0;
    SharedPreferences sharedPreferences;

    // Screen Size
    //private int screenWidth, screenHeight;


    // Composant graphique

    ImageView back;
    ImageView pause;
    private boolean state = true;
    ImageView restart;
    TextView score;

    LinearLayout layoutGameOver;
    LinearLayout layoutBarre;
    ImageView g_back;
    ImageView g_map;
    ImageView g_reset;
    TextView g_gameOver;
    TextView g_score;

    public static boolean GetDoubleTap() {
        return DoubleTap;
    }

    public static void setDoubleTap(boolean b) {
        DoubleTap = b;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene);

        mDetector = new GestureDetectorCompat(this,this);

        sharedPreferences = getSharedPreferences("SHAR_PREF_NAME",MODE_PRIVATE);
        highScore = sharedPreferences.getInt("ScoreH",0);


        gameView = (GameView) findViewById(R.id.SurfaceView01);

        back = (ImageView) findViewById(R.id.iv_back);
        pause = (ImageView) findViewById(R.id.iv_pause);
        restart = (ImageView) findViewById(R.id.iv_reset);
        score = (TextView) findViewById(R.id.tv_res);

        layoutGameOver = (LinearLayout) findViewById(R.id.LL_game_over);
        layoutBarre = (LinearLayout) findViewById(R.id.LL_barre);
        g_back = (ImageView) findViewById(R.id.g_back);
        g_reset = (ImageView) findViewById(R.id.g_reset);
        g_gameOver = (TextView) findViewById(R.id.g_text);
        g_score = (TextView) findViewById(R.id.g_score);
        g_map = (ImageView) findViewById(R.id.g_map) ;



        /**
         * Redirige le clic sur la méthode onClick
         */

        back.setOnClickListener(this);
        pause.setOnClickListener(this);
        restart.setOnClickListener(this);
        score.setOnClickListener(this);
        g_back.setOnClickListener(this);
        g_reset.setOnClickListener(this);
        g_map.setOnClickListener(this);


        final Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {


                        SCORE += 1;
                        Log.d("test","Run was call");
                        if(gameView.defaite){
                            counter += 1;
                            layoutGameOver.setVisibility(View.VISIBLE);
                            layoutGameOver.startAnimation(translate);
                            layoutBarre.setVisibility(View.INVISIBLE);
                            timer.cancel();
                            highScore = SCORE;
                            g_score.setText("Votre score: " + highScore);
                            // on stock le score
                            SharedPreferences.Editor e = sharedPreferences.edit();
                            e.putInt("scoreH",highScore);
                            e.apply();




                        }


                        score.setText(""+SCORE);

                    }
                });
            }};
        timer.schedule(timerTask,0,1000);


    }





    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on Resume");
        gameView.demarrer();

    }


    @Override
    protected void onPause() {
        super.onPause();
        gameView.arreter();
        timer.cancel();

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
                finish();
                break;

            case R.id.iv_pause:

                pause.startAnimation(animation);
                System.out.println("Pause");
                if(state){
                    onPause();
                    state = false;
                }
                else{
                    onResume();
                    state = true;
                }
                break;


            case R.id.iv_reset:
                restart.startAnimation(animation);
                System.out.println("reset");
                Intent game  = new Intent(GameActivity.this, GameActivity.class);
                startActivity(game);
                finish();
                break;

            // case defaite

            case R.id.g_back:
                g_back.startAnimation(animation);
                System.out.println("Retour");
                Intent goBack  = new Intent(GameActivity.this, MainActivity.class);
                startActivity(goBack);
                finish();
                break;

            case R.id.g_reset:
                restart.startAnimation(animation);
                System.out.println("reset");
                Intent play  = new Intent(GameActivity.this, GameActivity.class);
                startActivity(play);
                //overridePendingTransition(R.anim.go_up, R.anim.go_down);
                finish();
                break;


            case R.id.g_map:
                System.out.println("Ouverture de la map");
                Intent map = new Intent(GameActivity.this,MapsActivity.class);
                startActivity(map);
                break;

             default:
                 break;

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //Log.d(DEBUG_TAG,"onDoubleTab : " + e.toString());
        Toast.makeText(this,"+10", Toast.LENGTH_SHORT).show();
        DoubleTap = true;
        SCORE  = SCORE + 10;
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


}
