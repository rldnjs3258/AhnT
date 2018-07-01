package com.example.user.final2.framework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//메인
public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AppManager.getInstance().setGameActivity(this);
        setContentView(new GameView(this)); //GameView의 클래스에 대한 객체를 화면에 띄운다.
    }

    public void change (){
        startActivity(new Intent(getApplicationContext(),EndActivity.class));
    }
}
