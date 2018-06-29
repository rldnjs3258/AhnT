package com.example.Game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.gameframework.SpriteAnimation;

//플레이어이다.
public class Player extends SpriteAnimation {
    Rect m_BoundBox = new Rect();
    int m_Life = 50; //생명 수
    public int getLife() {return m_Life;}
    public void addLife(){m_Life++;} //생명 추가
    public void destroyPlayer(){m_Life--;} //생명 감소

    public Player (Bitmap bitmap){ //플레이어의 위치와 설정을 세팅한다.
        super(bitmap);
        //애니메이션 정보 설정
        this.initSpriteData(getBitmapWidth()/6, getBitmapHeight(), 3, 6); //프레임 나누기
        this.setPosition(280, 800); //초기 위치값 설정
    }

    //프레임워크 Update에서 지속적으로 호출할 메서드
    @Override
    public void Update(long gameTime){
        super.Update(gameTime);
        /*if (bMove){
            this.m_x += _dirX;
            this.m_y += _dirY;
        }*/
        m_BoundBox.set(m_x, m_y, m_x+getBitmapWidth()/6, m_y+getBitmapHeight());
    }
}