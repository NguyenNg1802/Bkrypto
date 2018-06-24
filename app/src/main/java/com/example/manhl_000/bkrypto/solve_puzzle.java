package com.example.manhl_000.bkrypto;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhl_000.bkrypto.Data.*;
import com.example.manhl_000.bkrypto.Histories.*;
import com.example.manhl_000.bkrypto.Tutorials.*;
import com.example.manhl_000.bkrypto.Puzzles.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class solve_puzzle extends AppCompatActivity {
    CustomArrayAdapter customArrayAdapter;
    ArrayList<puzzle_row> arrayList;
    ArrayList<Puzzle> puzzle_list;
    TextView plainText;
    TextView key;
    DataAccess db;
    int puzzle_number;
    Button submit;
    EditText editText;
    MenuItem checked;
    AlertDialog.Builder builder;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_solve_puzzle);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Puzzle  #"+1);

        Drawable drawable = getResources().getDrawable(R.drawable.back_button);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 130, 130, true));
        actionBar.setHomeAsUpIndicator(d);


        arrayList = new ArrayList<>();
        puzzle_list = new ArrayList<>();
        puzzle_number=1;


        db = new DataAccess(this);
        for (int i=1; i<=db.countRowPuzzle(); i++) {
            arrayList.add(new puzzle_row("Puzzle #"+i, R.drawable.done, db.getPuzzleSolve(i)));
            puzzle_list.add(new Puzzle(i, db.getPuzzleSolve(i), 0, db.getAnsID(i), db.getPuzzleNumber(i),
                    db.getPuzzleText(i), db.getPuzzleKey(i), db.getPuzzleHint(i), db.getPuzzleType(i)));

        }

        plainText = (TextView) findViewById(R.id.puzzle_problem);
        key = (TextView) findViewById(R.id.ott);

        plainText.setText(db.getPuzzleText(1));
        key.setText(db.getPuzzleKey(1));

        submit = (Button)findViewById(R.id.submit);
        editText = (EditText) findViewById(R.id.solution);

        submit.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view)
                    {
                        Log.d("Answer", db.getAnswer(puzzle_number-1));
                        Log.d("Edit", editText.getText().toString());
                        if (editText.getText().toString().toUpperCase().equals(db.getAnswer(puzzle_number).toUpperCase())) {

                            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
                            checked.setVisible(true);
                            puzzle_list.get(puzzle_number-1).setSolve(1);
                            arrayList.get(puzzle_number-1).setB(1);
                            db.updatePuzzleSolve(puzzle_number, puzzle_list.get(puzzle_number-1).getSolve());

                            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                            Date date = new Date();
                            String today  = dateFormat.format(date);
                            db.insertHistory(db.countRowHistory()+1, puzzle_number, today);

                            editText.getText().clear();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.puzzle_bar, menu);
        checked = menu.findItem(R.id.done);
        checked.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menu_id = item.getItemId();
        DisplayMetrics displayMetrics;
        AlertDialog dialog;
        int height, width;
        switch (menu_id) {
            case R.id.puzzle_list:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Puzzle list");

                customArrayAdapter = new CustomArrayAdapter(this, arrayList);

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setAdapter(customArrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(getApplicationContext(), arrayList.get(i).getText(), Toast.LENGTH_LONG).show();
                        puzzle_number = i+1;
                        getSupportActionBar().setTitle("Puzzle #"+puzzle_list.get(i).getNumber());

                        if (puzzle_list.get(i).getSolve() > 0) {
                            checked.setVisible(true);
                        }
                        else {
                            checked.setVisible(false);
                        }
                        plainText.setText(puzzle_list.get(i).getText());
                        key.setText(puzzle_list.get(i).getOtt());
                    }
                });

                displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                height = displayMetrics.heightPixels;
                width = displayMetrics.widthPixels;

                dialog = builder.create();
                dialog.show();
                dialog.getWindow().setLayout(width*80/100, height*80/100);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
                break;

            case R.id.hint:
                builder = new AlertDialog.Builder(this);

                builder.setMessage(puzzle_list.get(puzzle_number-1).getHint());


                displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                height = displayMetrics.heightPixels;
                width = displayMetrics.widthPixels;

                dialog = builder.create();
                dialog.show();

                TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , 20);
                textView.setPadding(120, 60, 90, 60);

                dialog.getWindow().setLayout(width*80/100, height*50/100);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
                break;
            case R.id.reset:
                checked.setVisible(false);
                puzzle_list.get(puzzle_number-1).setSolve(0);
                arrayList.get(puzzle_number-1).setB(0);
                db.updatePuzzleSolve(puzzle_number, puzzle_list.get(puzzle_number-1).getSolve());
                break;
            case R.id.history:
                intent = new Intent(this, History.class);
                startActivity(intent);
                break;
            case R.id.puzzle_tutorial:
                if (puzzle_list.get(puzzle_number-1).getType().equals("Challenge")) {
                    builder = new AlertDialog.Builder(this);

                    builder.setMessage(puzzle_list.get(puzzle_number-1).getHint());


                    displayMetrics = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    height = displayMetrics.heightPixels;
                    width = displayMetrics.widthPixels;

                    dialog = builder.create();
                    dialog.show();

                    TextView textView_tutorial = (TextView) dialog.findViewById(android.R.id.message);
                    textView_tutorial.setTextSize(TypedValue.COMPLEX_UNIT_SP , 20);
                    textView_tutorial.setText("This is a tough puzzle try figure it out for yourself and no tutorial given in this puzzle.");
                    textView_tutorial.setPadding(120, 60, 90, 60);

                    dialog.getWindow().setLayout(width*80/100, height*50/100);
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
                }
                else {
                    Intent intent = new Intent(this, Tutorial.class);
                    intent.putExtra("cipher_name", puzzle_list.get(puzzle_number-1).getType());
                    startActivity(intent);
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }



}
