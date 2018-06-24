package com.example.manhl_000.bkrypto.Data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by manhl_000 on 4/24/2018.
 */

public class DataAccess extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "BKrypto.db";
    private Context context;
    private String DB_PATH = "/data/data/com.example.manhl_000.bkrypto/databases/";
    private static String DB_NAME = "BKrypto.db";

    public DataAccess(Context context) {
        super(context,DATABASE_NAME,null,1);

        this.context = context;
        boolean dbexist = checkdatabase();

        if(dbexist)
        {
        }
        else
        {
            System.out.println("Database doesn't exist1!");

            createDatabase();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        /*db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.d("-------","Dropped");*/
    }

    public void createDatabase() {

        this.getReadableDatabase();

        try
        {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean checkdatabase() {

        boolean checkdb = false;

        try
        {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();

        }
        catch (SQLiteException e)
        {
            System.out.println("Database doesn't exist!");
        }

        return checkdb;
    }

    private void copyDatabase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        System.out.println("Copied!");
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void dropTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS cipher_table ");
    }


    public String getIntro(String cipher_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery( "select INTRODUCTION from Tutorial where CIPHER_NAME = '" + cipher_name + "'",null);
        Log.d("----",cipher_name);
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append(res.getString(0) + "\n");
        }
        return buffer.toString();
    }

    public String getHowToSolve(String cipher_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select HOWTOSOLVE from Tutorial where CIPHER_NAME = '" + cipher_name + "'",null);
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append(res.getString(0) + "\n");
        }

        return buffer.toString();
    }

    public byte[] getImage(String cipher_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int col;
        byte[] result = null;
        Cursor res = db.rawQuery( "select Image from " + "Tutorial" + " where CIPHER_NAME = '" + cipher_name + "'",null);
        //Log.d("----", String.valueOf(i));
        //StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            //buffer.append(res.getString(0) + "\n");
            col = res.getColumnIndexOrThrow("Image");
            result = res.getBlob(col);
        }
        return result;
    }

    public String getExample(String cipher_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select EXAMPLE from Tutorial where CIPHER_NAME = '" + cipher_name + "'",null);
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append(res.getString(0) + "\n");
        }
        return buffer.toString();
    }

    public String getMusicState() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select state from music",null);
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append(res.getString(0) + "\n");
        }
        return buffer.toString();
    }

    public String setMusicState(boolean state) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("UPDATE music SET state = " + state,null);
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append(res.getString(0) + "\n");
        }
        return buffer.toString();
    }

    public String getPuzzleText(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int col;
        String result="";
        Cursor res = db.rawQuery( "select Text from " + "Puzzle" + " where PID = '" + id + "'",null);
        //Log.d("----", String.valueOf(id));
        //StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            //buffer.append(res.getString(2) + "\n");
            col = res.getColumnIndexOrThrow("Text");
            result = res.getString(col);
        }
        return result;
    }

    public String getPuzzleKey(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int col;
        String result="";
        Cursor res = db.rawQuery( "select OTT from " + "Puzzle" + " where PID = '" + id + "'",null);
        //Log.d("----", String.valueOf(i));
        //StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            //buffer.append(res.getString(0) + "\n");
            col = res.getColumnIndexOrThrow("OTT");
            result = res.getString(col);
        }
        return result;
    }

    public String getPuzzleHint(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int col;
        String result="";
        Cursor res = db.rawQuery( "select Hint from " + "Puzzle" + " where PID = '" + id + "'",null);
        //Log.d("----", String.valueOf(id));
        //StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            //buffer.append(res.getString(0) + "\n");
            col = res.getColumnIndexOrThrow("Hint");
            result = res.getString(col);
        }
        return result;
    }

    public int getPuzzleNumber(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int col, number=0;
        Cursor res = db.rawQuery( "select Number from " + "Puzzle" + " where PID = '" + id + "'",null);
        //Log.d("----", String.valueOf(id));
        //StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
           //buffer.append(res.getString(0) + "\n");
            col = res.getColumnIndexOrThrow("Number");
            number = res.getInt(col);
        }
        return number;
    }

    public String getPuzzleType(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int col;
        String result="";
        Cursor res = db.rawQuery( "select Type from " + "Puzzle" + " where PID = '" + id + "'",null);
        //Log.d("----", String.valueOf(id));
        //StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            //buffer.append(res.getString(0) + "\n");
            col = res.getColumnIndexOrThrow("Type");
            result = res.getString(col);
        }
        return result;
    }

    public int getPuzzleSolve(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int col, number=0;
        Cursor res = db.rawQuery( "select Solve from " + "Puzzle" + " where PID = '" + id + "'", null);
        //Log.d("----", String.valueOf(id));
        //StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            //Log.d("----", res.getColumnIndex("Solve"));
            //buffer.append(res.getString(0));
            col = res.getColumnIndexOrThrow("Solve");
            number = res.getInt(col);
        }
        return number;
    }

    public void updatePuzzleSolve(int id, int value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = new String[] {String.valueOf(id)};
        ContentValues contentValues = new ContentValues();
        contentValues.put("Solve", value);
        db.update("Puzzle", contentValues, "PID = ?", whereArgs);
    }

    public String getAnswer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int col;
        String result="";
        Cursor res = db.rawQuery( "select Text from " + "Answer" + " where AnsID = '" + id + "'",null);
        //Log.d("----", String.valueOf(i));
        //StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            //buffer.append(res.getString(0) + "\n");
            col = res.getColumnIndexOrThrow("Text");
            result = res.getString(col);
        }
        return result;
    }

    public int getAnsID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int col, number=0;
        Cursor res = db.rawQuery( "select AnsID from " + "Answer" + " where AnsID = '" + id + "'", null);
        //Log.d("----", String.valueOf(id));
        //StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            //Log.d("----", res.getColumnIndex("Solve"));
            //buffer.append(res.getString(0));
            col = res.getColumnIndexOrThrow("AnsID");
            number = res.getInt(col);
        }
        return number;
    }



    public int countRowPuzzle() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, "Puzzle");
        db.close();
        return count;
    }

    private  String setDate(String s){
        String tmp = s.substring(6,8) + '/' +
                s.substring(4,6) + '/' +
                s.substring(0,4);
        return tmp;
    }

    private long checkDate(long x){
        long year = x / 10000;
        long month = (x - 20180000) / 100;
        long day = x % 100;
        if (x==0)
            if ((month == 2) || (month == 4) || (month == 6) || (month == 9) ||(month == 11))
                x = year*10000 + (month-1)*100 + 31;
            else if (month == 1)
                x = (year-1)*10000 + 12*100 + 31;
            else if (month == 3)
                x = year*10000 + (month-1)*100 + 28;
            else
                x = year*10000 + (month-1)*100 + 30;

        return x;
    }

    public String getDayHistory() {
        //get today
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String today  = dateFormat.format(date);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from History where solvedate = '" + today + "' order by HID DESC",null);
        StringBuffer buffer = new StringBuffer();
 //       Log.d("----",today);
        while (res.moveToNext()) {
            String day = setDate(res.getString(2));
            buffer.append("Puzzle:\t" + res.getString(1) + ",\t" + "Date:\t" + day + "\n");
        }
        return buffer.toString();
    }

    public String getWeekHistory() {
        //get today
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String today  = dateFormat.format(date);
        long currentday = Long.valueOf(today).longValue();

        StringBuffer buffer = new StringBuffer();
        for (long i=0; i<=6; i++) {
            currentday -= 1;
            currentday = checkDate(currentday);
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from History where solvedate = '" + currentday + "' order by HID DESC", null);
            while (res.moveToNext()) {
                String day = setDate(res.getString(2));
                buffer.append("Puzzle:\t" + res.getString(1) + ",\t" + "Date:\t" + day + "\n");
            }
        }
        return buffer.toString();
    }

    public boolean insertHistory(int hid, int pid, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("HID", hid);
        contentValues.put("PID", pid);
        contentValues.put("solvedate", date);
        long result = db.insert("History", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public int countRowHistory() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, "History");
        db.close();
        return count;
    }

}
