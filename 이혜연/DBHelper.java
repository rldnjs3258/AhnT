package com.example.user.afinal.gameframework;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE RankBoard(name CHAR(16) NOT NULL, score INTEGER);" );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    //데이터 삽입만 필요
    public void insert(String name, int score) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO RankBoard VALUES('"+name+"',"+score+");");
        db.close();
    }

    public String getResult() {
        //읽기가 가능하게 함.
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM RankBoard", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0) + ":"
                    + cursor.getString(1) + "\n";
        }
        return result;
    }



}