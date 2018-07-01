package com.example.user.final2.game;

import com.example.user.final2.R;
import com.example.user.final2.framework.AppManager;

//시간 약
public class ItemAddTime extends Item {
    public ItemAddTime (int x, int y){
        super (AppManager.getInstance().getBitmap(R.drawable.item3));
        this.initSpriteData(this.getBitmapWidth()/4, this.getBitmapHeight(), 3, 4);
        m_x = x;
        m_y = y;
        m_BoundBox.set(m_x, m_y, m_x + this.getM_spriteWidth(), m_y +this.getM_spriteHeight());

    }

    @Override
    public void GetItem(){
        AppManager.getInstance().m_gameState.CurrentTime += 3000; //시간 약 먹으면 3초 추가
    }
}
