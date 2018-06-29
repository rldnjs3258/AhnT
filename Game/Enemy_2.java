package com.example.Game;

import android.graphics.Rect;

import com.example.android_data_framework.R;
import com.example.gameframework.AppManager;


//Enemy_2 클래스
public class Enemy_2 extends Enemy {
    public Enemy_2(){
        super(AppManager.getInstance().getBitmap(R.drawable.enemy2));
        this.initSpriteData(getBitmapWidth()/6,getBitmapHeight(), 10, 6);
        hp = 20;
        speed = 3.5f;
        point=20;

        movetype = Enemy.MOVE_PATTERN_2;
    }

    public void Update(long GameTime){
        super.Update(GameTime);
        m_BoundBox.set(m_x, m_y, m_x + this.getM_spriteWidth(), m_y +this.getM_spriteHeight());
    }
}
