package com.example.new_viren_seller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Images extends AppCompatActivity {

    TextView splash;
    Runnable runnable;
    int login;
    public  static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_images);
        splash = findViewById(R.id.splash);

        preferences = getSharedPreferences("myperf",MODE_PRIVATE);
        editor = preferences.edit();
        login = preferences.getInt("login",0);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_in);
        splash.setAnimation(animation);
        Log.d("TTT", "onCreate: "+animation);
        runnable = new Runnable() {
            @Override
            public void run() {

                if(login==0) {
                    Intent intent = new Intent(Splash_Images.this, Main_Page.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(Splash_Images.this, Sing_Up_Page.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable,1000);
    }
}