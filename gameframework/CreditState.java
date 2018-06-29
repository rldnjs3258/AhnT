package com.example.gameframework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.android_data_framework.R;

//Credit 상태이다.
//IState의 클래스를 상속 받는다.
public class CreditState implements IState {
    Bitmap android;
    int x, y;

    @Override
    public void Destroy( ) { }

    //초기상태. 안드로이드를 그린다.
    @Override
    public void Init( ) {
        android = AppManager.getInstance( ).getBitmap(R.drawable. android);
    }

    //안드로이드를 지정 위치에 그린다.
    @Override
    public void Render(Canvas canvas) {
        canvas.drawBitmap( android, x, y, null);
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
        return true;
    }
}