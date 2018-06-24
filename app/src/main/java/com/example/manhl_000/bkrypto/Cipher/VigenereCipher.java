package com.example.manhl_000.bkrypto.Cipher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.manhl_000.bkrypto.R;
import com.example.manhl_000.bkrypto.in_decrypt;

public class VigenereCipher extends Activity {
    private EditText mCypherText;
    private Button mDecrypt;
    private TextView mDecryptedText;
    private Button mEncrypt;
    private TextView mEncryptedText;
    private EditText mKeyText;
    private EditText mKeyText2;
    private EditText mPlainText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vigenere);
        this.mPlainText = (EditText) findViewById(R.id.plaintext);
        this.mKeyText = (EditText) findViewById(R.id.keytext);
        this.mEncrypt = (Button) findViewById(R.id.encrypt);

        this.mEncryptedText = (TextView) findViewById(R.id.encryptedText);
        this.mCypherText = (EditText) findViewById(R.id.ciphertext);
        this.mKeyText2 = (EditText) findViewById(R.id.keytext2);
        this.mDecrypt = (Button) findViewById(R.id.decrypt);
        this.mDecryptedText = (TextView) findViewById(R.id.decryptedText);
        registerButtonEncryption();
        registerButtonDecryption();

        TextView tv = (TextView) findViewById(R.id.textView1);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "harryp.TTF");
        tv.setTypeface(face);
        TextView tv1 = (TextView) findViewById(R.id.textView2);
        Typeface face1 = Typeface.createFromAsset(getAssets(),
                "harryp.TTF");
        tv1.setTypeface(face1);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    private void registerButtonEncryption() {
        this.mEncrypt.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String pln = VigenereCipher.this.mPlainText.getText().toString();
                String key = VigenereCipher.this.mKeyText.getText().toString();
                VigenereCipher.this.checkEditTextEncrypt();
                VigenereCipher.this.encryptText(pln, key);
            }
        });
    }

    private void checkEditTextEncrypt() {
        if (this.mPlainText.getText().toString().equals("")) {
            this.mPlainText.setError("BLANK INPUT PLAINTEXT");
        }
        if (this.mKeyText.getText().toString().equals("")) {
            this.mKeyText.setError("BLANK INPUT KEY");
        }
    }

    private void encryptText(String pln, String key) {
        if (this.mPlainText.getText().toString().equals("") || this.mKeyText.getText().toString().equals("")) {
            checkEditTextEncrypt();
            return;
        }
        String res = "";
        pln = pln.toUpperCase();
        key = key.toUpperCase();
        int j = 0;
        for (int i = 0; i < pln.length(); i++) {
            char c = pln.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                res = new StringBuilder(String.valueOf(res)).append((char) (((key.charAt(j) + c) % 26) + 65)).toString();
                j = (j + 1) % key.length();
            }
        }
        this.mEncryptedText.setText(" " + res);
    }



    private void registerButtonDecryption() {
        this.mDecrypt.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String cyp = VigenereCipher.this.mCypherText.getText().toString();
                String key2 = VigenereCipher.this.mKeyText2.getText().toString();
                VigenereCipher.this.checkEditTextDecrypt();
                VigenereCipher.this.decryptText(cyp, key2);
            }
        });
    }

    private void checkEditTextDecrypt() {
        if (this.mCypherText.getText().toString().equals("")) {
            this.mCypherText.setError("BLANK INPUT PLAINTEXT");
        }
        if (this.mKeyText2.getText().toString().equals("")) {
            this.mKeyText2.setError("BLANK INPUT KEY");
        }
    }

    private void decryptText(String cyp, String key2) {
        if (this.mCypherText.getText().toString().equals("") || this.mKeyText2.getText().toString().equals("")) {
            checkEditTextDecrypt();
            return;
        }
        String res2 = "";
        cyp = cyp.toUpperCase();
        key2 = key2.toUpperCase();
        int j = 0;
        for (int i = 0; i < cyp.length(); i++) {
            char c = cyp.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                res2 = new StringBuilder(String.valueOf(res2)).append((char) ((((c - key2.charAt(j)) + 26) % 26) + 65)).toString();
                j = (j + 1) % key2.length();
            }
        }
        this.mDecryptedText.setText(" " + res2);
    }
}