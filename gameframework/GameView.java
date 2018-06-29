package com.example.gameframework;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.example.Game.GameState;

public class GameView extends SurfaceView implements SurfaceHolder.Callback { //view를 surface 뷰로 만들어준다.
    //surface 뷰는 화면을 백그라운드에서 처리할 수 있게 해준다. (화면에 대한 빠른 그래픽 처리를 할 수 있다.)
    //백그라운드에서 처리한 후 콜백으로 화면에 출력한다.
    private GameViewThread m_thread;
    private IState m_state;
    //그래픽 추가
    private GraphicObject m_Image;
    private SpriteAnimation m_spr;

    //GameView와 Resources의 인스턴스 정보를 Appmanager에 넘겨줌.
    //싱글턴 패턴을 이용해서, 모든 것을 Appmanager을 이용해서 인스턴스를 생성하는 것이다.
    public GameView(Context context){
        super(context);

        //키 입력 처리를 받기 위해서
        setFocusable(true);

        //AppManager에서 setGameView와 setResources를 인스턴스 처리 함
        AppManager.getInstance().setGameView(this);
        AppManager.getInstance().setResources(getResources());

        getHolder().addCallback(this); //콜백 함수로 만들어준다.
        //스레드를 m_thread 객체로 만들어준다.
        m_thread = new GameViewThread(getHolder(), this);
        //m_spr = new SpriteAnimation(BitmapFactory.decodeResource(getResources(), R.drawable.item1)); //이미지를 가져와서 객체를 만든다.
        //m_spr.initSpriteData(100, 167, 6, 5); //init으로 너비, 높이, 프레임을 초기화 한다.
        //m_Image = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.background2));

        changeGameState(new GameState());
    }

    // 배경을 그린다.
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color. BLACK);
        m_state.Render(canvas); //중요함. 그림을 그려라.
        m_state.Update(); //중요함. 현재 상태를 업데이트 해줘라.
        //m_spr.Draw(canvas); //잘린 이미지만큼 그리게 한다.
        //Update(); //잘린 이미지가 계속 쓰레드에서 업데이트 되게 해야한다.
        //m_Image .Draw(canvas);
    }

    //Update 메소드를 스레드에서 지속적으로 실행해야만 갱신이 수행되므로 스레드의 run에서 Update 메서드를 실행하게 함
    public void Update( ) {
        long gameTime = System.currentTimeMillis(); //시간을 받아서 시간에 따라 Update해서 프레임이 바꾸게 한다,
        m_spr.Update(gameTime);
        //m_state .Update( );
    }

    //키 입력에 대한 처리
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        m_state.onKeyDown(keyCode, event);
        return true;
    }

    //마우스 입력에 대한 처리
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        m_state.onTouchEvent(event);
        return true;
    }

    //백그라운드에서 계속 실행될 수 있게 스레드를 실행해준다.
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        m_thread.setRunning(true); //스레드를 실행 상태로 만든다.
        m_thread .start(); //스레드 실행
    }

    //게임의 상태가 파라미터 값으로 바뀐다.
    public void changeGameState(IState _state) {
        if ( m_state != null)
            m_state .Destroy( ); //게임 상태가 바뀌는 경우 destroy 한 후
        _state.Init( ); //초기화 해 준다.
        m_state = _state; //게임의 상태가 파라미터 값으로 바뀐다.
    }

    //바뀐 정보들이 surface에서 바뀐다.
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    //surface view가 끝난다. 스레드가 중지된다.
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        m_thread .setRunning(false);
        while(retry){
            try { //스레드를 중지 시킨다.
                m_thread.join();
                retry = false;
            }catch (InterruptedException e){//스레드가 종료되도록 계속 시도
            }
        }
    }
}