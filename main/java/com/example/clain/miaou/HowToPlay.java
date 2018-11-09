package com.example.clain.miaou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HowToPlay extends AppCompatActivity {

    // Bouton retour
    private ImageView retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        retour = (ImageView) findViewById(R.id.bt_retour);

        retour.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retour = new Intent(HowToPlay.this, MainActivity.class);
                startActivity(retour);
                //erridePendingTransition(R.anim.go_down, R.anim.go_up);
                finish();
            }
        });
    }
}
