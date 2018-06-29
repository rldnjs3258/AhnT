package com.example.gameframework;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

//이 IState를 여러 클래스에서 상속받을 수 있게 interface로 생성해준다.
//IState는 현재의 상태를 저장하는 것이다.
public interface IState {

    public void Init(); //상태가 생성되었을 때

    public void Destroy(); //상태가 소멸되었을 때

    public void Update(); //지속적으로 수행할 것들

    public void Render(Canvas canvas); //그려야 할 것들

    public boolean onKeyDown(int keyCode, KeyEvent event); //키 입력 처리

    public boolean onTouchEvent(MotionEvent event); //터치 입력 처리
}