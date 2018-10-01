package com.example.clain.miaou;

import android.content.Intent;
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
    ImageView credit;
    ImageView sonOn;
    ImageView sonOff;

    // Données

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Indique le fichier XML à charger
        setContentView(R.layout.activity_main);

        // récupère les éléments définis dans le fichier XML transmis à setContentView

        jouer = (ImageView) findViewById(R.id.jouer);
        score = (ImageView) findViewById(R.id.score);
        credit = (ImageView) findViewById(R.id.credit);
        sonOn = (ImageView) findViewById(R.id.sonOn);
        sonOff = (ImageView) findViewById(R.id.sonOff);


        // Redirige le clic sur la méthode onClick

        jouer.setOnClickListener(this);
        score.setOnClickListener(this);
        credit.setOnClickListener(this);
        sonOn.setOnClickListener(this);
        sonOff.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jouer:
                // animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                jouer.startAnimation(animation);


                System.out.println("Lancement du jeu");
                /*Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);*/
                Intent game  = new Intent(MainActivity.this, GameActivity.class);
                startActivity(game);
                break;

            case R.id.score:
                System.out.println("Affichage du score");
                break;

            case R.id.credit:
                System.out.println("Affichage du credit");
                break;

            case R.id.sonOn:
                System.out.println("Activer son");
                break;

            case R.id.sonOff:
                System.out.println("Désactiver son");
                break;

            default:
                break;
        }
    }
}
