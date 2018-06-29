package com.example.gameframework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

//Intro의 상태이다.
//IState 클래스를 상속받는다.
public class IntroState implements IState {
    Bitmap icon;
    int x, y;

    @Override
    public void Destroy( ) { }

    //초기상태. 아이콘을 그린다.
    @Override
    public void Init( ) {
        icon = AppManager.getInstance( ).getBitmap(R.drawable. icon);
    }

    //아이콘을 지정 위치에 그린다.
    @Override
    public void Render(Canvas canvas) {
        canvas.drawBitmap( icon, x, y, null);
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
        AppManager.getInstance( ).getGameView( ).changeGameState( new CreditState( ));
        return true;
    }
}