package com.example.clain.miaou;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    // Composant graphique

    ImageView jouer;
    ImageView score;
    ImageView sonOn;
    ImageView sonOff;
    TextView resetScore;
    ImageView i;



    // Données

    // Audio
    private MediaPlayer AudioGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Indique le fichier XML à charger
        setContentView(R.layout.activity_main);

        // récupère les éléments définis dans le fichier XML transmis à setContentView

        jouer = (ImageView) findViewById(R.id.jouer);
        score = (ImageView) findViewById(R.id.score);
        sonOn = (ImageView) findViewById(R.id.sonOn);
        sonOff = (ImageView) findViewById(R.id.sonOff);
        resetScore=(TextView) findViewById(R.id.tv_reset_score);
        i = (ImageView) findViewById(R.id.iv_i);



        // Redirige le clic sur la méthode onClick

        jouer.setOnClickListener(this);
        score.setOnClickListener(this);
        sonOn.setOnClickListener(this);
        sonOff.setOnClickListener(this);
        resetScore.setOnClickListener(this);
        i.setOnClickListener(this);



        // Init son du jeu
        play();


    }


    /**
     * Play and stop AudioGame
     */
    public void stop(){
        if(AudioGame != null){
            AudioGame.release();
            AudioGame = null;
        }
    }

    public void play(){
        stop();
        AudioGame = MediaPlayer.create(this, R.raw.float_space);
        AudioGame.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stop();
                    }
                }
        );
        AudioGame.start();
    }



    @Override
    public void onClick(View v) {

        Animation animationBlink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        Animation animationZoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);

        switch (v.getId()){
            case R.id.jouer:

                System.out.println("Lancement du jeu");
                // animation
                jouer.startAnimation(animationZoom);

                /*Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);*/
                Intent game  = new Intent(MainActivity.this, GameActivity.class);
                startActivity(game);
                finish();
                break;

            case R.id.score:
                System.out.println("Affichage du score");
                Intent scoreView  = new Intent(MainActivity.this,ScoreView.class);
                startActivity(scoreView);
                finish();

                break;


            case R.id.sonOn:
                System.out.println("Appui sonOn");
                sonOn.setVisibility(View.INVISIBLE);
                sonOff.setVisibility(View.VISIBLE);
                stop();




                break;

            case R.id.sonOff:

                System.out.println("Appui SonOff");

                sonOff.setVisibility(View.INVISIBLE);
                sonOn.setVisibility(View.VISIBLE);
                play();
                break;

            case R.id.tv_reset_score:
                System.out.println("Remise à 0 du score");
                resetScore.startAnimation(animationZoom);
                break;

            case R.id.iv_i:
                Intent i= new Intent(MainActivity.this, HowToPlay.class);
                startActivity(i);
                break;



            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop();
    }
} // Fin de la classe MainActiviy
