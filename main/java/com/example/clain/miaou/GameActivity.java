package com.example.clain.miaou;

import android.app.Service;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
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

public class GameActivity extends AppCompatActivity /*implements View.OnClickListener*//*,SensorEventListener*/ {

    // Screen Size
    private int screenWidth, screenHeight;

    private static final Random random = new Random();

    private int Res_score;


    private  GameView gameView;




    /*// Composant graphique

    ImageView back;
    ImageView pause;
    ImageView restart;
    TextView score;*/



    // Gestion des capteurs :
   /* private Sensor mAccelerometer;
    private SensorManager manager;
    private boolean accelSupported;*/




/*



    // Initialize  class
    private Handler handler = new Handler();
    private Timer timer = new Timer();
*/


    // Donnnées


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.scene);

        //Initialisation de gameview
        gameView = new GameView(this);

        //ajout contentview
       setContentView(gameView);

        // capteur
        /*manager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        mAccelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);*/


        /*back = (ImageView) findViewById(R.id.iv_back);
        pause = (ImageView) findViewById(R.id.iv_pause);
        restart = (ImageView) findViewById(R.id.iv_reset);
        score = (TextView) findViewById(R.id.tv_res);*/


        /**
         * Redirige le clic sur la méthode onClick
         */

        /*back.setOnClickListener(this);
        pause.setOnClickListener(this);
        restart.setOnClickListener(this);
        score.setOnClickListener(this);*/


        /**
         *   Get Screen Size
         *
         */

        /*WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;*/



    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on Resume");
        gameView.demarrer();
        /*activerDeplacement();
        GameTimer();*/

    }


    @Override
    protected void onPause() {
        super.onPause();
        //gameView.arreter();

        /*if(accelSupported){
                manager.unregisterListener(this,mAccelerometer);
        }
       */

        }




    /*@Override
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
                //timer.cancel();
                onPause();
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
    }*/


    /*****************************************************
     * Implémentation de l'interface SensorEventListener *
     *****************************************************/


    /*@Override
    public void onSensorChanged(SensorEvent event) {



        switch (event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:


                int x = (int) event.values[0];
                int y =(int) event.values[1];

                moveHero(-x*vitesse, y*vitesse);
        }

    }

    private void  moveHero(int x , int y){
        catX += x;
        catY += y;

        if(catX < 0){
            catX = 0;
        }else if (catX + cat.getWidth() > screenWidth){
            catX = screenWidth - cat.getWidth();
        }
        if(catY < 0){
            catY = 0;
        }

        if(catY > screenHeight - (screenHeight/4)){
            catY = screenHeight - (screenHeight/4);
        }


        cat.setX(catX);
        cat.setY(catY);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }*/
}
