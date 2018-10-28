package com.example.clain.miaou;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreView extends AppCompatActivity implements View.OnClickListener {

    // Composant graphique
    TextView score;
    TextView res;
    ImageView back;
    ImageView photo;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_view);
        sharedPreferences = getSharedPreferences("SHAR_PREF_NAME", Context.MODE_PRIVATE);
        score = (TextView) findViewById(R.id.tv_reset_score);
        res = (TextView) findViewById(R.id.tv_res);
        back = (ImageView) findViewById(R.id.iv_back);
        photo = (ImageView) findViewById(R.id.iv_photo);

        back.setOnClickListener(this);
        res.setText("" + sharedPreferences.getInt("scoreH",0));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_back:
                Intent menu  = new Intent(ScoreView.this, MainActivity.class);
                startActivity(menu);
                finish();
                break;

            case R.id.iv_photo:
                System.out.println("Prendre une photo");
                break;

            default:
                break;

        }
    }
}
