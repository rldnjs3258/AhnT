package com.example.gameframework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.Game.GameState;

//이 프레임워크의 모든 것(게임 뷰, 리소스)을 관리하는 AppManager이다.
//싱글턴 패턴을 통해 인스턴스를 만드는 곳이다.
public class AppManager {
    private GameView m_gameView; //메인 게임 뷰
    private Resources m_resources; //메인 게임 뷰의 리소스
    public GameState m_gameState; //게임 상황

    void setGameView (GameView _gameView){
        m_gameView = _gameView;
    }
    void setResources(Resources _resources){
        m_resources = _resources;
    }

    public GameView getGameView(){
        return m_gameView;
    }
    public Resources getResource(){
        return m_resources;
    }

    public Bitmap getBitmap(int r){
        return BitmapFactory.decodeResource(m_resources, r);
    }

    private static AppManager s_instance;

    //싱글턴 패턴을 이용해서 인스턴스가 만들어지게 한다.
    public static AppManager getInstance(){
        if (s_instance == null) s_instance = new AppManager();
        return s_instance;
    }
}