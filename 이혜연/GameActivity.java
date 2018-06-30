package com.example.user.afinal.gameframework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


//메인
public class GameActivity extends AppCompatActivity {

    public View gameView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppManager.getInstance().setm_gameActivity(this);
        super.onCreate(savedInstanceState);
        //gameView=new GameView(this);
        setContentView(new GameView(this)); //GameView의 클래스에 대한 객체를 화면에 띄운다.
       // setContentView(R.layout.endstate);
    }

    public void change (){
        startActivity(new Intent(getApplicationContext(),EndActivity.class));
        //Intent myIntent =new Intent(getApplicationContext(),EndActivity.class);
       //startActivity(myIntent);

    }


}
