package com.example.Game;

import com.example.gameframework.AppManager;
import com.example.gameframework.R;

//플레이어의 미사일이다.
public class Missile_Player extends Missile{
    public Missile_Player(int x, int y){ //미사일 플레이어의 x, y축 위치를 받아서 setposition 한다.
        super (AppManager.getInstance().getBitmap(R.drawable.missile_1));
        this.setPosition(x, y);
    }
    public void Update(){ //플레이어의 미사일은 플레이어의 위치에서 - 되어 위로 올라간다.
        m_y -= 20;

        if (m_y < 50) state = STATE_OUT;

        m_BoundBox.left = m_x;
        m_BoundBox.top = m_y;
        m_BoundBox.right = m_x + 43;
        m_BoundBox.bottom = m_y + 43;
    }
}
