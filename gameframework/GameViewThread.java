package com.example.gameframework;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

//화면을 백그라운드에서 처리할 수 있게 해주는 Thread이다.
//어플리케이션이 죽지 않게 계속 실행되게 해준다.
public class GameViewThread extends Thread {
    private SurfaceHolder m_surfaceHolder;
    private GameView m_gameView;
    private boolean m_run = false; //스레드 실행 상태 멤버 변수

    public GameViewThread(SurfaceHolder surfaceHolder, GameView gameView){
        m_surfaceHolder = surfaceHolder;
        m_gameView = gameView;
    }

    public void setRunning(boolean run){
        m_run = run; //스레드를 실행상태로 만든다.
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() { //스레드 실행
        Canvas _canvas;
        while(m_run){
            _canvas = null;
            try{ //SurfaceHolder를 통해 Surface에 접근해서 가져온다.
                _canvas = m_surfaceHolder.lockCanvas(null);
                synchronized (m_surfaceHolder){ //스레드에서 전부 그려진 이후에 화면에 띄우게 '동기화'한다.
                    m_gameView.onDraw(_canvas); //그림을 그린다.

                }
            } finally {
                if (_canvas != null) //Surface를 화면에 표시한다.
                    m_surfaceHolder.unlockCanvasAndPost(_canvas);
            }
        }
    }
}