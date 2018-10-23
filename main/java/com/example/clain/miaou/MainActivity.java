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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Composant graphique

    ImageView jouer;
    ImageView score;
    ImageView sonOn;
    ImageView sonOff;

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


        // Redirige le clic sur la méthode onClick

        jouer.setOnClickListener(this);
        score.setOnClickListener(this);
        sonOn.setOnClickListener(this);
        sonOff.setOnClickListener(this);

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
                //finish();
                break;

            case R.id.score:
                System.out.println("Affichage du score");
                break;


            case R.id.sonOn:
                System.out.println("Activer son");
                sonOn.startAnimation(animationBlink);
                sonOff.clearAnimation();
                play();
                break;

            case R.id.sonOff:
                System.out.println("Désactiver son");
                /*sonOff.startAnimation(animationBlink);
                sonOn.clearAnimation();*/
                stop();


                //sonOn.setVisibility(View.INVISIBLE);
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
