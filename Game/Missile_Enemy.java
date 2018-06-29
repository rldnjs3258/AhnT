package com.example.Game;

import com.example.android_data_framework.R;
import com.example.gameframework.AppManager;


//적의 미사일이다.
public class Missile_Enemy extends Missile{
    Missile_Enemy(int x, int y){
        super(AppManager.getInstance().getBitmap(R.drawable.missile_2));
        this.setPosition(x,y);
    }
    public void Update(){
        m_y += 4; //미사일이 아래로 발사되는 효과
        if (m_y >1550) state = STATE_OUT;

        m_BoundBox.set(m_x, m_y, m_x+m_bitmap.getWidth(), m_y+m_bitmap.getWidth());    }
}
