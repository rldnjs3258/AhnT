package com.example.user.final2.framework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.example.user.final2.R;
import com.example.user.final2.framework.AppManager;
import com.example.user.final2.framework.IState;
import com.example.user.final2.game.GameState;

//Intro의 상태이다.
//IState 클래스를 상속받는다.
public class IntroState implements IState {
    public Bitmap background;
    int x, y;


    private int w;
    private int h;

    public void setW(int w) { this.w = w; }

    public void setH(int h) { this.h = h;  }

    @Override
    public void Destroy( ) { }

    //초기상태. 아이콘을 그린다.
    @Override
    public void Init( ) {
        background= Bitmap.createScaledBitmap(AppManager.getInstance().getBitmap(R.drawable.intro),w,h,true);
    }

    //아이콘을 지정 위치에 그린다.
    @Override
    public void Render(Canvas canvas) {
        background= Bitmap.createScaledBitmap(AppManager.getInstance().getBitmap(R.drawable.intro),w,h,true);
        canvas.drawBitmap(background, x, y, null);
    }

    @Override
    public void Update( ) { }

    //키 입력이 있을 경우 true 리턴
    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event) {
        return true;
    }

    //마우스 입력이 있을 경우 true 리턴
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AppManager.getInstance( ).getGameView( ).changeGameState(new GameState());
        return true;
    }
}