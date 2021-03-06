package com.example.manhl_000.bkrypto.Cipher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.graphics.Typeface;

import com.example.manhl_000.bkrypto.Cipher.PlyfairCipher;
import com.example.manhl_000.bkrypto.R;
import com.example.manhl_000.bkrypto.in_decrypt;


public class PlayfairActivity extends AppCompatActivity {
    int [] fflArray;
    int [] spaceArray;
    boolean oddflag=false;
    String encrypted, decrypted;

    public void back_button(View view) {
        Intent intent = new Intent(this, in_decrypt.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_playfair);


        final String EnglishAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        final Button decryptButton = (Button) findViewById(R.id.decryptBTN);
        final Button encryptButton = (Button) findViewById(R.id.EncryptBTN);

       // final TextView tvPlain = (TextView) findViewById(R.id.tvPlaintext);
       // final TextView tvKey = (TextView) findViewById(R.id.tvmatrixSize);

        final TextView tvCipherText = (TextView) findViewById(R.id.tvCipherText);
        final TextView tvKeyText = (TextView) findViewById(R.id.tvKeyPlain);
        final TextView tvMatrixText = (TextView) findViewById(R.id.tvMatrix);
        final TextView tvResult = (TextView) findViewById(R.id.result);

        final EditText etPlainText = (EditText) findViewById(R.id.etPlaintext);
        final EditText etkey = (EditText) findViewById(R.id.etKey);

        final ToggleButton matrixToggle = (ToggleButton) findViewById(R.id.MtarixTogBTN);
        encrypted = "Encrypted";
        decrypted = "Decrypted";


        final int row = getIntent().getIntExtra("row", 0);
        final int col = getIntent().getIntExtra("col", 0);
        final int matrixSize = getIntent().getIntExtra("Size", 0);
        final String Alphabet = getIntent().getStringExtra("Alphabets");
        final char fillerLetter = getIntent().getCharExtra("fLetter", '@');


        if (Alphabet.equalsIgnoreCase(EnglishAlphabets)) {
            etPlainText.setHint("Plaintext English Letters; Numbers Only");
            etkey.setHint("Key English Letters; Numbers Only");

            decryptButton.setText("Decrypt");
            encryptButton.setText("Encrypt");
            tvResult.setText("Result");

            matrixToggle.setText("Show");
            matrixToggle.setTextOff("Show");
            matrixToggle.setTextOn("Hide");
        }



        //
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);


                matrixToggle.setChecked(false);
                String Validator = etPlainText.getText().toString().toUpperCase()+etkey.getText().toString().toUpperCase();
                Validator = Validator.replaceAll(" ", "");

                boolean isValid = true;
                for (int i = 0; i < Validator.length(); i++) {
                    String validLetter = Character.toString(Validator.charAt(i));
                    if (Alphabet.indexOf(validLetter) == -1) {
                        isValid = false;
                        break;

                    }

                }

                if (isValid == false) {

                    Toast.makeText(getApplicationContext(), "Invalid Letters", Toast.LENGTH_SHORT).show();
                    tvCipherText.setText("");
                    tvKeyText.setText("");
                    tvMatrixText.setText("");
                    matrixToggle.setChecked(false);


                } else if (etPlainText.length() == 0) {

                    Toast.makeText(getApplicationContext(), "Plaintext is missing", Toast.LENGTH_SHORT).show();

                } else if (etkey.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Key is missing", Toast.LENGTH_SHORT).show();


                } else {

                    tvResult.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    tvResult.setText(encrypted);


                    String key = etkey.getText().toString();
                    String text = etPlainText.getText().toString();

                    final PlyfairCipher Encryption = new PlyfairCipher(Alphabet, col, row, fillerLetter, text, key);

                    String cipherText = Encryption.encrypt(Encryption.getMatrix(), Encryption.getPlainText());
                    tvCipherText.setTextColor(getResources().getColor(R.color.colorAccent));
                    tvCipherText.setText(cipherText);
                    final String cipher = cipherText;
                    //etPlainText.setText(cipher);
                    fflArray=Encryption.getFllArray();
                    spaceArray=Encryption.getSpaceArray();
                    oddflag=Encryption.isOddFlag();
                    matrixToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                // The toggle is enabled
                                String RowNo = "R";
                                String message = "  Plaintext: ";
                                String key = "  Final Key: ";

                                String printedKey = Encryption.getKey();
                                String printedText = Encryption.getPlainText();
                                String explainedResult = cipher;
                                explainedResult = explainedResult.replaceAll("(.{2})(?!$)", "$1\t\t");

                                char[][] printedMatrix = Encryption.getMatrix();
                                StringBuilder printedMatrixString = new StringBuilder();


                                for (int i = 0; i < col; i++) {
                                    printedMatrixString.append(RowNo+(i+1) +"\t\t\t");

                                    for (int j = 0; j < row; j++) {
                                        printedMatrixString.append(printedMatrix[i][j]+"\t\t\t");

                                    }
                                    if (i < col - 1) {
                                        printedMatrixString.append("\n");
                                    }


                                }
                                printedText = printedText.replaceAll("(.{2})(?!$)", "$1\t");
                                tvKeyText.setText(message+"\t\t"+printedText+"\n"+key+"\t\t"+printedKey);
                                tvMatrixText.setText(printedMatrixString);
                                tvCipherText.setText(explainedResult);
                            } else {
                                tvKeyText.setText("");
                                tvMatrixText.setText("");
                                tvCipherText.setText(cipher);
                            }
                        }
                    });

                    //Hide Keyboard After clicking Button


                }


            }
        });

        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                matrixToggle.setChecked(false);

                String Validator = etPlainText.getText().toString().toUpperCase()+etkey.getText().toString().toUpperCase();
                Validator = Validator.replaceAll(" ", "");
                boolean isValid = true;

                for (int i = 0; i < Validator.length(); i++) {
                    String validLetter = Character.toString(Validator.charAt(i));
                    if (Alphabet.indexOf(validLetter) == -1) {
                        isValid = false;
                        break;

                    }

                }

                if (isValid == false) {

                    Toast.makeText(getApplicationContext(), "Invalid Letters", Toast.LENGTH_SHORT).show();
                    tvCipherText.setText("");
                    tvCipherText.setText("");
                    tvKeyText.setText("");
                    tvMatrixText.setText("");


                } else if (etPlainText.length() == 0) {

                    Toast.makeText(getApplicationContext(), "Plaintext is missing", Toast.LENGTH_SHORT).show();

                } else if (etkey.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Key is missing", Toast.LENGTH_SHORT).show();


                } else {

                    tvResult.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    tvResult.setText(decrypted);

                    String key = etkey.getText().toString();
                    String text = etPlainText.getText().toString();

                    final PlyfairCipher Decryption = new PlyfairCipher(Alphabet, col, row, fillerLetter, text, key);
                    Decryption.setFllArray(fflArray);
                    Decryption.setSpaceArray(spaceArray);
                    Decryption.setOddFlag(oddflag);
                    final String cipherText = Decryption.decrypt(Decryption.getMatrix(), Decryption.getPlainText());
                    tvCipherText.setTextColor(getResources().getColor(R.color.colorAccent));
                    tvCipherText.setText(cipherText);
                    final String cipher = cipherText;
                    //etPlainText.setText(cipher);

                    matrixToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                // The toggle is enabled
                                String RowNo = "R";
                                String message = "  Plaintext: ";
                                String key = "  Final Key: ";

                                String printedKey = Decryption.getKey();
                                String printedText = Decryption.getPlainText();
                                String explainedResult = cipher;
                                explainedResult = explainedResult.replaceAll("(.{2})(?!$)", "$1\t\t");

                                char[][] printedMatrix = Decryption.getMatrix();
                                StringBuilder printedMatrixString = new StringBuilder();


                                for (int i = 0; i < col; i++) {
                                    printedMatrixString.append(RowNo+ (i+1)+"\t\t\t");

                                    for (int j = 0; j < row; j++) {
                                        printedMatrixString.append(printedMatrix[i][j]+"\t\t\t");

                                    }
                                    if (i < col - 1) {
                                        printedMatrixString.append("\n");
                                    }


                                }
                                printedText = printedText.replaceAll("(.{2})(?!$)", "$1\t");
                                tvKeyText.setText(message+printedText+"\n"+key+printedKey);
                                tvMatrixText.setText(printedMatrixString);
                                tvCipherText.setText(explainedResult);
                            } else {
                                tvKeyText.setText("");
                                tvMatrixText.setText("");
                                tvCipherText.setText(cipher);
                            }
                        }
                    });
                    //                     Hide Keyboard After clicking Button
                }
            }
        });


        matrixToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    String RowNo = "R";

                    StringBuilder printedMatrixString = new StringBuilder();
                    int counter = 0;
                    for (int i = 0; i < col; i++) {
                        printedMatrixString.append(RowNo+(i+1)+"\t\t\t");
                        for (int j = 0; j < row; j++) {
                            printedMatrixString.append(Alphabet.charAt(counter)+"\t\t\t");
                            counter++;
                        }
                        if (i < col - 1) {
                            printedMatrixString.append("\n");
                        }
                    }
                    tvMatrixText.setText(printedMatrixString);
                } else {
                    tvKeyText.setText("");
                    tvMatrixText.setText("");
                }
            }
        });
    }
}
