package com.example.Game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.gameframework.GraphicObject;

//Missile 클래스는 적 / 플레이어가 발사하는 미사일이다.
//미사일도 그래픽이기 때문에 GraphicObject 클래스를 상속받는다.
public class Missile extends GraphicObject{
    public static final int STATE_NORMAL = 0; //상태를 위한 상수
    public static final int STATE_OUT = 1; //상태를 위한 상수
    public int state = STATE_NORMAL; //기본 상태는 NORMAL이다.
    Rect m_BoundBox = new Rect();

    public Missile(Bitmap bitmap){ //비트맵 이미지 이용
        super(bitmap);
    }

    public void Update(){
        if (m_y<0) state = STATE_OUT; //만약 화면 밖으로 벗어나면 미사일의 state를 STATE_OUT으로 바꾼다. 상태 값을 보고 배열에서 remove 해 줄 것이다.
        //if (m_y<50) state = STATE_OUT;
    }

}
