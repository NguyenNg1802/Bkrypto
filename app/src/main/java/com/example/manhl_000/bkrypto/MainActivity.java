package com.example.manhl_000.bkrypto;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button music;
    MediaPlayer music_bg;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        music_bg = MediaPlayer.create(this, R.raw.bg_music);
        music_bg.start();
        music_bg.setLooping(true);

        setContentView(R.layout.activity_main);
        music = findViewById(R.id.music_button);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music_button();
            }
        });
    }

    public void inDecrypt(View view) {
        final MediaPlayer click_sound = MediaPlayer.create(this,R.raw.click);
        click_sound.start();
        Intent intent = new Intent(this, in_decrypt.class);
        startActivity(intent);
    }

    public void in_solve_puzzle(View view) {
        final MediaPlayer click_sound = MediaPlayer.create(this,R.raw.click);
        click_sound.start();
        Intent intent = new Intent(this, solve_puzzle.class);
        startActivity(intent);
    }

    public void in_about(View view) {
        final MediaPlayer click_sound = MediaPlayer.create(this,R.raw.click);
        click_sound.start();
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void in_tutorial(View view) {
        final MediaPlayer click_sound = MediaPlayer.create(this,R.raw.click);
        click_sound.start();
        Intent intent = new Intent(this, in_tutorial.class);
        startActivity(intent);
    }

    public void music_button(){

        if(flag){
            doPause();
        }
        else
            doStart();

    }

    public void doStart()  {
        music.setBackgroundResource(R.drawable.speaker_on);

        music_bg.start();
        flag = true;

    }
    public void doPause()  {
        music.setBackgroundResource(R.drawable.speaker_off);
        music_bg.pause();
        flag = false;
    }
}
