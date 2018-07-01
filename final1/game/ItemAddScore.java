package com.example.user.final2.game;


import com.example.user.final2.R;
import com.example.user.final2.framework.AppManager;

public class ItemAddScore extends Item {
    public ItemAddScore (int x, int y) {
        super (AppManager.getInstance( ).getBitmap(R.drawable. item1));
        this .initSpriteData(this.getBitmapWidth()/4, this.getBitmapHeight(), 3, 4);
        m_x = x;
        m_y = y;
        m_BoundBox.set(m_x, m_y, m_x + this.getM_spriteWidth(), m_y +this.getM_spriteHeight());

    }

    @Override
    public void GetItem( ) {
        AppManager.getInstance().m_gameState.m_score += 100;
        //m_score += 100;
    }
}
