package com.example.Game;

import android.graphics.Rect;

import com.example.gameframework.AppManager;
import com.example.R;

//Enemy_3 클래스
public class Enemy_3 extends Enemy {
    public Enemy_3(){
        super(AppManager.getInstance().getBitmap(R.drawable.enemy3));
        this.initSpriteData(getBitmapWidth()/6,getBitmapHeight(), 3, 6);
        hp = 10;
        speed = 2.5f;

        movetype = Enemy.MOVE_PATTERN_3;
    }

    public void Update(long GameTime){
        super.Update(GameTime);
        m_BoundBox.set(m_x, m_y, m_x + this.getM_spriteWidth(), m_y +this.getM_spriteHeight());
    }
}
