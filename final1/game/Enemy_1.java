package com.example.user.final2.game;

import android.graphics.Rect;

import com.example.user.final2.R;
import com.example.user.final2.framework.AppManager;


//Enemy_1 클래스
public class Enemy_1 extends Enemy {
    public Enemy_1(){
        super(AppManager.getInstance().getBitmap(R.drawable.enemy1));
        this.initSpriteData(getBitmapWidth()/6,getBitmapHeight(), 10, 6);
        hp = 10;
        speed = 4.5f;
        point=10;

        movetype = Enemy.MOVE_PATTERN_1;
    }

    public void Update(long GameTime){
        super.Update(GameTime);

        m_BoundBox.set(m_x, m_y, m_x + this.getM_spriteWidth(), m_y +this.getM_spriteHeight()); //Enemy_1의 렉트 정보. (충돌에 필요)
    }
}
