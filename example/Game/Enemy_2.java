package com.example.Game;

import android.graphics.Rect;

import com.example.gameframework.AppManager;
import com.example.gameframework.R;

//Enemy_2 클래스
public class Enemy_2 extends Enemy {
    public Enemy_2(){
        super(AppManager.getInstance().getBitmap(R.drawable.enemy2));
        this.initSpriteData(getBitmapWidth()/6,getBitmapHeight(), 3, 6);
        hp = 10;
        speed = 2.5f;

        movetype = Enemy.MOVE_PATTERN_2;
    }

    public void Update(long GameTime){
        super.Update(GameTime);
        m_BoundBox.set(m_x, m_y, m_x + 62, m_y + 104);
    }
}
