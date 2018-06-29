package com.example.Game;

import android.graphics.Rect;

import com.example.gameframework.AppManager;
import com.example.gameframework.R;

public class Enemy_1 extends Enemy {
    Rect m_BoundBox = new Rect();
    public Enemy_1(){
        super(AppManager.getInstance().getBitmap(R.drawable.enemy1));
        this.initSpriteData(getBitmapWidth()/6,getBitmapHeight(), 3, 6);
        hp = 10;
        speed = 2.5f;

        movetype = Enemy.MOVE_PATTERN_1;
    }

    @Override
    public void Update(long GameTime){
        super.Update(GameTime);

        m_BoundBox.set(m_x, m_y, m_x + 62, m_y + 104);
    }
}
