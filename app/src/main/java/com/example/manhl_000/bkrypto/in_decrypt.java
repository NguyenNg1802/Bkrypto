package com.example.manhl_000.bkrypto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.manhl_000.bkrypto.Cipher.CaesarCipher;
import com.example.manhl_000.bkrypto.Cipher.PlayfairActivity;
import com.example.manhl_000.bkrypto.Cipher.VigenereCipher;

public class in_decrypt extends AppCompatActivity implements View.OnClickListener {
    Button caesar_button;
    Button playfair_button;
    Button vigenere_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_in_decrypt);
        caesar_button = (Button) findViewById(R.id.caesar);
        playfair_button = (Button) findViewById(R.id.playfair);
        vigenere_button = (Button) findViewById(R.id.vigenere);
        caesar_button.setOnClickListener(this);
        playfair_button.setOnClickListener(this);
        vigenere_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.caesar:
                intent = new Intent(this, CaesarCipher.class);
                startActivity(intent);
                break;
            case R.id.playfair:
                Intent i = new Intent(this, PlayfairActivity.class);
                i.putExtra("Alphabets", "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
                i.putExtra("col", (6));
                i.putExtra("row", (6));
                i.putExtra("Size", (36));
                i.putExtra("fLetter", 'X');
                startActivity(i);
                break;
            case R.id.vigenere:
                intent = new Intent(this, VigenereCipher.class);
                startActivity(intent);
                break;
        }
    }
    public void back_button(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
