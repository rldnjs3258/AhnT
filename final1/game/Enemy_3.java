package com.example.user.final2.game;

import android.graphics.Rect;

import com.example.user.final2.R;
import com.example.user.final2.framework.AppManager;


//Enemy_3 클래스
public class Enemy_3 extends Enemy {
    public Enemy_3(){
        super(AppManager.getInstance().getBitmap(R.drawable.enemy3));
        this.initSpriteData(getBitmapWidth()/6,getBitmapHeight(), 10, 6);
        hp = 30;
        speed = 2.5f;
        point=30;
        movetype = Enemy.MOVE_PATTERN_3;
    }

    public void Update(long GameTime){
        super.Update(GameTime);
        m_BoundBox.set(m_x, m_y, m_x + this.getM_spriteWidth(), m_y +this.getM_spriteHeight());
    }
}
