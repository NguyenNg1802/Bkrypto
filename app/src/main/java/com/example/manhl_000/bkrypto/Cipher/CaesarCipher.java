package com.example.manhl_000.bkrypto.Cipher;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhl_000.bkrypto.Adapter.WheelTextAdapter;
import com.example.manhl_000.bkrypto.Data.MenuItemData;
import com.example.manhl_000.bkrypto.MainActivity;
import com.example.manhl_000.bkrypto.in_decrypt;
import com.example.manhl_000.bkrypto.R;

import java.util.ArrayList;
import java.util.List;

import github.hellocsl.cursorwheel.CursorWheelLayout;

public class CaesarCipher extends AppCompatActivity implements CursorWheelLayout.OnMenuSelectedListener {

    CursorWheelLayout wheel_text;
    List<MenuItemData> lstText;
    TextView inputView;
    TextView key_text;
    Button encrypt;
    Button decrypt;
    Button back_button;
    char key;
    char input[];
    int diff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_caesar_cipher);
        init();
        clickButton();

    }

    private void clickButton(){

        final MediaPlayer click_sound = MediaPlayer.create(this,R.raw.click);

        encrypt.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                click_sound.start();
                if (inputView.getText().toString().isEmpty()) {
                    Toast msg = Toast.makeText(getBaseContext(), "INPUT TEXT FIRST!", Toast.LENGTH_LONG);
                    msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2 + 350);
                    msg.show();
                } else
                    showResult(Encrypt());
            }
        });

        decrypt.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                click_sound.start();
                if (inputView.getText().toString().isEmpty()) {
                    Toast msg = Toast.makeText(getBaseContext(), "INPUT TEXT FIRST!", Toast.LENGTH_LONG);
                    msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2 + 350);
                    msg.show();
                } else
                    showResult(Decrypt());
            }
        });

    }

    public void showResult(String msg){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // khởi tạo dialog

        View dialogView = getLayoutInflater().inflate(R.layout.result_layout,null);
        TextView result_text = dialogView.findViewById(R.id.result_text);
        result_text.setText(msg);
        alertDialogBuilder.setView(dialogView);


        final AlertDialog alertDialog = alertDialogBuilder.create();

        // tạo dialog
        alertDialog.show();
    }

    public String Decrypt() {
        loadData();
        String res = "";


        for (int j = 0; j < 26; j++) {

            char[] tmp = new char[input.length];
            for (int k = 0;  k < input.length; k++)
                tmp[k] = input[k];
            for (int i = 0; i < tmp.length; i++)
                if (tmp[i] < 65 || tmp[i] > 90)
                    tmp[i] = 32;
                else if (tmp[i] + j > 90)
                    tmp[i] = (char) (tmp[i] + j - 26);
                else
                    tmp[i] = (char) (tmp[i] + j);

            res = res + "#" + (j+1) + " A = " + (char)(65+j) + ": " + String.copyValueOf(tmp) + "\n";
        }

        return res;
    }

    public String Encrypt() {

        loadData();
        for (int i = 0; i < input.length; i++)
            if (input[i] < 65 || input[i] > 91)
                input[i] = 32;
            else if (input[i] + diff <= 'A' + 25)
                input[i] = (char) (input[i] + diff);
            else
                input[i] = (char) (input[i] + diff - 26);
        return String.copyValueOf(input);

    }

    private void init() {
        wheel_text = (CursorWheelLayout)findViewById(R.id.wheel_text);
        encrypt = findViewById(R.id.encypt_button);
        decrypt = findViewById(R.id.decrypt_button);
        inputView = (TextView)findViewById(R.id.textView);
        inputView.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        key_text = findViewById(R.id.key);
        initWheel();
    }

    private void initWheel() {
        lstText = new ArrayList<>();
        for(char i = 65; i<91; i++) {
            lstText.add(new MenuItemData(""+i));
        }
        //lstText.add(new MenuItemData("Z"));
        WheelTextAdapter adapter = new WheelTextAdapter(getBaseContext(),lstText);
        wheel_text.setAdapter(adapter);
        wheel_text.setOnMenuSelectedListener(this);
    }

    private void loadData(){
        input = inputView.getText().toString().toCharArray();
        diff = key - 'A';
    }



    @Override
    public void onItemSelected(CursorWheelLayout parent, View view, int pos) {
        key = lstText.get(pos).mTitle.charAt(0);
        key_text.setText("A = " + key);
    }

    @Override
    public void sound(){
        final MediaPlayer click_sound = MediaPlayer.create(this,R.raw.click);
        click_sound.start();
    }
}