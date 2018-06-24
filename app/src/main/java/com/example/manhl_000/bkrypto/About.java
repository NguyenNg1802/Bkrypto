package com.example.manhl_000.bkrypto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class About extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private Button feedback;
    private Intent intent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about);

        textView = (TextView) findViewById(R.id.about_software);
        feedback = (Button) findViewById(R.id.feedback);
        feedback.setOnClickListener(this);
    }

    public void buttonClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.feedback:
                Log.i("Send email", "");
                String[] TO = {"1552253@hcmut.edu.vn"};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Bkrypto feedback");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
