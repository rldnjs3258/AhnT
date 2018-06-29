package com.example.Game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.gameframework.AppManager;
import com.example.gameframework.SpriteAnimation;

//적을 생성한다.
public class Enemy extends SpriteAnimation{
    public int hp; //체력
    public float speed; //스피드

    public static final int MOVE_PATTERN_1 = 0; //패턴1
    public static final int MOVE_PATTERN_2 = 1; //패턴2
    public static final int MOVE_PATTERN_3 = 2; //패턴3

    protected int movetype;
    public static final int STATE_NORMAL = 0; //상태를 위한 변수
    public static final int STATE_OUT = 1; //상태를 위한 변수
    public int state = STATE_NORMAL; //기본 상태는 NORMAL
    Rect m_BoundBox = new Rect();
    long LastShoot = System.currentTimeMillis();

    public Enemy(Bitmap bitmap){
        super(bitmap);
    }

    //움직이는 로직
    void Move(){
        if (movetype == MOVE_PATTERN_1){ //패턴 1
            if (m_y <= 200) m_y += speed; //중간지점까지 기본 속도
            else            m_y += speed*2; //중간지점 이후 빠른 속도
            if (m_y > 1000)  state = STATE_OUT; //좌표값이 화면보다 크면 STATE_OUT
        }
        else if (movetype == MOVE_PATTERN_2){ //패턴 2
            if (m_y <= 200) m_y += speed; //중간지점까지 기본 속도
            else{ //중간지점 이후 대각선 이동
                m_x += speed;
                m_y += speed;
            }
            if (m_y > 1000)  state = STATE_OUT; //좌표값이 화면보다 크면 STATE_OUT
        }
        else if (movetype == MOVE_PATTERN_3){ //패턴 3
            if (m_y<=200) m_y+=speed; //중간지점까지 기본 속도
            else{ //중간지점 이후 대각선 이동
                m_x-= speed;
                m_y += speed;
            }
            if (m_y > 1000)  state = STATE_OUT; //좌표값이 화면보다 크면 STATE_OUT
        }
    }

    //공격하하는 로직
   void Attack(){
        if (System.currentTimeMillis() - LastShoot >= 4000){
            LastShoot = System.currentTimeMillis();
            //미사일 발사 로직
            AppManager.getInstance().m_gameState.m_enemmslist.add(new Missile_Enemy(m_x + 10, m_y + 104));
        }

    }

    @Override
    public void Update(long GameTime){
        super.Update(GameTime);
        Attack(); //적을 공격하게 함
        Move(); //적을 움직이게 함
    }

}
