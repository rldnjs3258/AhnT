package com.example.Game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.gameframework.AppManager;
import com.example.gameframework.SpriteAnimation;

public class Enemy extends SpriteAnimation{
    public int hp;
    public float speed;
    public static final int MOVE_PATTERN_1 = 0;
    public static final int MOVE_PATTERN_2 = 1;
    public static final int MOVE_PATTERN_3 = 2;
    protected int movetype;
    public static final int STATE_NORMAL = 0; //상태를 위한 변수
    public static final int STATE_OUT = 1; //상태를 위한 변수
    public int state = STATE_NORMAL; //기본 상태는 NORMAL
    Rect m_BoundBox = new Rect();
    long LastShoot = System.currentTimeMillis();

    public Enemy(Bitmap bitmap){
        super(bitmap);
    }

    void Move(){
        if (movetype == MOVE_PATTERN_1){
            if (m_y <= 200) m_y += speed;
            else            m_y += speed*2;
            if (m_y > 1000)  state = STATE_OUT; //좌표값이 화면보다 크면 STATE_OUT
        }
        else if (movetype == MOVE_PATTERN_2){
            if (m_y <= 200) m_y += speed;
            else{
                m_x += speed;
                m_y += speed;
            }
            if (m_y > 1000)  state = STATE_OUT; //좌표값이 화면보다 크면 STATE_OUT
        }
        else if (movetype == MOVE_PATTERN_3){
            if (m_y<=200) m_y+=speed;
            else{
                m_x-= speed;
                m_y += speed;
            }
            if (m_y > 1000)  state = STATE_OUT; //좌표값이 화면보다 크면 STATE_OUT
        }
    }
    void Attack(){
        if (System.currentTimeMillis() - LastShoot >= 2000){
            LastShoot = System.currentTimeMillis();

            AppManager.getInstance().m_gameState.m_enemmslist.add(new Missile_Enemy(m_x + 10, m_y + 104));
        }

    }
    @Override
    public void Update(long GameTime){
        super.Update(GameTime);
        Attack();
        Move();
    }

}
