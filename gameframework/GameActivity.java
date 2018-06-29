package com.example.gameframework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//메인
public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);
        setContentView(new GameView(this)); //GameView의 클래스에 대한 객체를 화면에 띄운다.
    }
}
