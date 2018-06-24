package com.example.manhl_000.bkrypto;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.manhl_000.bkrypto.Tutorials.*;

public class in_tutorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_in_tutorial);
    }

    public void tut_Caesar(View view) {
        final MediaPlayer click_sound = MediaPlayer.create(this,R.raw.click);
        click_sound.start();
        Intent intent = new Intent(this, Tutorial.class);
        intent.putExtra("cipher_name", "Caesar");
        startActivity(intent);
    }
    public void tut_Playfair(View view) {
        final MediaPlayer click_sound = MediaPlayer.create(this,R.raw.click);
        click_sound.start();
        Intent intent = new Intent(this, Tutorial.class);
        intent.putExtra("cipher_name", "Playfair");
        startActivity(intent);
    }
    public void tut_Virgenere(View view) {
        final MediaPlayer click_sound = MediaPlayer.create(this,R.raw.click);
        click_sound.start();
        Intent intent = new Intent(this, Tutorial.class);
        intent.putExtra("cipher_name", "Virgenere");
        startActivity(intent);
    }
    public void back_button(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
