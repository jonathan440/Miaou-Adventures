package com.example.clain.miaou;

import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Hero implements SensorEventListener {
    private Bitmap bitmap;
    private int x, y;
    private Rect collision;

    // Gestion des capteurs :
    private Sensor mAccelerometer;
    private SensorManager manager;
    private boolean accelSupported;

    // Vitesse perso
    private int vitesse = 3;

    private int screenWidth, screenHeight;

    public Hero(Context context, int screenX,int screenY){

        this.screenWidth = screenX;
        this.screenHeight = screenY;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat);
        x = (screenWidth/2)-screenWidth/10;
        y = screenHeight- bitmap.getHeight()*2;

        // capteur
        manager = (SensorManager) context.getSystemService(Service.SENSOR_SERVICE);
        if (manager != null) {
            mAccelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        collision = new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());
    }

    public  void activerDeplacement(){
        accelSupported = manager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    public void desactiverDeplacement(){
        if(accelSupported){
            manager.unregisterListener(this,mAccelerometer);
        }
    }

    private void  moveHero(int vx , int vy){
        x += vx;
        y += vy;

        if(x < 0){
            x = 0;
        }else if (x + bitmap.getWidth() > screenWidth){
            x = screenWidth - bitmap.getWidth();
        }
        if(y < 0){
            y = 0;
        }

        if(y > screenHeight - (screenHeight/4)){
            y = screenHeight - (screenHeight/4);
        }


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:


                int x = (int) event.values[0];
                int y =(int) event.values[1];

                moveHero(-x*vitesse, y*vitesse);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rect getCollision(){
        return collision;
    }
}
