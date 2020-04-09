package com.suptodas.diu.diuteacherinitialinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        databaseHelper = new DatabaseHelper(this);
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(MainActivity.this, FirstScreen.class);
                startActivity(home);
                finish();
            }
        },SPLASH_TIME_OUT);


    }
}
